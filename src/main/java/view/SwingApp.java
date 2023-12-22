package view;

import Repository.Repository;
import model.Employee;
import Repository.EmployeeRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class SwingApp extends JFrame {


    private final JTable employeeTable;
    private final Repository<Employee> employeeRepository;

    public SwingApp(){
        //configurar la ventana
        setTitle("Gestión de empleados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,230);

        //Crear una tabla para mostrar los empleados
        employeeTable=new JTable();
        JScrollPane scrollPane=new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        //Crear botones para acciones
        JButton agregarButton=new JButton("Agregar");
        JButton actualizarButton=new JButton("Actualizar");
        JButton eliminarButton=new JButton("Eliminar");

        //Configurar el panel de botones
        JPanel buttonPanel=new JPanel();
        buttonPanel.add(agregarButton);
        buttonPanel.add(actualizarButton);
        buttonPanel.add(eliminarButton);
        add(buttonPanel,BorderLayout.SOUTH);

        //Establecer estilos para los botones
        agregarButton.setBackground(new Color(46,204,113));
        agregarButton.setForeground(Color.WHITE);
        agregarButton.setFocusPainted(false);

        actualizarButton.setBackground(new Color(52,152,219));
        actualizarButton.setForeground(Color.WHITE);
        actualizarButton.setFocusPainted(false);

        eliminarButton.setBackground(new Color(231,76,60));
        eliminarButton.setForeground(Color.WHITE);
        eliminarButton.setFocusPainted(false);


        //Crear el objeto repository para acceder a la base de datos
        employeeRepository=new EmployeeRepository();


        //Agregar ActionListener para los botones
        agregarButton.addActionListener(e->{
            try{
                agregarEmpleado();

            }catch(SQLException ex){
                throw new RuntimeException(ex);
            }
        });

        actualizarButton.addActionListener(e->actualizarEmpleado());
        eliminarButton.addActionListener(e->eliminarEmpleado());

    }


    private void refreshEmployeeTable(){
        //Obtener la lista actualizada de empleados desde la bd
        try{
            List<Employee> employees=employeeRepository.findAll();

            //Crear un modelo de tabla y establecer los datos de los empleados
            DefaultTableModel model=new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Nombre");
            model.addColumn("Apellido Paterno");
            model.addColumn("Apellido Materno");
            model.addColumn("Email");
            model.addColumn("Salario");

            for(Employee employee:employees){
                Object[] rowData={
                        employee.getEmpId(),
                        employee.getEmpFirstName(),
                        employee.getEmpPSurName(),
                        employee.getEmpMSurName(),
                        employee.getEmpEmail(),
                        employee.getEmpSalary()
                };
                model.addRow(rowData);


            }
            //establecer el modelo de tabla actualizado
            employeeTable.setModel(model);
            }catch(SQLException e){
            JOptionPane.showMessageDialog(this,"Error al obtener los datos de la bd");


        }


    }

    private void agregarEmpleado() throws SQLException {
//Crear un formulario para agregar empleado
        JTextField nombreField=new JTextField();
        JTextField paternoField=new JTextField();
        JTextField maternoField=new JTextField();
        JTextField emailField=new JTextField();
        JTextField salarioField=new JTextField();

        Object[] fields={
                "Nombre:",nombreField,
                "Apellido Paterno",paternoField,
                "Apellido Materno",maternoField,
                "Email",emailField,
                "Salario",salarioField
        };

        int result=JOptionPane.showConfirmDialog(this,fields,"Agregar EMpleado",JOptionPane.OK_OPTION);

        if(result==JOptionPane.OK_OPTION){
            //Crear un nuevo objeto Employee con los datos ingresados
            Employee employee=new Employee();
            employee.setEmpFirstName(nombreField.getText());
            employee.setEmpPSurName(paternoField.getText());
            employee.setEmpMSurName(maternoField.getText());
            employee.setEmpEmail(emailField.getText());
            employee.setEmpSalary(Float.parseFloat(salarioField.getText()));

            //Gueardar el nuevo empleado en la bd
            employeeRepository.save(employee);

            //Actualizar la tabla con los empleados actualizados
            refreshEmployeeTable();

            JOptionPane.showMessageDialog(this,"Empleado agregado correctamente","éxito",JOptionPane.OK_OPTION);
        }
    }

    private void actualizarEmpleado() {
        //Obtener el Id del empleado a actualizar
        String empleadoIdStr = JOptionPane.showInputDialog(this, "Ingrese el id del empleado", "Actualizar", JOptionPane.OK_OPTION);
        if (empleadoIdStr != null) {
            try {
                int empleadoId = Integer.parseInt(empleadoIdStr);

                //Obtener el empleado desde la base de datos

                Employee empleado = employeeRepository.getById(empleadoId);

                if (empleado != null) {
                    //Crear un formulario con los datos del empleado
                    JTextField nombreField = new JTextField(empleado.getEmpFirstName());
                    JTextField apellidoPaternoField = new JTextField(empleado.getEmpPSurName());
                    JTextField apellidoMaternoField = new JTextField(empleado.getEmpMSurName());
                    JTextField emailField = new JTextField(empleado.getEmpEmail());
                    JTextField salarioField = new JTextField(String.valueOf(empleado.getEmpSalary()));

                    Object[] fields = {
                            "Nombre", nombreField,
                            "Apellido paterno", apellidoPaternoField,
                            "Apellido materno", apellidoMaternoField,
                            "Email", emailField,
                            "Salario:", salarioField
                    };

                    int confirmResult = JOptionPane.showConfirmDialog(this, fields, "Actualizar Empleado", JOptionPane.OK_OPTION);
                    if (confirmResult == JOptionPane.OK_OPTION) {
                        //Actualizar los datos del empleado con los valores ingresados en el formulario
                        empleado.setEmpFirstName(nombreField.getText());
                        empleado.setEmpPSurName(apellidoPaternoField.getText());
                        empleado.setEmpMSurName(apellidoMaternoField.getText());
                        empleado.setEmpEmail(emailField.getText());
                        empleado.setEmpSalary(Float.parseFloat(salarioField.getText()));

                        //Guardar los cambios en la base de datos
                        employeeRepository.save(empleado);

                        //Actualizar la tabla de empleados en la interfaz
                        refreshEmployeeTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró ningún empleado con el ID estipulado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al obtener los datos de la bd", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void eliminarEmpleado(){
        //Obtener el ID del empleado a eliminar
        String empleadoIdStr=JOptionPane.showInputDialog(this,"Ingrese el ID del empleado a eliminar","Eliminar empleado",JOptionPane.OK_OPTION);
        if(empleadoIdStr!=null){
            try{
                int empleadoId=Integer.parseInt(empleadoIdStr);

                //Confirmar la eliminación del empleado
                int confirmResult=JOptionPane.showConfirmDialog(this,"¿Está seguro de eliminar el usuario seleccionado?","confirmar eliminación",JOptionPane.OK_CANCEL_OPTION);
                if(confirmResult==JOptionPane.YES_OPTION){
                    //Eliminar el empleado de la base de datos
                    employeeRepository.delete(empleadoId);

                    //Actualizar la tabla de empleaods en la interfaz
                    refreshEmployeeTable();
                }



            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this,"Ingrese un valor numérico válido para el ID","Error",JOptionPane.ERROR_MESSAGE);

            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }

    }
}

package view;

import Repository.Repository;
import model.Employee;
import Repository.EmployeeRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

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

        actualizarButton.addActionListener(e->actulizarEmpleado());
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

            for(Employee employee: employees){
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

    private void agregarEmpleado(){
        
    }
}

package view;

import Repository.Repository;
import model.Employee;

import javax.swing.*;
import java.awt.*;

public class SwingApp extends JFrame {


    private final JTable employeeTable;

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

    }
}

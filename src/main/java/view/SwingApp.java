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


    }
}

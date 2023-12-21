package main;

import Repository.*;
import model.Employee;
import util.DatabaseConnection;

import java.sql.*;

public class Main {

    public static Statement stmt=null;
    public static PreparedStatement ps=null;
    public static ResultSet rs=null;


    public static void main(String[] args) throws SQLException {
        try (Connection conn= DatabaseConnection.getInstance()){
            Repository<Employee> repository=new EmployeeRepository();

            //esta para la lista
            //repository.findAll().forEach(System.out::println);
            //esta para el getById
           //System.out.println(repository.getById(4));
             /*
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM employees");){

            while (rs.next()) {
                System.out.println(rs.getString("emp_firstName"));


            }
*/

            //ver si el cambio se está ejecutando, primero con un findall()
            repository.findAll().forEach(System.out::println);

/*
            System.out.println("----------- Actualizando un empleado -------------");

            Employee employee=new Employee();
            employee.setEmpId(7);
            employee.setEmpFirstName("Ricardo");
            employee.setEmpPSurName("García");
            employee.setEmpMSurName("Cristancho");
            employee.setEmpEmail("rigacri@hotmail.com");
            employee.setEmpSalary(6_800_000F);
            //método save implementado
            repository.save(employee);

            System.out.println("----- empleado actualizado-----");
            repository.findAll().forEach(System.out::println);
            */


            repository.delete(7);
            System.out.println(".....................Empleado eliminado.....................\n");

            repository.findAll().forEach(System.out::println);


        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("X-| Algo salió mal, revise los parámetros de conexión");
        }

    }
}

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


            System.out.println("----------- Insertando un empleado -------------");
            Employee employee=new Employee();
            employee.setEmpFirstName("Camilo");
            employee.setEmpPSurName("Ardila");
            employee.setEmpMSurName("Toloza");
            employee.setEmpEmail("miloca@hotmail.com");
            employee.setEmpSalary(3_330_000F);
            //método save implementado
            repository.save(employee);

            System.out.println("-----Nuevo empleado insertado-----");

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("X-| Algo salió mal, revise los parámetros de conexión");
        }

    }
}

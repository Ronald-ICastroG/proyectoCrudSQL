package Repository;

import model.Employee;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }
    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees=new ArrayList<>();
        try(Statement stmt=getConnection().createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM employees")){


        while(rs.next()){
            createEmployee(rs, employees);
        }
        }




    @Override
    public Employee getById(Integer id) {
        return null;
    }

    @Override
    public void save(Employee employee) {

    }

    @Override
    public void delete(Integer id) {

    }
        private Employee createEmployee(ResultSet rs, List<Employee> employees) throws SQLException {
            Employee e=new Employee();
            e.setEmpId(rs.getInt("emp_id"));
            e.setEmpFirstName(rs.getString("emp_firstName"));
            e.setEmpPSurName(rs.getString("emp_PSurName"));
            e.setEmpMSurName(rs.getString("emp_MSurName"));
            e.setEmpEmail(rs.getString("emp_email"));
            e.setEmpSalary(rs.getFloat("emp_salary"));
            employees.add(e);
        }


}

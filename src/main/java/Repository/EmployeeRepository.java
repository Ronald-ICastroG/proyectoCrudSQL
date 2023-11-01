package Repository;

import model.Employee;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {
   public static final String SQL_SAVE="INSERT INTO employees (emp_firstName,emp_PSurName,emp_MSurName,emp_email,emp_salary) vALUES(?,?,?,?,?)";
   public static final String SQL_UPDATE="UPDATE employees set emp_firstName=?,emp_PSurName=?,emp_MSurName=?,emp_email=?,emp_salary=? WHERE emp_id=?";

   public static final String SQL_DELETE="DELETE FROM employees WHERE emp_id=?";

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }
    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employees")) {


            while (rs.next()) {
                Employee e=createEmployee(rs);
                employees.add(e);
            }
        }
        return employees;
    }



    @Override
    public Employee getById(Integer id) throws SQLException {
        Employee employee=null;
        try(PreparedStatement ps=getConnection().prepareStatement("SELECT * FROM employees WHERE emp_id=?")){
            ps.setInt(1,id);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()) {
                    employee=createEmployee(rs);
                }
            }
        }
        return employee;
    }

    @Override
    public void save(Employee employee) throws SQLException {
        String sql;
        if(employee.getEmpId()!=null && employee.getEmpId()>0){
          sql=SQL_UPDATE;
            System.out.println("/----------Actualizando un registro-------------/\n");
        }else{
            sql=SQL_SAVE;
            System.out.println("/----------Insertando un registro-------------/\n");
        }
        try(PreparedStatement ps =getConnection().prepareStatement(sql)){
            ps.setString(1, employee.getEmpFirstName());
            ps.setString(2, employee.getEmpPSurName());
            ps.setString(3, employee.getEmpMSurName());
            ps.setString(4,employee.getEmpEmail());
            ps.setFloat(5,employee.getEmpSalary());
            if(employee.getEmpId()!=null&&employee.getEmpId()>0){
                ps.setInt(6,employee.getEmpId());
            }

            int rowsAffected=ps.executeUpdate();
            System.out.println("Registros insertados: "+rowsAffected);
        }


    }
    @Override
    public void delete(Integer id) throws SQLException {

        String sql=SQL_DELETE;

        try(PreparedStatement ps=getConnection().prepareStatement(sql)){
            ps.setInt(1, id);
            int rowsAffected=ps.executeUpdate();
            System.out.println(rowsAffected+" registros eliminados...");
        }

    }

    private Employee createEmployee(ResultSet rs) throws SQLException {
            Employee e=new Employee();
            e.setEmpId(rs.getInt("emp_id"));
            e.setEmpFirstName(rs.getString("emp_firstName"));
            e.setEmpPSurName(rs.getString("emp_PSurName"));
            e.setEmpMSurName(rs.getString("emp_MSurName"));
            e.setEmpEmail(rs.getString("emp_email"));
            e.setEmpSalary(rs.getFloat("emp_salary"));
            return e;
        }


}



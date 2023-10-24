package main;

import java.sql.*;

public class Main {
    public static final String JDBC_URL="jdbc:mysql://localhost:3306/project";
    public static final String JDBC_USER="root";
    public static final String JDBC_PSWD="admin";
    public static Connection conn=null;
    public static Statement stmt=null;
    public static PreparedStatement ps=null;
    public static ResultSet rs=null;


    public static void main(String[] args) throws SQLException {
        try {
            //1. Conexión a nuestra base de datos, pasando 3 argumentos
            conn= DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PSWD);
            if (conn!=null){
                System.out.println("Conexión exitosa con maven :D");
            }
            //2.crear una delacarción objeto
            stmt=conn.createStatement();

            //3. Ejecutar consulta sql
            rs=stmt.executeQuery("SELECT * FROM employees");
            //4. devolver registros
            while (rs.next()) {
                System.out.println(rs.getString("emp_firstName"));


            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("X-| Algo salió mal, revise los parámetros de conexión");
        }

    }
}

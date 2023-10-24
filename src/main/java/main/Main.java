package main;

import util.DatabaseConnection;

import java.sql.*;

public class Main {

    public static Statement stmt=null;
    public static PreparedStatement ps=null;
    public static ResultSet rs=null;


    public static void main(String[] args) throws SQLException {
        try (Connection conn= DatabaseConnection.getInstance();
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM employees");){

            while (rs.next()) {
                System.out.println(rs.getString("emp_firstName"));


            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("X-| Algo salió mal, revise los parámetros de conexión");
        }

    }
}

package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static final String JDBC_URL="jdbc:mysql://localhost:3306/project";
    public static final String JDBC_USER="root";
    public static final String JDBC_PSWD="admin";
    public static Connection conn=null;


    public static Connection getInstance() throws SQLException {
        if(conn==null){
            conn= DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PSWD);

        }
        return conn;
    }
}

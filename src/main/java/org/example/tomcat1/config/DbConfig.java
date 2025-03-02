package org.example.tomcat1.config;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
    private static String url="jdbc:mysql://localhost:3306/ProjectManagement";
    private static String user="root";
    private static String password="root";

    private DbConfig() {}
    public static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url,user,password);
    }

}

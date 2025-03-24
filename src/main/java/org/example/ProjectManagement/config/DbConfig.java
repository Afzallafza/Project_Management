package org.example.ProjectManagement.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
    public static final AppProperties instance=AppProperties.getInstance();
    private DbConfig() {
    }

    public static Connection connect() throws SQLException, ClassNotFoundException, IOException {
        String MYSQL_DB_URL = instance.getProperty("mysql.db.url");
        String MYSQL_USER = instance.getProperty("mysql.db.user");
        String MYSQL_PASSWORD = instance.getProperty("mysql.db.password");
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(MYSQL_DB_URL, MYSQL_USER, MYSQL_PASSWORD);
    }
}

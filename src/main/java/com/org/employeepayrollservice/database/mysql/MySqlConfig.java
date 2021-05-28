package com.org.employeepayrollservice.database.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConfig {
    private boolean isDriverLoaded = false;
    private static final Logger LOGGER = LoggerFactory.getLogger("com.example");
    private Connection connection = null;
    private static MySqlConfig mySqlInstance = null;

    private void loadDriver(){
        LOGGER.trace("Loading driver");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            isDriverLoaded = true;
        }catch (ClassNotFoundException e){
            LOGGER.error("Cannot find the driver in classpath.");
            throw new IllegalStateException("Cannot find the driver in classpath",e);
        }
    }
    public Connection getSqlConnection(String username,String password) throws SQLException {
        LOGGER.trace("Establishing MySql connection");
        String jdbcUrl = "jdbc:mysql://localhost:3306/employee_payroll_service";
        try {
            return connection = DriverManager.getConnection(jdbcUrl, username, password);
        }catch (SQLException e){
            LOGGER.error("mysql connection failed.");
            throw new SQLException("mysql connection failed");
        }
    }
    public static MySqlConfig getMySqlInstance(){
        if(mySqlInstance == null)
            mySqlInstance = new MySqlConfig();
        return mySqlInstance;
    }

}

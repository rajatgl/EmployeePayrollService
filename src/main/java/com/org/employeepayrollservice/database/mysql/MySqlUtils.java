package com.org.employeepayrollservice.database.mysql;

import java.sql.*;
import java.util.List;

public abstract class MySqlUtils<T> {
    /**
     *
     * @param query to be executed [belongs to DDL]
     * @return true/false depending on the status of the operation
     * @throws SQLException
     */
    protected Boolean execute(String query) throws SQLException {
        Boolean isSuccessful = false;
        Connection connection = MySqlConfig.getMySqlInstance().getSqlConnection("root","password120596");
        try{
            Statement stmt = connection.createStatement();
            try{
                isSuccessful = stmt.execute(query);
            }finally {
                stmt.close();
            }
        }finally {
            connection.close();
        }
        return isSuccessful;
    }

    /**
     *
     * @param query to be executed [belongs to DML]
     * @return number of rows updated
     * @throws SQLException
     */
    protected Integer executeUpdate(String query) throws SQLException {
        Integer isSuccessful = 0;
        Connection connection = MySqlConfig.getMySqlInstance().getSqlConnection("root","password120596");
        try{
            Statement stmt = connection.createStatement();
            try{
                isSuccessful = stmt.executeUpdate(query);
            }finally {
                stmt.close();
            }
        }finally {
            connection.close();
        }
        return isSuccessful;
    }

    /**
     *
     * @param query to be executed [belongs to DQL]
     * @return list of items- each item representing each row in the MySQL table
     * @throws SQLException
     */
    protected List<T> executeQuery(String query) throws SQLException {
        List<T> list;
        Connection connection = MySqlConfig.getMySqlInstance().getSqlConnection("root","password120596");
        try{
            Statement stmt = connection.createStatement();
            try{
                ResultSet resultSet = stmt.executeQuery(query);
                try{
                    list = collectData(resultSet);
                }finally {
                    resultSet.close();
                }
            }finally {
                stmt.close();
            }
        }finally {
            connection.close();
        }
        return list;
    }

    protected abstract List<T> collectData(ResultSet resultSet) throws SQLException;

}

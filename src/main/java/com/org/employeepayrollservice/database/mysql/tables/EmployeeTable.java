package com.org.employeepayrollservice.database.mysql.tables;

import com.org.employeepayrollservice.database.interfaces.ICrud;
import com.org.employeepayrollservice.database.mysql.MySqlUtils;
import com.org.employeepayrollservice.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTable extends MySqlUtils<Employee> implements ICrud<Employee> {
    public EmployeeTable() {
        try{
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @throws SQLException
     */
    private void createTable() throws SQLException {
        String createQuery = " create table if not exists employee_payroll\n" +
                "(\n" +
                "    `id`INT unsigned NOT NULL AUTO_INCREMENT,\n" +
                "    `name` VARCHAR(150) NOT NULL,\n" +
                "    `salary` double NOT NULL ,\n" +
                "    `start` DATE NOT NULL,\n" +
                "     PRIMARY KEY (id)\n" +
                ")";
        execute(createQuery);
    }


    protected List<Employee> collectData(ResultSet resultSet) throws SQLException {
        List<Employee> employees = new ArrayList<Employee>();
        while(resultSet.next()){
            Employee employee = new Employee(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("salary"),
                    resultSet.getDate("start"));
            employees.add(employee);
        }
        return  employees;
    }

    public void create(Employee obj) {
        String insertQuery = "insert into employee_payroll(`name`,`salary`,`start`) value('"+obj.getName()+"','"+obj.getSalary()+"','"+obj.getStart()+"')";
        try {
            executeUpdate(insertQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Employee> retrieve(int key){
        List<Employee> employees = new ArrayList<Employee>();
        String selectQuery = "select * from employee_payroll where `id`="+key;
        try{
            employees = executeQuery(selectQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
        return employees;
    }

    public void update(int key, Employee value) {
        String updateQuery = "UPDATE employee_payroll set `name`='"+value.getName()+"',`salary`='"+value.getSalary()+"',`start`='"+value.getStart()+"'WHERE `id`="+key;
        try{
            executeUpdate(updateQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(int key) {
        String deleteQuery = "DELETE employee_payroll WHERE `id`="+key;
        try{
            executeUpdate(deleteQuery);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

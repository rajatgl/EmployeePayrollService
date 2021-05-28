package com.org.employeepayrollservice;

import com.org.employeepayrollservice.database.mysql.tables.EmployeeTable;
import com.org.employeepayrollservice.entity.Employee;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    static java.sql.Date date(String date, String pattern) {
        Date dateObj;
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            dateObj = formatter.parse(date);
        } catch (Exception e) {
            dateObj = new Date();
            e.printStackTrace();
        }
        return new java.sql.Date(dateObj.getTime());
    }
    static java.sql.Date date(String date) {
        Date dateObj;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateObj = formatter.parse(date);
        } catch (Exception e) {
            dateObj = new Date();
            e.printStackTrace();
        }
        return new java.sql.Date(dateObj.getTime());
    }

    public static  void main(String[] args){
        Employee employee1 = new Employee("Rajat", 20000.00, date("2021-05-26"));
        EmployeeTable employeeTable = new EmployeeTable();
        employeeTable.create(employee1);
    }
}
package com.org.employeepayrollservice;

import com.google.gson.Gson;
import com.org.employeepayrollservice.database.mysql.tables.EmployeeTable;
import com.org.employeepayrollservice.entity.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
        Boolean runner = true;
        Employee employee1 = new Employee("Rajat", 20000.00, date("2021-05-26"));
        EmployeeTable employeeTable = new EmployeeTable();

        Scanner sc = new Scanner(System.in);
        System.out.println("choice: ");
        System.out.println("1.Insert new employee\n" +
                "2.retrieve all\n" +
                "3.retrieve by employeeId\n" +
                "4.update salary by employeeId\n" +
                "5.delete employee\n" +
                "6.delete all employees" +
                "7.add multiple employee records from json file\n" +
                "8.exit");

        while(runner) {

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    //insert employee into table
                    employeeTable.create(employee1);
                    break;
                case 2:
                    //read all employees onto the console
                    List<Employee> employees = employeeTable.retrieveAll();
                    for (Employee employee : employees) {
                        System.out.println(employee.toString());
                    }
                    break;
                case 3:
                    //retrieve employee by id
                    employees = employeeTable.retrieve(1);
                    for (Employee employee : employees) {
                        System.out.println(employee.toString());
                    }
                    break;
                case 4:
                    //update salary using prepared statement
                    employeeTable.updateSalaryUsingPreparedStatement(1, 50000);
                    break;
                case 5:
                    //delete employee by id
                    employeeTable.delete(1);
                    break;
                case 6:
                    //delete all employees
                    employeeTable.deleteAll();
                    break;
                case 7:
                    //add multiple employees
                    Gson gson = new Gson();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\rajath\\eclipse-workspace\\EmployeePayrollService\\src\\main\\resources\\employee.json"));
                        Employee[] employeeRecords = gson.fromJson(reader,Employee[].class);
                        Arrays.stream(employeeRecords).forEach(employee ->{
                            Runnable task = () ->{
                                employeeTable.create(employee);
                            };
                        });
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    runner = false;
                    break;

            }
        }
    }
}

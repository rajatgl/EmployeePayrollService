package com.org.employeepayrollservice.entity;

import java.util.Date;

public class Employee {

    int employeeId;
    String name;
    double salary;
    Date start;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", start=" + start +
                '}';
    }

    public Employee(int employeeId, String name, double salary, Date start) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
        this.start = start;
    }
    public Employee(String name, double salary, Date start) {
        this.name = name;
        this.salary = salary;
        this.start = start;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Date getStart() {
        return start;
    }
}

package com.org.employeepayrollservice.dao;

import com.org.employeepayrollservice.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    public List<Employee> getAllEmployee();
    public Employee getEmployee(int employeeId);
    public void updateEmployee(Employee employee);
    public void deleteEmployee(Employee employee);
}

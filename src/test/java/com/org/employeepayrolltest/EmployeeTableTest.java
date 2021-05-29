package com.org.employeepayrolltest;

import org.junit.Before;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import org.junit.Test;
import com.google.gson.Gson;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EmployeeTableTest {
    @Before
    public void setup() {
        baseURI ="http://localhost";
        port = 3000;
    }

    public PayrollDetail[] getEmployeeList() {
        Response response = get("/Payroll");
        System.out.println("Payroll Data in JsonServer: "+response.asString());
        PayrollDetail[] empArray = new Gson().fromJson(response.asString(),PayrollDetail[].class);
        return empArray;
    }

    @Test
    public void givenpayrollData_whenreterived_shouldMatchtheCount() {
        PayrollDetail[] empArray = getEmployeeList();
        PayrollService payrollservice = new PayrollService(Arrays.asList(empArray));
        int entries = payrollservice.countEntries();
        assertEquals(4, entries);
    }

    public Response addPayrollRecord(PayrollDetail payrolldata) {
        String empJson = new Gson().toJson(payrolldata);
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        System.out.println(empJson);
        request.body(empJson);
        return request.post("/Payroll");
    }

    @Test
    public void givennewPayrollRecord_whenAdded_shouldreturn201statusCode() {
        PayrollDetail[] empArray = getEmployeeList();
        PayrollService payrollservice = new PayrollService(Arrays.asList(empArray));

        PayrollDetail payrolldetail = new PayrollDetail("Virat", "Kohli", "M", 56000);

        Response response = addPayrollRecord(payrolldetail);
        System.out.println(response.asString());
        int statusCode = response.getStatusCode();
        assertEquals(201, statusCode);

        payrolldetail = new Gson().fromJson(response.asString(), PayrollDetail.class);
        payrollservice.addRecordToPayroll(payrolldetail);
        int entries = payrollservice.countEntries();
        assertEquals(4, entries);

    }

}

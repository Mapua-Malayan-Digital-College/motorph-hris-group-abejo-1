package com.example.test.fx123;

import com.example.fx123.Employees;

public class SamplePopulatingEmployeesRecord {
    public static void main(String[] args) {
        // Add all employees
        Employees.addAllEmployees();

        // Get the size of the employee records
        int employeeRecordSize = Employees.records.size();
        int expectedSize = 25;

        // Check if the size matches the expected value
        boolean sizeMatches = (employeeRecordSize == expectedSize);

        // Print the result
        System.out.println("Employees record size: " + employeeRecordSize);
        System.out.println("Expected size: " + expectedSize);
        System.out.println("Size matches: " + sizeMatches);
    }
}

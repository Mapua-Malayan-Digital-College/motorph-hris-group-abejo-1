package com.example.test.fx123;

import com.example.fx123.CsvUtils;
import com.example.fx123.EmployeeLeave;
import com.example.fx123.Employees;
import com.example.fx123.MainApp;

public class SampleDeleleteEmployeeDataByLineNumber {
    public static void main(String[] args) {
        // Sample Delete Jomari Abejo Data
        CsvUtils.deleteEmployeeRecordByLineNumber(MainApp.EMPLOYEE_CSV, (CsvUtils.findLineNumberByEmployeeNumber(MainApp.EMPLOYEE_CSV, "10016")));
    }
}

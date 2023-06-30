package com.example.test.fx123;

import com.example.fx123.CsvUtils;
import com.example.fx123.MainApp;

public class SampleDeleleteEmployeeDataByLineNumber {
    public static void main(String[] args) {
        // Sample Delete Jomari Abejo Data
        CsvUtils.deleteEmployeeRecordByLineNumber(MainApp.EMPLOYEE_DETAILS_CSV, (CsvUtils.findLineNumberByEmployeeNumber(MainApp.EMPLOYEE_DETAILS_CSV, "10016")));
    }
}

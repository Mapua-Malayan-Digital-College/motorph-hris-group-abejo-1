package com.example.test.fx123;

import com.example.fx123.CsvUtils;
import com.example.fx123.Employees;

public class SamplefindLineNumberByEmployeeNumber {
    public static void main(String[] args) {
        System.out.println(CsvUtils.findLineNumberByEmployeeNumber("F:\\motorph-hris-group-abejo-1\\src\\main\\resources\\csv\\MotorPH Employee Data - Employee Details.csv","100121") == 0 ? "Not found" : "Found");
        System.out.println(CsvUtils.findLineNumberByEmployeeNumber("F:\\motorph-hris-group-abejo-1\\src\\main\\resources\\csv\\MotorPH Employee Data - Employee Details.csv","10001") == 0 ? "Not found" : "Found");
        System.out.println(CsvUtils.findLineNumberByEmployeeNumber("F:\\motorph-hris-group-abejo-1\\src\\main\\resources\\csv\\MotorPH Employee Data - Employee Details.csv","100121"));
        System.out.println(CsvUtils.findLineNumberByEmployeeNumber("F:\\motorph-hris-group-abejo-1\\src\\main\\resources\\csv\\MotorPH Employee Data - Employee Details.csv","10001"));
        CsvUtils.findLineNumberByEmployeeNumber("F:\\motorph-hris-group-abejo-1\\src\\main\\resources\\csv\\MotorPH Employee Data - Employee Details.csv","10025");
    }
}

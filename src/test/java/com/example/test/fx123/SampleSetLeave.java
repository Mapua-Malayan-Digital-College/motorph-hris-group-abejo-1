package com.example.test.fx123;

import com.example.fx123.Employees;
import com.example.fx123.EmployeeLeave;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SampleSetLeave {
    public static void main(String[] args) {
        Employees.addAllEmployees();
        EmployeeLeave.addAllLeaves();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        EmployeeLeave manageLeaves = null;
        try {
            manageLeaves = new EmployeeLeave(10001,"Crisostomo", "Jose","Vacation",("6/1/2022"));
            manageLeaves.createEmployeeLeave();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

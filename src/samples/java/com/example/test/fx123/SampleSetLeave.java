package com.example.test.fx123;

import com.example.fx123.Employees;
import com.example.fx123.Leaves;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SampleSetLeave {
    public static void main(String[] args) {
        Employees.addAllEmployees();
        Leaves.addAllLeaves();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Leaves manageLeaves = null;
        try {
            manageLeaves = new Leaves(10001,"Crisostomo", "Jose","Vacation",("6/1/2022"));
            manageLeaves.createEmployeeLeave();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.example.test.fx123;

import com.example.fx123.CsvUtils;
import com.example.fx123.Employees;

public class SamplePopulateEmployee {
    public static void main(String[] args) {
        CsvUtils.addAllEmployee();
        System.out.println(Employees.records.size());
    }
}

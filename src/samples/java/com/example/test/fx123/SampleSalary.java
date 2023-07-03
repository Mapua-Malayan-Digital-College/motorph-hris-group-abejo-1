package com.example.test.fx123;

import com.example.fx123.CsvUtils;

public class SampleSalary {
    public static void main(String[] args) {
        String hourly_rate = "70585.05";
        System.out.println(CsvUtils.addCommaAndTwoDecimalsForFloatStr(String.valueOf(hourly_rate)));
        System.out.println(CsvUtils.addCommaToStrInt(String.valueOf(hourly_rate)));
    }
}

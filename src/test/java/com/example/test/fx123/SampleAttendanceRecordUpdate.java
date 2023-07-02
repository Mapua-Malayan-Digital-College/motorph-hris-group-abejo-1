package com.example.test.fx123;

import com.example.fx123.CsvUtils;

import java.io.IOException;

public class SampleAttendanceRecordUpdate {
    public static void main(String[] args) throws IOException {
        // sample update line 2 of attendance csv
        CsvUtils.updateAttendanceCSVViaOldStringtoNewString("10001,Crisostomo,Jose,1/9/2022,8:00,17:00","10001,Crisostomoo,Jose,1/10/2022,8:00,17:00");
    }
}

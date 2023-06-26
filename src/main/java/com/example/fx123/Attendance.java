package com.example.fx123;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Attendance {
    public static ArrayList<Attendance> records = new ArrayList<>();
    private int employee_number;
    private String l_name;
    private String f_name;
    private String date;
    private String timeIn;
    private String timeOut;

    public Attendance(int employeeNumber, String lastName, String firstName,
                      String date, String timeIn, String timeOut) {
        this.employee_number = employeeNumber;
        this.l_name = lastName;
        this.f_name = firstName;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public int getEmployee_number() {
        return employee_number;
    }

    public String getL_name() {
        return l_name;
    }

    public String getF_name() {
        return f_name;
    }

    public String getDate() {
        return date;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    @Override
    public String toString() {
        return "Attendance{"
                + "employee_number=" + employee_number + ", lName='" + l_name + '\''
                + ", fName='" + f_name + '\'' + ", date=" + date + ", timeIn=" + timeIn
                + ", timeOut=" + timeOut + '}';
    }

    public static void addAllAttendanceRecord() {
        try {
            String path = MainApp.ATTENDANCE_TSV;
            BufferedReader tsvReader = new BufferedReader(new FileReader(path));
            String line;

            int i = 0;
            while ((line = tsvReader.readLine()) != null) {
                if (i == 0) {
                    i++;
                    continue;
                }
                String[] arr = line.split("\t");
                if (arr.length == 5) {
                    Arrays.stream(arr).forEach(System.out::println);
                    System.out.println("arr length = " + arr.length);
                }

                Attendance attendance = new Attendance(
                        Integer.valueOf(arr[0]), arr[1], arr[2], arr[3], arr[4], arr[5]);
                Attendance.records.add(attendance);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void clearAttendanceRecord() {
        records.clear();
    }

    public String getFullName() {
        return f_name + " " + l_name;
    }
}

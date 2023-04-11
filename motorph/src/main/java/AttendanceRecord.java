import gui.EmployeeData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class AttendanceRecord extends EmployeeData {
    String [][] employeeAttendanceRecord;
    public String [][] storeAttendanceRecordData(String pathOfAttendanceRecord, int employeeID, int year){
        // ex: [6, 2125]
        String [] grid_length = (countRowAndCol(pathOfAttendanceRecord).split(","));
        // ex: 6
        int row_length = Integer.parseInt(grid_length[0]);
        // ex: 2125
        int col_length = Integer.parseInt(grid_length[1]);
        System.out.println("col_length = " + grid_length[0]);
        System.out.println("row_length = " + grid_length[1]);
        employeeAttendanceRecord = new String[col_length][row_length];

        return readTSV(new File(pathOfAttendanceRecord), col_length,row_length);
    }

    public String[][] readTSV(File path, int col, int row) {
        System.out.println("THE COL SHOULD = " + col);
        System.out.println("THE ROW SHOULD = " + row);
        String [][] Data = new String[col][row];
        try (BufferedReader TSVReader = new BufferedReader(new FileReader(path))) {
            String line = null;
            System.out.println("COL " + col);
            System.out.println("ROW " + row);
            int outer = 0;
            while ((line = TSVReader.readLine()) != null && outer != col) {
                String[] lineItems = line.split("\t");
                {
                    Data[outer][0] = lineItems[0]; // Employee #
                    Data[outer][1] = lineItems[1]; // Last Name
                    Data[outer][2] = lineItems[2]; // First Name
                    Data[outer][3] = lineItems[3]; // Date
                    Data[outer][4] = lineItems[4]; // Time In
                    Data[outer][5] = lineItems[5]; // Time Out
                    outer++;
                }
            }
            return Data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Data;
    }

}

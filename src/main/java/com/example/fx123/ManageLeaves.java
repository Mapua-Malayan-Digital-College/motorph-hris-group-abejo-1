package com.example.fx123;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ManageLeaves {

    private int eid;
    private String last_name, first_name, leave_type;
    private Date leave_start, leave_end;

    private static final int
            MAX_SICK_LEAVES = 5,
            MAX_VACATION_LEAVES = 10,
            MAX_EMERGENCY_LEAVES = 5;

    public static List<ManageLeaves> RECORDS = new ArrayList<ManageLeaves>();


    ManageLeaves(int employeeNumber, String lname, String fname, String leaveType, Date leaveStart, Date leaveEnd) {
        this.eid = employeeNumber;
        this.last_name = lname;
        this.first_name = fname;
        this.leave_type = leaveType;
        this.leave_start = leaveStart;
        this.leave_end = leaveEnd;
    }

    public static void addAllLeavesOfEmployee(String eid) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(MainApp.LEAVES_TSV_PATH));
            boolean headers = true;
            String line = null;

            while ((line = br.readLine()) != null) {

                String [] arr = line.split("\t");

                // skip headers
                if (headers) {
                    headers = false;
                }


                else if (arr[0].equals(eid)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
                    try {
                        Date startDateFormatter = sdf.parse(arr[4]);
                        Date endDateFormatter = sdf.parse(arr[5]);

                        ManageLeaves sl = new ManageLeaves(Integer.valueOf(arr[0]),arr[1],arr[2],arr[3],startDateFormatter,endDateFormatter);

                        RECORDS.add(sl);

                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createEmployeeLeave(int eid,String lname,String fname,String leaveType,Date leaveStart,Date leaveEnd) {
        if (isAllowedToCreateLeave()) {
            ManageLeaves sl = new ManageLeaves(eid,lname,fname,leaveType,leaveStart,leaveEnd);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(MainApp.LEAVES_TSV_PATH, true));
                writer.write(sl.toTabString());
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else System.out.println("This employee is already consumed this type of leave...");
    }

    public static boolean isAllowedToCreateLeave() {
        String [] str = getRemainingLeaves().split("\t");
        int emergency = Integer.parseInt(str[0]) ,
            sick = Integer.parseInt(str[1])  ,
            vacation = Integer.parseInt(str[2]);

        if (emergency >= MAX_EMERGENCY_LEAVES || sick >= MAX_SICK_LEAVES || vacation >= MAX_VACATION_LEAVES) return false;
        else return true;
    }


    @Override
    public String toString() {
        return "SetLeave{" +
                "eid=" + eid +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", leave_type='" + leave_type + '\'' +
                ", leave_start=" + leave_start +
                ", leave_end=" + leave_end +
                '}';
    }

    public String toTabString() {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String start_date = sdf.format(leave_start);
        String end_date = sdf.format(leave_start);

        return eid  +  "\t" +
            last_name + "\t" +
            first_name + "\t" +
            leave_type  + "\t" +
            start_date  + "\t" +
            end_date     + "\t";
    }

    public static String getRemainingLeaves() {
        int emergency_counter = 0, sick_counter = 0, vacation_counter = 0;

            for (int i = 0; i < RECORDS.size(); i++) {
                switch (RECORDS.get(i).leave_type.toLowerCase()) {
                    case "sick":
                        sick_counter++;
                        break;
                    case "vacation":
                        vacation_counter++;
                        break;
                    case "emergency":
                        emergency_counter++;
                        break;
                }
            }
        return emergency_counter+"\t"+sick_counter+"\t"+vacation_counter;
    }

    public static void clearRecords() {
        RECORDS.clear();
    }
}

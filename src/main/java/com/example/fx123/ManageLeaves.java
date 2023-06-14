package com.example.fx123;

import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ManageLeaves {

    private int eid;
    private String last_name, first_name, leave_type;
    private Date leave_start, leave_end;

    private static final int
            MAX_SICK_LEAVES = 5,
            MAX_VACATION_LEAVES = 10,
            MAX_EMERGENCY_LEAVES = 5;

    public static List<ManageLeaves> RECORDS = new ArrayList<ManageLeaves>();


    public ManageLeaves(int employeeNumber, String lname, String fname, String leaveType, Date leaveStart, Date leaveEnd) {
        this.eid = employeeNumber;
        this.last_name = lname;
        this.first_name = fname;
        this.leave_type = leaveType;
        this.leave_start = leaveStart;
        this.leave_end = leaveEnd;
    }

    public static void addAllLeavesOfEmployee(String eid) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(MainApp.LEAVE_TSV));
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


    public  void createEmployeeLeave(int eid, String lname, String fname, String leaveType, Date leaveStart, Date leaveEnd) {
        this.leave_type = leaveType;
        this.leave_start = leaveStart;
        this.leave_end = leaveEnd;
        if (isAllowedToCreateLeave()) {
            ManageLeaves addNewLeave = new ManageLeaves(eid,lname,fname,leaveType,leaveStart,leaveEnd);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(MainApp.LEAVE_TSV, true));
                writer.write(addNewLeave.toTabString());
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.err.println("Employee exceeds credit limit");
        }
    }

    private boolean isAllowedToCreateLeave() {

        /**
         *      Remaining Leaves Array =
         *          [0] = Emergency,
         *          [1] = Sick,
         *          [2] = Vacation
         */
        String [] remainingLeaves = getRemainingLeavesOfEmployee().split("\t");
        int consumable_emergency = Integer.parseInt(remainingLeaves[0]),
            consumable_sick = Integer.parseInt(remainingLeaves[1]),
            consumable_vacation = Integer.parseInt(remainingLeaves[2]);
        if (RECORDS.isEmpty()) addAllLeavesOfEmployee(String.valueOf(eid));
        if (isEmployeeNumberExist()) {
            if (getLeaveType().equals("emergency")) {
                if (Math.abs((totalDaysOfLeave() - consumable_emergency)) <= MAX_EMERGENCY_LEAVES && (totalDaysOfLeave() + consumable_emergency) <= MAX_EMERGENCY_LEAVES) {
                    return consumable_emergency < MAX_EMERGENCY_LEAVES;
                }
            }
            else if (getLeaveType().equals("sick")) {
                if (Math.abs(totalDaysOfLeave() - consumable_sick) <= MAX_SICK_LEAVES&& (totalDaysOfLeave() + consumable_sick) <= MAX_SICK_LEAVES) {
                    return consumable_sick < MAX_SICK_LEAVES;
                }
            }
            else if (getLeaveType().equals("vacation")) {
                if ((Math.abs(totalDaysOfLeave() - consumable_vacation)) <= MAX_VACATION_LEAVES && (totalDaysOfLeave() + consumable_vacation) <= MAX_VACATION_LEAVES) {
                    return consumable_vacation < MAX_VACATION_LEAVES;
                }
            }
        }
        return false;
    }

    public boolean isEmployeeNumberExist() {
        for (int i = 0; i < Employees.records.size() ; i++) {
            if (Employees.records.get(i).getId().equals(String.valueOf(eid))) {
                return true;
            }
        }
        return false;
    }

    private int totalDaysOfLeave() {
        Calendar start_calendar = Calendar.getInstance();
        Calendar end_calendar = Calendar.getInstance();

        start_calendar.setTime(leave_start);
        end_calendar.setTime(leave_end);

        int start_dayOfYear = start_calendar.get(Calendar.DAY_OF_YEAR);
        int end_dayOfYear = end_calendar.get(Calendar.DAY_OF_YEAR);

        return end_dayOfYear - start_dayOfYear;
    }

    public String getLeaveType() {
        switch (leave_type.toLowerCase()) {
            case "emergency" -> {
                return "emergency";
            }
            case "sick" -> {
                return "sick";
            }
            case "vacation" -> {
                return "vacation";
            }
        }
        return "leave type doesn't exist";
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

        Calendar end_date_calendar = Calendar.getInstance();
        end_date_calendar.setTime(leave_end);
        int end_dayOfYear = end_date_calendar.get(Calendar.DAY_OF_YEAR);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, end_dayOfYear+1);
        Date endDate = calendar.getTime();
        String incrementedDate = sdf.format(endDate);

        // Increment the date once if the leave start is same with leave end
        String end_date = (leave_start.toString().equals(leave_end.toString()))
                ? incrementedDate : sdf.format(leave_end);

        return eid  +  "\t" +
            last_name + "\t" +
            first_name + "\t" +
            leave_type  + "\t" +
            start_date  + "\t" +
            end_date     + "\t";
    }

    public String getRemainingLeavesOfEmployee() {
        int emergency_counter = 0, sick_counter = 0, vacation_counter = 0;

            for (int i = 0; i < RECORDS.size(); i++) {
                if (eid == RECORDS.get(i).eid) {
                    Calendar start_calendar = Calendar.getInstance();
                    Calendar end_calendar = Calendar.getInstance();

                    start_calendar.setTime(RECORDS.get(i).leave_start);
                    end_calendar.setTime(RECORDS.get(i).leave_end);

                    int start_dayOfYear = start_calendar.get(Calendar.DAY_OF_YEAR);
                    int end_dayOfYear = end_calendar.get(Calendar.DAY_OF_YEAR);

                    int differenceDayOfYear = (end_dayOfYear == start_dayOfYear) ? 1 : end_dayOfYear - start_dayOfYear;
                    switch (RECORDS.get(i).leave_type.toLowerCase()) {
                        case "sick":
                            sick_counter += differenceDayOfYear;
                            break;
                        case "vacation":
                            vacation_counter += differenceDayOfYear;
                            break;
                        case "emergency":
                            emergency_counter += differenceDayOfYear;
                            break;
                    }
                }
            }
        return emergency_counter+"\t"+sick_counter+"\t"+vacation_counter;
    }

    public static void clearRecords() {
        RECORDS.clear();
    }
}

package com.example.fx123;

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

    public static void createEmployeeLeave(int eid,String lname,String fname,String leaveType,Date leaveStart,Date leaveEnd) {
        if (isAllowedToCreateLeave(eid,leaveStart,leaveEnd,leaveType)) {
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
        else System.out.println("This employee is already consumed this type of leave...");
    }

    // TODO: 6/13/2023 Include in condition the date range, if range of leave is greater than to the remaining leaves for the specific leave type
    //          then it will not processed.

    public static boolean isAllowedToCreateLeave(int eid, Date leave_start, Date leave_end, String leave_type) {
        Calendar start_calendar = Calendar.getInstance();
        Calendar end_calendar = Calendar.getInstance();

        start_calendar.setTime(leave_start);
        end_calendar.setTime(leave_end);

        int start_dayOfYear = start_calendar.get(Calendar.DAY_OF_YEAR);
        int end_dayOfYear = end_calendar.get(Calendar.DAY_OF_YEAR);

        //  get the difference of starting sched and end sched of leave
        int differenceDayOfYear = end_dayOfYear - start_dayOfYear;

        System.out.println("Difference of end leave and start leave = " + differenceDayOfYear + "\t For " + leave_type);

        String [] str = getRemainingLeavesOfEmployee(eid).split("\t");

        System.out.println("leave_type.toLowerCase()\t" + leave_type.toLowerCase());

        int emergency = Integer.parseInt(str[0]) ,
            sick = Integer.parseInt(str[1]),
            vacation = Integer.parseInt(str[2]),
                remaining_leave =
                switch (leave_type.toLowerCase())
                    {
                        case "emergency" ->
                                differenceDayOfYear - (MAX_EMERGENCY_LEAVES);
                        case "sick" ->
                                differenceDayOfYear - (MAX_SICK_LEAVES);
                        case "vacation" ->
                                differenceDayOfYear - (MAX_VACATION_LEAVES);
                        default -> 0;
                    };
        System.out.println("getRemainingLeavesOfEmployee()" +
                "\tEmergency=\t" + emergency +
                "\tSick=\t" + sick +
                "\tVacation=\t" + vacation);
        System.out.println("remaining leave = " + remaining_leave);
        if (differenceDayOfYear > remaining_leave) {
            System.out.println("The differenceDayOfYear("+differenceDayOfYear+")"+ "is greater than remaining_leave("+remaining_leave+")");
            return false; // check if the scheduled leave exceeds to the remaining leave of the specified leave type
        }
        //emergency >= MAX_EMERGENCY_LEAVES || sick >= MAX_SICK_LEAVES || vacation >= MAX_VACATION_LEAVES
        else if (true) {
            int credits = 0;
            for (int i = 0; i < RECORDS.size(); i++){
                if (RECORDS.get(i).leave_type.equalsIgnoreCase(leave_type)){
                        credits++;
                }
            }
            int leave_type_max = switch (leave_type.toLowerCase()) {
                case "emergency" -> MAX_EMERGENCY_LEAVES;
                case "sick" -> MAX_SICK_LEAVES;
                case "vacation" -> MAX_VACATION_LEAVES;
                default -> 0;
            };

            if (credits >= leave_type_max) {
                System.out.println("second attempt false");
                return false;
            }
        }
        System.out.println("END TRUE");
        return true;
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
        String end_date = sdf.format(leave_end);

        return eid  +  "\t" +
            last_name + "\t" +
            first_name + "\t" +
            leave_type  + "\t" +
            start_date  + "\t" +
            end_date     + "\t";
    }

    // TODO: 6/13/2023 we incremented leaves via types, now we need to count it via start-end date by utilizing leave type
    public static String getRemainingLeavesOfEmployee(int eid) {
        int emergency_counter = 0, sick_counter = 0, vacation_counter = 0;

            for (int i = 0; i < RECORDS.size(); i++) {
                if (eid == RECORDS.get(i).eid) {
                    Calendar start_calendar = Calendar.getInstance();
                    Calendar end_calendar = Calendar.getInstance();

                    start_calendar.setTime(RECORDS.get(i).leave_start);
                    end_calendar.setTime(RECORDS.get(i).leave_end);

                    int start_dayOfYear = start_calendar.get(Calendar.DAY_OF_YEAR);
                    int end_dayOfYear = end_calendar.get(Calendar.DAY_OF_YEAR);

                    //  get the difference of starting sched and end sched of leave
                    int differenceDayOfYear = end_dayOfYear - start_dayOfYear;
                    switch (RECORDS.get(i).leave_type.toLowerCase()) {
                        case "sick" ->
                                sick_counter += differenceDayOfYear;
                        case "vacation" ->
                                vacation_counter += differenceDayOfYear;
                        case "emergency" ->
                                emergency_counter += differenceDayOfYear;
                    }
                }
            }
        System.out.println("emergency_counter = " + emergency_counter + "\tsick_counter = \t"+ sick_counter + "\tvacation_counter =\t" + vacation_counter);
        return emergency_counter+"\t"+sick_counter+"\t"+vacation_counter;
    }

    public static void clearRecords() {
        RECORDS.clear();
    }
}

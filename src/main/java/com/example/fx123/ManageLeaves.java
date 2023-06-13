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
        else System.out.println("This employee is already consumed this type of leave...");
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
            System.out.println("Starting at if parent✅");
            System.out.println(getLeaveType().equals("emergency"));
            System.out.println("sick left = " + consumable_sick);
            System.out.println("totalDaysOfLeave() = " + totalDaysOfLeave());
            if (getLeaveType().equals("emergency")) {
                // includes to if condition consumable_emergency < 5 &&
                if (Math.abs((totalDaysOfLeave() - consumable_emergency)) < 5 && (totalDaysOfLeave() + consumable_emergency) <= 5) {
                    System.out.println("true at initial else if if✅");
                    return true;
                }
            }
            else if (getLeaveType().equals("sick")) {
                if ((Math.abs(totalDaysOfLeave() - consumable_sick)) < 5 && (totalDaysOfLeave() + consumable_sick) <= 5) {
                    System.out.println("true at secondary else if if✅");
                    return true;
                }
            }
            else if (getLeaveType().equals("vacation")) {
                System.out.println("is it positive : " + (totalDaysOfLeave() - consumable_vacation));
                if ((Math.abs(totalDaysOfLeave() - consumable_vacation)) < 10 && (totalDaysOfLeave() + consumable_vacation) <= 10) {
                    System.out.println("true at tertiary else if if✅");
                    return true;
                }
            }
        }
        System.out.println("Ending falsy❌");
        return false;
    }

    public boolean isEmployeeNumberExist() {
        for (int i = 0; i < Employees.records.size() ; i++) {
            if (Employees.records.get(i).getId().equals(String.valueOf(eid))) {
                System.out.println("isEmployeeNumberExist = TRUE!!!");
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

    // TODO: 6/13/2023 Include in condition the date range, if range of leave is greater than to the remaining leaves for the specific leave type
    //          then it will not processed.

    public  boolean isAllowedToCreateLeaveClone() {
        Calendar start_calendar = Calendar.getInstance();
        Calendar end_calendar = Calendar.getInstance();

        start_calendar.setTime(leave_start);
        end_calendar.setTime(leave_end);

        int start_dayOfYear = start_calendar.get(Calendar.DAY_OF_YEAR);
        int end_dayOfYear = end_calendar.get(Calendar.DAY_OF_YEAR);

        //  get the difference of starting sched and end sched of leave
        int schedule_range = end_dayOfYear - start_dayOfYear;

        System.out.println("We want to append this sched to tsv Difference of end leave and start leave = " + schedule_range + "\t For " + leave_type);

        String [] str = getRemainingLeavesOfEmployee().split("\t");

        System.out.println("leave_type.toLowerCase()\t" + leave_type.toLowerCase());

        int emergency = Integer.parseInt(str[0]) ,
            sick = Integer.parseInt(str[1]),
            vacation = Integer.parseInt(str[2]),
                remaining_leave =
                switch (leave_type.toLowerCase())
                    {
                        case "emergency" ->
                                (MAX_EMERGENCY_LEAVES)  - schedule_range;
                        case "sick" ->
                                (MAX_SICK_LEAVES)  - schedule_range;
                        case "vacation" ->
                                (MAX_VACATION_LEAVES)  - schedule_range;
                        default -> 0;
                    };
        System.out.println("getRemainingLeavesOfEmployee()" +
                "\tEmergency=\t" + emergency +
                "\tSick=\t" + sick +
                "\tVacation=\t" + vacation);
        System.out.println("remaining leave = " + remaining_leave);

        boolean [] isAllowed = new boolean[]{true,true,true}; // [0] = emergency, [1] = sick, [2] = vacation

        if (remaining_leave < schedule_range && emergency > 5){
            isAllowed[0] = false;
        }

        else if (remaining_leave < schedule_range && sick > 5) {
            isAllowed[1] = false;
        }

        else if (remaining_leave < schedule_range && vacation > 10) {
            isAllowed[2] = false;
        }

        System.out.println("The differenceDayOfYear("+schedule_range+")"+ "is greater than remaining_leave("+remaining_leave+")");
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

                    //  get the difference of starting sched and end sched of leave
                    //  if the starting date and end date of leave the same, it should be considered as one day difference or one day leave
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
                System.out.println("emergency counter = " + emergency_counter);
                System.out.println("sick      counter = " + sick_counter);
                System.out.println("vacation  counter = " + vacation_counter);
            }
        System.out.println("emergency_counter = " + emergency_counter + "\tsick_counter = \t"+ sick_counter + "\tvacation_counter =\t" + vacation_counter);
        return emergency_counter+"\t"+sick_counter+"\t"+vacation_counter;
    }

    public static void clearRecords() {
        RECORDS.clear();
    }
}

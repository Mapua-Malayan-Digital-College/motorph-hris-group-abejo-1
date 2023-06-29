package com.example.fx123;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EmployeeLeave {
    private int eid;
    private String last_name, first_name, leave_type;
    private Date leave_start, leave_end;

    public final int MAX_SICK_LEAVES = 5, MAX_VACATION_LEAVES = 10,
            MAX_EMERGENCY_LEAVES = 5;

    public static List<EmployeeLeave> RECORDS = new ArrayList<>();

    public int getEid() {
        return eid;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLeave_type() {
        return leave_type;
    }

    public Date getLeave_start() {
        return leave_start;
    }

    public Date getLeave_end() {
        return leave_end;
    }

    public EmployeeLeave(int employeeNumber, String lname, String fname,
                         String leaveType, Date leaveStart, Date leaveEnd) {
        this.eid = employeeNumber;
        this.last_name = lname;
        this.first_name = fname;
        this.leave_type = leaveType;
        this.leave_start = leaveStart;
        this.leave_end = leaveEnd;
    }

    public static void addAllLeaves() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(MainApp.LEAVE_CSV));
            boolean headers = true;
            String line;

            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");

                Arrays.stream(arr).forEach(System.out::println);
                // skip headers
                if (headers) {
                    headers = false;
                    continue;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    Date startDateFormatter = sdf.parse(arr[4]);
                    Date endDateFormatter = sdf.parse(arr[5]);

                    EmployeeLeave sl = new EmployeeLeave(Integer.valueOf(arr[0]), arr[1],
                            arr[2], arr[3], startDateFormatter, endDateFormatter);
                    RECORDS.add(sl);

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createEmployeeLeave() {
        if (isAllowedToCreateLeave()) {
            try {
                BufferedWriter writer =
                        new BufferedWriter(new FileWriter(MainApp.LEAVE_CSV, true));
                writer.write(toCommaString());
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.err.println("Employee exceeds credit limit");
        }
    }

    private boolean isAllowedToCreateLeave() {
        if (isEmployeeNumberExist()) {
            System.out.println("Total Days = " + totalDaysLeave());

            String[] remaining_leaves = getConsumedCredits().split("\t");
            int consumedEmergencyCredits = Integer.parseInt(remaining_leaves[0]),
                    consumedSickCredits = Integer.parseInt(remaining_leaves[1]),
                    consumedVacationCredits = Integer.parseInt(remaining_leaves[2]);

            if (leave_start.equals(leave_end))
                return false;

            else if (getLeaveType().equals("Emergency")
                    && (totalDaysLeave() + consumedEmergencyCredits)
                    <= MAX_EMERGENCY_LEAVES)
                return true;

            else if (getLeaveType().equals("Sick")
                    && (totalDaysLeave() + consumedSickCredits) <= MAX_SICK_LEAVES)
                return true;

            else
                return getLeaveType().equals("Vacation")
                        && (totalDaysLeave() + consumedVacationCredits)
                        <= MAX_VACATION_LEAVES;
        }
        return false;
    }

    public boolean isEmployeeNumberExist() {
        for (int i = 0; i < Employees.records.size(); i++) {
            if (Employees.records.get(i).getId().equals(String.valueOf(eid))) {
                return true;
            }
        }
        return false;
    }

    public int totalDaysLeave() {
        Calendar start_calendar = Calendar.getInstance();
        Calendar end_calendar = Calendar.getInstance();

        start_calendar.setTime(leave_start);
        end_calendar.setTime(leave_end);

        int start_dayOfYear = start_calendar.get(Calendar.DAY_OF_YEAR);
        int end_dayOfYear = end_calendar.get(Calendar.DAY_OF_YEAR);

        System.out.println("start_dayOfYear = " + start_dayOfYear);
        System.out.println("end_dayOfYear = " + end_dayOfYear);

        return end_dayOfYear - start_dayOfYear;
    }

    public String getLeaveType() {
        switch (leave_type.toLowerCase()) {
            case "emergency":
                return "Emergency";
            case "sick":
                return "Sick";
            case "acation":
                return "Vacation";
        }
        return "leave type doesn't exist";
    }

    @Override
    public String toString() {
        return "SetLeave{"
                + "eid=" + eid + ", last_name='" + last_name + '\'' + ", first_name='"
                + first_name + '\'' + ", leave_type='" + leave_type + '\''
                + ", leave_start=" + leave_start + ", leave_end=" + leave_end + '}';
    }

    public String toCommaString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String start_date = sdf.format(leave_start);
        String end_date = sdf.format(leave_end);

        return eid + "," + last_name + "," + first_name + "," + leave_type + ","
                + start_date + "," + end_date + ",";
    }

    public String getConsumedCredits() {
        int emergency_counter = 0, sick_counter = 0, vacation_counter = 0;

        for (EmployeeLeave record : RECORDS) {
            if (eid == record.eid) {
                Calendar start_calendar = Calendar.getInstance();
                Calendar end_calendar = Calendar.getInstance();

                start_calendar.setTime(record.leave_start);
                end_calendar.setTime(record.leave_end);

                int start_dayOfYear = start_calendar.get(Calendar.DAY_OF_YEAR);
                int end_dayOfYear = end_calendar.get(Calendar.DAY_OF_YEAR);

                int differenceDayOfYear = end_dayOfYear - start_dayOfYear;
                switch (record.leave_type.toLowerCase()) {
                    case "sick" -> sick_counter += differenceDayOfYear;
                    case "vacation" -> vacation_counter += differenceDayOfYear;
                    case "emergency" -> emergency_counter += differenceDayOfYear;
                }
            }
        }
        System.out.println("emergency = "+emergency_counter);
        System.out.println("sick = "+sick_counter);
        System.out.println("vacation = "+vacation_counter);
        return emergency_counter+"\t"+sick_counter+"\t"+vacation_counter;
    }
}

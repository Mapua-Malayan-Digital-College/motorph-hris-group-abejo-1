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

public class Leaves {
    private int eid;
    private String last_name, first_name, leave_type;
    private String leave_date;

    public static final int MAX_SICK_LEAVES = 5, MAX_VACATION_LEAVES = 10,
            MAX_EMERGENCY_LEAVES = 5;

    public static List<Leaves> RECORDS = new ArrayList<>();
    public static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

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

    public String getLeave_date() {
        return leave_date;
    }

    public Leaves(int employeeNumber, String lname, String fname,
                  String leaveType, String leaveDate) {
        this.eid = employeeNumber;
        this.last_name = lname;
        this.first_name = fname;
        this.leave_type = leaveType;
        this.leave_date = leaveDate;
    }

    public static void addAllLeaves() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(MainApp.LEAVE_CSV));
            boolean headers = true;
            String line;

            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");

                // skip headers
                if (headers) {
                    headers = false;
                    continue;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

                Leaves sl = new Leaves(Integer.valueOf(arr[0]), arr[1],
                        arr[2], arr[3], arr[4]);
                RECORDS.add(sl);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createEmployeeLeave() throws ParseException {
        if (true) {
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


    public boolean isEmployeeNumberExist() {
        for (int i = 0; i < Employees.records.size(); i++) {
            if (Employees.records.get(i).getId().equals(String.valueOf(eid))) {
                return true;
            }
        }
        return false;
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
                + "eid=" + eid + ", last_name='" + last_name + ',' + ", first_name='"
                + first_name + ',' + ", leave_type='" + leave_type + ','
                + ", leave_start=" + leave_date +'}';
    }

    public String toCommaString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String leave_date = (this.leave_date);

        return eid + "," + last_name + "," + first_name + "," + leave_type + ","
                + leave_date;
    }

    public String getConsumedCredits() throws ParseException {
        int emergency_counter = 0, sick_counter = 0, vacation_counter = 0;

        for (int i = 0; i < Leaves.RECORDS.size(); i++) {
            if (eid == Leaves.RECORDS.get(i).getEid()) {
                Calendar leave_date = Calendar.getInstance();
                Date date_leave = sdf.parse(Leaves.RECORDS.get(i).getLeave_date());
                leave_date.setTime(date_leave);
                String dateString = Leaves.RECORDS.get(i).leave_date;

                switch (Leaves.RECORDS.get(i).leave_type.toLowerCase()) {
                    case "sick" -> sick_counter++;
                    case "vacation" -> vacation_counter++;
                    case "emergency" -> emergency_counter++;
                }
            }
        }
        System.out.println("emergency = "+emergency_counter);
        System.out.println("sick = "+sick_counter);
        System.out.println("vacation = "+vacation_counter);
        return emergency_counter+"\t"+sick_counter+"\t"+vacation_counter;
    }
}

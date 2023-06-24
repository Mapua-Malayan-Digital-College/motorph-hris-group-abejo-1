package com.example.fx123;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Salary {
    static int calculateWeeklySalary() throws ParseException {
        return 0;
    }
    static int calculateWeeklyHoursWorked(int eid, int numMonth,List<Attendance> lsAttendance) throws ParseException {
        // sort lsAttendance
        Collections.sort(lsAttendance, new Comparator<Attendance>() {
            @Override
            public int compare(Attendance o1, Attendance o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        int i = 0;
        int eidcounter = 0;
        // Hours Worked Breakdown
        // Increment one of this variable once, if the attendance date matches to week of month
        int weekOne = 0,
            weekTwo = 0,
            weekThree = 0,
            weekFour = 0,
            weekFive = 0,
            weekSix = 0;
        while (i < lsAttendance.size()) {
            System.out.println(lsAttendance.get(i).toString());
            if (eid == (lsAttendance.get(i).getEmployee_number())) {
                // check if month is the same
                // mm,dd,yy
                String [] arrDateAttendance = lsAttendance.get(i).getDate().split("/");
                if (Integer.parseInt(arrDateAttendance[0]) == (numMonth)) {
                    // TODO: 6/24/2023 increment weeklyhoursworked
                    calendar.setTime(sdf.parse(lsAttendance.get(i).getDate()));

                    String startTimeString = lsAttendance.get(i).getTimeIn();
                    String endTimeString = lsAttendance.get(i).getTimeOut();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

                    LocalTime startTime = LocalTime.parse(startTimeString, formatter);
                    LocalTime endTime = LocalTime.parse(endTimeString, formatter);

                    Duration duration = Duration.between(startTime, endTime);

                    long totalHours = duration.toHours();


                    System.out.println("Total hours worked: " + totalHours);
                    if (calendar.get(Calendar.WEEK_OF_MONTH) == 1) {
                        weekOne += ((int) totalHours);
                    }
                    else if (calendar.get(Calendar.WEEK_OF_MONTH) == 2) {
                        weekTwo += ((int) totalHours);
                    }
                    else if (calendar.get(Calendar.WEEK_OF_MONTH) == 3) {
                        weekThree += ((int) totalHours);
                    }
                    else if (calendar.get(Calendar.WEEK_OF_MONTH) == 4) {
                        weekFour += ((int) totalHours);
                    }
                    else if (calendar.get(Calendar.WEEK_OF_MONTH) == 5) {
                        weekFive += ((int) totalHours);
                    }
                    else {
                        weekSix += ((int) totalHours);
                    }
                    System.out.println(eidcounter+"."+"Date Attendance = " + lsAttendance.get(i).getDate() + " Week " + calendar.getWeeksInWeekYear() + "Week of the month " + calendar.get(Calendar.WEEK_OF_MONTH));
                }
            }
            i++;
        }
        System.out.println("eid counter = " + eidcounter);
        System.out.println("Hours Worked Breakdown");
        System.out.println("Week 1: " + weekOne);
        System.out.println("Week 2: " + weekTwo);
        System.out.println("Week 3: " + weekThree);
        System.out.println("Week 4: " + weekFour);
        System.out.println("Week 5: " + weekFive);
        System.out.println("Week 6: " + weekSix);
        return 0;
    }

//    public static void main(String[] args) throws ParseException {
//        Attendance.addAllAttendanceRecord();
//        calculateWeeklyHoursWorked(10001,1,Attendance.records);
//
//        System.out.println(Attendance.records.size());
//    }
}

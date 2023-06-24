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

    /**
     *
     * @param eid employee number
     * @param numMonth Month to be calculated worked hours, Use the month number instead of month words, it use to calculate the
     * @param year Year to be calculated worked hours
     * @param lsAttendance attendance list
     * @return Array of integer that includes [total hours worked, week 1 hours worked, week 2 hours worked ,
     *                                          week 3 hours worked, week 4 hours worked, week 5 hours worked,
     *                                          week 6 hours worked];
     * @throws ParseException
     */
    static int [] calculateWeeklyHoursWorked(int eid, int numMonth, int year,List<Attendance> lsAttendance) throws ParseException {
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
        int
            total_hours_worked = 0,
            weekOne = 0,
            weekTwo = 0,
            weekThree = 0,
            weekFour = 0,
            weekFive = 0,
            weekSix = 0;
        while (i < lsAttendance.size()) {

            if (eid == (lsAttendance.get(i).getEmployee_number())) {
                // mm,dd,yy
                String [] arrDateAttendance = lsAttendance.get(i).getDate().split("/");

                /**
                 * Not all attendance records from csv/tsv have four digits( ex: [2022 = 22]
                 *  To solve the problem we can create another variable where we can make the week year into four digits.
                 */

                int getArrYear = Integer.parseInt(arrDateAttendance[2]) > 2000
                        ? Integer.parseInt(arrDateAttendance[2])
                        : Integer.parseInt(arrDateAttendance[2]) + 2000;

                calendar.setTime(sdf.parse(lsAttendance.get(i).getDate()));
                int final_week_year = calendar.getWeekYear() > 2000 ? calendar.getWeekYear() : calendar.getWeekYear() + 2000;
                // check if month and year from datasource is the same with params
                if (Integer.parseInt(arrDateAttendance[0]) == (numMonth)
                        && getArrYear == final_week_year) {
                    System.out.println("@final week year = " + final_week_year);

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
                        total_hours_worked += ((int) totalHours);
                    }
                    else if (calendar.get(Calendar.WEEK_OF_MONTH) == 2) {
                        weekTwo += ((int) totalHours);
                        total_hours_worked += ((int) totalHours);
                    }
                    else if (calendar.get(Calendar.WEEK_OF_MONTH) == 3) {
                        weekThree += ((int) totalHours);
                        total_hours_worked += ((int) totalHours);
                    }
                    else if (calendar.get(Calendar.WEEK_OF_MONTH) == 4) {
                        weekFour += ((int) totalHours);
                        total_hours_worked += ((int) totalHours);
                    }
                    else if (calendar.get(Calendar.WEEK_OF_MONTH) == 5) {
                        weekFive += ((int) totalHours);
                        total_hours_worked += ((int) totalHours);
                    }
                    else {
                        weekSix += ((int) totalHours);
                        total_hours_worked += ((int) totalHours);
                    }
                    eidcounter++;
                    System.out.println(eidcounter+"."+"Date Attendance = " + lsAttendance.get(i).getDate() + " Week " + calendar.getWeeksInWeekYear() + "Week of the month " + calendar.get(Calendar.WEEK_OF_MONTH));
                }
            }
            i++;
        }
        System.out.println("Hours Worked Breakdown month "+ numMonth +" for Employee number = " + eid + " and he/she called " + eidcounter + " times.");
        System.out.println("Week 1: " + weekOne);
        System.out.println("Week 2: " + weekTwo);
        System.out.println("Week 3: " + weekThree);
        System.out.println("Week 4: " + weekFour);
        System.out.println("Week 5: " + weekFive);
        System.out.println("Week 6: " + weekSix);
        System.out.println("Total Hours Worked = " + total_hours_worked);
        return new int[] {};
    }

    public static void main(String[] args) throws ParseException {
        Attendance.addAllAttendanceRecord();
        calculateWeeklyHoursWorked(10001,1,2022,Attendance.records);

        System.out.println(Attendance.records.size());
    }
}

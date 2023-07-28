package com.example.fx123;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;

public class Salary {
    private int eid;
    private int num_month;
    private int year;
    private double net_salary;
    private double[] weekly_gross_salary = new double[6];
    private double[] weekly_hours_worked = new double[6];

    public double getMonthly_gross_salary() {
        return Arrays.stream(weekly_gross_salary).sum();
    }
    public double getMonthly_net_salary() {
        return net_salary;
    }

    public double getMonthly_hours_worked() {
        return Arrays.stream(weekly_hours_worked).sum();
    }

    public double getWeekly_gross_salary(int week) {
        return weekly_gross_salary[week];
    }

    public double getWeekly_hours_worked(int week) {
        return weekly_hours_worked[week];
    }

    public Salary(int eid, int month, int year) throws ParseException {
        if (Employees.records.isEmpty())
            Employees.addAllEmployees();
        if (Attendance.records.isEmpty())
            Attendance.addAllAttendanceRecord();

        this.eid = eid;
        this.num_month = month;
        this.year = year;
        calculateWeeklySalary();
        Deduction deduction =
                new Deduction(Employees.records.get(eid - 10001).getBasic_salary(),
                        getMonthly_gross_salary());
        double increased_precision_net_salary = getMonthly_gross_salary()
                - deduction.getWithholdingTax() - deduction.TotalContribution();
        this.net_salary = increased_precision_net_salary;
    }

    void calculateWeeklySalary() throws ParseException {
        calculateWeeklyHoursWorked();

        /**
         *  [0]  week 1 gross salary
         *  [1] week 2 gross salary
         *  [2] week 3 gross salary
         *  [3] week 4 gross salary
         *  [4] week 5 gross salary
         *  [5] week 6 gross salary
         */
        double hourly_rate = Employees.records.get(eid - 10_001).getHourly_rate();
        weekly_gross_salary[0] = weekly_hours_worked[0] * hourly_rate;
        weekly_gross_salary[1] = weekly_hours_worked[1] * hourly_rate;
        weekly_gross_salary[2] = weekly_hours_worked[2] * hourly_rate;
        weekly_gross_salary[3] = weekly_hours_worked[3] * hourly_rate;
        weekly_gross_salary[4] = weekly_hours_worked[4] * hourly_rate;
        weekly_gross_salary[5] = weekly_hours_worked[5] * hourly_rate;

        System.out.println("Hourly Rate = " + hourly_rate);
        System.out.println("Gross salary eid = " + eid
                + " for month = " + this.num_month + " "
                + "Total Hours Worked = " + getMonthly_hours_worked());
    }


    void calculateWeeklyHoursWorked() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        int i = 0;
        int eidcounter = 0;
        // Hours Worked Breakdown
        // Increment one of this variable name week once and total hours worked , if
        // the attendance date matches to week of month

        while (i < Attendance.records.size()) {
            if (eid == (Attendance.records.get(i).getEmployee_number())) {
                // mm,dd,yy
                String[] arrDateAttendance =
                        Attendance.records.get(i).getDate().split("/");

                /**
                 * Not all attendance records from csv/tsv have four digits( ex: [2022 =
                 * 22] To solve the problem we can create another variable where we can
                 * make the week year into four digits.
                 */

                calendar.setTime(sdf.parse(Attendance.records.get(i).getDate()));
                int final_week_year = calendar.getWeekYear() > 2000
                        ? calendar.getWeekYear()
                        : calendar.getWeekYear() + 2000;

                if (Integer.parseInt(arrDateAttendance[0]) == (num_month)
                        && year == final_week_year) {
                    String startTimeString = Attendance.records.get(i).getTimeIn();
                    String endTimeString = Attendance.records.get(i).getTimeOut();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

                    LocalTime startTime = LocalTime.parse(startTimeString, formatter);
                    LocalTime endTime = LocalTime.parse(endTimeString, formatter);

                    Duration duration = Duration.between(startTime, endTime);

                    int hours_worked_daily = (int) duration.toHours();

                    /**
                     * Increment weekly hours worked according to it's attendance record
                     * ex attendance record:
                     *      January 2,  2022 is week 2
                     *      January 9,  2022 is week 3
                     *      January 16, 2022 is week 4
                     *      January 23, 2022 is week 5
                     *      January 30, 2022 is week 6
                     *  supposedly totalHours will increment our array.
                     *  weekly_hours_worked[0] = Week 1
                     *             ||
                     *            ||
                     *           ||
                     *          ||
                     *  weekly_hours_worked[5] = Week 6
                     */

                    switch (calendar.get(Calendar.WEEK_OF_MONTH)) {
                        // used arrow to remove break statement
                        case 1 -> this.weekly_hours_worked[0] += hours_worked_daily;
                        case 2 -> this.weekly_hours_worked[1] += hours_worked_daily;
                        case 3 -> this.weekly_hours_worked[2] += hours_worked_daily;
                        case 4 -> this.weekly_hours_worked[3] += hours_worked_daily;
                        case 5 -> this.weekly_hours_worked[4] += hours_worked_daily;
                        case 6 -> this.weekly_hours_worked[5] += hours_worked_daily;
                    }
                    eidcounter++;
                    System.out.println(eidcounter + "."
                            + "Date Attendance = " + Attendance.records.get(i).getDate()
                            + " Week " + calendar.getWeeksInWeekYear() + "Week of the month "
                            + calendar.get(Calendar.WEEK_OF_MONTH));
                }
            }
            i++;
        }
        // sum total hours worked
        System.out.println("Hours Worked Breakdown month " + num_month
                + " for Employee number = " + eid + " and he/she called " + eidcounter
                + " times.");
        System.out.println("Week 1: " + weekly_hours_worked[0]);
        System.out.println("Week 2: " + weekly_hours_worked[1]);
        System.out.println("Week 3: " + weekly_hours_worked[2]);
        System.out.println("Week 4: " + weekly_hours_worked[3]);
        System.out.println("Week 5: " + weekly_hours_worked[4]);
        System.out.println("Week 6: " + weekly_hours_worked[5]);
        System.out.println("Total Hours Worked = " + getMonthly_hours_worked());
    }
}

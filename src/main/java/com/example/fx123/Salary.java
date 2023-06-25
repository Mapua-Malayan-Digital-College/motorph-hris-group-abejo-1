package com.example.fx123;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Salary {

    private int eid;
    private int num_month;
    private int year;
    private float net_salary;
    private float sss, philhealth, pagibig, withholding_tax;
    private int [] weekly_gross_salary = new int[6];
    private int [] weekly_hours_worked = new int[6];

    public float getMonthly_gross_salary() {
        return Arrays.stream(weekly_gross_salary).sum();
    }
    public float getMonthly_net_salary () {
        return net_salary;
    }

    public float getMonthly_hours_worked() {
        return Arrays.stream(weekly_hours_worked).sum();
    }

    public int getWeekly_gross_salary(int week) {
        return weekly_gross_salary[week];
    }

    public int getWeekly_hours_worked(int week) {
        return weekly_hours_worked[week];
    }

    public Salary(int eid, int month, int year) throws ParseException {

        if (Employees.records.isEmpty()) Employees.addAllEmployees();
        if (Attendance.records.isEmpty()) Attendance.addAllAttendanceRecord();

        this.eid = eid;
        this.num_month = month;
        this.year = year;
        calculateWeeklySalary();
        Deduction deduction = new Deduction(Employees.records.get(eid - 10001).getBasic_salary(),getMonthly_gross_salary());
        this.sss = deduction.deductSSS();
        this.philhealth = deduction.deductPhilHealth();
        this.pagibig = deduction.deductPagIbig();
        this.withholding_tax = deduction.getWithholdingTax();
        this.net_salary = getMonthly_gross_salary() - deduction.getWithholdingTax();
    }


      void calculateWeeklySalary() throws ParseException {
        calculateWeeklyHoursWorked();


        /** [0] total gross salary
         *  [1] week 1 gross salary
         *  [2] week 2 gross salary
         *  [3] week 3 gross salary
         *  [4] week 4 gross salary
         *  [5] week 5 gross salary
         *  [6] week 6 gross salary
         */
        int hourly_rate = ((int) Employees.records.get(eid - 10_001).getHourly_rate());
        weekly_gross_salary[0] = weekly_hours_worked[0] * hourly_rate;
        weekly_gross_salary[1] = weekly_hours_worked[1] * hourly_rate;
        weekly_gross_salary[2] = weekly_hours_worked[2] * hourly_rate;
        weekly_gross_salary[3] = weekly_hours_worked[3] * hourly_rate;
        weekly_gross_salary[4] = weekly_hours_worked[4] * hourly_rate;
        weekly_gross_salary[5] = weekly_hours_worked[5] * hourly_rate;

        System.out.println("Hourly Rate = " + hourly_rate);
        System.out.println("Gross salary eid = "+ eid  + " for month = " + this.num_month + " " + "Total Hours Worked = " + getMonthly_hours_worked());
    }

    /**
     *
     * @return Array of integer that includes [total hours worked, week 1 hours worked, week 2 hours worked ,
     *                                          week 3 hours worked, week 4 hours worked, week 5 hours worked,
     *                                          week 6 hours worked];
     * @throws ParseException
     */
    void calculateWeeklyHoursWorked() throws ParseException {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        int i = 0;
        int eidcounter = 0;
        // Hours Worked Breakdown
        // Increment one of this variable name week once and total hours worked , if the attendance date matches to week of month

        while (i < Attendance.records.size()) {

            if (eid == (Attendance.records.get(i).getEmployee_number())) {
                // mm,dd,yy
                String [] arrDateAttendance = Attendance.records.get(i).getDate().split("/");

                /**
                 * Not all attendance records from csv/tsv have four digits( ex: [2022 = 22]
                 *  To solve the problem we can create another variable where we can make the week year into four digits.
                 */

                int getArrYear = Integer.parseInt(arrDateAttendance[2]) > 2000
                        ? Integer.parseInt(arrDateAttendance[2])
                        : Integer.parseInt(arrDateAttendance[2]) + 2000;

                calendar.setTime(sdf.parse(Attendance.records.get(i).getDate()));
                int final_week_year = calendar.getWeekYear() > 2000 ? calendar.getWeekYear() : calendar.getWeekYear() + 2000;
                // check if month and year from datasource is the same with params
                if (Integer.parseInt(arrDateAttendance[0]) == (num_month)
                        && year == final_week_year
//                        && this.year == getArrYear
                ) {

                    String startTimeString = Attendance.records.get(i).getTimeIn();
                    String endTimeString = Attendance.records.get(i).getTimeOut();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

                    LocalTime startTime = LocalTime.parse(startTimeString, formatter);
                    LocalTime endTime = LocalTime.parse(endTimeString, formatter);

                    Duration duration = Duration.between(startTime, endTime);

                    int totalHours = (int) duration.toHours();


                    if (calendar.get(Calendar.WEEK_OF_MONTH) == 1) {
                        this.weekly_hours_worked[0] += totalHours;
                    }
                    else if (calendar.get(Calendar.WEEK_OF_MONTH) == 2) {
                        this.weekly_hours_worked[1] += totalHours;
                    }
                    else if (calendar.get(Calendar.WEEK_OF_MONTH) == 3) {
                        this.weekly_hours_worked[2] += totalHours;
                    }
                    else if (calendar.get(Calendar.WEEK_OF_MONTH) == 4) {
                        this.weekly_hours_worked[3] += totalHours;

                    }
                    else if (calendar.get(Calendar.WEEK_OF_MONTH) == 5) {
                        this.weekly_hours_worked[4] += totalHours;
                    }
                    else {
                        this.weekly_hours_worked[5] += totalHours;
                    }
                    eidcounter++;
                    System.out.println(eidcounter+"."+"Date Attendance = " + Attendance.records.get(i).getDate() + " Week " + calendar.getWeeksInWeekYear() + "Week of the month " + calendar.get(Calendar.WEEK_OF_MONTH));
                }
            }
            i++;
        }
        // sum total hours worked
        System.out.println("Hours Worked Breakdown month "+ num_month +" for Employee number = " + eid + " and he/she called " + eidcounter + " times.");
        System.out.println("Week 1: " + weekly_hours_worked[0]);
        System.out.println("Week 2: " + weekly_hours_worked[1]);
        System.out.println("Week 3: " + weekly_hours_worked[2]);
        System.out.println("Week 4: " + weekly_hours_worked[3]);
        System.out.println("Week 5: " + weekly_hours_worked[4]);
        System.out.println("Week 6: " + weekly_hours_worked[5]);
        System.out.println("Total Hours Worked = " + getMonthly_hours_worked());
    }

    public static void main(String[] args) throws ParseException {
        Attendance.addAllAttendanceRecord();
        Salary salary = new Salary(10001,1,2022);
        System.out.println("Compensation = " + salary.getMonthly_gross_salary());
        System.out.println("Net sal      = " + salary.net_salary);
//        System.out.println("Hours worked Breakdown");
//        Arrays.stream(salary.weekly_hours_worked).forEach(System.out::println);
//        System.out.println("sum hours worked : " + salary.getMonthly_hours_worked());
//        System.out.println("Gross salary Breakdown");
//        System.out.println("sum gross salary : " + salary.getMonthly_gross_salary());
//        Arrays.stream(salary.weekly_gross_salary).forEach(System.out::println);
//        System.out.println("\n\n\nMonthly Gross Salary is equal to " + salary.getMonthly_gross_salary());
//        System.out.println("\n\n\nMonthly Net Salary is equal to " + salary.getMonthly_net_salary());
    }
}

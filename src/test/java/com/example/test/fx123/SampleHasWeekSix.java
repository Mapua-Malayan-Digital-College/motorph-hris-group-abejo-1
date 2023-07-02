package com.example.test.fx123;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class SampleHasWeekSix {
    public static void main(String[] args) throws ParseException {

        for (int i = 1; i < 13; i++) {
            System.out.println(i+". " + hasWeekSix(i,2022));
        }

    }

    public static boolean hasWeekSix(int month, int year) throws ParseException {

        int assumed_day = 1;
        String dateString = month+"/"+assumed_day+"/"+year;
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
        System.out.println(dateString);

        try {
            Date date = dateFormat.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int innermonth = calendar.get(Calendar.MONTH);
            int inneryear = calendar.get(Calendar.YEAR);

            calendar.set(Calendar.YEAR, inneryear);
            calendar.set(Calendar.MONTH, innermonth);
            calendar.set(Calendar.DAY_OF_MONTH, 5);

            int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            System.out.println(lastDayOfMonth);
            calendar.set(Calendar.DAY_OF_MONTH, lastDayOfMonth);

            int maxWeeksOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);

            System.out.println("The maximum number of weeks in the month is: " + maxWeeksOfMonth);

            return maxWeeksOfMonth >= 6;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}

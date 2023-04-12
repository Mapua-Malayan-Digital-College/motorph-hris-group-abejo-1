import gui.EmployeeData;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class AttendanceRecord extends EmployeeData
{
    int count;
    String [][] employeeAttendanceRecord;
    public ArrayList<String[]> storeAttendanceRecordData(String pathOfAttendanceRecord, int employeeID)
    {
        // ex: [6, 2125]
        String [] grid_length = (countRowAndCol(pathOfAttendanceRecord).split(","));
        // ex: 6
        int row_length = Integer.parseInt(grid_length[0]);
        // ex: 2125
        int col_length = Integer.parseInt(grid_length[1]);
        employeeAttendanceRecord = new String[col_length][row_length];

        return readTSV(new File(pathOfAttendanceRecord), col_length,row_length, employeeID);
    }

    public ArrayList<String[]> readTSV(File path, int col, int row, int employeeID)
    {
        String [][] Data = new String[col][row];
        ArrayList<String[]> arr = new ArrayList<>();
        try (BufferedReader TSVReader = new BufferedReader(new FileReader(path)))
        {
            String line = null;
            int outer = 0;
            while ((line = TSVReader.readLine()) != null && outer != col)
            {
                String[] lineItems = line.split("\t");

                if (lineItems[0].equals(String.valueOf(employeeID))){
                    arr.add(lineItems);
                }
            }
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    public int worked_hours(ArrayList<String []> attendance_record, int year, int month) throws ParseException {
        int i = 0;

        int worked_seconds = 0;
        int present = 0;

        int yyyy = year;
        int yy = yyyy - 2_000;
        while (i < attendance_record.size())
        {
            String str_date = attendance_record.get(i)[3];
            String [] mm_dd_yyyy = str_date.split("/");
            int rcv_mm = Integer.valueOf(mm_dd_yyyy[0]);
            int rcv_yr = Integer.valueOf(mm_dd_yyyy[2]);
            String time_in = attendance_record.get(i)[4];
            String time_out = attendance_record.get(i)[5];

            SimpleDateFormat sdf_time = new SimpleDateFormat("HH:mm");

            /**
             * DATA SET is not consistent so we need to adjust our condition by adding yy
             * because some of the row[index] of date has only two digits for year and some have four digits
             *      ex (year four digit )
             *      = 1/9/2022
             *      ex (yy two digit )
             *      = 10/14/22
             */

            if ((rcv_yr == year || rcv_yr == yy) && rcv_mm == month){

                Date IN = sdf_time.parse(time_in);
                int inGetHour = Integer.valueOf(IN.getHours());
                int inGetMinutes = Integer.valueOf(IN.getMinutes());
                Date OUT = sdf_time.parse(time_out);
                int outGetHour = OUT.getHours();
                int outGetMinutes = OUT.getMinutes();


                int diffHour = Math.abs(outGetHour - inGetHour);
                int diffMinute = Math.abs(outGetMinutes - inGetMinutes);

                //  convert to seconds and sum the seconds
                int difference =
                        (diffHour * 60 * 60) + (diffMinute * 60);
                this.count++;
                worked_seconds += difference;
                present++;
            }
            i++;

        }
        return worked_seconds;
    }

    /**
     * Testing for Attendance Record
     */
    public static void main(String[] args) throws ParseException {
        AttendanceRecord ad = new AttendanceRecord();

        String path = "F:\\motorph-hris-group-abejo-1\\motorph\\src\\main\\tsv\\MotorPH Employee Data - Attendance Record.tsv";


        int JAN = (ad.worked_hours(ad.storeAttendanceRecordData(path,10001),2022,1));
        int FEB = (ad.worked_hours(ad.storeAttendanceRecordData(path,10001),2022,2));
        int MAR = (ad.worked_hours(ad.storeAttendanceRecordData(path,10001),2022,3));
        int APR = (ad.worked_hours(ad.storeAttendanceRecordData(path,10001),2022,4));
        int MAY = (ad.worked_hours(ad.storeAttendanceRecordData(path,10001),2022,5));
        int JUN = (ad.worked_hours(ad.storeAttendanceRecordData(path,10001),2022,6));
        int JUL = (ad.worked_hours(ad.storeAttendanceRecordData(path,10001),2022,7));
        int AUG = (ad.worked_hours(ad.storeAttendanceRecordData(path,10001),2022,8));
        int SEP = (ad.worked_hours(ad.storeAttendanceRecordData(path,10001),2022,9));
        int OCT = (ad.worked_hours(ad.storeAttendanceRecordData(path,10001),2022,10));
        int NOV = (ad.worked_hours(ad.storeAttendanceRecordData(path,10001),2022,11));
        int DEC = (ad.worked_hours(ad.storeAttendanceRecordData(path,10001),2022,12));
        // based on the data set employeeID 10001 named Jose Crisostomo
        // called 87 times on the attendance record sheet
        System.out.println(ad.count==87);
        int totalWorkedHours = (JAN+FEB+MAR+APR+MAY+JUN+JUL+AUG+SEP+OCT+NOV+DEC) / 60 / 60; //  seconds/60/60 = hours
        System.out.println("Hours " + totalWorkedHours);
    }
}

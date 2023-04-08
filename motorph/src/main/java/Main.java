import gui.EmployeeData;
import gui.LocateEmployee;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Main
{
    public static void main (String[] args) throws IOException
    {
        EmployeeData employeeData = new EmployeeData ();
        LocateEmployee locateEmployee = new LocateEmployee ();

        /**
         * EMPLOYEE DATA START
         */

        employeeData.getTxtField_EmployeeDetails ().setText (
                "src/main/csv/MotorPH Employee Data - Employee Details (4).csv");
        employeeData.getTxtField_AttendanceDetails ().setText (
                "src/main/csv/MotorPH Employee Data - Attendance Record.csv");
        Image icon = Toolkit.getDefaultToolkit ().createImage (
                "https://avatars.githubusercontent.com/u/70135786?v=4");

        ActionListener actionListener = new ActionListener () {
            @Override public void actionPerformed (ActionEvent e)
            {
                switch (e.getActionCommand ())
                {
                    case ("Next"):
                        try
                        {
                            String EmployeeDetailsLength = employeeData.countRowAndCol (
                                    employeeData.getTxtField_EmployeeDetails ().getText ());
                            String AttendanceDetailsLength = employeeData.countRowAndCol (
                                    employeeData.getTxtField_AttendanceDetails ().getText ());

                            System.out.println ("@employeedetailslength   = "
                                    + EmployeeDetailsLength);

                            String[] arrEmployeeDetails = EmployeeDetailsLength.split (",");
                            int rowsEmployeeDetails
                                    = Integer.parseInt (arrEmployeeDetails[0]);

                            String[] arrAttendanceRecord
                                    = AttendanceDetailsLength.split (",");
                            int colAttendanceDetails
                                    = Integer.parseInt (arrAttendanceRecord[0]);

                            String rowEmployeeDetailsLength_isNineTen
                                    = Integer.valueOf (rowsEmployeeDetails).equals (19)

                                    ? "Correct"
                                    : "Employee Details Row Length Incorrect";
                            String rowAttendanceDetailsLength_isSix
                                    = Integer.valueOf (colAttendanceDetails).equals (6)

                                    ? "Correct"
                                    : "Attendance Details Row Length Incorrect";

                            System.out.println ();
                            System.out.println ("this is employee details");
                            System.out.println (rowEmployeeDetailsLength_isNineTen);
                            System.out.println ("this is attendance details");
                            System.out.println (rowAttendanceDetailsLength_isSix);

                            if ((rowEmployeeDetailsLength_isNineTen)
                                    .equals (rowAttendanceDetailsLength_isSix))
                            {
                                System.out.println (locateEmployee.getClass ());
                                System.out.println ("Set to visible");
                                locateEmployee.setVisible (true);
                                locateEmployee.CSVEmployeeData = employeeData.readData2 (
                                        employeeData.getTxtField_EmployeeDetails ().getText ());
                                employeeData.dispose ();
                            }
                            employeeData.dispose ();
                        }
                        catch (Exception eNext)
                        {
                            eNext.printStackTrace ();
                        }
                }
            }
        };

        employeeData.getNextButton ().addActionListener (actionListener);

        employeeData.setIconImage (icon);
        employeeData.setTitle ("Locate CSV✅");
        employeeData.setContentPane (employeeData.getPanelMain ());
        employeeData.setDefaultCloseOperation (WindowConstants.DISPOSE_ON_CLOSE);
        employeeData.pack ();
        employeeData.setLocationRelativeTo (null);
        employeeData.setVisible (true);

        /**
         * EMPLOYEE DATA END
         */

        /**
         * LOCATE EMPLOYEE START
         */

        ActionListener actionListenerLocateEmployee = new ActionListener () {
            @Override public void actionPerformed (ActionEvent e)
            {
                switch (e.getActionCommand ())
                {

                    case ("Jan"):
                        System.out.println ("January Clicked!");
                        break;
                    case ("Feb"):
                        System.out.println ("February Clicked!");
                        break;
                    case ("Mar"):
                        System.out.println ("March Clicked!");
                        break;
                    case ("Apr"):
                        System.out.println ("April Clicked!");
                        break;
                    case ("May"):
                        System.out.println ("May Clicked!");
                        break;
                    case ("Jun"):
                        System.out.println ("June Clicked!");
                        break;
                    case ("Jul"):
                        System.out.println ("July Clicked!");
                        break;
                    case ("Aug"):
                        System.out.println ("August Clicked!");
                        break;
                    case ("Sep"):
                        System.out.println ("September Clicked!");
                        break;
                    case ("Oct"):
                        System.out.println ("October Clicked!");
                        break;
                    case ("Nov"):
                        System.out.println ("November Clicked!");
                        break;
                    case ("Dec"):
                        System.out.println ("December Clicked!");
                        break;
                    case ("Clear"):
                        System.out.println ("Clear Fields");
                        locateEmployee.getTxtField_EmployeeNumber ().setText ("");
                        String[] greetings = { "Hello",
                                "Hi",
                                "Hey",
                                "Nice to see you",
                                "It's great to see you",
                                "Good to see you",
                                "Long time no see",
                                "It's been a while",
                                "Yo!",
                                "Howdy!" };
                        int min = 0, max = 9;
                        Random random = new Random ();
                        int random_number = random.nextInt (max - min + 1);
                        locateEmployee.getLbl_Number ().setText (greetings[random_number]);
                        locateEmployee.getTxtField_EmployeeNumber ().requestFocus ();
                        break;
                }
            }
        };
        locateEmployee.getBtn_Jan ().addActionListener (
                actionListenerLocateEmployee);
        locateEmployee.getBtn_Feb ().addActionListener (
                actionListenerLocateEmployee);
        locateEmployee.getBtn_Mar ().addActionListener (
                actionListenerLocateEmployee);
        locateEmployee.getBtn_Apr ().addActionListener (
                actionListenerLocateEmployee);
        locateEmployee.getBtn_May ().addActionListener (
                actionListenerLocateEmployee);
        locateEmployee.getBtn_Jun ().addActionListener (
                actionListenerLocateEmployee);
        locateEmployee.getBtn_Jul ().addActionListener (
                actionListenerLocateEmployee);
        locateEmployee.getBtn_Aug ().addActionListener (
                actionListenerLocateEmployee);
        locateEmployee.getBtn_Sep ().addActionListener (
                actionListenerLocateEmployee);
        locateEmployee.getBtn_Oct ().addActionListener (
                actionListenerLocateEmployee);
        locateEmployee.getBtn_Nov ().addActionListener (
                actionListenerLocateEmployee);
        locateEmployee.getBtn_Dec ().addActionListener (
                actionListenerLocateEmployee);
        locateEmployee.getTxtField_EmployeeNumber ().addActionListener (
                actionListenerLocateEmployee);
        locateEmployee.getBtn_Clear ().addActionListener (
                actionListenerLocateEmployee);

        FocusListener focusListener = new FocusListener () {
            @Override public void focusGained (FocusEvent e) {}

            @Override public void focusLost (FocusEvent e) {}
        };
        locateEmployee.getTxtField_EmployeeNumber ().addFocusListener (
                focusListener);

        DocumentListener documentListener = new DocumentListener () {
            @Override public void insertUpdate (DocumentEvent e)
            {
                System.out.println (
                        "The input is "
                                + locateEmployee.getTxtField_EmployeeNumber ().getText ());
                String checkNumber
                        = locateEmployee.isNumeric (
                        locateEmployee.getTxtField_EmployeeNumber ().getText ())
                        ? (locateEmployee.getTxtField_EmployeeNumber ().getText ())
                        : "Invalid input number";
                locateEmployee.getLbl_Number ().setText (checkNumber);

                int rowOfEmployeeNumber = 0;
                for (String[] outer : locateEmployee.CSVEmployeeData)
                {
                    if (outer[rowOfEmployeeNumber].equals (
                            locateEmployee.getTxtField_EmployeeNumber ().getText ()))
                    {
                        locateEmployee.getLbl_Number ().setForeground (Color.GREEN);
                        break;
                    }
                    else
                    {
                        locateEmployee.getLbl_Number ().setForeground (Color.RED);
                    }
                }
            }

            @Override public void removeUpdate (DocumentEvent e)
            {
                System.out.println (
                        "The input is "
                                + locateEmployee.getTxtField_EmployeeNumber ().getText ());
                String checkNumber
                        = locateEmployee.isNumeric (
                        locateEmployee.getTxtField_EmployeeNumber ().getText ())
                        ? (locateEmployee.getTxtField_EmployeeNumber ().getText ())
                        : "Invalid input number";
                locateEmployee.getLbl_Number ().setText (checkNumber);
            }

            @Override public void changedUpdate (DocumentEvent e)
            {
                locateEmployee.getLbl_Number ().setText (
                        locateEmployee.getTxtField_EmployeeNumber ().getText ());
            }
        };
        locateEmployee.getTxtField_EmployeeNumber ()
                .getDocument ()
                .addDocumentListener (documentListener);

        /**
         * LOCATE EMPLOYEE END
         */
    }
}

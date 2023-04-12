import gui.EmployeeData;
import gui.EmployeeProfile;
import gui.LocateEmployee;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Main
{
    public static void main (String[] args) throws IOException
    {
        EmployeeData employeeData = new EmployeeData ();
        LocateEmployee locateEmployee = new LocateEmployee ();
        EmployeeProfile employeeProfile = new EmployeeProfile();
        /**
         * EMPLOYEE DATA START
         */

        employeeData.getTxtField_EmployeeDetails ().setText (
                "src/main/tsv/MotorPH Employee Data - Employee Details.tsv");
        employeeData.getTxtField_AttendanceDetails ().setText (
                "src/main/tsv/MotorPH Employee Data - Attendance Record.tsv");
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

                            boolean isNineTen
                                    = Integer.valueOf (rowsEmployeeDetails).equals (19)

                                    ? true
                                    : false;
                            boolean isSix
                                    = Integer.valueOf (colAttendanceDetails).equals (6)

                                    ? true
                                    : false;

                            System.out.println ();
                            System.out.println ("this is employee details");
                            System.out.println (isNineTen);
                            System.out.println ("this is attendance details");
                            System.out.println (isSix);

                            if ((isNineTen) == (isSix))
                            {
                                System.out.println (locateEmployee.getClass ());
                                System.out.println ("Locate Employee set visibility to true");
                                locateEmployee.setVisible (true);
                                locateEmployee.CSVEmployeeData = EmployeeData.readTSV (new File(employeeData.getTxtField_EmployeeDetails().getText()));
                                employeeData.dispose ();
                            }
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
                boolean isAbleToLogin =
                    employeeProfile.getLbl_resultName().getText() != ""
                    || employeeProfile.getLbl_resultBirthdate().getText() != ""
                    || employeeProfile.getLbl_resultAddress().getText() != "";
                switch (e.getActionCommand ())
                {
                    case ("Jan"):
                    case ("Feb"):
                    case ("Mar"):
                    case ("Apr"):
                    case ("May"):
                    case ("Jun"):
                    case ("Jul"):
                    case ("Aug"):
                    case ("Sep"):
                    case ("Oct"):
                    case ("Nov"):
                    case ("Dec"):
                        System.out.println(e.getActionCommand() + "Clicked!");
                        if (isAbleToLogin)
                        {
                            employeeProfile.setVisible(true);
                            locateEmployee.dispose();
                        }
                        else
                            JOptionPane.showMessageDialog(locateEmployee.getPanelMain(), "Employee number "+
                            locateEmployee.getTxtField_EmployeeNumber().getText()
                            +" not found!");
                            locateEmployee.getTxtField_EmployeeNumber().requestFocus();
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
                        locateEmployee.getLbl_Number().setForeground(Color.BLACK);
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
            int rowOfEmployeeNumber = 0;
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

                for (String[] outer : locateEmployee.CSVEmployeeData)
                {
                    if (outer[rowOfEmployeeNumber].equals (
                            locateEmployee.getTxtField_EmployeeNumber ().getText ()))
                    {


                        // outer[0] = employee number
                        // outer[1] = last name
                        // outer[2] = first name
                        // outer[3] = birthday
                        // outer[4] = address
                        // outer[5] = phone number

                        Employee employee = new Employee(
                                outer[0],
                                outer[1],
                                outer[2],
                                outer[3],
                                outer[4],
                                outer[5]
                        );
                        // outer[6] = SSS
                        // outer[7] = PHILHEALTH
                        // outer[8] = TIN
                        // outer[9] = PAGIBIG
                        Bank bank = new Bank(
                                outer[6],
                                outer[7],
                                outer[8],
                                outer[9]);
                        // outer[10] = Status
                        // outer[11] = Position
                        // outer[12] = Immediate Supervisor
                        Occupation occupation = new Occupation(
                                outer[10],
                                outer[11],
                                outer[12]);
                        // outer[13] = Basic Salary
                        // outer[14] = Rice Subsidy
                        // outer[15] = Phone Allowance
                        // outer[16] = Clothing Allowance
                        // outer[17] = Gross Semi-monthly Rate
                        // outer[18] = Hourly Rate
                        Salary salary = new Salary(
                                (outer[13]),
                                (outer[14]),
                                (outer[15]),
                                (outer[16]),
                                (outer[17]),
                                (outer[18])
                        );
//                        System.out.println("Basic Salary " + outer[13]);
//                        System.out.println("Rice Subsidy " + outer[14]);
//                        System.out.println("Phone Allowance " + outer[15]);
//                        System.out.println("Clothing Allowance " + outer[16]);
//                        System.out.println("Gross Semi-Monthly Rate " + outer[17]);
//                        System.out.println("Hourly Rate" + outer[18]);

                        employeeProfile.getLbl_resultName().setText(employee.first_name + " " + employee.last_name);
                        employeeProfile.getLbl_resultAddress().setText(employee.address);
                        employeeProfile.getLbl_resultBirthdate().setText(employee.birthday);
                        locateEmployee.getLbl_Number ().setForeground (new Color(0,100,0));
                        locateEmployee.getLbl_EnterEmployeeNumber().setForeground((new Color(0,100,0)));
                        break;
                    }
                    else
                    {
                        locateEmployee.getLbl_Number ().setForeground (Color.RED);
                        locateEmployee.getLbl_EnterEmployeeNumber().setForeground(Color.BLACK);
                        employeeProfile.getLbl_resultName().setText("");
                        employeeProfile.getLbl_resultAddress().setText("");
                        employeeProfile.getLbl_resultBirthdate().setText("");
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

                for (String[] outer : locateEmployee.CSVEmployeeData)
                {
                    if (outer[rowOfEmployeeNumber].equals (
                            locateEmployee.getTxtField_EmployeeNumber ().getText ()))
                    {


                        // outer[0] = employee number
                        // outer[1] = last name
                        // outer[2] = first name
                        // outer[3] = birthday
                        // outer[4] = address
                        // outer[5] = phone number

                        Employee employee = new Employee(
                                outer[0],
                                outer[1],
                                outer[2],
                                outer[3],
                                outer[4],
                                outer[5]
                        );
                        // outer[6] = SSS
                        // outer[7] = PHILHEALTH
                        // outer[8] = TIN
                        // outer[9] = PAGIBIG
                        Bank bank = new Bank(
                                outer[6],
                                outer[7],
                                outer[8],
                                outer[9]);
                        // outer[10] = Status
                        // outer[11] = Position
                        // outer[12] = Immediate Supervisor
                        Occupation occupation = new Occupation(
                                outer[10],
                                outer[11],
                                outer[12]);
                        // outer[13] = Basic Salary
                        // outer[14] = Rice Subsidy
                        // outer[15] = Phone Allowance
                        // outer[16] = Clothing Allowance
                        // outer[17] = Gross Semi-monthly Rate
                        // outer[18] = Hourly Rate
                        Salary salary = new Salary(
                                (outer[13]),
                                (outer[14]),
                                (outer[15]),
                                (outer[16]),
                                (outer[17]),
                                (outer[18])
                        );
//                        System.out.println("Basic Salary " + outer[13]);
//                        System.out.println("Rice Subsidy " + outer[14]);
//                        System.out.println("Phone Allowance " + outer[15]);
//                        System.out.println("Clothing Allowance " + outer[16]);
//                        System.out.println("Gross Semi-Monthly Rate " + outer[17]);
//                        System.out.println("Hourly Rate" + outer[18]);

                        employeeProfile.getLbl_resultName().setText(employee.first_name + " " + employee.last_name);
                        employeeProfile.getLbl_resultAddress().setText(employee.address);
                        employeeProfile.getLbl_resultBirthdate().setText(employee.birthday);
                        locateEmployee.getLbl_Number ().setForeground (new Color(0,100,0));
                        locateEmployee.getLbl_EnterEmployeeNumber().setForeground((new Color(0,100,0)));
                        break;
                    }
                    else
                    {
                        locateEmployee.getLbl_Number ().setForeground (Color.RED);
                        locateEmployee.getLbl_EnterEmployeeNumber().setForeground(Color.BLACK);
                        employeeProfile.getLbl_resultName().setText("");
                        employeeProfile.getLbl_resultAddress().setText("");
                        employeeProfile.getLbl_resultBirthdate().setText("");
                    }
                }
            }

            @Override public void changedUpdate (DocumentEvent e)
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

                for (String[] outer : locateEmployee.CSVEmployeeData)
                {
                    if (outer[rowOfEmployeeNumber].equals (
                            locateEmployee.getTxtField_EmployeeNumber ().getText ()))
                    {


                        // outer[0] = employee number
                        // outer[1] = last name
                        // outer[2] = first name
                        // outer[3] = birthday
                        // outer[4] = address
                        // outer[5] = phone number

                        Employee employee = new Employee(
                                outer[0],
                                outer[1],
                                outer[2],
                                outer[3],
                                outer[4],
                                outer[5]
                        );
                        // outer[6] = SSS
                        // outer[7] = PHILHEALTH
                        // outer[8] = TIN
                        // outer[9] = PAGIBIG
                        Bank bank = new Bank(
                                outer[6],
                                outer[7],
                                outer[8],
                                outer[9]);
                        // outer[10] = Status
                        // outer[11] = Position
                        // outer[12] = Immediate Supervisor
                        Occupation occupation = new Occupation(
                                outer[10],
                                outer[11],
                                outer[12]);
                        // outer[13] = Basic Salary
                        // outer[14] = Rice Subsidy
                        // outer[15] = Phone Allowance
                        // outer[16] = Clothing Allowance
                        // outer[17] = Gross Semi-monthly Rate
                        // outer[18] = Hourly Rate
                        Salary salary = new Salary(
                                (outer[13]),
                                (outer[14]),
                                (outer[15]),
                                (outer[16]),
                                (outer[17]),
                                (outer[18])
                        );
//                        System.out.println("Basic Salary " + outer[13]);
//                        System.out.println("Rice Subsidy " + outer[14]);
//                        System.out.println("Phone Allowance " + outer[15]);
//                        System.out.println("Clothing Allowance " + outer[16]);
//                        System.out.println("Gross Semi-Monthly Rate " + outer[17]);
//                        System.out.println("Hourly Rate" + outer[18]);

                        employeeProfile.getLbl_resultName().setText(employee.first_name + " " + employee.last_name);
                        employeeProfile.getLbl_resultAddress().setText(employee.address);
                        employeeProfile.getLbl_resultBirthdate().setText(employee.birthday);
                        locateEmployee.getLbl_Number ().setForeground (new Color(0,100,0));
                        locateEmployee.getLbl_EnterEmployeeNumber().setForeground((new Color(0,100,0)));
                        break;
                    }
                    else
                    {
                        locateEmployee.getLbl_Number ().setForeground (Color.RED);
                        locateEmployee.getLbl_EnterEmployeeNumber().setForeground(Color.BLACK);
                        employeeProfile.getLbl_resultName().setText("");
                        employeeProfile.getLbl_resultAddress().setText("");
                        employeeProfile.getLbl_resultBirthdate().setText("");

                    }
                }
            }
        };
        locateEmployee.getTxtField_EmployeeNumber ()
                .getDocument ()
                .addDocumentListener (documentListener);

        /**
         * LOCATE EMPLOYEE END
         */


        /**
         * EMPLOYEE PROFILE START
         */


        ActionListener actionListenerEmployeeProfile = new ActionListener () {
            @Override public void actionPerformed (ActionEvent e){
                switch (e.getActionCommand()) {
                    case "Return" -> {
                        locateEmployee.setVisible(true);
                        locateEmployee.getTxtField_EmployeeNumber().setText("");
                        locateEmployee.getLbl_Number().setText("Below");
                        locateEmployee.getLbl_Number().setForeground(Color.BLACK);
                        locateEmployee.getTxtField_EmployeeNumber().requestFocus();
                        employeeProfile.getLbl_resultName().setText("");
                        employeeProfile.getLbl_resultAddress().setText("");
                        employeeProfile.getLbl_resultBirthdate().setText("");
                        employeeProfile.dispose();
                    }
                    case "btn_WorkedHours" -> System.out.println("Worked Hours Clicked!");
                    case "btn_GrossSalary" -> System.out.println("Gross Salary Clicked!");
                    case "btn_NetSalary" -> System.out.println("Net Salary Clicked!");
                }
            }
        };

        employeeProfile.getBtn_Return().addActionListener(actionListenerEmployeeProfile);
        employeeProfile.getBtn_WorkedHours().addActionListener(actionListenerEmployeeProfile);
        employeeProfile.getBtn_GrossSalary().addActionListener(actionListenerEmployeeProfile);
        employeeProfile.getBtn_NetSalary().addActionListener(actionListenerEmployeeProfile);



        /**
         * EMPLOYEE PROFILE END
         */

    }
}

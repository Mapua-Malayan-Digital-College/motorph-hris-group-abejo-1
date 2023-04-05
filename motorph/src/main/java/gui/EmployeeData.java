package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

public class EmployeeData<E> extends JFrame
{
    private JButton nextButton;
    private JPanel panelMain;
    private JTextField txtField_EmployeeDetails;
    private JTextField txtField_AttendanceDetails;
    private JButton
    getNextButton ()
    {
        return nextButton;
    }
    private ArrayList<E> DATA_EMPLOYEE_DETAILS;
    private ArrayList<E> DATA_ATTENDANCE_DETAILS;

    public void
    setNextButton (JButton nextButton)
    {
        this.nextButton = nextButton;
    }

    public JPanel
    getPanelMain ()
    {
        return panelMain;
    }

    public void
    setPanelMain (JPanel panelMain)
    {
        this.panelMain = panelMain;
    }

    public JTextField
    getTxtField_EmployeeDetails ()
    {
        return txtField_EmployeeDetails;
    }

    public void
    setTxtField_EmployeeDetails (JTextField txtField_EmployeeDetails)
    {
        this.txtField_EmployeeDetails = txtField_EmployeeDetails;
    }

    public JTextField
    getTxtField_AttendanceDetails ()
    {
        return txtField_AttendanceDetails;
    }

    public void
    setTxtField_AttendanceDetails (JTextField txtField_AttendanceDetails)
    {
        this.txtField_AttendanceDetails = txtField_AttendanceDetails;
    }

    public ArrayList<E>
    getDATA_EMPLOYEE_DETAILS ()
    {
        return DATA_EMPLOYEE_DETAILS;
    }

    public void
    setDATA_EMPLOYEE_DETAILS (ArrayList<E> DATA_EMPLOYEE_DETAILS)
    {
        this.DATA_EMPLOYEE_DETAILS = DATA_EMPLOYEE_DETAILS;
    }

    public ArrayList<E>
    getDATA_ATTENDANCE_DETAILS ()
    {
        return DATA_ATTENDANCE_DETAILS;
    }

    public void
    setDATA_ATTENDANCE_DETAILS (ArrayList<E> DATA_ATTENDANCE_DETAILS)
    {
        this.DATA_ATTENDANCE_DETAILS = DATA_ATTENDANCE_DETAILS;
    }


    EmployeeData()
    {
        txtField_EmployeeDetails.setText (
                "src/main/csv/MotorPH Employee Data - Employee Details.csv");
        txtField_AttendanceDetails.setText (
                "src/main/csv/MotorPH Employee Data - Attendance Record.csv");
        Image icon = Toolkit.getDefaultToolkit ().createImage (
                "https://avatars.githubusercontent.com/u/70135786?v=4");

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (("Next").equals(e.getActionCommand()))
                {
                    String EmployeeDetailsLength = countRowAndCol(getTxtField_EmployeeDetails().getText());
                    String AttendanceDetailsLength = countRowAndCol(getTxtField_AttendanceDetails().getText());

                    System.out.println("@employeedetailslength   = " + EmployeeDetailsLength);
                    System.out.println("@attendancedetailslength = " + AttendanceDetailsLength);


                    String [] arrEmployeeDetails = EmployeeDetailsLength.split(",");
                    int rowsEmployeeDetails = Integer.parseInt(arrEmployeeDetails[0]);

                    String [] arrAttendanceRecord = AttendanceDetailsLength.split(",");
                    int colAttendanceDetails = Integer.parseInt(arrAttendanceRecord[0]);

                    String rowEmployeeDetailsLength_isNineTen
                            = Integer.valueOf(rowsEmployeeDetails).equals(19)

                            ? "Employee Details Row Length Correct!"
                            : "Employee Details Row Length Incorrect";
                    String rowAttendanceDetailsLength_isSix
                            = Integer.valueOf(colAttendanceDetails).equals(6)

                            ? "Attendance Details Row Length Correct! "
                            : "Attendance Details Row Length Incorrect";

                    System.out.println(rowEmployeeDetailsLength_isNineTen);
                    System.out.println(rowAttendanceDetailsLength_isSix);
                }
            }
        };

        getNextButton().addActionListener(actionListener);







        setIconImage (icon);
        setTitle ("Locate CSV✅");
        setContentPane (getPanelMain ());
        setDefaultCloseOperation (DISPOSE_ON_CLOSE);
        pack ();
        setLocationRelativeTo (null);
        setVisible (true);



    }
    public static String
    countRowAndCol (String path)
    {

        String csvFile = path;
        int rowCount = 0;
        int columnCount = 0;

        try (BufferedReader br = new BufferedReader (new FileReader (csvFile)))
        {
            String line;
            while ((line = br.readLine ()) != null)
            {
                rowCount++;
                String[] columns = line.split (",");
                if (rowCount == 1)
                {
                    // Count the number of columns in the header row
                    columnCount = columns.length;
                }
                else if (columns.length > columnCount)
                {
                    // Update the column count if a row has more columns than the
                    // header row
                    columnCount = columns.length;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace ();
        }
        System.out.println ("Total rows: " + rowCount);
        System.out.println ("Total columns: " + columnCount);
        return rowCount + "," + columnCount;
    }
}

/*
 *
 *  Testing
*/
//class TEST_Employee_java {
//        public static void main(String[] args) throws IOException {
//            String myName = "Jomari S. Abejo";
//            Arrays.stream(myName.split(" ")).forEach(System.out::println);
//            System.out.println(Arrays.stream(myName.split(" ")).count());
//
//
//            String myname = "Jomari,Sarmiento,Abejo";
//            String [] myArrName = myname.split(",");
//            System.out.println(Arrays.toString(myArrName));
//
//
//            String resultt2 = Employee.countRowAndCol("src/main/csv/MotorPH
//            Employee Data - Attendance Record.csv"); System.out.println(resultt2); String []
//            row_col = resultt2.split(","); System.out.println("The row of the csv is = " +
//            row_col[0]); System.out.println("The col of the csv is = " + row_col[1]);
//
//
//            String name = "Employee #,Last Name,First Name,Birthday,Address,Phone Number,SSS #,Philhealth #,TIN #,Pag-ibig #,Status,Position,Immediate Supervisor,Basic Salary,Rice Subsidy,Phone Allowance,Clothing Allowance,Gross Semi-monthly Rate,Hourly Rate";
//            String [] edarr = name.split(",");
//            Arrays.stream(edarr).forEach(System.out::println);
//            System.out.println("THE LENGTH IS = " + edarr.length);
//
//            EmployeeData ed = new EmployeeData();
//
//        }
//    }
//*/
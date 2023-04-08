package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

public class EmployeeData extends JFrame
{
    private JButton nextButton;
    private JPanel panelMain;
    private JTextField txtField_EmployeeDetails;
    private JTextField txtField_AttendanceDetails;

    public JButton
    getNextButton()
    {
        return nextButton;
    }

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


    public EmployeeData()
    {
    }

    public String
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
                String[] columns = line.split (",");

                if (rowCount == 0)
                {
                    rowCount = columns.length;
                }
                else
                {
                    columnCount++;
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

    /*public static ArrayList<String[]> readData(String path) throws IOException {
        int count = 0;
        String file = path;
        ArrayList<String[]> content = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                content.add(line.split(","));
            }
        } catch (FileNotFoundException e) {
            //Some error logging
        }
        return content;
    }*/
    public  ArrayList<String[]> readData2(String path) throws IOException {
        int count = 0;
        String file = path;
        ArrayList<String[]> content = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (count == 0){
                    count++;
                    continue;
                }
                String [] col = (line.split(","));
                content.add(col);
            }
        } catch (FileNotFoundException e) {
            //Some error logging
        }
        return content;
    }

}

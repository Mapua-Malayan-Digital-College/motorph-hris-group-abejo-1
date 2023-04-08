package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LocateEmployee extends EmployeeData
{
    private JTextField TxtField_EmployeeNumber;
    private JButton btn_Feb;
    private JButton btn_Jan;
    private JPanel panelMain;
    private JLabel Lbl_EnterEmployee;
    private JButton btn_Mar;
    private JButton btn_Apr;
    private JButton btn_May;
    private JButton btn_Jun;
    private JButton btn_Jul;
    private JButton btn_Aug;
    private JButton btn_Sep;
    private JButton btn_Oct;
    private JButton btn_Nov;
    private JButton btn_Dec;
    private JButton btn_Clear;
    private JLabel lbl_Number;

    public JTextField
    getTxtField_EmployeeNumber ()
    {
        return TxtField_EmployeeNumber;
    }

    public void
    setTxtField_EmployeeNumber (JTextField txtField_EmployeeNumber)
    {
        TxtField_EmployeeNumber = txtField_EmployeeNumber;
    }

    public JButton
    getBtn_Feb ()
    {
        return btn_Feb;
    }

    public void
    setBtn_Feb (JButton btn_Feb)
    {
        this.btn_Feb = btn_Feb;
    }

    public JButton
    getBtn_Jan ()
    {
        return btn_Jan;
    }

    public void
    setBtn_Jan (JButton btn_Jan)
    {
        this.btn_Jan = btn_Jan;
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

    public JLabel
    getLbl_EnterEmployeeNumber ()
    {
        return Lbl_EnterEmployee;
    }

    public void
    setLbl_EnterEmployeeNumber (JLabel lbl_EnterEmployeeNumber)
    {
        Lbl_EnterEmployee = lbl_EnterEmployeeNumber;
    }

    public JButton
    getBtn_Mar ()
    {
        return btn_Mar;
    }

    public void
    setBtn_Mar (JButton btn_Mar)
    {
        this.btn_Mar = btn_Mar;
    }

    public JButton
    getBtn_Apr ()
    {
        return btn_Apr;
    }

    public void
    setBtn_Apr (JButton btn_Apr)
    {
        this.btn_Apr = btn_Apr;
    }

    public JButton
    getBtn_May ()
    {
        return btn_May;
    }

    public void
    setBtn_May (JButton btn_May)
    {
        this.btn_May = btn_May;
    }

    public JButton
    getBtn_Jun ()
    {
        return btn_Jun;
    }

    public void
    setBtn_Jun (JButton btn_Jun)
    {
        this.btn_Jun = btn_Jun;
    }

    public JButton
    getBtn_Jul ()
    {
        return btn_Jul;
    }

    public void
    setBtn_Jul (JButton btn_Jul)
    {
        this.btn_Jul = btn_Jul;
    }

    public JButton
    getBtn_Aug ()
    {
        return btn_Aug;
    }

    public void
    setBtn_Aug (JButton btn_Aug)
    {
        this.btn_Aug = btn_Aug;
    }

    public JButton
    getBtn_Sep ()
    {
        return btn_Sep;
    }

    public void
    setBtn_Sep (JButton btn_Sep)
    {
        this.btn_Sep = btn_Sep;
    }

    public JButton
    getBtn_Oct ()
    {
        return btn_Oct;
    }

    public void
    setBtn_Oct (JButton btn_Oct)
    {
        this.btn_Oct = btn_Oct;
    }

    public JButton
    getBtn_Nov ()
    {
        return btn_Nov;
    }

    public void
    setBtn_Nov (JButton btn_Nov)
    {
        this.btn_Nov = btn_Nov;
    }

    public JButton
    getBtn_Dec ()
    {
        return btn_Dec;
    }

    public void
    setBtn_Dec (JButton btn_Dec)
    {
        this.btn_Dec = btn_Dec;
    }

    public JButton getBtn_Clear() {
        return btn_Clear;
    }

    public void setBtn_Clear(JButton btn_Clear) {
        this.btn_Clear = btn_Clear;
    }

    public JLabel getLbl_Number() {
        return lbl_Number;
    }

    public void setLbl_Number(JLabel lbl_Number) {
        this.lbl_Number = lbl_Number;
    }

    public ArrayList<String[]> CSVEmployeeData;
    public LocateEmployee() {
        getTxtField_EmployeeNumber().requestFocus();
        setContentPane (getPanelMain ());
        setDefaultCloseOperation (DISPOSE_ON_CLOSE);
        pack ();
        setLocationRelativeTo (null);
    }

    /*  Check if the input string is numeric
     *  @params Whether or not the input is numerical will be tested
     *  @return ? true : false
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
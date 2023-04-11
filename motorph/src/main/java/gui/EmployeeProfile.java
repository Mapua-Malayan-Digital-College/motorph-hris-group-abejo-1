package gui;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class EmployeeProfile extends JFrame{

    private JPanel panelMain;
    private JButton btn_Return;
    private JButton btn_WorkedHours;
    private JTextField txtField_result_WorkedHours;
    private JTextField txtField_result_GrossSalary;
    private JTextField txtField_result_NetSalary;
    private JLabel lbl_resultName;
    private JLabel lbl_resultBirthdate;
    private JLabel lbl_resultAddress;
    private JButton btn_GrossSalary;
    private JButton btn_NetSalary;

    public JPanel getPanelMain() {
        return panelMain;
    }

    public void setPanelMain(JPanel panelMain) {
        this.panelMain = panelMain;
    }

    public JButton getBtn_Return() {
        return btn_Return;
    }

    public void setBtn_Return(JButton btn_Return) {
        this.btn_Return = btn_Return;
    }

    public JButton getBtn_WorkedHours() {
        return btn_WorkedHours;
    }

    public void setBtn_WorkedHours(JButton btn_WorkedHours) {
        this.btn_WorkedHours = btn_WorkedHours;
    }

    public JTextField getTxtField_result_WorkedHours() {
        return txtField_result_WorkedHours;
    }

    public void setTxtField_result_WorkedHours(JTextField txtField_result_WorkedHours) {
        this.txtField_result_WorkedHours = txtField_result_WorkedHours;
    }

    public JTextField getTxtField_result_GrossSalary() {
        return txtField_result_GrossSalary;
    }

    public void setTxtField_result_GrossSalary(JTextField txtField_result_GrossSalary) {
        this.txtField_result_GrossSalary = txtField_result_GrossSalary;
    }

    public JTextField getTxtField_result_NetSalary() {
        return txtField_result_NetSalary;
    }

    public void setTxtField_result_NetSalary(JTextField txtField_result_NetSalary) {
        this.txtField_result_NetSalary = txtField_result_NetSalary;
    }

    public JLabel getLbl_resultName() {
        return lbl_resultName;
    }

    public void setLbl_resultName(JLabel lbl_resultName) {
        this.lbl_resultName = lbl_resultName;
    }

    public JLabel getLbl_resultBirthdate() {
        return lbl_resultBirthdate;
    }

    public void setLbl_resultBirthdate(JLabel lbl_resultBirthdate) {
        this.lbl_resultBirthdate = lbl_resultBirthdate;
    }

    public JLabel getLbl_resultAddress() {
        return lbl_resultAddress;
    }

    public void setLbl_resultAddress(JLabel lbl_resultAddress) {
        this.lbl_resultAddress = lbl_resultAddress;
    }

    public JButton getBtn_GrossSalary() {
        return btn_GrossSalary;
    }

    public void setBtn_GrossSalary(JButton btn_GrossSalary) {
        this.btn_GrossSalary = btn_GrossSalary;
    }

    public JButton getBtn_NetSalary() {
        return btn_NetSalary;
    }

    public void setBtn_NetSalary(JButton btn_NetSalary) {
        this.btn_NetSalary = btn_NetSalary;
    }

    public EmployeeProfile() {

        setTitle ("Locate CSV✅");
        setContentPane (getPanelMain ());
        setDefaultCloseOperation (WindowConstants.DISPOSE_ON_CLOSE);
        pack ();
        setLocationRelativeTo (null);
        setVisible (false);
    }
}
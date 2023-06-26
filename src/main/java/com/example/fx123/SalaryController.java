package com.example.fx123;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SalaryController implements Runnable {
    @FXML private ComboBox<String> cb_select_MM;

    @FXML private TextField txtField_select_YY;

    @FXML private Label lbl_basic_salary;

    @FXML private Label lbl_fullname;

    @FXML private Label lbl_hourly_rate;

    @FXML private Label lbl_net_salary;

    @FXML private Label lbl_pagibig;

    @FXML private Label lbl_philhealth;

    @FXML private Label lbl_sss;

    @FXML private Label lbl_total_gross_salary;

    @FXML private Label lbl_total_gross_salary1;

    @FXML private Label lbl_total_hours_worked;

    @FXML private Label lbl_w1_gross_salary;

    @FXML private Label lbl_w1_hours_worked;

    @FXML private Label lbl_w2_gross_salary;

    @FXML private Label lbl_w2_hours_worked;

    @FXML private Label lbl_w3_gross_salary;

    @FXML private Label lbl_w3_hours_worked;

    @FXML private Label lbl_w4_gross_salary;

    @FXML private Label lbl_w4_hours_worked;

    @FXML private Label lbl_w5_gross_salary;

    @FXML private Label lbl_w5_hours_worked;

    @FXML private Label lbl_w6_gross_salary;

    @FXML private Label lbl_w6_hours_worked;

    @FXML private Label lbl_witholding_tax;

    @FXML private TextField txtField_eid;

    @FXML
    void onAttendanceClicked(ActionEvent event) {
        try {
            SceneController.attendanceScene(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onEmployeeClicked(ActionEvent event) {
        try {
            SceneController.employeeScene(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onLeavesClicked(ActionEvent event) {
        try {
            SceneController.leavesScene(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onLogoutClicked(ActionEvent event) {
        try {
            SceneController.loginScene(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onSalaryClicked(ActionEvent event) {
        System.out.println("Salary Clicked");
    }

    @Override
    public void run() {
        System.out.println("Salary view running...");
        /**
         * Add combo box items for months
         */
        cb_select_MM.getItems().addAll("January", "February", "March", "April",
                "May", "June", "July", "August", "September", "October", "November",
                "December");
    }

    public void initialize() {
        run();
    }

    private int monthWordToInt() {
        int searched_month = 0;
        switch (cb_select_MM.getValue()) {
            case "January":
                searched_month = 1;
                break;
            case "February":
                searched_month = 2;
                break;
            case "March":
                searched_month = 3;
                break;
            case "April":
                searched_month = 4;
                break;
            case "May":
                searched_month = 5;
                break;
            case "June":
                searched_month = 6;
                break;
            case "July":
                searched_month = 7;
                break;
            case "August":
                searched_month = 8;
                break;
            case "September":
                searched_month = 9;
                break;
            case "October":
                searched_month = 10;
                break;
            case "November":
                searched_month = 11;
                break;
            case "December":
                searched_month = 12;
                break;
        }
        return searched_month;
    }
    public void search_employee(ActionEvent actionEvent) throws ParseException {
        System.out.println(
                "Starting ... Attendance Record Size = " + Attendance.records.size());
        int employee_number = Integer.parseInt(txtField_eid.getText());
        int search_year = Integer.parseInt(txtField_select_YY.getText());

        /**
         * Initialized decimal formatter
         */
        DecimalFormat decimalFormat = new DecimalFormat("#.####");

        /**
         * Compute Salary
         */

        Salary getSalary =
                new Salary(employee_number, monthWordToInt(), search_year);

        /**
         * Set Employee Details
         */
        lbl_fullname.setText(
                Employees.records.get(employee_number - 10001).getF_name() + " "
                        + Employees.records.get(employee_number - 10001).getL_name());
        lbl_hourly_rate.setText(String.valueOf(
                Employees.records.get(employee_number - 10001).getHourly_rate()));
        lbl_basic_salary.setText(String.valueOf(
                Employees.records.get(employee_number - 10001).getBasic_salary()));

        /**
         * Set Hours Worked Breakdown
         */
        lbl_w1_hours_worked.setText(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_hours_worked(0))));
        lbl_w2_hours_worked.setText(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_hours_worked(1))));
        lbl_w3_hours_worked.setText(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_hours_worked(2))));
        lbl_w4_hours_worked.setText(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_hours_worked(3))));
        lbl_w5_hours_worked.setText(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_hours_worked(4))));
        lbl_w6_hours_worked.setText(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_hours_worked(5))));

        /**
         * Set Gross Salary Breakdown
         */
        lbl_w1_gross_salary.setText(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_gross_salary(0))));
        lbl_w2_gross_salary.setText(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_gross_salary(1))));
        lbl_w3_gross_salary.setText(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_gross_salary(2))));
        lbl_w4_gross_salary.setText(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_gross_salary(3))));
        lbl_w5_gross_salary.setText(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_gross_salary(4))));
        lbl_w6_gross_salary.setText(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_gross_salary(5))));

        /**
         * Set total hours worked and gross salary
         */
        lbl_total_hours_worked.setText(String.valueOf(
                decimalFormat.format(getSalary.getMonthly_hours_worked())));
        lbl_total_gross_salary.setText(String.valueOf(
                decimalFormat.format(getSalary.getMonthly_gross_salary())));
        lbl_total_gross_salary1.setText(String.valueOf(
                decimalFormat.format(getSalary.getMonthly_gross_salary())));
        double gross_salary = getSalary.getMonthly_hours_worked()
                * Float.parseFloat(lbl_hourly_rate.getText());
        System.out.println(
                "Get basic salary   = " + Integer.parseInt(lbl_basic_salary.getText()));
        System.out.println("Get gross salary   = " + gross_salary);
        Deduction getDeduction = new Deduction(
                Integer.parseInt(lbl_basic_salary.getText()), gross_salary);
        System.out.println(
                "Get ded Philhealth = " + getDeduction.deductPhilHealth());
        /**
         * Set Deduction Breakdown
         */
        lbl_sss.setText(
                String.valueOf(decimalFormat.format(getDeduction.deductSSS())));
        lbl_pagibig.setText(
                String.valueOf(decimalFormat.format(getDeduction.deductPagIbig())));
        lbl_philhealth.setText(
                String.valueOf(decimalFormat.format(getDeduction.deductPhilHealth())));
        lbl_witholding_tax.setText(
                String.valueOf(decimalFormat.format(getDeduction.getWithholdingTax())));
        /**
         * Set Net Salary
         */
        lbl_net_salary.setText(String.valueOf(
                decimalFormat.format(getSalary.getMonthly_net_salary())));
        System.out.println(
                "Finishing ... Attendance Record Size = " + Attendance.records.size());
        /**
         * Check net salary precision
         *
         */
        System.out.println("⬇️⬇️⬇️This is the gross salary");
        System.out.println(getSalary.getMonthly_gross_salary());
        System.out.println("⬇️⬇️⬇️This is the deduction");
        System.out.println(getDeduction.getWithholdingTax());
        System.out.println("⬇️⬇️⬇️This is the net salary");
        System.out.println(getSalary.getMonthly_net_salary());
    }

    public void onGenerateClicked(ActionEvent actionEvent) {
        try {
            search_employee(actionEvent);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

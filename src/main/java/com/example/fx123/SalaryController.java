package com.example.fx123;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    @FXML private Label finalweek6lbl;

    @FXML private Label lbl_tax; // taxable income

    @FXML private Label lbl_total_deductions;

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
        lbl_w1_hours_worked.setText(CsvUtils.makeStringLengthToTwenty(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_hours_worked(0)))));
        lbl_w2_hours_worked.setText(CsvUtils.makeStringLengthToTwenty(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_hours_worked(1)))));
        lbl_w3_hours_worked.setText(CsvUtils.makeStringLengthToTwenty(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_hours_worked(2)))));
        lbl_w4_hours_worked.setText(CsvUtils.makeStringLengthToTwenty(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_hours_worked(3)))));
        lbl_w5_hours_worked.setText(CsvUtils.makeStringLengthToTwenty(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_hours_worked(4)))));
        lbl_w6_hours_worked.setText(CsvUtils.makeStringLengthToTwenty(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_hours_worked(5)))));

        /**
         * Set Gross Salary Breakdown
         */
        lbl_w1_gross_salary.setText(CsvUtils.makeStringLengthToTwenty(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_gross_salary(0)))));
        lbl_w2_gross_salary.setText(CsvUtils.makeStringLengthToTwenty(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_gross_salary(1)))));
        lbl_w3_gross_salary.setText(CsvUtils.makeStringLengthToTwenty(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_gross_salary(2)))));
        lbl_w4_gross_salary.setText(CsvUtils.makeStringLengthToTwenty(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_gross_salary(3)))));
        lbl_w5_gross_salary.setText(CsvUtils.makeStringLengthToTwenty(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_gross_salary(4)))));
        lbl_w6_gross_salary.setText(CsvUtils.makeStringLengthToTwenty(String.valueOf(
                decimalFormat.format(getSalary.getWeekly_gross_salary(5)))));

        /**
         * Set total hours worked and gross salary
         */
        lbl_total_hours_worked.setText(CsvUtils.makeStringLengthToTwenty(String.valueOf(
                decimalFormat.format(getSalary.getMonthly_hours_worked()))));
        lbl_total_gross_salary.setText(CsvUtils.makeStringLengthToTwenty(String.valueOf(
                decimalFormat.format(getSalary.getMonthly_gross_salary()))));
        lbl_total_gross_salary1.setText(CsvUtils.makeStringLengthToTwenty(String.valueOf(
                decimalFormat.format(getSalary.getMonthly_gross_salary()))));
        double gross_salary = getSalary.getMonthly_hours_worked()
                * Float.parseFloat(lbl_hourly_rate.getText());
        System.out.println(
                "Get basic salary   = " + Integer.parseInt(lbl_basic_salary.getText()));
        System.out.println("Get gross salary   = " + gross_salary);
        Deduction getDeduction = new Deduction(
                Integer.parseInt(lbl_basic_salary.getText()), gross_salary);

        /**
         * Set Deduction Breakdown
         */
        lbl_sss.setText(
                String.valueOf(decimalFormat.format(getDeduction.deductSSS())));
        lbl_pagibig.setText(
                String.valueOf(decimalFormat.format(getDeduction.deductPagIbig())));
        lbl_philhealth.setText(
                String.valueOf(decimalFormat.format(getDeduction.deductPhilHealth())));

        lbl_total_deductions.setText("-"+String.valueOf(decimalFormat.format(getDeduction.TotalContribution())));

        /**
         * Set value taxable income
         */

        lbl_tax.setText(String.valueOf(decimalFormat.format(getDeduction.getTaxableIncome())));

        /**
         * Set Value for tax
         */

        lbl_witholding_tax.setText("-"+CsvUtils.makeStringLengthToTwenty(String.valueOf(decimalFormat.format(getDeduction.getWithholdingTax()))));

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

        if (hasWeekSix()) {
            finalweek6lbl.setVisible(true);
            lbl_w6_gross_salary.setVisible(true);
            lbl_w6_hours_worked.setVisible(true);
        }
        else {
            finalweek6lbl.setVisible(false);
            lbl_w6_gross_salary.setVisible(false);
            lbl_w6_hours_worked.setVisible(false);
        }
    }

    public void onGenerateClicked(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            //  check if textfields employee_number and year only contains numbers.
            if (txtField_eid.getText().matches("\\d*")
                    && txtField_select_YY.getText().matches("\\d*")) {
                boolean is_employee_found = false;
                // check if employee number exists
                for (int i = 0; i < Employees.records.size(); i++) {
                    // continue generating employee salary
                    if (Employees.records.get(i).getId().equals(txtField_eid.getText())) {
                        is_employee_found = true;
                        search_employee(actionEvent);
                        // check if the net salary is negative, if true, the employee is
                        // expected to have blank attendance record.
                        if (Double.parseDouble(lbl_net_salary.getText()) < 0) {
                            // update labels to zero since the employee doesn't have any
                            // attendance record
                            lbl_philhealth.setText("0");
                            lbl_pagibig.setText("0");
                            lbl_sss.setText("0");
                            lbl_witholding_tax.setText("0");
                            lbl_total_deductions.setText("0");
                            lbl_tax.setText("0");
                            lbl_net_salary.setText("No attendance record");
                            alert.setTitle("Employee " + txtField_eid.getText()
                                    + " doesn't have any attendance");
                            alert.setContentText("Blank attendance record for month "
                                    + cb_select_MM.getValue() + " year "
                                    + txtField_select_YY.getText());
                            alert.showAndWait();
                            txtField_select_YY.requestFocus();
                            break;
                        }
                        break;
                    }
                }
                if (!is_employee_found) {
                    alert.setTitle("Employee number not found");
                    alert.setContentText(
                            "Employee number " + txtField_eid.getText() + " not found.");
                    alert.showAndWait();
                    txtField_eid.requestFocus();
                }
            } else {
                alert.setTitle("Invalid value text field");
                alert.setContentText(
                        "Please check your text field, it might have invalid characters");
                alert.showAndWait();
            }
        }

        catch (NumberFormatException numberFormatException) {
                alert.setTitle("NumberFormatException");
                alert.setContentText(numberFormatException.getMessage());
                alert.show();
                txtField_select_YY.requestFocus();
            } catch (ParseException e) {
            alert.setTitle("ParseException");
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    /**
     * Check month and year if the month has week six
     */
    public boolean hasWeekSix() throws ParseException {

        int assumed_day = 1;
        String dateString = String.valueOf(monthWordToInt())+"/"+assumed_day+"/"+String.valueOf(txtField_select_YY.getText());
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


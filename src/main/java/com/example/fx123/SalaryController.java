package com.example.fx123;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;

public class SalaryController {

    @FXML
    private Button btn_attendance;

    @FXML
    private Button btn_employee;

    @FXML
    private Button btn_generate;

    @FXML
    private Button btn_leaves;

    @FXML
    private Button btn_payslips;

    @FXML
    private ComboBox<?> cb_select_MM;

    @FXML
    private ComboBox<?> cb_select_YY;

    @FXML
    private Label lbl_basic_salary;

    @FXML
    private Label lbl_fullname;

    @FXML
    private Label lbl_hourly_rate;

    @FXML
    private Label lbl_net_salary;

    @FXML
    private Label lbl_pagibig;

    @FXML
    private Label lbl_philhealth;

    @FXML
    private Label lbl_sss;

    @FXML
    private Label lbl_total_gross_salary;

    @FXML
    private Label lbl_total_gross_salary1;

    @FXML
    private Label lbl_total_hours_worked;

    @FXML
    private Label lbl_w1_gross_salary;

    @FXML
    private Label lbl_w1_hours_worked;

    @FXML
    private Label lbl_w2_gross_salary;

    @FXML
    private Label lbl_w2_hours_worked;

    @FXML
    private Label lbl_w3_gross_salary;

    @FXML
    private Label lbl_w3_hours_worked;

    @FXML
    private Label lbl_w4_gross_salary;

    @FXML
    private Label lbl_w4_hours_worked;

    @FXML
    private Label lbl_w5_gross_salary;

    @FXML
    private Label lbl_w5_hours_worked;

    @FXML
    private Label lbl_w6_gross_salary;

    @FXML
    private Label lbl_w6_hours_worked;

    @FXML
    private Label lbl_witholding_tax;

    @FXML
    private TextField txtField_eid;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

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

}

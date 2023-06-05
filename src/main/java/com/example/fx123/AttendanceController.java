package com.example.fx123;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Date;

public class AttendanceController implements Runnable {

    @FXML
    private Button btn_attendance;

    @FXML
    private Button btn_employee;

    @FXML
    private Button btn_leaves;

    @FXML
    private TableColumn<Attendance, String> date;

    @FXML
    private DatePicker dp_birthday;

    @FXML
    private TableView<Attendance> attendanceTableView;

    @FXML
    private TableColumn<Attendance, Integer> employee_number;

    @FXML
    private TableColumn<Attendance, String> f_name;

    @FXML
    private TableColumn<Attendance, String> l_name;

    @FXML
    private Label lbl_address;

    @FXML
    private Label lbl_birthday;

    @FXML
    private Label lbl_employee_number;

    @FXML
    private Label lbl_fName;

    @FXML
    private Label lbl_lName;

    @FXML
    private Label lbl_phone;

    @FXML
    private ComboBox<?> ps_comboBox_month;

    @FXML
    private Label ps_lbl_HourlyRate;

    @FXML
    private Label ps_lbl_deductionPagIbig;

    @FXML
    private Label ps_lbl_deductionPhilHealth;

    @FXML
    private Label ps_lbl_deductionSSS;

    @FXML
    private Label ps_lbl_deductionTotal;

    @FXML
    private Label ps_lbl_grossWage_clothingAllowance;

    @FXML
    private Label ps_lbl_grossWage_monthlySalary;

    @FXML
    private Label ps_lbl_grossWage_phoneAllowance;

    @FXML
    private Label ps_lbl_grossWage_riceSubsidy;

    @FXML
    private Label ps_lbl_grossWage_salary;

    @FXML
    private Label ps_lbl_grossWage_totalHoursWorked;

    @FXML
    private Label ps_lbl_netSalary_salary;

    @FXML
    private Label ps_lbl_netWage_withHoldingTax;

    @FXML
    private Label ps_lbl_totalHoursWorked;

    @FXML
    private Label ps_lbl_week1;

    @FXML
    private Label ps_lbl_week2;

    @FXML
    private Label ps_lbl_week3;

    @FXML
    private Label ps_lbl_week4;

    @FXML
    private Label ps_lbl_week5;

    @FXML
    private TextField ps_tf_employeeName;

    @FXML
    private TextField ps_tf_employeeNumber;

    @FXML
    private TextField tf_employee_number;

    @FXML
    private TextField tf_fName;

    @FXML
    private TextField tf_lName;

    @FXML
    private TextField tf_search;

    @FXML
    private TextField tf_timeIN;

    @FXML
    private TextField tf_timeOUT;

    @FXML
    private TableColumn<Attendance, String> timeIn;

    @FXML
    private TableColumn<Attendance, String> timeOut;


    @FXML
    void filterTableData(ActionEvent event) {

    }

    @FXML
    void handleCancelAttendanceClick(ActionEvent event) {

    }

    @FXML
    void handleCreateAttendanceClick(ActionEvent event) {

    }

    @FXML
    void handleDeleteAttendanceClick(ActionEvent event) {

    }

    @FXML
    void handleSaveAttendanceClick(ActionEvent event) {

    }

    @FXML
    void handleUpdateAttendanceClick(ActionEvent event) {

    }

    @FXML
    void onLogoutClicked(ActionEvent event) {

    }

    @FXML
    void refreshEmployeeScene(ActionEvent event) {

    }

    @Override
    public void run() {
        setCellValueFactoryTableColumns();
        Attendance.addAllAttendanceRecord();
        ObservableList list = FXCollections.observableArrayList(Attendance.records);
        attendanceTableView.setItems(list);

        tableViewSelectedItemListener();
        btn_attendance.requestFocus();
    }

    public void tableViewSelectedItemListener() {

        attendanceTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, attendance) -> {
            if (attendance != null) {

                // print employee data via console.
                System.out.println(attendance.toString());
            }
        });
    }

    public void setCellValueFactoryTableColumns() {
        employee_number.setCellValueFactory(new PropertyValueFactory<Attendance,Integer>("employee_number"));
        l_name.setCellValueFactory(new PropertyValueFactory<Attendance,String>("l_name"));
        f_name.setCellValueFactory(new PropertyValueFactory<Attendance,String>("f_name"));
        date.setCellValueFactory(new PropertyValueFactory<Attendance,String>("date"));
        timeIn.setCellValueFactory(new PropertyValueFactory<Attendance,String>("timeIn"));
        timeOut.setCellValueFactory(new PropertyValueFactory<Attendance,String>("timeOut"));
    }
    public void initialize() {
        System.out.println("Attendance Controller initialized...");
        run();
    }

    public void handleEmployeeClick(ActionEvent actionEvent) {
        try {
            SceneController.employeeScene(actionEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

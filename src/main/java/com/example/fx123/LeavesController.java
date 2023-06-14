package com.example.fx123;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LeavesController implements Runnable {

    @FXML
    private Button btn_addNewEmployee;

    @FXML
    private Button btn_attendance;

    @FXML
    private Button btn_cancel;

    @FXML
    private Button btn_deleteSelectedEmployee;

    @FXML
    private Button btn_employee;

    @FXML
    private Button btn_leaves;

    @FXML
    private Button btn_saveOrUpdate;

    @FXML
    private ComboBox<String> comboBox_selected_request;

    @FXML
    private DatePicker dp_end_date;

    @FXML
    private DatePicker dp_start_date;

    @FXML
    private TableColumn<ManageLeaves, Integer> eid;

    @FXML
    private TableView<ManageLeaves> leavesTableView;

    @FXML
    private TableColumn<ManageLeaves, String> first_name;

    @FXML
    private TableColumn<ManageLeaves, String> last_name;

    @FXML
    private TableColumn<ManageLeaves, Date> leave_end;

    @FXML
    private TableColumn<ManageLeaves, Date> leave_start;

    @FXML
    private TableColumn<ManageLeaves, String> leave_type;

    @FXML
    private TextField tf_employee_number;

    @FXML
    private TextField tf_fName;

    @FXML
    private TextField tf_lName;

    @FXML
    private TextField tf_search;

    @FXML
    void filterTableData(ActionEvent event) {

    }

    @FXML
    void onClckedEmployee(ActionEvent event) {
        try {
            SceneController.employeeScene(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickAttendance(ActionEvent event) {
        try {
            SceneController.attendanceScene(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onClickedLeaves(ActionEvent event) {

    }

    @FXML
    void onDeleteLeaveClicked(ActionEvent event) {

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
    void onSaveLeaveClicked(ActionEvent event) {

    }

    @FXML
    void resetDetailsTextField(ActionEvent event) {

    }

    @FXML
    void setNewLeave(ActionEvent event) {

    }

    @Override
    public void run() {


    }

    public void initialize () {
        setCellValueFactoryTableColumns();
        ManageLeaves.addAllLeaves();
        ObservableList<ManageLeaves> list = FXCollections.observableArrayList(ManageLeaves.RECORDS);
        list.stream().forEach(System.out::println);
        System.out.println("Size of observable list = " + list.size());
        leavesTableView.setItems(list);
        addComboBoxItems();
    }

    private void setCellValueFactoryTableColumns() {
        eid.setCellValueFactory(new PropertyValueFactory<ManageLeaves,Integer>("eid"));
        last_name.setCellValueFactory(new PropertyValueFactory<ManageLeaves,String>("last_name"));
        first_name.setCellValueFactory(new PropertyValueFactory<ManageLeaves,String>("first_name"));
        leave_type.setCellValueFactory(new PropertyValueFactory<ManageLeaves,String>("leave_type"));
        leave_start.setCellValueFactory(new PropertyValueFactory<ManageLeaves,Date>("leave_start"));
        leave_end.setCellValueFactory(new PropertyValueFactory<ManageLeaves, Date>("leave_end"));
    }

    public void onClickedPayslip(ActionEvent actionEvent) {
        System.out.println(textFieldsAndDatePickerToTabString());
        // inprogress
    }

    public void addComboBoxItems () {
        ObservableList<String> items = FXCollections.observableArrayList(
                "Emergency",
                "Sick",
                "Leave"
        );
        comboBox_selected_request.setItems(items);
    }

    public String textFieldsAndDatePickerToTabString() {
        return
            (tf_employee_number.getText().isEmpty() ? "Unknown" : tf_employee_number.getText()) + "\t"+
            (tf_lName.getText().isEmpty() ? "Unknown" : tf_lName.getText()) + "\t"+
            (tf_fName.getText().isEmpty() ? "Unknown" : tf_fName.getText()) + "\t"+
            (comboBox_selected_request.getValue())+ "\t"+
            (dp_start_date.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + "\t"+
            (dp_end_date.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
    }
}

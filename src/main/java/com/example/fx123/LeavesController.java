package com.example.fx123;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LeavesController implements Runnable {

    private int tableViewSelectedLineNumber;

    public int getTableViewSelectedLineNumber() {
        return tableViewSelectedLineNumber;
    }

    public void setTableViewSelectedLineNumber(int tableViewSelectedLineNumber) {
        this.tableViewSelectedLineNumber = tableViewSelectedLineNumber;
    }

    @FXML
    private Button btn_addNewEmployee;

    @FXML
    private Button btn_attendance;

    @FXML
    private Button btn_cancel;

    @FXML
    private Button btn_delete;

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
    private TableColumn<ManageLeaves, String> leave_end;

    @FXML
    private TableColumn<ManageLeaves, String> leave_start;

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
    private Label lbl_num_emergency_result;

    @FXML
    private Label lbl_num_sick_result;

    @FXML
    private Label lbl_num_vacation_result;

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
        run();
    }

    @FXML
    void onDeleteLeaveClicked(ActionEvent event) {
        TsvUtils.deleteEmployeeRecordByLineNumber(MainApp.LEAVE_TSV,getTableViewSelectedLineNumber() + 2);
        ManageLeaves.RECORDS.clear();
        run();
        btn_cancel.setDisable(true);
        btn_delete.setDisable(true);
        btn_saveOrUpdate.setDisable(true);
    }

    @FXML
    void onLogoutClicked(ActionEvent event) {
        try {
            SceneController.loginScene(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createEmployeeLeave(ManageLeaves leaves, ActionEvent event) {
        leaves.createEmployeeLeave();
        ManageLeaves.RECORDS.clear();
        resetDetailsTextField(event);
        run();
    }

    @FXML
    void onSaveLeaveClicked(ActionEvent event) {

        LocalDate startLeave = dp_start_date.getValue();
        LocalDate endLeave = dp_end_date.getValue();

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        Date startLeaveDate = null;
        Date endLeaveDate = null;

        try {
            startLeaveDate = sdf.parse(startLeave.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            endLeaveDate = sdf.parse(endLeave.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (btn_saveOrUpdate.getText().equalsIgnoreCase("save")) {
            ManageLeaves leave = new ManageLeaves(Integer.parseInt(tf_employee_number.getText()),
                    tf_lName.getText(), tf_fName.getText(), comboBox_selected_request.getValue(),
                    startLeaveDate, endLeaveDate);

            String[] credits = leave.getConsumedCredits().split("\t");
            int emergency_spent = Integer.valueOf(credits[0]);
            int sick_spent = Integer.valueOf(credits[1]);
            int vacation_spent = Integer.valueOf(credits[2]);
            int total_days_new_leave = leave.totalDaysLeave();

            Alert alert = new Alert(Alert.AlertType.ERROR);

            switch (leave.getLeaveType().toLowerCase()) {
                case "emergency":
                    if ((emergency_spent + total_days_new_leave) <= leave.MAX_EMERGENCY_LEAVES) {
                        createEmployeeLeave(leave, event);
                    } else {
                        alert.setTitle("Emergency Leave Limit Exceeded");
                        alert.showAndWait();
                    }
                    break;
                case "sick":
                    if ((sick_spent + total_days_new_leave) <= leave.MAX_SICK_LEAVES) {
                        createEmployeeLeave(leave, event);
                    } else {
                        alert.setTitle("Sick Leave Limit Exceeded");
                        alert.showAndWait();
                    }
                    break;
                case "vacation":
                    if ((vacation_spent + total_days_new_leave) <= leave.MAX_VACATION_LEAVES) {
                        createEmployeeLeave(leave, event);
                    } else {
                        alert.setTitle("Vacation Leave Limit Exceeded");
                        alert.showAndWait();
                    }
                    break;
            }
        }

        if (btn_saveOrUpdate.getText().equalsIgnoreCase("update")) {
            String [] updatedData = {
                    tf_employee_number.getText(),
                    tf_lName.getText(),
                    tf_fName.getText(),
                    comboBox_selected_request.getValue(),
                    sdf.format(startLeaveDate),
                    sdf.format(endLeaveDate)
            };
            TsvUtils.updateByLineNumber(MainApp.LEAVE_TSV, getTableViewSelectedLineNumber() + 2,updatedData);
            btn_saveOrUpdate.setText("Save");
            ManageLeaves.RECORDS.clear();
            resetDetailsTextField(event);
            run();
        }
    }

    @FXML
    void resetDetailsTextField(ActionEvent event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        LocalDate localDate = LocalDate.parse("January 1, 2022", formatter);
        tf_employee_number.setText("");
        tf_fName.setText("");
        tf_lName.setText("");
        comboBox_selected_request.setValue("Select request -");
        dp_start_date.setValue(localDate);
        dp_end_date.setValue(localDate);
        btn_saveOrUpdate.setText("Save");


        btn_leaves.requestFocus();
        onClickedLeaves(event);

        lbl_num_emergency_result.setText("0");
        lbl_num_sick_result.setText("0");
        lbl_num_vacation_result.setText("0");
    }

    @FXML
    void setNewLeave(ActionEvent event) {
        resetDetailsTextField(event);
        tf_employee_number.requestFocus();
        btn_saveOrUpdate.setText("Save");
        btn_cancel.setDisable(false);
        btn_saveOrUpdate.setDisable(false);
    }


    public void tableViewSelectedItemListener() {

        leavesTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, leave) -> {
            if (leave != null) {

                System.out.println("Selected Line Number = " + tableViewSelectedLineNumber);
                setTableViewSelectedLineNumber(leavesTableView.getSelectionModel().getSelectedIndex());

                System.out.println(leave.toString());



                /**
                 * Update the textfields if there is an selected item on tableview textfields
                 */

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");

                // Convert Date into LocalDate to store to datepicker

                String str_start_leave = sdf.format(leave.getLeave_start());
                String str_end_leave = sdf.format(leave.getLeave_end());

                LocalDate local_date_start_leave = LocalDate.parse(str_start_leave , dtf);
                LocalDate local_date_end_leave = LocalDate.parse(str_end_leave , dtf);

                tf_employee_number.setText(String.valueOf(leave.getEid()));
                tf_lName.setText(leave.getLast_name());
                tf_fName.setText(leave.getFirst_name());
                comboBox_selected_request.setValue(leave.getLeave_type());
                dp_start_date.setValue(local_date_start_leave);
                dp_end_date.setValue(local_date_end_leave);
                /**
                 * Set save and cancel button to enabled because we have now selected item,
                 * we can update it via save button and cancel to terminate the update.
                 */
                btn_saveOrUpdate.setText("Update");
                btn_delete.setDisable(false);
                btn_cancel.setDisable(false);
                btn_saveOrUpdate.setDisable(false);

                String [] creditsleave = leave.getConsumedCredits().split("\t");
                lbl_num_emergency_result.setText(creditsleave[0]);
                lbl_num_sick_result.setText(creditsleave[1]);
                lbl_num_vacation_result.setText(creditsleave[2]);
            }
        });
    }


    @Override
    public void run() {
        if(ManageLeaves.RECORDS.isEmpty()) ManageLeaves.addAllLeaves();
        setCellValueFactoryTableColumns();
        tableViewSelectedItemListener();
        ObservableList<ManageLeaves> list = FXCollections.observableArrayList(ManageLeaves.RECORDS);
        leavesTableView.setItems(list);
        addComboBoxItems();

        btn_delete.setDisable(true);
        btn_cancel.setDisable(true);
        btn_saveOrUpdate.setDisable(true);
    }



    public void initialize () {
        run();
    }

    private void setCellValueFactoryTableColumns() {
        eid.setCellValueFactory(new PropertyValueFactory<ManageLeaves,Integer>("eid"));
        last_name.setCellValueFactory(new PropertyValueFactory<ManageLeaves,String>("last_name"));
        first_name.setCellValueFactory(new PropertyValueFactory<ManageLeaves,String>("first_name"));
        leave_type.setCellValueFactory(new PropertyValueFactory<ManageLeaves,String>("leave_type"));
        leave_start.setCellValueFactory(new PropertyValueFactory<ManageLeaves,String>("leave_start"));
        leave_end.setCellValueFactory(new PropertyValueFactory<ManageLeaves, String>("leave_end"));
    }

    public void onClickedPayslip(ActionEvent actionEvent) {
        // inprogress
    }

    public void addComboBoxItems () {
        ObservableList<String> items = FXCollections.observableArrayList(
                "Emergency",
                "Sick",
                "Vacation"
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

    public void refreshAttendanceList(ActionEvent actionEvent) {
        // Clear Employees Record
        ManageLeaves.clearRecords();
    }
}

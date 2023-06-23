package com.example.fx123;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class LeavesController implements Runnable {
    private int tableViewSelectedLineNumber;

    public int getTableViewSelectedLineNumber() {
        return tableViewSelectedLineNumber;
    }

    public void setTableViewSelectedLineNumber(int tableViewSelectedLineNumber) {
        this.tableViewSelectedLineNumber = tableViewSelectedLineNumber;
    }

    @FXML private Button btn_addNewEmployee;

    @FXML private Button btn_attendance;

    @FXML private Button btn_cancel;

    @FXML private Button btn_delete;

    @FXML private Button btn_employee;

    @FXML private Button btn_leaves;

    @FXML private Button btn_save_update;

    @FXML private ComboBox<String> comboBox_selected_request;

    @FXML private DatePicker dp_end_date;

    @FXML private DatePicker dp_start_date;

    @FXML private TableColumn<EmployeeLeave, Integer> eid;

    @FXML private TableView<EmployeeLeave> leavesTableView;

    @FXML private TableColumn<EmployeeLeave, String> first_name;

    @FXML private TableColumn<EmployeeLeave, String> last_name;

    @FXML private TableColumn<EmployeeLeave, String> leave_end;

    @FXML private TableColumn<EmployeeLeave, String> leave_start;

    @FXML private TableColumn<EmployeeLeave, String> leave_type;

    @FXML private TextField tf_employee_number;

    @FXML private TextField tf_fName;

    @FXML private TextField tf_lName;

    @FXML private TextField tf_search;

    @FXML private Label lbl_num_emergency_result;

    @FXML private Label lbl_num_sick_result;

    @FXML private Label lbl_num_vacation_result;

    @FXML
    void filterTableData(ActionEvent event) {}

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
        btn_leaves.requestFocus();
    }

    @FXML
    void onDeleteLeaveClicked(ActionEvent event) {
        TsvUtils.deleteEmployeeRecordByLineNumber(
                MainApp.LEAVE_TSV, getTableViewSelectedLineNumber() + 2);
        EmployeeLeave.RECORDS.clear();
        run();
        btn_cancel.setDisable(true);
        btn_delete.setDisable(true);
        btn_save_update.setDisable(true);
    }

    @FXML
    void onLogoutClicked(ActionEvent event) {
        try {
            SceneController.loginScene(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createEmployeeLeave(EmployeeLeave leaves, ActionEvent event) {
        leaves.createEmployeeLeave();
        EmployeeLeave.RECORDS.clear();
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
            startLeaveDate = sdf.parse(
                    startLeave.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            endLeaveDate =
                    sdf.parse(endLeave.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        EmployeeLeave leave =
                new EmployeeLeave(Integer.parseInt(tf_employee_number.getText()),
                        tf_lName.getText(), tf_fName.getText(),
                        comboBox_selected_request.getValue(), startLeaveDate, endLeaveDate);
        if (btn_save_update.getText().equalsIgnoreCase("save")) {
            String[] credits = leave.getConsumedCredits().split("\t");
            int emergency_spent = Integer.parseInt(credits[0]);
            int sick_spent = Integer.parseInt(credits[1]);
            int vacation_spent = Integer.parseInt(credits[2]);

            Alert alert = new Alert(Alert.AlertType.ERROR);

            switch (leave.getLeaveType().toLowerCase()) {
                case "emergency":
                    if ((emergency_spent + leave.totalDaysLeave())
                            <= leave.MAX_EMERGENCY_LEAVES) {
                        createEmployeeLeave(leave, event);
                    } else {
                        alert.setTitle("Emergency Leave Limit Exceeded");
                        alert.showAndWait();
                    }
                    break;
                case "sick":
                    if ((sick_spent + leave.totalDaysLeave()) <= leave.MAX_SICK_LEAVES) {
                        createEmployeeLeave(leave, event);
                    } else {
                        alert.setTitle("1. Sick Leave Limit Exceeded");
                        alert.showAndWait();
                        System.out.println("Why");
                    }
                    break;
                case "vacation":
                    if ((vacation_spent + leave.totalDaysLeave())
                            <= leave.MAX_VACATION_LEAVES) {
                        createEmployeeLeave(leave, event);
                    } else {
                        alert.setTitle("Vacation Leave Limit Exceeded");
                        alert.showAndWait();
                    }
                    break;
            }
        }

        if (btn_save_update.getText().equalsIgnoreCase("update")) {
            String[] arr_credits_spent = leave.getConsumedCredits().split("\t");
            int emergency_spent = Integer.parseInt(arr_credits_spent[0]);
            int sick_spent = Integer.parseInt(arr_credits_spent[1]);
            int vacation_spent = Integer.parseInt(arr_credits_spent[2]);
            int total_days_new_leave = leave.totalDaysLeave();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            String[] updatedData = {String.valueOf(leave.getEid()),
                    leave.getLast_name(), leave.getFirst_name(), leave.getLeaveType(),
                    sdf.format(leave.getLeave_start()), sdf.format(leave.getLeave_end())};

            int sum_day_leave, diff_day_leave;

            switch (leave.getLeaveType().toLowerCase()) {
                case "emergency":
                    System.out.println("❌");
                    sum_day_leave = total_days_new_leave + emergency_spent;
                    diff_day_leave = Math.abs(total_days_new_leave - emergency_spent);

                    // for higher leave
                    if (leave.MAX_EMERGENCY_LEAVES >= sum_day_leave) {
                        TsvUtils.updateByLineNumber(MainApp.LEAVE_TSV,
                                getTableViewSelectedLineNumber() + 2, updatedData);
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setTitle("Emergency Updating for higher days");
                        alert.showAndWait();
                    } else if (diff_day_leave <= leave.MAX_EMERGENCY_LEAVES
                            && total_days_new_leave <= leave.MAX_EMERGENCY_LEAVES) {
                        TsvUtils.updateByLineNumber(MainApp.LEAVE_TSV,
                                getTableViewSelectedLineNumber() + 2, updatedData);
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setTitle("Emergency Updating for lower days");
                        alert.showAndWait();
                    }

                    else {
                        alert.setTitle("Emergency Leave Limit Exceeded");
                        alert.setContentText("Emergency Spent = " + emergency_spent + "\n"
                                + "Total Days New  = " + total_days_new_leave + "\n"
                                + "Emergency MAX = 5");
                        alert.showAndWait();
                    }
                    break;

                case "sick":
                    sum_day_leave = total_days_new_leave + sick_spent;
                    diff_day_leave = Math.abs(total_days_new_leave - sick_spent);
                    // for higher leave
                    if (leave.MAX_SICK_LEAVES >= sum_day_leave) {
                        TsvUtils.updateByLineNumber(MainApp.LEAVE_TSV,
                                getTableViewSelectedLineNumber() + 2, updatedData);
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setTitle("Sick Updating for higher days");
                        alert.showAndWait();
                    } else if (diff_day_leave <= leave.MAX_SICK_LEAVES
                            && total_days_new_leave <= leave.MAX_SICK_LEAVES) {
                        TsvUtils.updateByLineNumber(MainApp.LEAVE_TSV,
                                getTableViewSelectedLineNumber() + 2, updatedData);
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setTitle("Sick Updating for lower days");
                        alert.showAndWait();
                    }

                    else {
                        alert.setTitle("Sick Leave Limit Exceeded");
                        alert.setContentText("Sick Spent = " + emergency_spent + "\n"
                                + "Total Days New  = " + total_days_new_leave + "\n"
                                + "Sick MAX = 5");
                        alert.showAndWait();
                    }
                    break;

                case "vacation":
                    sum_day_leave = total_days_new_leave + vacation_spent;
                    diff_day_leave = Math.abs(total_days_new_leave - vacation_spent);
                    // for higher leave
                    if (leave.MAX_VACATION_LEAVES >= sum_day_leave) {
                        TsvUtils.updateByLineNumber(MainApp.LEAVE_TSV,
                                getTableViewSelectedLineNumber() + 2, updatedData);
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setTitle("Vacation Updating for higher days");
                        alert.showAndWait();
                    } else if (diff_day_leave <= leave.MAX_VACATION_LEAVES
                            && total_days_new_leave <= leave.MAX_VACATION_LEAVES) {
                        TsvUtils.updateByLineNumber(MainApp.LEAVE_TSV,
                                getTableViewSelectedLineNumber() + 2, updatedData);
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setTitle("Vacation Updating for lower days");
                        alert.showAndWait();
                    }

                    else {
                        alert.setTitle("Vacation Leave Limit Exceeded");
                        alert.setContentText("Vacation Spent = " + emergency_spent + "\n"
                                + "Total Days New  = " + total_days_new_leave + "\n"
                                + "Vacation MAX = 5");
                        alert.showAndWait();
                    }
                    break;
            }

            System.out.println("String [] updatedData");
            System.out.println(leave.getEid());
            System.out.println(leave.getLast_name());
            System.out.println(leave.getFirst_name());
            System.out.println(comboBox_selected_request.getValue());
            System.out.println("PRINTING DATA IN ARRAY✅");
            Arrays.stream(updatedData).forEach(System.out::println);
            btn_save_update.setText("Save");
            EmployeeLeave.RECORDS.clear();
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
        btn_save_update.setText("Save");

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
        btn_save_update.setText("Save");
        btn_cancel.setDisable(false);
        btn_save_update.setDisable(false);
    }

    public void tableViewSelectedItemListener() {
        leavesTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, leave) -> {
                    if (leave != null) {
                        System.out.println(
                                "Selected Line Number = " + tableViewSelectedLineNumber);
                        setTableViewSelectedLineNumber(
                                leavesTableView.getSelectionModel().getSelectedIndex());

                        System.out.println(leave.toString());

                        /**
                         * Update the textfields if there is an selected item on tableview
                         * textfields
                         */

                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");

                        // Convert Date into LocalDate to store to datepicker

                        String str_start_leave = sdf.format(leave.getLeave_start());
                        String str_end_leave = sdf.format(leave.getLeave_end());

                        LocalDate local_date_start_leave =
                                LocalDate.parse(str_start_leave, dtf);
                        LocalDate local_date_end_leave =
                                LocalDate.parse(str_end_leave, dtf);

                        tf_employee_number.setText(String.valueOf(leave.getEid()));
                        tf_lName.setText(leave.getLast_name());
                        tf_fName.setText(leave.getFirst_name());
                        comboBox_selected_request.setValue(leave.getLeave_type());
                        dp_start_date.setValue(local_date_start_leave);
                        dp_end_date.setValue(local_date_end_leave);
                        /**
                         * Set save and cancel button to enabled because we have now
                         * selected item, we can update it via save button and cancel to
                         * terminate the update.
                         */
                        btn_save_update.setText("Update");
                        btn_delete.setDisable(false);
                        btn_cancel.setDisable(false);
                        btn_save_update.setDisable(false);
                        System.out.println("Already set to false");
                        System.out.println("btn_delete.setDisable(false);\n"
                                + "                btn_cancel.setDisable(false);\n"
                                + "                btn_saveOrUpdate.setDisable(false);");

                        String[] creditsleave = leave.getConsumedCredits().split("\t");
                        lbl_num_emergency_result.setText(creditsleave[0]);
                        lbl_num_sick_result.setText(creditsleave[1]);
                        lbl_num_vacation_result.setText(creditsleave[2]);
                    }
                });
    }

    @Override
    public void run() {
        if (EmployeeLeave.RECORDS.isEmpty())
            EmployeeLeave.addAllLeaves();
        setCellValueFactoryTableColumns();
        tableViewSelectedItemListener();
        ObservableList<EmployeeLeave> list =
                FXCollections.observableArrayList(EmployeeLeave.RECORDS);
        leavesTableView.setItems(list);
        addComboBoxItems();

        btn_delete.setDisable(true);
        btn_cancel.setDisable(true);
        btn_save_update.setDisable(true);
    }

    public void initialize() {
        run();
    }

    private void setCellValueFactoryTableColumns() {
        eid.setCellValueFactory(
                new PropertyValueFactory<EmployeeLeave, Integer>("eid"));
        last_name.setCellValueFactory(
                new PropertyValueFactory<EmployeeLeave, String>("last_name"));
        first_name.setCellValueFactory(
                new PropertyValueFactory<EmployeeLeave, String>("first_name"));
        leave_type.setCellValueFactory(
                new PropertyValueFactory<EmployeeLeave, String>("leave_type"));
        leave_start.setCellValueFactory(
                new PropertyValueFactory<EmployeeLeave, String>("leave_start"));
        leave_end.setCellValueFactory(
                new PropertyValueFactory<EmployeeLeave, String>("leave_end"));
    }

    public void onClickedPayslip(ActionEvent actionEvent) {
        // inprogress
    }

    public void addComboBoxItems() {
        ObservableList<String> items =
                FXCollections.observableArrayList("Emergency", "Sick", "Vacation");
        comboBox_selected_request.setItems(items);
    }
}

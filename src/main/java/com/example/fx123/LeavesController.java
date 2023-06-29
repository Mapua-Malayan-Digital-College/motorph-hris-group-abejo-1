package com.example.fx123;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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

    @FXML private Button btn_cancel;

    @FXML private Button btn_delete;

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
    void onClickedDeleteLeave(ActionEvent event) {
        CsvUtils.deleteEmployeeRecordByLineNumber(
                MainApp.LEAVE_CSV, getTableViewSelectedLineNumber() + 2);
        EmployeeLeave.RECORDS.clear();
        run();
        lbl_num_emergency_result.setText("0");
        lbl_num_sick_result.setText("0");
        lbl_num_vacation_result.setText("0");
        btn_cancel.setDisable(true);
        btn_delete.setDisable(true);
        btn_save_update.setDisable(true);
    }

    @FXML
    void onClickedLogout(ActionEvent event) {
        try {
            SceneController.loginScene(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createEmployeeLeave(EmployeeLeave leaves, ActionEvent event) {
        leaves.createEmployeeLeave();
        EmployeeLeave.RECORDS.clear();
        onClickedCancel(event);
    }

    @FXML
    void onSaveLeaveClicked(ActionEvent event) {
        LocalDate dp_startLeave = dp_start_date.getValue();
        LocalDate dp_endLeave = dp_end_date.getValue();

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        Date startLeaveDate;
        Date endLeaveDate;

        try {
            startLeaveDate = sdf.parse(
                    dp_startLeave.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            endLeaveDate = sdf.parse(
                    dp_endLeave.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));

            EmployeeLeave leave = new EmployeeLeave(
                    Integer.parseInt(tf_employee_number.getText()), tf_lName.getText(),
                    tf_fName.getText(), comboBox_selected_request.getValue(),
                    startLeaveDate, endLeaveDate);
            if (btn_save_update.getText().equalsIgnoreCase("save")) {
                String[] credits = leave.getConsumedCredits().split("\t");
                int emergency_spent = Integer.parseInt(credits[0]);
                int sick_spent = Integer.parseInt(credits[1]);
                int vacation_spent = Integer.parseInt(credits[2]);

                Alert alert = new Alert(Alert.AlertType.ERROR);

                if (!isLeaveOccupied(tf_employee_number.getText(),
                        sdf.format(startLeaveDate), sdf.format(endLeaveDate))) {
                    switch (leave.getLeaveType()) {
                        case "Emergency" -> {
                            if ((emergency_spent + leave.totalDaysLeave())
                                    <= leave.MAX_EMERGENCY_LEAVES) {
                                createEmployeeLeave(leave, event);
                                refreshLeaveTbl();
                            } else {
                                alert.setTitle("Emergency Leave Limit Exceeded");
                                alert.showAndWait();
                            }
                        }
                        case "Sick" -> {
                            if ((sick_spent + leave.totalDaysLeave())
                                    <= leave.MAX_SICK_LEAVES) {
                                createEmployeeLeave(leave, event);
                                refreshLeaveTbl();
                            } else {
                                alert.setTitle("Sick Leave Limit Exceeded");
                                alert.showAndWait();
                            }
                        }
                        case "Vacation" -> {
                            if ((vacation_spent + leave.totalDaysLeave())
                                    <= leave.MAX_VACATION_LEAVES) {
                                createEmployeeLeave(leave, event);
                                refreshLeaveTbl();
                            } else {
                                alert.setTitle("Vacation Leave Limit Exceeded");
                                alert.showAndWait();
                            }
                        }
                    }
                }
                else
                {
                    Alert alert_save = new Alert (Alert.AlertType.ERROR);
                    alert_save.setTitle ("Leave Occupied");
                    alert_save.setContentText (
                            "Leave is already occupied, please try other dates");
                    alert_save.showAndWait ();
                }
            }


            if (btn_save_update.getText ().equalsIgnoreCase ("update"))
            {
                System.out.println (
                        "IS SET LEAVE OCCUPIED = "
                                + isLeaveOccupied (tf_employee_number.getText (),
                                sdf.format (startLeaveDate),
                                sdf.format (endLeaveDate)));
                if (!isLeaveOccupied (tf_employee_number.getText (),
                        sdf.format (startLeaveDate),
                        sdf.format (endLeaveDate)))
                {
                    String[] arr_credits_spent = leave.getConsumedCredits ().split ("\t");
                    int emergency_spent = Integer.parseInt (arr_credits_spent[0]),
                            sick_spent = Integer.parseInt (arr_credits_spent[1]),
                            vacation_spent = Integer.parseInt (arr_credits_spent[2]),
                            total_days_new_leave = leave.totalDaysLeave ();

                    Alert alert = new Alert (Alert.AlertType.ERROR);
                    String[] updatedData = { String.valueOf (leave.getEid ()),
                            leave.getLast_name (),
                            leave.getFirst_name (),
                            leave.getLeaveType (),
                            sdf.format (leave.getLeave_start ()),
                            sdf.format (leave.getLeave_end ()) };

                    int sum_day_leave, diff_day_leave;

                    switch (leave.getLeaveType().toLowerCase()) {
                        case "emergency" -> {
                            sum_day_leave = total_days_new_leave + emergency_spent;
                            diff_day_leave
                                    = Math.abs(total_days_new_leave - emergency_spent);

                            // for higher leave
                            if (leave.MAX_EMERGENCY_LEAVES >= sum_day_leave) {
                                CsvUtils.updateByLineNumber(
                                        MainApp.LEAVE_CSV,
                                        getTableViewSelectedLineNumber() + 2, updatedData);
                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                alert.setTitle("Emergency Updating for higher days");
                                alert.showAndWait();
                                refreshLeaveTbl();
                            } else if (diff_day_leave <= leave.MAX_EMERGENCY_LEAVES
                                    && total_days_new_leave
                                    <= leave.MAX_EMERGENCY_LEAVES
                                    &&
                                    ((total_days_new_leave) <= (leave.MAX_EMERGENCY_LEAVES - emergency_spent))) {
                                System.out.println(diff_day_leave);
                                System.out.println(total_days_new_leave);
                                CsvUtils.updateByLineNumber(
                                        MainApp.LEAVE_CSV,
                                        getTableViewSelectedLineNumber() + 2, updatedData);
                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                alert.setTitle("Emergency Updating for lower days");
                                alert.showAndWait();
                                refreshLeaveTbl();
                            } else {
                                alert.setTitle("Emergency Leave Limit Exceeded");
                                alert.setContentText(
                                        "Emergency Spent = " + emergency_spent + "\n"
                                                + "Total Days New  = " + total_days_new_leave + "\n"
                                                + "Emergency MAX = 5");
                                alert.showAndWait();
                            }
                        }
                        case "sick" -> {
                            sum_day_leave = total_days_new_leave + sick_spent;
                            diff_day_leave
                                    = Math.abs(total_days_new_leave - sick_spent);
                            // for higher leave
                            if (leave.MAX_SICK_LEAVES >= sum_day_leave) {
                                CsvUtils.updateByLineNumber(
                                        MainApp.LEAVE_CSV,
                                        getTableViewSelectedLineNumber() + 2, updatedData);
                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                alert.setTitle("Sick Updating for higher days");
                                alert.showAndWait();
                                refreshLeaveTbl();
                            } else if (diff_day_leave <= leave.MAX_SICK_LEAVES
                                    && total_days_new_leave <= leave.MAX_SICK_LEAVES
                                    &&
                                    ((total_days_new_leave) <= (leave.MAX_SICK_LEAVES - sick_spent))) {
                                CsvUtils.updateByLineNumber(
                                        MainApp.LEAVE_CSV,
                                        getTableViewSelectedLineNumber() + 2, updatedData);
                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                alert.setTitle("Sick Updating for lower days");
                                alert.showAndWait();
                                refreshLeaveTbl();
                            } else {
                                alert.setTitle("Sick Leave Limit Exceeded");
                                alert.setContentText(
                                        "Sick Spent = " + emergency_spent + "\n"
                                                + "Total Days New  = " + total_days_new_leave + "\n"
                                                + "Sick MAX = 5");
                                alert.showAndWait();
                            }
                        }
                        case "vacation" -> {
                            sum_day_leave = total_days_new_leave + vacation_spent;
                            diff_day_leave
                                    = Math.abs(total_days_new_leave - vacation_spent);
                            // for higher leave
                            if (leave.MAX_VACATION_LEAVES >= sum_day_leave) {
                                CsvUtils.updateByLineNumber(
                                        MainApp.LEAVE_CSV,
                                        getTableViewSelectedLineNumber() + 2, updatedData);
                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                alert.setTitle("Vacation Updating for higher days");
                                alert.showAndWait();
                                refreshLeaveTbl();
                            } else if (diff_day_leave <= leave.MAX_VACATION_LEAVES
                                    && total_days_new_leave <= leave.MAX_VACATION_LEAVES
                                    && ((total_days_new_leave) <= (leave.MAX_VACATION_LEAVES - vacation_spent))) {
                                CsvUtils.updateByLineNumber(
                                        MainApp.LEAVE_CSV,
                                        getTableViewSelectedLineNumber() + 2, updatedData);
                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                alert.setTitle("Vacation Updating for lower days");
                                alert.showAndWait();
                                refreshLeaveTbl();
                            } else {
                                alert.setTitle("Vacation Leave Limit Exceeded");
                                alert.setContentText(
                                        "Vacation Spent = " + emergency_spent + "\n"
                                                + "Total Days New  = " + total_days_new_leave + "\n"
                                                + "Vacation MAX = 5");
                                alert.showAndWait();
                            }
                        }
                    }
                }
                else
                {
                    Alert alert_update = new Alert (Alert.AlertType.ERROR);
                    alert_update.setTitle ("Leave Occupied");
                    alert_update.setContentText (
                            "Leave is already occupied, please try other dates");
                    alert_update.showAndWait ();
                }
            }
        }
        catch (ParseException ex)
        {
            throw new RuntimeException (ex);
        }
    }
    void refreshLeaveTbl() {
        btn_save_update.setText ("Save");
        EmployeeLeave.RECORDS.clear ();
        run ();
    }
    @FXML
    void
    onClickedCancel (ActionEvent event)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("MMMM d, yyyy");
        LocalDate localDate = LocalDate.parse ("January 1, 2022", formatter);
        tf_employee_number.setText ("");
        tf_fName.setText ("");
        tf_lName.setText ("");
        comboBox_selected_request.setValue ("Select request -");
        dp_start_date.setValue (localDate);
        dp_end_date.setValue (localDate);
        btn_save_update.setText ("Save");
        lbl_num_emergency_result.setText ("0");
        lbl_num_sick_result.setText ("0");
        lbl_num_vacation_result.setText ("0");
        btn_leaves.requestFocus ();
        //        onClickedLeaves(event);
    }

    @FXML
    void
    onClickedSetNewLeave (ActionEvent event)
    {
        enableFields();

        tf_employee_number.requestFocus ();
        btn_save_update.setText ("Save");
        btn_cancel.setDisable (false);
        btn_save_update.setDisable (false);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("MMMM d, yyyy");
        LocalDate localDateStart = LocalDate.parse ("January 1, 2022", formatter);
        LocalDate localDateEnd = LocalDate.parse ("January 2, 2022", formatter);
        tf_employee_number.setText ("");
        tf_fName.setText ("");
        tf_lName.setText ("");
        comboBox_selected_request.setValue ("Select request -");
        dp_start_date.setValue (localDateStart);
        dp_end_date.setValue (localDateEnd);
        btn_save_update.setText ("Save");
        tf_employee_number.requestFocus ();
    }

    public void
    tableViewSelectedItemListener ()
    {
        leavesTableView.getSelectionModel ().selectedItemProperty ().addListener (
                (obs, oldSelection, leave) -> {
                    if (leave != null)
                    {
                        System.out.println ("Selected Line Number = "
                                + tableViewSelectedLineNumber);
                        setTableViewSelectedLineNumber (
                                leavesTableView.getSelectionModel ().getSelectedIndex ());

                        /**
                         * Update the textfields if there is an selected item on tableview
                         * textfields
                         */

                        SimpleDateFormat sdf = new SimpleDateFormat ("MM/dd/yyyy");
                        DateTimeFormatter dtf
                                = DateTimeFormatter.ofPattern ("MM/dd/yyyy");

                        // Convert Date into LocalDate to store to datepicker

                        String str_start_leave = sdf.format (leave.getLeave_start ());
                        String str_end_leave = sdf.format (leave.getLeave_end ());

                        LocalDate local_date_start_leave
                                = LocalDate.parse (str_start_leave, dtf);
                        LocalDate local_date_end_leave
                                = LocalDate.parse (str_end_leave, dtf);

                        tf_employee_number.setText (String.valueOf (leave.getEid ()));
                        tf_lName.setText (leave.getLast_name ());
                        tf_fName.setText (leave.getFirst_name ());
                        comboBox_selected_request.setValue (leave.getLeave_type ());
                        dp_start_date.setValue (local_date_start_leave);
                        dp_end_date.setValue (local_date_end_leave);
                        /**
                         * Set save and cancel button to enabled because we have now
                         * selected item, we can update it via save button and cancel to
                         * terminate the update.
                         */
                        btn_save_update.setText ("Update");
                        btn_delete.setDisable (false);
                        btn_cancel.setDisable (false);
                        btn_save_update.setDisable (false);
                        System.out.println ("Already set to false");
                        System.out.println (
                                "btn_delete.setDisable(false);\n"
                                        + "                btn_cancel.setDisable(false);\n"
                                        + "                btn_saveOrUpdate.setDisable(false);");

                        String[] creditsleave = leave.getConsumedCredits ().split ("\t");
                        lbl_num_emergency_result.setText (creditsleave[0]);
                        lbl_num_sick_result.setText (creditsleave[1]);
                        lbl_num_vacation_result.setText (creditsleave[2]);
                    }
                });
    }

    @Override
    public void
    run ()
    {
        if (EmployeeLeave.RECORDS.isEmpty ())
            EmployeeLeave.addAllLeaves ();
        setCellValueFactoryTableColumns ();
        tableViewSelectedItemListener ();
        ObservableList<EmployeeLeave> list
                = FXCollections.observableArrayList (EmployeeLeave.RECORDS);
        leavesTableView.setItems (list);
        addComboBoxItems ();


        btn_delete.setDisable (true);
        btn_cancel.setDisable (true);
        btn_save_update.setDisable (true);
    }

    public void enableFields() {
        tf_employee_number.setDisable(false);
        tf_fName.setDisable(false);
        tf_lName.setDisable(false);
        dp_start_date.setDisable(false);
        comboBox_selected_request.setDisable(false);
        dp_end_date.setDisable(false);
    }

    public void
    initialize ()
    {
        run ();
    }

    private void
    setCellValueFactoryTableColumns ()
    {
        eid.setCellValueFactory (
                new PropertyValueFactory<>("eid"));
        last_name.setCellValueFactory (
                new PropertyValueFactory<>("last_name"));
        first_name.setCellValueFactory (
                new PropertyValueFactory<>("first_name"));
        leave_type.setCellValueFactory (
                new PropertyValueFactory<>("leave_type"));
        leave_start.setCellValueFactory (
                new PropertyValueFactory<>("leave_start"));
        leave_end.setCellValueFactory (
                new PropertyValueFactory<>("leave_end"));
    }


    public void
    addComboBoxItems ()
    {
        ObservableList<String> items
                = FXCollections.observableArrayList ("Emergency", "Sick", "Vacation");
        comboBox_selected_request.setItems (items);
    }

    boolean
    isLeaveOccupied (String eid, String estart_leave, String eend_leave)
    {
        try
        {
            BufferedReader br = new BufferedReader (new FileReader (MainApp.LEAVE_CSV));
            String[] line;
            while (br.readLine () != null)
            {
                try {
                    line = br.readLine ().split (",");
                    if (eid.equals (line[0]))
                    {
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        // convert the string from tsv to Date
                        Date date_start = sdf.parse(line[4]);
                        Date date_end = sdf.parse(line[5]);
                        // convert the string input of the user to Date
                        Date input_date_start = sdf.parse(estart_leave);
                        Date input_date_end = sdf.parse(eend_leave);

                        Calendar tsv_start_calendar = Calendar.getInstance();
                        Calendar tsv_end_calendar = Calendar.getInstance();
                        Calendar input_start_calendar = Calendar.getInstance();
                        Calendar input_end_calendar = Calendar.getInstance();

                        tsv_start_calendar.setTime(date_start);
                        tsv_end_calendar.setTime(date_end);
                        input_start_calendar.setTime(input_date_start);
                        input_end_calendar.setTime(input_date_end);

                        int tsv_start_dayOfYear = tsv_start_calendar.get(Calendar.DAY_OF_YEAR);
                        int tsv_end_dayOfYear = tsv_end_calendar.get(Calendar.DAY_OF_YEAR);
                        int input_start_dayOfYear = input_start_calendar.get(Calendar.DAY_OF_YEAR);
                        int input_end_dayOfYear = input_end_calendar.get(Calendar.DAY_OF_YEAR);

                        System.out.println("🗓️tsv_start_dayOfYear  = " + tsv_start_dayOfYear);
                        System.out.println("🗓️tsv_end_dayOfYear  = " + tsv_end_dayOfYear);
                        System.out.println("🗓️input_start_dayOfYear  = " + input_start_dayOfYear);
                        System.out.println("🗓️input_end_dayOfYear  = " + input_end_dayOfYear);
                        if (estart_leave.equals (line[4]) && eend_leave.equals (line[5]))
                        {
                            return true;
                        }
                        if (input_start_dayOfYear <= tsv_start_dayOfYear && input_end_dayOfYear >= tsv_end_dayOfYear)
                        {
                            return true;
                        }
                    }
                }
                catch (Exception e) {
                    // br.readline is null
                    return false;
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException (e);
        }
        return false;
    }

    public void onClickedSalary(ActionEvent actionEvent) {
        try {
            SceneController.salaryScene(actionEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

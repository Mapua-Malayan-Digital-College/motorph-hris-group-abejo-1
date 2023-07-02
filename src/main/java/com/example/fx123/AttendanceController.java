package com.example.fx123;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AttendanceController implements Runnable {
    private String getTableSelectedItemString;

    @FXML private TableView<Attendance> attendanceTableView;

    @FXML private Button btn_attendance;

    @FXML private TableColumn<Attendance, String> date;

    @FXML private DatePicker datePicker;

    @FXML private TableColumn<Attendance, Integer> employee_number;

    @FXML private TableColumn<Attendance, String> f_name;

    @FXML private TableColumn<Attendance, String> l_name;

    @FXML private TextField tf_employee_number;

    @FXML private TextField tf_fName;

    @FXML private TextField tf_lName;

    @FXML private TextField tf_search;

    @FXML private TextField tf_timeIN;

    @FXML private TextField tf_timeOUT;

    @FXML private TableColumn<Attendance, String> timeIn;

    @FXML private TableColumn<Attendance, String> timeOut;

    @FXML private Button btn_cancel;

    @FXML private Button btn_save;

    @FXML private Button btn_delete;

    @FXML private Label lbl_attendance_size;

    // This will be use to check the search bar if it has values
    private int tableViewSelectedLineNumber;
    @FXML
    void filterTableData(ActionEvent event) {
        int employeeCounter = 0;
        String searchText = tf_search.getText().toLowerCase();
        // Clear previous filtering
        attendanceTableView.getItems().clear();

        // Filter data based on search text
        for (Attendance item : Attendance.records) {
            if (item.toString().toLowerCase().contains(searchText)) {
                attendanceTableView.getItems().add(item);
                employeeCounter++;
            } else if (item.getFullName().toLowerCase().contains(searchText)) {
                attendanceTableView.getItems().add(item);
                employeeCounter++;
            };
        }
        // Change Employee Number Size
        lbl_attendance_size.setText(String.valueOf(employeeCounter));
    }

    @FXML
    void handleDeleteAttendanceClick(ActionEvent event) {
        try {
            CsvUtils.deleteEmployeeRecordByLineNumber(
                    MainApp.ATTENDANCE_CSV, tableViewSelectedLineNumber + 2);
            refreshAttendanceList(event);
            run();
            clearTextField(event);
            btn_attendance.requestFocus();
            btn_save.setDisable(true);
            btn_cancel.setDisable(true);
            // Reset attribute
            btn_save.setText("Save");
            System.out.println(Attendance.records.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void onLogoutClicked(ActionEvent event) {
        try {
            SceneController.loginScene(event);
        } catch (IOException e) {
            System.out.println("Cannot load login scene...");
        }
    }

    public void setCellValueFactoryTableColumns() {
        employee_number.setCellValueFactory(
                new PropertyValueFactory<Attendance, Integer>("employee_number"));
        l_name.setCellValueFactory(
                new PropertyValueFactory<Attendance, String>("l_name"));
        f_name.setCellValueFactory(
                new PropertyValueFactory<Attendance, String>("f_name"));
        date.setCellValueFactory(
                new PropertyValueFactory<Attendance, String>("date"));
        timeIn.setCellValueFactory(
                new PropertyValueFactory<Attendance, String>("timeIn"));
        timeOut.setCellValueFactory(
                new PropertyValueFactory<Attendance, String>("timeOut"));
    }

    public void handleEmployeeClick(ActionEvent actionEvent) {
        try {
            SceneController.employeeScene(actionEvent);
            Attendance.clearAttendanceRecord();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        setCellValueFactoryTableColumns();
        if (Attendance.records.isEmpty())
            Attendance.addAllAttendanceRecord();
        ObservableList<Attendance> list =
                FXCollections.observableArrayList(Attendance.records);
        attendanceTableView.setItems(list);
        tableViewSelectedItemListener();
        btn_attendance.requestFocus();
        lbl_attendance_size.setText(String.valueOf(Attendance.records.size()));
    }

    public void initialize() {
        run();
    }

    public void handleCreateNewAttendanceClick(ActionEvent actionEvent) {
        System.out.println("Calling handleCreateNewAttendanceClick");
        tf_employee_number.requestFocus();
        btn_cancel.setDisable(false);
        btn_save.setDisable(false);
        // Enable TextFields
        enableTextFields();
        // set fields to blank
        tf_employee_number.setText("");
        tf_lName.setText("");
        tf_fName.setText("");
        datePicker.setValue(null);
        tf_timeIN.setText("");
        tf_timeOUT.setText("");
        tf_employee_number.requestFocus();
    }

    public void handleCancelClick(ActionEvent actionEvent) {
        clearTextField(actionEvent);
        btn_save.setText("Save");
        btn_attendance.requestFocus();
        btn_cancel.setDisable(true);
        btn_save.setDisable(true);
        btn_delete.setDisable(true);
        disableTextFields();
    }
    public void handleSaveClick(ActionEvent actionEvent) throws IOException {


        /**
         * Create Attendance
         */
        if (isAttendanceFieldsBlank() && btn_save.getText().equals("Save")) {
            System.out.println(attendanceDetailsTextFieldToCSVString());
            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(MainApp.ATTENDANCE_CSV, true));
            // 'true' flag is used to append data to the existing file.
            // Write the new employee details to the file
            writer.write(
                    attendanceDetailsTextFieldToCSVString()); // Assuming you have a
            // method to convert an
            // employee object to a
            // string

            // Add a new line after writing the employee details
            writer.newLine();

            // Close the writer
            writer.close();
            afterCreateOrUpdateAttendance(actionEvent);
            lbl_attendance_size.setText(String.valueOf(Attendance.records.size()));

        }
        /**
         * Update Attendace
         */
        if (btn_save.getText().equals("Update")) {
            if (isEmployeeNumberExist((tf_employee_number.getText()))) {
               System.out.println(getTableSelectedItemString);
               try {
                   CsvUtils.updateAttendanceCSVViaOldStringtoNewString(getTableSelectedItemString, attendanceDetailsTextFieldToCSVString());
                   afterCreateOrUpdateAttendance(actionEvent);
               } catch (IOException e) {
                       e.printStackTrace();
                       throw new RuntimeException(e);
                    };
                }
            }
        }

    public void afterCreateOrUpdateAttendance(ActionEvent actionEvent) {
        refreshAttendanceList(actionEvent);
        run();
        clearTextField(actionEvent);
        disableTextFields();
        btn_attendance.requestFocus();

        btn_save.setDisable(true);
        btn_cancel.setDisable(true);
        btn_save.setText("Save");
    }

    private String attendanceDetailsTextFieldToCSVString() {
        try {
            return (tf_employee_number.getText()
                    + ","
                    + tf_lName.getText()
                    + ","
                    + tf_fName.getText()
                    + ","
                    + datePicker.getValue().format(
                    DateTimeFormatter.ofPattern("M/d/yyyy"))
                    + ","
                    + tf_timeIN.getText()
                    + ","
                    + tf_timeOUT.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No selected date");
            alert.setContentText("Please select date before you save.");
            alert.show();
        }
        return null;
    }


    public void clearTextField(ActionEvent actionEvent) {
        tf_employee_number.setText("");
        tf_fName.setText("");
        tf_lName.setText("");
        tf_timeIN.setText("");
        tf_timeOUT.setText("");
        datePicker.setValue(null);
    }
    public void tableViewSelectedItemListener() {
        attendanceTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, attendance) -> {
                    if (attendance != null) {

                        this.getTableSelectedItemString = attendance.toCommaSeparatedValueString();
                        tableViewSelectedLineNumber =
                                attendanceTableView.getSelectionModel().getSelectedIndex();
                        // update the value via save button
                        btn_save.setText("Update");

                        // print employee data via console.
                        System.out.println(attendance.toString());

                        DateTimeFormatter formatterForFourDigitYear =
                                DateTimeFormatter.ofPattern("M/d/yyyy");
                        DateTimeFormatter formatterForTwoDigitYear =
                                DateTimeFormatter.ofPattern("M/d/yy");

                        LocalDate attendanceValue = null;

                        try {
                            attendanceValue = LocalDate.parse(
                                    attendance.getDate(), formatterForFourDigitYear);
                        } catch (Exception e) {
                            attendanceValue = LocalDate.parse(
                                    attendance.getDate(), formatterForTwoDigitYear);
                        }

                        /**
                         * Update the textfields if there is an selected item on tableview
                         * textfields
                         */

                        tf_employee_number.setText(
                                String.valueOf(attendance.getEmployee_number()));
                        tf_fName.setText(attendance.getF_name());
                        tf_lName.setText(attendance.getL_name());
                        datePicker.setValue(attendanceValue);
                        tf_timeIN.setText(attendance.getTimeIn());
                        tf_timeOUT.setText(attendance.getTimeOut());
                        enableTextFields();

                        /**
                         * Set save and cancel button to enabled because we have now
                         * selected item, we can update it via save button and cancel to
                         * terminate the update.
                         */
                        btn_save.setText("Update");
                        btn_cancel.setDisable(false);
                        btn_save.setDisable(false);
                        btn_delete.setDisable(false);
                    }
                });
    }

    public void refreshAttendanceList(ActionEvent actionEvent) {
        // Clear Attenance Record
        Attendance.clearAttendanceRecord();
        // aDD All attendance
        Attendance.addAllAttendanceRecord();
    }

    public boolean isAttendanceFieldsBlank() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(datePicker == null) {
            alert.setTitle("Blank attendance date");
            alert.setContentText("Please select the calendar icon and choose attendance date.");
            alert.show();
            datePicker.requestFocus();
            return false;
        }


        TextField[] textFields = {
                tf_employee_number,
                tf_fName,
                tf_lName,
                tf_timeIN,
                tf_timeOUT,
        };
        String [] strTextFieldsName = {
                "Employee number",
                "First name",
                "Last name",
                "Time in",
                "Time out"
        };
        for (int i = 0; i < textFields.length; i++) {
            if (textFields[i].getText().equals("")) {
                alert.setTitle("Blank Input Value");
                alert.setContentText(strTextFieldsName[i] + " textfield needs a value to be entered.");
                alert.show();
                textFields[i].requestFocus();
                return false;
            }
        }

        String pattern = "\\d";

        if (!isEmployeeNumberExist(tf_employee_number.getText())) {
            if (!tf_employee_number.getText().matches(pattern)) {
                alert.setTitle("Invalid Employee Number");
                alert.setContentText("Employee number entered invalid :" + tf_employee_number.getText());
                alert.show();
                tf_employee_number.requestFocus();
                return false;
            }
            alert.setTitle("Employee Number " + tf_employee_number.getText() + " not found");
            alert.setContentText("Employee number doesn't exist...");
            alert.show();
            tf_employee_number.requestFocus();
            return false;
        }
        else if (!isNotTimeDigitOnly(tf_timeIN)) {
            alert.setTitle("Invalid time in input");
            alert.setHeaderText("Your Time in input = " + tf_timeIN.getText());
            alert.setContentText("Please follow the format: hh:mm ");
            alert.show();
            tf_timeIN.requestFocus();
            return false;
        }

        else if (!isNotTimeDigitOnly(tf_timeOUT)) {
            alert.setTitle("Invalid time out input");
            alert.setHeaderText("Your Time in input = " + tf_timeOUT.getText());
            alert.setContentText("Please follow the format: hh:mm ");
            alert.show();
            tf_timeOUT.requestFocus();
            return false;
        }
        else {
            return true;
        }
    }


    private boolean isNotTimeDigitOnly(TextField tf) {
        String strTime = tf.getText().replace(":","");
        try{
            int numTimeIn = Integer.parseInt(strTime);
            boolean isTimeInDigitsOnly = String.valueOf(Integer.valueOf(numTimeIn)).matches("[0-9]+");
            if (isTimeInDigitsOnly){
                System.out.println("Yes it is digit only");
                return true;
            }
        }
        catch (NumberFormatException exception) {
            System.out.println("No it is not string digit only "+exception.getMessage());
        }
        return false;
    }

    public static boolean isEmployeeNumberExist(String employeeNumber) {
        for (int i = 0; i < Employees.records.size(); i++)
            if (Employees.records.get(i).getId().equals(employeeNumber))
                return true;

        return false;
    }

    public void enableTextFields() {
        tf_timeOUT.setDisable(false);
        tf_timeIN.setDisable(false);
        tf_fName.setDisable(false);
        tf_lName.setDisable(false);
        tf_employee_number.setDisable(false);
        datePicker.setDisable(false);
    }

    public void disableTextFields() {
        tf_timeOUT.setDisable(true);
        tf_timeIN.setDisable(true);
        tf_fName.setDisable(true);
        tf_lName.setDisable(true);
        tf_employee_number.setDisable(true);
        datePicker.setDisable(true);
    }

    public void onClckedEmployee(ActionEvent actionEvent) {
        try {
            SceneController.employeeScene(actionEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onClickedAttendance(ActionEvent actionEvent) {
        try {
            Attendance.records.clear();
            SceneController.attendanceScene(actionEvent);
            btn_attendance.requestFocus();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onClickedLeaves(ActionEvent actionEvent) {
        try {
            SceneController.leavesScene(actionEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onClickedSalary(ActionEvent actionEvent) {
        try {
            SceneController.salaryScene(actionEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

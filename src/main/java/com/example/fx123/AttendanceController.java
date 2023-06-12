package com.example.fx123;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AttendanceController implements Runnable{

    @FXML
    private TableView<Attendance> attendanceTableView;

    @FXML
    private Button btn_attendance;

    @FXML
    private TableColumn<Attendance, String> date;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableColumn<Attendance, Integer> employee_number;

    @FXML
    private TableColumn<Attendance, String> f_name;

    @FXML
    private TableColumn<Attendance, String> l_name;

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
    private Button btn_cancel;

    @FXML
    private Button btn_save;

    @FXML
    private Button btn_delete;

    private boolean isCreateNewAttendance;
    private int tableViewSelectedLineNumber;
    @FXML
    void filterTableData(ActionEvent event) {

    }

    @FXML
    void handleDeleteAttendanceClick(ActionEvent event) {
        try {
            TsvUtils.deleteEmployeeRecordByLineNumber(MainApp.ATTENDANCE_TSV,tableViewSelectedLineNumber + 2);
            isCreateNewAttendance = false;
            refreshEmployeeList(event);
            run();
            clearTextField(event);
            btn_attendance.requestFocus();
            btn_save.setDisable(true);
            btn_cancel.setDisable(true);
            // Reset attribute
            isCreateNewAttendance = false;
            btn_save.setText("Save");
        }
        catch (Exception e) {
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
        employee_number.setCellValueFactory(new PropertyValueFactory<Attendance,Integer>("employee_number"));
        l_name.setCellValueFactory(new PropertyValueFactory<Attendance,String>("l_name"));
        f_name.setCellValueFactory(new PropertyValueFactory<Attendance,String>("f_name"));
        date.setCellValueFactory(new PropertyValueFactory<Attendance,String>("date"));
        timeIn.setCellValueFactory(new PropertyValueFactory<Attendance,String>("timeIn"));
        timeOut.setCellValueFactory(new PropertyValueFactory<Attendance,String>("timeOut"));
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
        Attendance.addAllAttendanceRecord();
        ObservableList<Attendance> list = FXCollections.observableArrayList(Attendance.records);
        attendanceTableView.setItems(list);

        tableViewSelectedItemListener();
        btn_attendance.requestFocus();
    }

    public void initialize() {
        run();
    }

    public void handleCreateNewAttendanceClick(ActionEvent actionEvent) {
        tf_employee_number.requestFocus();
        btn_cancel.setDisable(false);
        btn_save.setDisable(false);
        isCreateNewAttendance = true;
        // Enable TextFields
        enableTextFields();
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

        if (isAllowedToCreateAttendance() && isCreateNewAttendance) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(MainApp.ATTENDANCE_TSV, true));
            // 'true' flag is used to append data to the existing file.
            // Write the new employee details to the file
            writer.write(attendanceDetailsTextFieldToTabString()); // Assuming you have a method to convert an employee object to a string

            // Add a new line after writing the employee details
            writer.newLine();

            // Close the writer
            writer.close();
            afterCreateOrUpdateAttendance(actionEvent);
        }
        else {
            if (isEmployeeNumberExist((tf_employee_number.getText()))) {
                try{
                    String [] newValues = attendanceDetailsTextFieldToTabString().split("\t");
                    TsvUtils.updateEmployeeNumberByLineNumber(MainApp.ATTENDANCE_TSV,tableViewSelectedLineNumber+2,newValues);
                    clearTextField(actionEvent);
                    afterCreateOrUpdateAttendance(actionEvent);
                }
                catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("There is no date on your field.");
                    alert.setHeaderText(null);
                    alert.setContentText("You can use datepicker to select date or you can input mm/dd/yy on the datefield");
                    alert.showAndWait();
                }
            }
        }
    }

    public void afterCreateOrUpdateAttendance(ActionEvent actionEvent) {
        isCreateNewAttendance = false;
        refreshEmployeeList(actionEvent);
        run();
        clearTextField(actionEvent);
        disableTextFields();
        btn_attendance.requestFocus();

        btn_save.setDisable(true);
        btn_cancel.setDisable(true);
        btn_save.setText("Save");
    }

    private String attendanceDetailsTextFieldToTabString() {

        try{
            return
                    (tf_employee_number.getText().isEmpty() ? "Unknown" : tf_employee_number.getText()) + "\t"+
                            (tf_lName.getText().isEmpty() ? "Unknown" : tf_lName.getText()) + "\t"+
                            (tf_fName.getText().isEmpty() ? "Unknown" : tf_fName.getText()) + "\t"+
                            (datePicker.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + "\t"+
                                    (tf_timeIN.getText().isEmpty() ? "0:00" : tf_timeIN.getText()) + "\t" +
                                    (tf_timeOUT.getText().isEmpty() ? "0:00" : tf_timeOUT.getText()) + "\t");
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No selected date");
            alert.setContentText("Please select date before you save.");
            alert.showAndWait();
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

        attendanceTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, attendance) -> {
            if (attendance != null) {
                tableViewSelectedLineNumber = attendanceTableView.getSelectionModel().getSelectedIndex();
                // update the value via save button
                isCreateNewAttendance = false;

                // print employee data via console.
                System.out.println(attendance.toString());

                DateTimeFormatter formatterForFourDigitYear = DateTimeFormatter.ofPattern("M/d/yyyy");
                DateTimeFormatter formatterForTwoDigitYear = DateTimeFormatter.ofPattern("M/d/yy");

                LocalDate attendanceValue = null;

                try {
                    attendanceValue = LocalDate.parse(attendance.getDate(),formatterForFourDigitYear);
                }
                catch (Exception e) {
                    attendanceValue = LocalDate.parse(attendance.getDate(),formatterForTwoDigitYear);
                }

                /**
                 * Update the textfields if there is an selected item on tableview textfields
                 */

                tf_employee_number.setText(String.valueOf(attendance.getEmployee_number()));
                tf_fName.setText(attendance.getF_name());
                tf_lName.setText(attendance.getL_name());
                datePicker.setValue(attendanceValue);
                tf_timeIN.setText(attendance.getTimeIn());
                tf_timeOUT.setText(attendance.getTimeOut());
                enableTextFields();

                /**
                 * Set save and cancel button to enabled because we have now selected item,
                 * we can update it via save button and cancel to terminate the update.
                 */
                btn_save.setText("Update");
                btn_cancel.setDisable(false);
                btn_save.setDisable(false);
                btn_delete.setDisable(false);
            }
        });
    }

    public void refreshEmployeeList(ActionEvent actionEvent) {
        // Clear Employees Record
        Attendance.clearAttendanceRecord();
    }

    public boolean isAllowedToCreateAttendance() {
        TextField [] arrTextFields = {
                tf_employee_number,
                tf_lName,
                tf_fName,
                tf_timeIN,
                tf_timeOUT,
        };
        for (TextField textField : arrTextFields) {
            if (textField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Blank Input Value");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a value in the text field.");
                alert.showAndWait();
                textField.requestFocus();
                return false;
            }
        }
        if (datePicker.getValue()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank date value");
            alert.setHeaderText(null);
            alert.setContentText("Please select a value in the datepicker.");
            alert.showAndWait();
            return false;
        }


        if (!isEmployeeNumberExist(tf_employee_number.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Employee Number");
            alert.setHeaderText(null);
            alert.setContentText("Employee number doesn't exist...");
            alert.showAndWait();
            tf_employee_number.requestFocus();
            return false;
        }
        System.out.println("Goodbye isAllowedToCreateAttendance()");
        return true;
    }

    public static boolean isEmployeeNumberExist(String employeeNumber) {
        for (int i = 0; i < Employees.records.size(); i++)
            if (Employees.records.get(i).getId().equals(employeeNumber)) return true;

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
}
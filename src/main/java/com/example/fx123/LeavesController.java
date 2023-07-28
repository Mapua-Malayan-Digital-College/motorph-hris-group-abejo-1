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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LeavesController implements Runnable {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/d/yyyy");
    private int tableViewSelectedLineNumber;

    public int getTableViewSelectedLineNumber() {
        return tableViewSelectedLineNumber;
    }

    public void setTableViewSelectedLineNumber(int tableViewSelectedLineNumber) {
        this.tableViewSelectedLineNumber = tableViewSelectedLineNumber;
    }

    @FXML
    private Button btn_cancel;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_leaves;

    @FXML
    private Button btn_save_right;

    @FXML
    private Button btn_spent_credits;

    @FXML
    private ComboBox<String> comboBox_selected_request;


    @FXML
    private DatePicker dp_leave_date;

    @FXML
    private TableColumn<Leaves, Integer> eid;

    @FXML
    private TableView<Leaves> leavesTableView;

    @FXML
    private TableColumn<Leaves, String> first_name;

    @FXML
    private TableColumn<Leaves, String> last_name;

    @FXML
    private TableColumn<Leaves, String> leave_date;

    @FXML
    private TableColumn<Leaves, String> leave_type;

    @FXML
    private TextField tf_employee_number;

    @FXML
    private TextField tf_fName;

    @FXML
    private TextField tf_lName;

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
        btn_leaves.requestFocus();
    }

    @FXML
    void onClickedDeleteLeave(ActionEvent event) {
        CsvUtils.deleteEmployeeRecordByLineNumber(
                MainApp.LEAVE_CSV, getTableViewSelectedLineNumber()+2);
        Leaves.RECORDS.clear();
        run();
        lbl_num_emergency_result.setText("0");
        lbl_num_sick_result.setText("0");
        lbl_num_vacation_result.setText("0");
        btn_cancel.setDisable(true);
        btn_delete.setDisable(true);
        btn_save_right.setDisable(true);
        btn_spent_credits.setDisable(true);
    }

    @FXML
    void onClickedLogout(ActionEvent event) {
        try {
            SceneController.loginScene(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createEmployeeLeave(Leaves leaves, ActionEvent event) throws ParseException {
        leaves.createEmployeeLeave();
        Leaves.RECORDS.clear();
        onClickedCancel(event);
    }

    @FXML
    void onSaveLeaveClicked(ActionEvent event) throws IOException, ParseException {
        /**
         * Crete Leave
         */
        if (btn_save_right.getText().equalsIgnoreCase("save")) {
            System.out.println("Create Leave Crust");
            if (isFieldsNotBlank()) { // check if all fields has values
                System.out.println("Create Leave Mantle");
                if (!isLeaveDateDuplicate()) { // if it is not duplicate we can proceed
                    System.out.println("Create Leave Outer Core");
                    if (hasCreditsLeft()) { // check if employee has credits left for their choosen leave request
                        System.out.println("Create Leave Inner Core");
                        Leaves employeeLeave = new Leaves(Integer.parseInt(tf_employee_number.getText()),tf_lName.getText(),tf_fName.getText(),comboBox_selected_request.getValue(),dp_leave_date.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy")));
                        createEmployeeLeave(employeeLeave,event);
                        refreshLeaveTbl();
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Fully consumed credits");
                        alert.setContentText("\"Credits fully consumed for " + comboBox_selected_request.getValue());
                        alert.show();
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Duplicate Entry");
                    alert.setContentText(tf_employee_number.getText()+" "+ "has already filed leave for date " + dp_leave_date.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy")));
                    alert.show();
                }
            }
        }
    }

    private boolean hasCreditsLeft() throws IOException {
        int [] arrOfCreditsLeft = new int[4];
        /**
         * [0] = emergency
         * [1] = sick
         * [2] = vacation
         */

        /**
         * Increment the credits by checking eid and leave request type
         */

        BufferedReader br = new BufferedReader(new FileReader(MainApp.LEAVE_CSV));
            String line;
            boolean isheader = true;
            while((line = br.readLine()) != null) {
                String [] arr = line.split(",");

                if (isheader) {
                    isheader = false;
                    continue;
                }
                if (arr[0].equals(tf_employee_number.getText())
                        && arr[3].equals(comboBox_selected_request.getValue())) {
                    switch (arr[3].toLowerCase()) {
                        case "emergency" : {
                            arrOfCreditsLeft[0]+=1;
                            break;
                        }
                        case "sick" : {
                            arrOfCreditsLeft[1]+=1;
                            break;
                        }
                        case "vacation" : {
                            arrOfCreditsLeft[2]+=1;
                            break;
                        }
                    }
                }
            }


        Alert alert = new Alert(Alert.AlertType.ERROR);

        if (comboBox_selected_request.getValue().equals("Emergency")) {
            if (arrOfCreditsLeft[0] < Leaves.MAX_EMERGENCY_LEAVES) {
                return true;
            }
            else {
                alert.setTitle("Emergency request fully consumed");
                alert.setHeaderText("You do not have enough emergency credits.");
            }
        }

        else if (comboBox_selected_request.getValue().equals("Sick")) {
            if (arrOfCreditsLeft[1] < Leaves.MAX_EMERGENCY_LEAVES) {
                return true;
            }
            else {
                alert.setTitle("Sick request fully consumed");
                alert.setHeaderText("You do not have enough Sick credits.");
            }
        }

        else if (comboBox_selected_request.getValue().equals("Vacation")) {
            if (arrOfCreditsLeft[2] < Leaves.MAX_VACATION_LEAVES) {
                return true;
            }
            else {
                alert.setTitle("Vacation request fully consumed");
                alert.setHeaderText("You do not have enough vacation credits.");
            }
        }
        return false;
    }

    public boolean isFieldsNotBlank() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        /**
         * Check if datepicker has null value
         */
        if (dp_leave_date.getValue() == null) {
            alert.setTitle("Blank value for leave date.");
            alert.setContentText("Please select calendar icon and select leave date");
            alert.show();
            return false;
        }
            TextField [] arrTextField = {
                    tf_employee_number,
                    tf_fName,
                    tf_lName,
            };
            String [] arrFieldName = {
                    "Employee number",
                    "First name",
                    "Last name",
            };
            /**
             * Loop text field and check if it is blank
             */
            for (int i = 0; i < arrFieldName.length; i++) {
                if (arrTextField[i].getText().isEmpty()) {
                    alert.setTitle("No input.");
                    alert.setContentText("Please enter " + arrFieldName[i] +" to proceed.");
                    alert.show();
                    arrTextField[i].requestFocus();
                    return false;
                }
            }
        /**
         * Check if leave request blank
         */
        if (comboBox_selected_request.getValue().isEmpty()) {
            alert.setHeaderText("Blank value for leave type");
            alert.setContentText("PLease select value for your leave request type");
            alert.show();
            comboBox_selected_request.requestFocus();
            return false;
        }
        return true;
    }

    void refreshLeaveTbl() {
        btn_save_right.setText("Save");
        Leaves.RECORDS.clear();
        run();
    }

    @FXML
    void
    onClickedCancel(ActionEvent event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        LocalDate localDate = LocalDate.parse("January 1, 2022", formatter);
        tf_employee_number.setText("");
        tf_fName.setText("");
        tf_lName.setText("");
        comboBox_selected_request.setValue("Select request -");
        dp_leave_date.setValue(localDate);
        btn_save_right.setText("Save");
        lbl_num_emergency_result.setText("0");
        lbl_num_sick_result.setText("0");
        lbl_num_vacation_result.setText("0");
        disableFields();
        btn_leaves.requestFocus();
    }

    @FXML
    void
    onClickedSetNewLeave(ActionEvent event) {
        enableFields();

        tf_employee_number.requestFocus();
        btn_save_right.setText("Save");
        btn_cancel.setDisable(false);
        btn_save_right.setDisable(false);
        btn_spent_credits.setDisable(false);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        LocalDate localDateStart = LocalDate.parse("January 1, 2022", formatter);
        tf_employee_number.setText("");
        tf_fName.setText("");
        tf_lName.setText("");
        comboBox_selected_request.setValue("Select request -");
        dp_leave_date.setValue(localDateStart);
        btn_save_right.setText("Save");
        tf_employee_number.requestFocus();
    }

    private int [] credits_left = new int[3];

    public void
    tableViewSelectedItemListener() {
        leavesTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newValue) -> {
                    if (newValue != null) {


                        setTableViewSelectedLineNumber(leavesTableView.getSelectionModel().getSelectedIndex());

                        enableFields();

                        /**
                         * Update the textfields if there is an selected item on tableview
                         * textfields
                         */

                        tf_employee_number.setText(String.valueOf(newValue.getEid()));
                        tf_lName.setText(newValue.getLast_name());
                        tf_fName.setText(newValue.getFirst_name());
                        comboBox_selected_request.setValue(newValue.getLeave_type());

                        /**
                         * We want to set the leave.getLeave_date with String data type into Datepicker
                         */

                        DateTimeFormatter dtf
                                = DateTimeFormatter.ofPattern("M/d/yyyy");
                        LocalDate localDate = LocalDate.from(dtf.parse(newValue.getLeave_date().replaceAll("\\s","")));


                        dp_leave_date.setValue(localDate);
                        /**
                         * Set save and cancel button to enabled because we have now
                         * selected item, we can update it via save button and cancel to
                         * terminate the update.
                         */
                        btn_save_right.setDisable(true);
                        btn_delete.setDisable(false);
                        btn_cancel.setDisable(false);
                        btn_spent_credits.setDisable(false);
                        String[] creditsleave;
                        try {
                            creditsleave = newValue.getConsumedCredits().split("\t");
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }

                        this.credits_left[0] = Integer.valueOf(creditsleave[0]);
                        this.credits_left[1] = Integer.valueOf(creditsleave[1]);
                        this.credits_left[2] = Integer.valueOf(creditsleave[2]);

                        lbl_num_emergency_result.setText(creditsleave[0]);
                        lbl_num_sick_result.setText(creditsleave[1]);
                        lbl_num_vacation_result.setText(creditsleave[2]);
                    }
                });
    }

    @Override
    public void
    run() {
        if (Leaves.RECORDS.isEmpty()) Leaves.addAllLeaves();
        tableViewSelectedItemListener();
        ObservableList<Leaves> list
                = FXCollections.observableArrayList(Leaves.RECORDS);
        leavesTableView.setItems(list);
        addComboBoxItems();


        btn_delete.setDisable(true);
        btn_cancel.setDisable(true);
        btn_save_right.setDisable(true);
        btn_spent_credits.setDisable(true);
    }

    public void enableFields() {
        tf_employee_number.setDisable(false);
        tf_fName.setDisable(false);
        tf_lName.setDisable(false);
        dp_leave_date.setDisable(false);
        comboBox_selected_request.setDisable(false);
    }

    public void disableFields() {
        tf_employee_number.setDisable(true);
        tf_fName.setDisable(true);
        tf_lName.setDisable(true);
        dp_leave_date.setDisable(true);
        comboBox_selected_request.setDisable(true);
        btn_cancel.setDisable(true);
        btn_save_right.setDisable(true);
        btn_delete.setDisable(true);
        btn_spent_credits.setDisable(true);
    }

    public void
    initialize() {
        setCellValueFactoryTableColumns();
        run();
    }

    private void
    setCellValueFactoryTableColumns() {
        eid.setCellValueFactory(
                new PropertyValueFactory<>("eid"));
        last_name.setCellValueFactory(
                new PropertyValueFactory<>("last_name"));
        first_name.setCellValueFactory(
                new PropertyValueFactory<>("first_name"));
        leave_type.setCellValueFactory(
                new PropertyValueFactory<>("leave_type"));
        leave_date.setCellValueFactory(
                new PropertyValueFactory<>("leave_date"));
    }


    public void
    addComboBoxItems() {
        ObservableList<String> items
                = FXCollections.observableArrayList("Emergency", "Sick", "Vacation");
        comboBox_selected_request.setItems(items);
    }

    public boolean
    isLeaveDateDuplicate() {
        boolean isDuplicate = false; // assume that there is no duplicate yet
        try {
            BufferedReader br = new BufferedReader(new FileReader(MainApp.LEAVE_CSV));
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                    if (isHeader) {
                        isHeader = false;
                        continue;
                    }
                    String[] arrline = line.split(",");

                    if (tf_employee_number.getText().equals(arrline[0])
                            &&
                            (dp_leave_date.getValue().format(DateTimeFormatter.ofPattern("M/d/yyyy"))).equals(arrline[4])) {
                        isDuplicate = true;
                        break;
                    }
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return isDuplicate;
    }

    public void onClickedSalary(ActionEvent actionEvent) {
        try {
            SceneController.salaryScene(actionEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onActionEmployeeNUmberTF(ActionEvent actionEvent) throws ParseException {
        if (isEmployeeNumberExist()) {
            updateCreditsSpentUI();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Employee Number not found.");
            alert.setContentText("Employee Number " + tf_employee_number.getText() + " does not exist.");
            alert.show();
            tf_employee_number.requestFocus();
        }
    }

    public void onClickedSpentCredits(ActionEvent actionEvent) throws ParseException {
        if (isEmployeeNumberExist()) {
            updateCreditsSpentUI();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Employee Number not found.");
            alert.setContentText("Employee Number " + tf_employee_number.getText() + " does not exist.");
            alert.show();
            tf_employee_number.requestFocus();
        }
    }

    public void updateCreditsSpentUI() throws ParseException {
        int emergency_counter = 0, sick_counter = 0, vacation_counter = 0;

        for (int i = 0; i < Leaves.RECORDS.size(); i++) {
            if (Integer.valueOf(tf_employee_number.getText()).equals(Leaves.RECORDS.get(i).getEid())) {
                Calendar leave_date = Calendar.getInstance();
                Date date_leave = simpleDateFormat.parse(Leaves.RECORDS.get(i).getLeave_date());
                leave_date.setTime(date_leave);
                switch (Leaves.RECORDS.get(i).getLeave_type().toLowerCase()) {
                    case "sick" -> sick_counter++;
                    case "vacation" -> vacation_counter++;
                    case "emergency" -> emergency_counter++;
                }
            }
        }
        lbl_num_emergency_result.setText(String.valueOf(emergency_counter));
        lbl_num_sick_result.setText(String.valueOf(sick_counter));
        lbl_num_vacation_result.setText(String.valueOf(vacation_counter));
    }

    public boolean isEmployeeNumberExist() {
        for (int i = 0; i < Employees.records.size(); i++) {
            if (Employees.records.get(i).getId().equals(String.valueOf(tf_employee_number.getText()))) {
                return true;
            }
        }
        return false;
    }

    public void cb_onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.SPACE) {
            comboBox_selected_request.show();
            keyEvent.consume();
        }
    }
}

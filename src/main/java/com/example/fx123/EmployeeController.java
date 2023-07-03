package com.example.fx123;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class EmployeeController implements Runnable {
    /**
     * Start of Attributes
     */
    @FXML private Label lbl_employeeSize;

    @FXML private TableColumn<Employees, String> address;

    @FXML private TableColumn<Employees, Integer> basic_salary;

    @FXML private TableColumn<Employees, String> birthday;

    @FXML private Button btn_cancel;

    @FXML private Button btn_deleteSelectedEmployee;

    @FXML private Button btn_employee;

    @FXML private Button btn_saveOrUpdate;

    @FXML private TableColumn<Employees, Integer> clothing_alw;

    @FXML private TableView<Employees> employeeTable;

    @FXML private TableColumn<Employees, String> f_name;

    @FXML private TableColumn<Employees, Integer> gross_semi_monthly_rate;

    @FXML private TableColumn<Employees, Float> hourly_rate;

    @FXML private TableColumn<Employees, String> id;

    @FXML private TableColumn<Employees, String> immediate_supervisor;

    @FXML private TableColumn<Employees, String> l_name;

    @FXML private TableColumn<Employees, String> pagibig_num;

    @FXML private TableColumn<Employees, String> philhealth_num;

    @FXML private TableColumn<Employees, Integer> phone_alw;

    @FXML private TableColumn<Employees, String> phone_num;

    @FXML private TableColumn<Employees, String> position;

    @FXML private TableColumn<Employees, String> rice_subsidy;

    @FXML private TableColumn<Employees, String> sss_num;

    @FXML private TableColumn<Employees, String> status;

    @FXML private TextField tf_address;

    @FXML private TextField tf_basicSalary;

    @FXML private DatePicker dp_birthday;

    @FXML private TextField tf_clothingAllowance;

    @FXML private TextField tf_employee_number;

    @FXML private TextField tf_fName;

    @FXML private TextField tf_grossSemiMonthlyRate;

    @FXML private TextField tf_hourlyRate;

    @FXML private TextField tf_immediateSupervisor;

    @FXML private TextField tf_lName;

    @FXML private TextField tf_pagibig;

    @FXML private TextField tf_philhealth;

    @FXML private TextField tf_phone_number;

    @FXML private TextField tf_phoneAllowance;

    @FXML private TextField tf_position;

    @FXML private TextField tf_riceSubsidy;

    @FXML private TextField tf_sss;

    @FXML private TextField tf_status;

    @FXML private TextField tf_tin;

    @FXML private TableColumn<Employees, String> tin_num;

    @FXML private TextField tf_search;

    private boolean isAddNewEmployee = false;
    private String tableViewSelectedEmployeeNumber;

    /**
     * End of Attributes
     */
    public void initialize() {
        setCellValueFactoryTableColumns();
        run();
    }

    @FXML
    public void refreshEmployeeScene(ActionEvent actionEvent) {
        // Clear Employees Record
        Employees.clearEmployees();
        // Add Employees
        Employees.addAllEmployees();
    }

    /**
     * Sets up the cell value factory for our table column using the
     * setCellValueFactoryTableColumns() method. The resulting structure of the
     * table column will be as follows:
     *
     * <TableColumn>
     * <CellValueFactory>
     * <PropertyValueFactory></PropertyValueFactory>
     * </CellValueFactory>
     * </TableColumn>
     */
    public void setCellValueFactoryTableColumns() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        l_name.setCellValueFactory(new PropertyValueFactory<>("l_name"));
        f_name.setCellValueFactory(new PropertyValueFactory<>("f_name"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        phone_num.setCellValueFactory(new PropertyValueFactory<>("phone_num"));
        sss_num.setCellValueFactory(new PropertyValueFactory<>("sss_num"));
        philhealth_num.setCellValueFactory(
                new PropertyValueFactory<>("philhealth_num"));
        tin_num.setCellValueFactory(new PropertyValueFactory<>("tin_num"));
        pagibig_num.setCellValueFactory(new PropertyValueFactory<>("pagibig_num"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        immediate_supervisor.setCellValueFactory(
                new PropertyValueFactory<>("immediate_supervisor"));
        basic_salary.setCellValueFactory(
                new PropertyValueFactory<>("basic_salary"));
        rice_subsidy.setCellValueFactory(
                new PropertyValueFactory<>("rice_subsidy"));
        phone_alw.setCellValueFactory(new PropertyValueFactory<>("phone_alw"));
        clothing_alw.setCellValueFactory(
                new PropertyValueFactory<>("clothing_alw"));
        gross_semi_monthly_rate.setCellValueFactory(
                new PropertyValueFactory<>("gross_semi_monthly_rate"));
        hourly_rate.setCellValueFactory(new PropertyValueFactory<>("hourly_rate"));
    }

    private String employeeDetailsTextFieldToCsvString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

        String employee_number = tf_employee_number.getText().isEmpty()
                ? "N/A"
                : tf_employee_number.getText(),
                lName = tf_lName.getText().isEmpty() ? "N/A" : tf_lName.getText(),
                fName = tf_fName.getText().isEmpty() ? "N/A" : tf_fName.getText(),
                birthday = dp_birthday.getValue().format(formatter),
                address =
                        tf_address.getText().isEmpty() ? "N/A" : tf_address.getText(),
                phone = tf_phone_number.getText().isEmpty() ? "N/A" : tf_phone_number.getText(),
                sss = tf_sss.getText().isEmpty() ? "N/A" : tf_sss.getText(),
                philhealth = tf_philhealth.getText().isEmpty()
                        ? "N/A"
                        : tf_philhealth.getText(),
                tin = tf_tin.getText().isEmpty() ? "N/A" : tf_tin.getText(),
                pagibig =
                        tf_pagibig.getText().isEmpty() ? "N/A" : tf_pagibig.getText(),
                status = tf_status.getText().isEmpty() ? "N/A" : tf_status.getText(),
                position =
                        tf_position.getText().isEmpty() ? "N/A" : tf_position.getText(),
                immediateSupervisor = tf_immediateSupervisor.getText().equals("")
                        ? "N/A"
                        : tf_immediateSupervisor.getText(),
                basicSalary = tf_basicSalary.getText().isEmpty()
                        ? "0"
                        : tf_basicSalary.getText(),
                riceSubsidy = tf_riceSubsidy.getText().isEmpty()
                        ? "0"
                        : tf_riceSubsidy.getText(),
                phoneAllowance = tf_phoneAllowance.getText().isEmpty()
                        ? "0"
                        : tf_phoneAllowance.getText(),
                clothingAllowance = tf_clothingAllowance.getText().isEmpty()
                        ? "0"
                        : tf_clothingAllowance.getText(),
                grossSemiMonthlyRate = tf_grossSemiMonthlyRate.getText().isEmpty()
                        ? "0"
                        : tf_grossSemiMonthlyRate.getText(),
                hourlyRate = tf_hourlyRate.getText().isEmpty()
                        ? "0"
                        : tf_hourlyRate.getText();

        // we add double quotes to the first index of string
        return employee_number + "," + CsvUtils.addDoubleQuotesIfStringHasComma(lName) + "," +
                CsvUtils.addDoubleQuotesIfStringHasComma(fName) + "," +
                CsvUtils.addDoubleQuotesIfStringHasComma(birthday) + "," +
                CsvUtils.addDoubleQuotesIfStringHasComma(address) + "," +
                CsvUtils.addDoubleQuotesIfStringHasComma(phone) + "," +
                CsvUtils.addDoubleQuotesIfStringHasComma(sss) + "," +
                CsvUtils.addDoubleQuotesIfStringHasComma(philhealth) + "," +
                CsvUtils.addDoubleQuotesIfStringHasComma(tin) + "," +
                CsvUtils.addDoubleQuotesIfStringHasComma(pagibig) + "," +
                CsvUtils.addDoubleQuotesIfStringHasComma(status) + "," +
                CsvUtils.addDoubleQuotesIfStringHasComma(position) + ","  +
                CsvUtils.addDoubleQuotesIfStringHasComma(immediateSupervisor) + "," +
                CsvUtils.addDoubleQuotesIfStringHasComma(CsvUtils.addCommaToStrInt(basicSalary)) + "," +
            CsvUtils.addDoubleQuotesIfStringHasComma(CsvUtils.addCommaToStrInt(riceSubsidy)) + "," +
                CsvUtils.addDoubleQuotesIfStringHasComma(CsvUtils.addCommaToStrInt(phoneAllowance)) + "," +
                CsvUtils.addDoubleQuotesIfStringHasComma(CsvUtils.addCommaToStrInt(clothingAllowance)) + "," +
                CsvUtils.addDoubleQuotesIfStringHasComma(CsvUtils.addCommaAndTwoDecimalsForFloatStr(grossSemiMonthlyRate)) + "," +
                CsvUtils.addDoubleQuotesIfStringHasComma(CsvUtils.addCommaAndTwoDecimalsForFloatStr(hourlyRate));
    }

    private String employeeDetailsTextFieldToTabularSeparatedValue() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

        String employee_number = tf_employee_number.getText().isEmpty()
                ? "N/A"
                : tf_employee_number.getText(),
                lName = tf_lName.getText().isEmpty() ? "N/A" : tf_lName.getText(),
                fName = tf_fName.getText().isEmpty() ? "N/A" : tf_fName.getText(),
                birthday = dp_birthday.getValue().format(formatter),
                address =
                        tf_address.getText().isEmpty() ? "N/A" : tf_address.getText(),
                phone = tf_phone_number.getText().isEmpty() ? "N/A" : tf_phone_number.getText(),
                sss = tf_sss.getText().isEmpty() ? "N/A" : tf_sss.getText(),
                philhealth = tf_philhealth.getText().isEmpty()
                        ? "N/A"
                        : tf_philhealth.getText(),
                tin = tf_tin.getText().isEmpty() ? "N/A" : tf_tin.getText(),
                pagibig =
                        tf_pagibig.getText().isEmpty() ? "N/A" : tf_pagibig.getText(),
                status = tf_status.getText().isEmpty() ? "N/A" : tf_status.getText(),
                position =
                        tf_position.getText().isEmpty() ? "N/A" : tf_position.getText(),
                immediateSupervisor = tf_immediateSupervisor.getText().equals("")
                        ? "N/A"
                        : tf_immediateSupervisor.getText(),
                basicSalary = tf_basicSalary.getText().isEmpty()
                        ? "0"
                        : tf_basicSalary.getText(),
                riceSubsidy = tf_riceSubsidy.getText().isEmpty()
                        ? "0"
                        : tf_riceSubsidy.getText(),
                phoneAllowance = tf_phoneAllowance.getText().isEmpty()
                        ? "0"
                        : tf_phoneAllowance.getText(),
                clothingAllowance = tf_clothingAllowance.getText().isEmpty()
                        ? "0"
                        : tf_clothingAllowance.getText(),
                grossSemiMonthlyRate = tf_grossSemiMonthlyRate.getText().isEmpty()
                        ? "0"
                        : tf_grossSemiMonthlyRate.getText(),
                hourlyRate = tf_hourlyRate.getText().isEmpty()
                        ? "0"
                        : tf_hourlyRate.getText();

        // we add double quotes to the first index of string
        return employee_number + "\t" + CsvUtils.addDoubleQuotesIfStringHasComma(lName) + "\t" +
                CsvUtils.addDoubleQuotesIfStringHasComma(fName) + "\t" +
                CsvUtils.addDoubleQuotesIfStringHasComma(birthday) + "\t" +
                CsvUtils.addDoubleQuotesIfStringHasComma(address) + "\t" +
                CsvUtils.addDoubleQuotesIfStringHasComma(phone) + "\t" +
                CsvUtils.addDoubleQuotesIfStringHasComma(sss) + "\t" +
                CsvUtils.addDoubleQuotesIfStringHasComma(philhealth) + "\t" +
                CsvUtils.addDoubleQuotesIfStringHasComma(tin) + "\t" +
                CsvUtils.addDoubleQuotesIfStringHasComma(pagibig) + "\t" +
                CsvUtils.addDoubleQuotesIfStringHasComma(status) + "\t" +
                CsvUtils.addDoubleQuotesIfStringHasComma(position) + "\t"  +
                CsvUtils.addDoubleQuotesIfStringHasComma(immediateSupervisor) + "\t" +
                CsvUtils.addDoubleQuotesIfStringHasComma(CsvUtils.addCommaToStrInt(basicSalary)) + "\t" +
                CsvUtils.addDoubleQuotesIfStringHasComma(CsvUtils.addCommaToStrInt(riceSubsidy)) + "\t" +
                CsvUtils.addDoubleQuotesIfStringHasComma(CsvUtils.addCommaToStrInt(phoneAllowance)) + "\t" +
                CsvUtils.addDoubleQuotesIfStringHasComma(CsvUtils.addCommaToStrInt(clothingAllowance)) + "\t" +
                CsvUtils.addDoubleQuotesIfStringHasComma(CsvUtils.addCommaAndTwoDecimalsForFloatStr(grossSemiMonthlyRate)) + "\t" +
                CsvUtils.addDoubleQuotesIfStringHasComma(CsvUtils.addCommaAndTwoDecimalsForFloatStr(hourlyRate));
    }

    public void resetDetailsTextField(ActionEvent actionEvent) {
        tf_employee_number.setText("");
        tf_address.setText("");
        tf_basicSalary.setText("");
        dp_birthday.setValue(null);
        tin_num.setText("");
        tf_fName.setText("");
        tf_lName.setText("");
        tf_clothingAllowance.setText("");
        tf_grossSemiMonthlyRate.setText("");
        tf_tin.setText("");
        tf_status.setText("");
        tf_sss.setText("");
        tf_riceSubsidy.setText("");
        tf_position.setText("");
        tf_phoneAllowance.setText("");
        tf_philhealth.setText("");
        tf_pagibig.setText("");
        tf_immediateSupervisor.setText("");
        tf_hourlyRate.setText("");
        tf_phone_number.setText("");

        btn_cancel.setDisable(true);
        isAddNewEmployee = false;
        btn_saveOrUpdate.setDisable(true);
        btn_saveOrUpdate.setText("Select Employee");
        btn_deleteSelectedEmployee.setDisable(true);

        btn_employee.requestFocus();
    }

    /**
     * Listener for selected item on the table view component
     */

    public void tableViewSelectedItemListener() {
        employeeTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, employee) -> {
                    if (employee != null) {
                        // print employee data via console.

                        /**
                         * Update the Details textfields
                         */

                        tf_employee_number.setText(employee.getId());
                        tableViewSelectedEmployeeNumber = employee.getId();
                        tf_address.setText(employee.getAddress());
                        tf_basicSalary.setText(String.valueOf(employee.getBasic_salary()));
                        DateTimeFormatter formatter =
                                DateTimeFormatter.ofPattern("MMMM d, yyyy");
                        LocalDate birthday =
                                LocalDate.parse(employee.getBirthday(), formatter);
                        dp_birthday.setValue(birthday);
                        tf_fName.setText(employee.getF_name());
                        tf_lName.setText(employee.getL_name());
                        tf_clothingAllowance.setText(
                                String.valueOf(employee.getClothing_alw()));
                        tf_grossSemiMonthlyRate.setText(
                                String.valueOf(employee.getGross_semi_monthly_rate()));
                        tf_tin.setText(String.valueOf(employee.getTin_num()));
                        tf_status.setText(employee.getStatus());
                        tf_sss.setText(employee.getSss_num());
                        tf_riceSubsidy.setText(String.valueOf(employee.getRice_subsidy()));
                        tf_position.setText(employee.getPosition());
                        tf_phoneAllowance.setText(String.valueOf(employee.getPhone_alw()));
                        tf_philhealth.setText(String.valueOf(employee.getPhilhealth_num()));
                        tf_pagibig.setText(String.valueOf(employee.getPagibig_num()));
                        tf_immediateSupervisor.setText(employee.getImmediate_supervisor());
                        tf_hourlyRate.setText(String.valueOf(employee.getHourly_rate()));
                        tf_phone_number.setText(String.valueOf(employee.getPhone_num()));

                        /**
                         * Set save and cancel button to enabled because we have now
                         * selected item, we can update it via save button and cancel to
                         * terminate the update.
                         */
                        btn_saveOrUpdate.setDisable(false);
                        isAddNewEmployee = false;
                        btn_cancel.setDisable(false);
                        btn_deleteSelectedEmployee.setDisable(false);
                        btn_saveOrUpdate.setText("Update");
                    }
                });
    }

    @Override
    public void run() {
        ObservableList<Employees> list = FXCollections.observableArrayList(Employees.records);
        employeeTable.setItems(list);
        lbl_employeeSize.setText(String.valueOf(Employees.records.size()));

        tableViewSelectedItemListener();
    }

    public void createEmployee(ActionEvent actionEvent) {
        employeeTable.setItems(
                FXCollections.observableArrayList(Employees.records));
        resetDetailsTextField(actionEvent);
        isAddNewEmployee = true;
        // Refresh UI
        int lastIndex = (Employees.records.size() - 1);
        int previousEmployeeID =
                Integer.parseInt(Employees.records.get(lastIndex).getId());
        // newest employee id
        previousEmployeeID++;
        tf_employee_number.setText(String.valueOf(previousEmployeeID));
        lbl_employeeSize.setText(String.valueOf(Employees.records.size()));
        tf_search.setText("");
        btn_saveOrUpdate.setText("Save Employee");
        btn_saveOrUpdate.setDisable(false);
        btn_cancel.setDisable(false);
        tf_fName.requestFocus();
    }

    public void onSaveEmployeeClicked(ActionEvent actionEvent) {
        System.out.println(actionEvent.getEventType());
        System.out.println(actionEvent.getSource());
        System.out.println(actionEvent.getTarget());
        System.out.println(actionEvent.getClass());
        try {
            if (isEmployeeProfileFieldsNotEmpty()) {
                if (checkFieldsThatContainsNumbers()) {
                    {
                        if (isAddNewEmployee) {
                            System.out.println(employeeDetailsTextFieldToCsvString());
                            BufferedWriter writer =
                                    new BufferedWriter(new FileWriter(MainApp.EMPLOYEE_DETAILS_CSV, true));
                            // 'true' flag is used to append data to the existing file.
                            // Write the new employee details to the file

                            writer.write(
                                    employeeDetailsTextFieldToCsvString()); // Assuming you have a
                            // method to convert an
                            // employee object to a
                            // string

                            // Add a new line after writing the employee details
                            writer.newLine();

                            // Close the writer
                            writer.close();

                            // Refresh Employee Records
                            refreshEmployeeScene(actionEvent);
                            // Refresh Table View Items
                            run();
                            // Reset TextFields
                            resetDetailsTextField(actionEvent);
                            // Go back to employee btn focus
                            btn_employee.requestFocus();
                            // Disable the save and cancel btn
                            btn_saveOrUpdate.setDisable(true);
                            btn_cancel.setDisable(true);
                            // Reset attribute
                            isAddNewEmployee = false;
                            btn_saveOrUpdate.setText("Save");
                        }
                        else {
                            System.out.println("Im at else statement line 473");
                            if (checkFieldsThatContainsNumbers()) {
                                System.out.println("CheckFieldsThatContainsNumbers has been accessed at line 475");
                                String[] newValues = employeeDetailsTextFieldToTabularSeparatedValue().split("\t");
                                CsvUtils.updateByLineNumber(MainApp.EMPLOYEE_DETAILS_CSV,
                                        CsvUtils.findLineNumberByEmployeeNumber(
                                                MainApp.EMPLOYEE_DETAILS_CSV, tf_employee_number.getText()),
                                        newValues);
                                resetDetailsTextField(actionEvent);


                                // Refresh Employee Records
                                refreshEmployeeScene(actionEvent);
                                // Refresh Table View Items
                                run();
                                // Reset TextFields
                                resetDetailsTextField(actionEvent);
                                // Go back to employee btn focus
                                btn_employee.requestFocus();
                                // Disable the save and cancel btn
                                btn_saveOrUpdate.setDisable(true);
                                btn_cancel.setDisable(true);
                                // Reset attribute
                                isAddNewEmployee = false;
                                btn_saveOrUpdate.setText("Save");
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(
                    "File not found: " + fileNotFoundException.getMessage());
        } catch (IOException ioException) {
            System.out.println("An I/O error occured: " + ioException.getMessage());
        }
    }

    public void onLogoutClicked(ActionEvent actionEvent) {
        Employees.records.clear();
        try {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Logging out.");
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(e -> alert.hide());
            delay.play();
            alert.showAndWait();
            SceneController.loginScene(actionEvent);
        } catch (IOException ioException) {
            throw new IllegalArgumentException("Login scene can't load.");
        }
    }

    public void onDeleteEmployeeClicked(ActionEvent actionEvent) {
        System.out.println("Start deleting employee here...");
        CsvUtils.deleteEmployeeRecordByLineNumber(MainApp.EMPLOYEE_DETAILS_CSV,
                CsvUtils.findLineNumberByEmployeeNumber(
                        MainApp.EMPLOYEE_DETAILS_CSV, tableViewSelectedEmployeeNumber));
        refreshEmployeeScene(actionEvent);

        // Refresh Employee Records
        refreshEmployeeScene(actionEvent);
        // Refresh Table View Items
        run();
        // Reset TextFields
        resetDetailsTextField(actionEvent);
        // Go back to employee btn focus
        btn_employee.requestFocus();
        // Disable the save and cancel btn
        btn_saveOrUpdate.setDisable(true);
        btn_cancel.setDisable(true);
        // Reset attribute
        isAddNewEmployee = false;
        btn_saveOrUpdate.setText("Save");
    }

    public void filterTableData(ActionEvent actionEvent) {
        int employeeCounter = 0;
        String searchText = tf_search.getText();
        // Clear previous filtering
        employeeTable.getItems().clear();

        // Filter data based on search text
        for (Employees item : Employees.records) {
            if (item.toString().toLowerCase().contains(searchText.toLowerCase())) {
                employeeTable.getItems().add(item);
                employeeCounter++;
            }
        }
        // Change Employee Number Size
        lbl_employeeSize.setText(String.valueOf(employeeCounter));
    }

    public void onAttendanceClicked(ActionEvent actionEvent) {
        try {
            Attendance.records.clear();
            SceneController.attendanceScene(actionEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onLeavesClicked(ActionEvent actionEvent) {
        try {
            SceneController.leavesScene(actionEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onSalaryClicked(ActionEvent actionEvent) {
        try {
            SceneController.salaryScene(actionEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkFieldsThatContainsNumbers() {
        TextField[] arrTextField = {
                tf_basicSalary,
                tf_riceSubsidy,
                tf_phoneAllowance,
                tf_clothingAllowance,
                tf_grossSemiMonthlyRate,
                tf_hourlyRate
        };

        String pattern = "-?\\d+(\\.\\d+)?"; // digit with optional decimal pattern

        Alert alert = new Alert(Alert.AlertType.ERROR);
        for (int i = 0; i < arrTextField.length; i++) {
            // check if the string if it is not pure digits
            if (!arrTextField[i].getText().replace(",", "").matches(pattern)) {
                System.out.println("Invalid number: " + arrTextField[i] + " at index " + i);
                alert.setTitle("Invalid number: " + arrTextField[i].getText().replace(",", ""));
                alert.setContentText("Invalid number for component " + arrTextField[i].getId()
                        + " = " + arrTextField[i].getText().replace(",", ""));
                alert.show();
                arrTextField[i].requestFocus();
                return false;
            }
        }
        return true;
    }

    public boolean isEmployeeProfileFieldsNotEmpty() {
        if (dp_birthday.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank birthday");
            alert.setContentText("There is a blank date. Please select the calendar icon and select birthday.");
            alert.show();
            dp_birthday.requestFocus();
            return false;
        }
        TextField [] textField = {
                tf_employee_number,
                tf_fName,
                tf_lName,
                tf_address,
                tf_phone_number,
                tf_sss,
                tf_philhealth,
                tf_tin,
                tf_pagibig,
                tf_status,
                tf_position,
                tf_immediateSupervisor,
        };

        String [] str_textField_name = {
                "Employee number",
                "First name",
                "Last name",
                "Address",
                "Phone number",
                "SSS number",
                "Philhealth number",
                "Tin number",
                "Pagibig number",
                "Status",
                "Position",
                "Immediate Supervisor"
        };

        for (int i =  0; i < textField.length; i++) {
            if (textField[i].getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Blank textfield");
                alert.setContentText("There is a blank textfield. Please enter " + str_textField_name[i]);
                alert.show();
                textField[i].requestFocus();
                return false;
            }
        }
        return true;
    }

    public void onActionViewDetails(ActionEvent actionEvent) {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Alert");
            alert.setHeaderText(null);

            // Create 18 TextFields
            TextField[] textFields = new TextField[18];
            for (int i = 0; i < textFields.length; i++) {
                textFields[i] = new TextField();
            }
            textFields[0].setText(tf_employee_number.getText());
            textFields[1].setText(tf_lName.getText());
            textFields[2].setText(tf_fName.getText());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
            textFields[3].setText(dp_birthday.getValue().format(formatter));
            textFields[4].setText(tf_address.getText());
            textFields[5].setText(tf_phone_number.getText());
            textFields[6].setText(tf_sss.getText());
            textFields[7].setText(tf_philhealth.getText());
            textFields[8].setText(tf_tin.getText());
            textFields[9].setText(tf_pagibig.getText());
            textFields[10].setText(tf_status.getText());
            textFields[11].setText(tf_immediateSupervisor.getText());
            textFields[12].setText(CsvUtils.addCommaToStrInt(tf_basicSalary.getText()));
            textFields[13].setText(CsvUtils.addCommaToStrInt(tf_riceSubsidy.getText()));
            textFields[14].setText(CsvUtils.addCommaToStrInt(tf_phoneAllowance.getText()));
            textFields[15].setText(CsvUtils.addCommaToStrInt(tf_clothingAllowance.getText()));
            textFields[16].setText(CsvUtils.addCommaToStrInt(tf_grossSemiMonthlyRate.getText()));
            textFields[17].setText(CsvUtils.addCommaAndTwoDecimalsForFloatStr(tf_hourlyRate.getText()));

            // Create a GridPane to hold the TextFields
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(10));

            // Add TextFields to the GridPane
            int columnIndex = 0;
            int rowIndex = 0;
            for (TextField textField : textFields) {
                gridPane.add(textField, columnIndex, rowIndex);
                columnIndex++;
                if (columnIndex == 2) {
                    columnIndex = 0;
                    rowIndex++;
                }
            }

            // Create a VBox to hold the GridPane and Copy button
            VBox vbox = new VBox(10);
            vbox.getChildren().addAll(gridPane);

            // Create an HBox to hold the Copy button
            HBox hbox = new HBox(10);
            vbox.getChildren().addAll(hbox);

            // Set the VBox as the content of the alert
            alert.getDialogPane().setContent(vbox);

            // Add OK button and handle its action
            ButtonType okButton = new ButtonType("OK");
            alert.getButtonTypes().setAll(okButton);
            alert.setOnCloseRequest(e -> {
                // Retrieve the entered values from TextFields
                for (TextField textField : textFields) {
                    System.out.println(textField.getText());
                }
            });

            // Show the alert
            alert.showAndWait();
        }
        catch (NullPointerException nullPointerException) {
            Alert alertcatch = new Alert(Alert.AlertType.ERROR, "Please select item on tableview.");
            alertcatch.showAndWait();
        }
    }
}

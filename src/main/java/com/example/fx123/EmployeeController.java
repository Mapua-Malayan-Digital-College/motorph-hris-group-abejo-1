package com.example.fx123;


import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeController implements Runnable {
    /**
     * Start of Attributes
     */
    @FXML
    private Label lbl_employeeSize;

    @FXML
    private TableColumn<Employees, String> address;

    @FXML
    private TableColumn<Employees, Integer> basic_salary;

    @FXML
    private TableColumn<Employees, String> birthday;

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
    private Button btn_saveOrUpdate;

    @FXML
    private TableColumn<Employees, Integer> clothing_alw;

    @FXML
    private TableView<Employees> employeeTable;

    @FXML
    private TableColumn<Employees, String> f_name;

    @FXML
    private TableColumn<Employees, Integer> gross_semi_monthly_rate;

    @FXML
    private TableColumn<Employees, Float> hourly_rate;

    @FXML
    private TableColumn<Employees, String> id;

    @FXML
    private TableColumn<Employees, String> immediate_supervisor;

    @FXML
    private TableColumn<Employees, String> l_name;

    @FXML
    private TableColumn<Employees, String> pagibig_num;

    @FXML
    private TableColumn<Employees, String> philhealth_num;

    @FXML
    private TableColumn<Employees, Integer> phone_alw;

    @FXML
    private TableColumn<Employees, String> phone_num;

    @FXML
    private TableColumn<Employees, String> position;

    @FXML
    private TableColumn<Employees, String> rice_subsidy;

    @FXML
    private TableColumn<Employees, String> sss_num;

    @FXML
    private TableColumn<Employees, String> status;

    @FXML
    private TextField tf_address;

    @FXML
    private TextField tf_basicSalary;

    @FXML
    private DatePicker dp_birthday;

    @FXML
    private TextField tf_clothingAllowance;

    @FXML
    private TextField tf_employee_number;

    @FXML
    private TextField tf_fName;

    @FXML
    private TextField tf_grossSemiMonthlyRate;

    @FXML
    private TextField tf_hourlyRate;

    @FXML
    private TextField tf_immediateSupervisor;

    @FXML
    private TextField tf_lName;

    @FXML
    private TextField tf_pagibig;

    @FXML
    private TextField tf_philhealth;

    @FXML
    private TextField tf_phone;

    @FXML
    private TextField tf_phoneAllowance;

    @FXML
    private TextField tf_position;

    @FXML
    private TextField tf_riceSubsidy;

    @FXML
    private TextField tf_sss;

    @FXML
    private TextField tf_status;

    @FXML
    private TextField tf_tin;

    @FXML
    private TableColumn<Employees, String> tin_num;

    @FXML
    private TextField tf_search;

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
     * Sets up the cell value factory for our table column using the setCellValueFactoryTableColumns() method.
     * The resulting structure of the table column will be as follows:
     *
     * <TableColumn>
     * <CellValueFactory>
     * <PropertyValueFactory></PropertyValueFactory>
     * </CellValueFactory>
     * </TableColumn>
     */
    public void setCellValueFactoryTableColumns() {
        id.setCellValueFactory(new PropertyValueFactory<Employees, String>("id"));
        l_name.setCellValueFactory(new PropertyValueFactory<Employees, String>("l_name"));
        f_name.setCellValueFactory(new PropertyValueFactory<Employees, String>("f_name"));
        birthday.setCellValueFactory(new PropertyValueFactory<Employees, String>("birthday"));
        address.setCellValueFactory(new PropertyValueFactory<Employees, String>("address"));
        phone_num.setCellValueFactory(new PropertyValueFactory<Employees, String>("phone_num"));
        sss_num.setCellValueFactory(new PropertyValueFactory<Employees, String>("sss_num"));
        philhealth_num.setCellValueFactory(new PropertyValueFactory<Employees, String>("philhealth_num"));
        tin_num.setCellValueFactory(new PropertyValueFactory<Employees, String>("tin_num"));
        pagibig_num.setCellValueFactory(new PropertyValueFactory<Employees, String>("pagibig_num"));
        status.setCellValueFactory(new PropertyValueFactory<Employees, String>("status"));
        position.setCellValueFactory(new PropertyValueFactory<Employees, String>("position"));
        immediate_supervisor.setCellValueFactory(new PropertyValueFactory<Employees, String>("immediate_supervisor"));
        basic_salary.setCellValueFactory(new PropertyValueFactory<Employees, Integer>("basic_salary"));
        rice_subsidy.setCellValueFactory(new PropertyValueFactory<Employees, String>("rice_subsidy"));
        phone_alw.setCellValueFactory(new PropertyValueFactory<Employees, Integer>("phone_alw"));
        clothing_alw.setCellValueFactory(new PropertyValueFactory<Employees, Integer>("clothing_alw"));
        gross_semi_monthly_rate.setCellValueFactory(new PropertyValueFactory<Employees, Integer>("gross_semi_monthly_rate"));
        hourly_rate.setCellValueFactory(new PropertyValueFactory<Employees, Float>("hourly_rate"));
    }


    private String employeeDetailsTextFieldToTabString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

        String
                employee_number = tf_employee_number.getText().isEmpty() ? "N/A" : tf_employee_number.getText(),
                lName = tf_lName.getText().isEmpty() ? "N/A" : tf_lName.getText(),
                fName = tf_fName.getText().isEmpty() ? "N/A" : tf_fName.getText(),
                birthday = dp_birthday.getValue().format(formatter),
                address = tf_address.getText().isEmpty() ? "N/A" : tf_address.getText(),
                phone = tf_phone.getText().isEmpty() ? "N/A" : tf_phone.getText(),
                sss = tf_sss.getText().isEmpty() ? "N/A" : tf_sss.getText(),
                philhealth = tf_philhealth.getText().isEmpty() ? "N/A" : tf_philhealth.getText(),
                tin = tf_tin.getText().isEmpty() ? "N/A" : tf_tin.getText(),
                pagibig = tf_pagibig.getText().isEmpty() ? "N/A" : tf_pagibig.getText(),
                status = tf_status.getText().isEmpty() ? "N/A" : tf_status.getText(),
                position = tf_position.getText().isEmpty() ? "N/A" : tf_position.getText(),
                immediateSupervisor = tf_immediateSupervisor.getText().equals("") ? "N/A" : tf_immediateSupervisor.getText(),
                basicSalary = tf_basicSalary.getText().isEmpty() ? "0" : tf_basicSalary.getText(),
                riceSubsidy = tf_riceSubsidy.getText().isEmpty() ? "0" : tf_riceSubsidy.getText(),
                phoneAllowance = tf_phoneAllowance.getText().isEmpty() ? "0" : tf_phoneAllowance.getText(),
                clothingAllowance = tf_clothingAllowance.getText().isEmpty() ? "0" : tf_clothingAllowance.getText(),
                grossSemiMonthlyRate = tf_grossSemiMonthlyRate.getText().isEmpty() ? "0" : tf_grossSemiMonthlyRate.getText(),
                hourlyRate = tf_hourlyRate.getText().isEmpty() ? "0" : tf_hourlyRate.getText();

        return
                employee_number + "\t"+
                        lName + "\t"+
                        fName + "\t"+
                        birthday + "\t"+
                        address + "\t"+
                        phone + "\t"+
                        sss + "\t"+
                        philhealth + "\t"+
                        tin + "\t"+
                        pagibig + "\t"+
                        status + "\t"+
                        position + "\t"+
                        immediateSupervisor + "\t"+
                        basicSalary + "\t"+
                        riceSubsidy + "\t"+
                        phoneAllowance + "\t"+
                        clothingAllowance + "\t"+
                        grossSemiMonthlyRate + "\t"+
                        hourlyRate;
    }

    public void resetDetailsTextField(ActionEvent actionEvent) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        LocalDate localDate = LocalDate.parse("November 28, 2001", formatter);
        tf_employee_number.setText("");
        tf_address.setText("");
        tf_basicSalary.setText(String.valueOf(""));
        dp_birthday.setValue(localDate);
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
        tf_phone.setText("");

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

        employeeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, employee) -> {
            if (employee != null) {

                // print employee data via console.
                System.out.println(employee.toString());


                /**
                 * Update the Details textfields
                 */

                tf_employee_number.setText(employee.getId());
                tableViewSelectedEmployeeNumber = employee.getId();
                tf_address.setText(employee.getAddress());
                tf_basicSalary.setText(String.valueOf(employee.getBasic_salary()));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                LocalDate birthday = LocalDate.parse(employee.getBirthday(), formatter);
                dp_birthday.setValue(birthday);
                tf_fName.setText(employee.getF_name());
                tf_lName.setText(employee.getL_name());
                tf_clothingAllowance.setText(String.valueOf(employee.getClothing_alw()));
                tf_grossSemiMonthlyRate.setText(String.valueOf(employee.getGross_semi_monthly_rate()));
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
                tf_phone.setText(String.valueOf(employee.getPhone_num()));

                /**
                 * Set save and cancel button to enabled because we have now selected item,
                 * we can update it via save button and cancel to terminate the update.
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

        ObservableList list = FXCollections.observableArrayList(Employees.records);
        employeeTable.setItems(list);
        lbl_employeeSize.setText(String.valueOf(Employees.records.size()));

        tableViewSelectedItemListener();
    }

    public void createEmployee(ActionEvent actionEvent) {

        employeeTable.setItems(FXCollections.observableArrayList(Employees.records));
        resetDetailsTextField(actionEvent);
        isAddNewEmployee = true;
        // Refresh UI
        int lastIndex = (Employees.records.size()-1);
        int previousEmployeeID = Integer.valueOf(Employees.records.get(lastIndex).getId());
        // newest employee id
        previousEmployeeID++;
        tf_employee_number.setText(String.valueOf(previousEmployeeID));
        System.out.println("The new employee id = "+previousEmployeeID);
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

            System.out.println("isAddNewEmployee TRUE ?"+isAddNewEmployee);
            if (isAddNewEmployee) {
                System.out.println("Start creating employee here...");
                BufferedWriter writer = new BufferedWriter(new FileWriter(MainApp.EMPLOYEE_TSV, true));
                // 'true' flag is used to append data to the existing file.
                System.out.println("employeeDetailsTextFieldToString");
                // Write the new employee details to the file
                writer.write(employeeDetailsTextFieldToTabString()); // Assuming you have a method to convert an employee object to a string

                // Add a new line after writing the employee details
                writer.newLine();

                // Close the writer
                writer.close();
            }

            else {
                System.out.println("Start updating employee here...");
                String [] newValues = employeeDetailsTextFieldToTabString().split("\t");
                System.out.println("Array starting here...");
                System.out.println(employeeDetailsTextFieldToTabString());
                System.out.println("Array ending here...");
                TsvUtils.updateByLineNumber(MainApp.EMPLOYEE_TSV,TsvUtils.findLineNumberByEmployeeNumber(MainApp.EMPLOYEE_TSV,tf_employee_number.getText()),newValues);
                resetDetailsTextField(actionEvent);
            }
            isAddNewEmployee = false;
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found: " + fileNotFoundException.getMessage());
        }
        catch (IOException ioException) {
            System.out.println("An I/O error occured: " + ioException.getMessage());
        }

        finally {
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

    public void onLogoutClicked(ActionEvent actionEvent) {
        Employees.records.clear();
        try{
            Alert alert = new Alert(Alert.AlertType.WARNING,"Logging out.");
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(e -> alert.hide());
            delay.play();
            alert.showAndWait();
            SceneController.loginScene(actionEvent);
        }
        catch (IOException ioException) {
            throw new IllegalArgumentException("Login scene can't load.");
        }
    }

    public void onDeleteEmployeeClicked(ActionEvent actionEvent) {
        System.out.println("Start deleting employee here...");
        TsvUtils.deleteEmployeeRecordByLineNumber(MainApp.EMPLOYEE_TSV,TsvUtils.findLineNumberByEmployeeNumber(MainApp.EMPLOYEE_TSV,tableViewSelectedEmployeeNumber));
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
            if (String.valueOf(item.toString().toLowerCase())
                    .contains(searchText.toLowerCase())) {
                employeeTable.getItems().add(item);
                employeeCounter++;
            }
        }
        // Change Employee Number Size
        lbl_employeeSize.setText(String.valueOf(employeeCounter));
    }

    public void onClickAttendance(ActionEvent actionEvent) {
        try {
            Attendance.records.clear();
            SceneController.attendanceScene(actionEvent);
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

    public void onClickedPaySlips(ActionEvent actionEvent) {
        // inprogress
    }
}
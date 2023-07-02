package com.example.fx123;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class LoginController {
    @FXML private TextField txtField_employeeNumber;

    @FXML private PasswordField pwField_password;

    private boolean verifyUser(String user, String pwd) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(MainApp.EMPLOYEE_CREDENTIALS_CSV));
            String line;
            while ((line = br.readLine()) != null) {
                String [] arrCredentials = line.split(",");
                String getUsername =  arrCredentials[0],
                       getPassword = arrCredentials[1];
                // verify that each row contains a username and password in a csv
                if (user.equals(getUsername) && pwd.equals(getPassword)) {
                    return true;
                }
            }
            // if username and password does not exist on the csv then it will fail the verification.
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void loginAction(ActionEvent event) {
        if (verifyUser(
                txtField_employeeNumber.getText(), pwField_password.getText())) {
            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION, "Successfully logged in.");
            alert.showAndWait();

            // Populate Employees
            Employees.addAllEmployees();
            // Change Scene
            try {
                SceneController.employeeScene(event);
            } catch (IOException ioException) {
                throw new IllegalArgumentException("Scene can't load.");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username/password. Please try again.");
            alert.showAndWait();
            txtField_employeeNumber.requestFocus();
        }
    }
}
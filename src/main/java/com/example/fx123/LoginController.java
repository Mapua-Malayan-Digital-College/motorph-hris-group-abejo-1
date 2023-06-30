package com.example.fx123;

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

    private boolean verifyUser(String username, String password) {
        // debug mode
        return username.equals("") && password.equals("");
        // original
        // return username.equals("user") && password.equals("admin1234");
    }
    public void loginAction(ActionEvent event) throws IOException {
        if (verifyUser(
                txtField_employeeNumber.getText(), pwField_password.getText())) {
            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION, "Successfully logged in.");
            alert.show();
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(e -> alert.hide());
            delay.play();

            // Populate Employees and Attendance
            Employees.addAllEmployees();
            // Change Scene
            try {
                SceneController.employeeScene(event);
            } catch (IOException ioException) {
                throw new IllegalArgumentException("Scene can't load.");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Invalid username or password. Please try again.");
            alert.show();
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(e -> alert.hide());
            delay.play();
            txtField_employeeNumber.requestFocus();
        }
    }
}
package com.example.fx123;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LoginController {
    public static final Stage stage = new Stage();
    @FXML
    private TextField txtField_employeeNumber;

    @FXML
    private PasswordField pwField_password;

    @FXML
    private Button btn_login;

    private boolean verifyUser(String username, String password) {
        return username.equals("10001") && password.equals("");
    }
    public void loginAction(ActionEvent event) throws IOException {
        if (verifyUser(txtField_employeeNumber.getText(),
                pwField_password.getText())) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Logged in successfully");
            alert.show();
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(e -> alert.hide());
            delay.play();

            // Populate Employees and Attendance
            Employees.addAllEmployees();
            // Change Scene
            try{
                SceneController.employeeScene(event);
            }
            catch (IOException ioException){
                throw new IllegalArgumentException("Employee scene can't load.");
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password. Please try again.");
            alert.show();
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(e -> alert.hide());
            delay.play();
            txtField_employeeNumber.requestFocus();
        }
    }
}
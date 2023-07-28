package com.example.fx123;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static final String EMPLOYEE_CREDENTIALS_CSV = "src\\main\\resources\\csv\\MotorPH Employee Data - Credentials.csv";
    public static final String EMPLOYEE_DETAILS_CSV = "src\\main\\resources\\csv\\MotorPH Employee Data - Employee Details.csv";
    public static final String ATTENDANCE_CSV = "src\\main\\resources\\csv\\MotorPH Employee Data - Attendance Record.csv";
    public static final String LEAVE_CSV = "src\\main\\resources\\csv\\MotorPH Employee Data - Leaves.csv";
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(LoginController.class.getResource("login-view.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package com.example.fx123;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static final String EMPLOYEE_TSV =
            "src/main/resources/tsv/MotorPH Employee Data - Employee Details.tsv";
    public static final String ATTENDANCE_TSV =
            "src/main/resources/tsv/MotorPH Employee Data - Attendance Record.tsv";
    public static final String LEAVE_TSV =
            "src/main/resources/tsv/MotorPH Employee Data - Leaves.tsv";
    public static final String CREDENTIALS_CSV =
            "src\\main\\resources\\csv\\MotorPH Employee Data - Credetials.csv";
    public static final String EMPLOYEE_CSV =
            "src\\main\\resources\\csv\\MotorPH Employee Data - Employee Details.csv";
    public static final String ATTENDANCE_CSV =
            "src\\main\\resources\\csv\\MotorPH Employee Data - Attendance Record.csv";
    public static final String LEAVE_CSV =
            "src\\main\\resources\\csv\\MotorPH Employee Data - Leaves.csv";
    @Override
    public void start(Stage stage) {
        try {
            Parent root =
                    FXMLLoader.load(LoginController.class.getResource("login.fxml"));
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

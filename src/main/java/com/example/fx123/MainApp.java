package com.example.fx123;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp  extends Application {
    public static final String EMPLOYEE_TSV = "src/main/resources/tsv/MotorPH Employee Data - Employee Details.tsv";
    public static final String ATTENDANCE_TSV = "src/main/resources/tsv/MotorPH Employee Data - Attendance Record.tsv";
    @Override
    public void start(Stage stage) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

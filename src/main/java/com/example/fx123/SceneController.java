package com.example.fx123;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private static Stage stage;
    private static Scene scene;
    private static Parent root;

    public static void loginScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("login.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Login Scene");
        stage.show();
    }


    public static void employeeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(EmployeesController.class.getResource("employee-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Employees");
        stage.show();
    }

    public static void attendanceScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(AttendanceController.class.getResource("attendance-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Attendance");
        stage.show();
    }
}
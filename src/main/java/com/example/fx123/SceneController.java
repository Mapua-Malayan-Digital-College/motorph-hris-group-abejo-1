package com.example.fx123;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private static Stage stage;
    private static Scene scene;

    public static void loginScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("login-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Login View");
        stage.show();
    }

    public static void employeeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                LeavesController.class.getResource("employee-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Employee View");
        stage.show();
    }

    public static void attendanceScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                AttendanceController.class.getResource("attendance-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Attendance View");
        stage.show();
    }

    public static void leavesScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                AttendanceController.class.getResource("leaves-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Leaves View");
        stage.show();
    }
    public static void salaryScene(ActionEvent event) throws IOException {
        Parent root =
                FXMLLoader.load(SalaryController.class.getResource("salary-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Salary View");
        stage.show();
    }
}
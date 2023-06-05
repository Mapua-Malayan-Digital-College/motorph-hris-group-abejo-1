module com.example.fx123 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.fx123 to javafx.fxml;
    exports com.example.fx123;
}
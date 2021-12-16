module com.example.brief3javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.sql;

    opens com.example.brief3javafx to javafx.fxml;
    exports com.example.brief3javafx;
    exports com.example.brief3javafx.controller;
    opens com.example.brief3javafx.controller to javafx.fxml;
}
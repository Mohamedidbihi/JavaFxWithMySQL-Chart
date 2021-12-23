module com.example.brief3javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.sql;
    requires java.mail;
    requires apache.log4j.extras;

    opens com.example.brief3javafx to javafx.fxml;
    exports com.example.brief3javafx;
    exports com.example.brief3javafx.controller;
    opens com.example.brief3javafx.controller to javafx.fxml;
    exports com.example.brief3javafx.enums;
    opens com.example.brief3javafx.enums to javafx.fxml;
}
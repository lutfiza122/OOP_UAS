module com.notes.notes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.notes.notes to javafx.fxml;
    opens com.notes.notes.entity to javafx.base;
    exports com.notes.notes;
    exports com.notes.notes.controllers;
    opens com.notes.notes.controllers to javafx.fxml;
}
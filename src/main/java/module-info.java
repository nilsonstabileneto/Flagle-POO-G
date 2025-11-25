module com.example.flagle {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.flagle to javafx.fxml;
    exports com.example.flagle;
}
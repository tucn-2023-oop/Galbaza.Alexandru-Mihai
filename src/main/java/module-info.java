module com.example.farm2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.junit.jupiter.api;


    opens com.example.farm2 to javafx.fxml;
    exports com.example.farm2;
    exports com.example.farm2.DAO;
    opens com.example.farm2.DAO to javafx.fxml;
    exports com.example.farm2.Controllers;
    opens com.example.farm2.Controllers to javafx.fxml;
    exports com.example.farm2.Classes;
    opens com.example.farm2.Classes to javafx.fxml;
    exports com.example.farm2.Unused;
    opens com.example.farm2.Unused to javafx.fxml;
}
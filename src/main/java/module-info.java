module bureau.visitorbureau {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens bureau.visitorbureau to javafx.fxml;
    exports bureau.visitorbureau;
}
module pair.task.ui {
    requires javafx.controls;
    requires javafx.fxml;


    opens pair.task.ui to javafx.fxml;
    exports pair.task.ui;
}
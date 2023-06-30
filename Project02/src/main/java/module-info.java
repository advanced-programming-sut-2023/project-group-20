module Project02 {
    requires javafx.controls;
    requires javafx.fxml;

    exports org.example.view;
    opens org.example.view to javafx.fxml;
}
module com.example.ekz_graf {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.ekz_graf to javafx.fxml;
    exports com.example.ekz_graf;
}
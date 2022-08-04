module com.example.asmjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.asmjava to javafx.fxml;
    exports com.example.asmjava;
}
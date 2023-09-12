module com.example.spaceplace {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires java.desktop;


    opens com.example.spaceplace to javafx.fxml;
    exports com.example.spaceplace;
}
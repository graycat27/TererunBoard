module pro.eng.yui.sample.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens pro.eng.yui.sample.demo to javafx.fxml;
    exports pro.eng.yui.sample.demo;
}
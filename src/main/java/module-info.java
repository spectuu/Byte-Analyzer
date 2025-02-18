module spectu.antivirus {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires static lombok;
    requires com.google.gson;

    opens spectu.antivirus to javafx.fxml;
    opens spectu.antivirus.resource to com.google.gson;
    opens spectu.antivirus.resource.data to com.google.gson;
    exports spectu.antivirus;
    opens spectu.antivirus.resource.json to com.google.gson;

}
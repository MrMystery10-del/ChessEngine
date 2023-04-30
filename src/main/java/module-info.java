module com.knightclient.core {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.knightclient.core to javafx.fxml;
    exports com.knightclient.core;
    exports com.knightclient.core.controllers;
    opens com.knightclient.core.controllers to javafx.fxml;
}
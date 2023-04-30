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
    requires java.desktop;
    requires java.logging;
    requires com.fasterxml.jackson.databind;
    requires org.testng;
    requires junit;
    requires org.junit.jupiter.api;

    opens com.knightclient.core to javafx.fxml;
    exports com.knightclient.core;
    exports com.knightclient.core.controllers;
    opens com.knightclient.core.controllers to javafx.fxml;
}
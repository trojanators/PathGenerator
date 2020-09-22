package org.usfirst.frc.team5740.gui;

import org.usfirst.frc.team5740.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphPage extends Application {

    private FXMLLoader loader = new FXMLLoader();

    // Starts JavaFX Gui
    @Override
    public void start(Stage stage) throws Exception {

        Main.logger.info("loading Fxml file");
        loader.setLocation(getClass().getResource("/Path_display.fxml"));
        Main.logger.info("Done Loading PathGraph.fxml file");

        Parent root_path = loader.load();
        Scene scene = new Scene(root_path, 954, 462);

        stage.setTitle("PathGraph By Nicholas Blackburn");
        stage.setScene(scene);
        stage.show();
        Main.logger.warning("Successfully displaying PathGraph page");

        Main.logger.warning("Starting Graph Data Function");
    }

    // closes fx stage for PAthData
    public void onClose(Stage stage) {
        stage.hide();
    }

}
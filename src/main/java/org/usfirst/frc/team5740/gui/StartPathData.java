package org.usfirst.frc.team5740.gui;

import org.usfirst.frc.team5740.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartPathData extends Application {

    private FXMLLoader loader = new FXMLLoader();

    // Starts JavaFX Gui
    @Override
    public void start(Stage stage) throws Exception {

        Main.logger.info("loading Fxml file");
        loader.setLocation(getClass().getResource("/PathData.fxml"));
        Main.logger.info("Done Loading PathData.fxml file");
        
        Parent root_path = loader.load();
        Scene scene = new Scene(root_path, 640, 400);

        stage.setTitle("PathGenerator By Nicholas Blackburn");
        stage.setScene(scene);
        stage.show();
        Main.logger.warning("Successfully displaying Pathdata page");

        Main.logger.warning("Starting PathData Function");
    }

    // closes fx stage for PAthData
    public void onClose(Stage stage) {
        stage.hide();
    }
    
   

}
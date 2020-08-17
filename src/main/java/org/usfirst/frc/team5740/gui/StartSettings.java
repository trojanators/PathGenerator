package org.usfirst.frc.team5740.gui;

import org.usfirst.frc.team5740.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartSettings extends Application {

    private final FXMLLoader loader = new FXMLLoader();

    // Starts JavaFX Gui
    @Override
    public void start(final Stage stage) throws Exception {
        Main.logger.info("loading Fxml file");
        loader.setLocation(getClass().getResource("/settings.fxml"));
        Main.logger.info("Done Loading settings.fxml file");
        Parent root = loader.load(); 
       

        Scene scene = new Scene(root, 640, 400);
    
        stage.setTitle("PathGenerator By Nicholas Blackburn");
        stage.setScene(scene);
        stage.show();
        Main.logger.warning("Successfully displaying Settings page");
        
        Main.logger.warning("Starting Settings Function's");
    }
    public void open(Stage stage) {
        stage.show();
    }
    
}
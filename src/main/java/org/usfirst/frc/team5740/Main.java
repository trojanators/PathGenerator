package org.usfirst.frc.team5740;

import java.io.File;
import java.io.FileInputStream;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    
    private final SimpleFormatter formatter = new SimpleFormatter();
    public static Logger logger = Logger.getLogger(Main.class.getName());
    private final FXMLLoader loader = new FXMLLoader();
    

    // Starts JavaFX Gui
    @Override
    public void start(final Stage stage) throws Exception {
        // logger setup
        FileHandler fh = new FileHandler("PathGen.log");
        logger.addHandler(fh);
        fh.setFormatter(formatter);

        logger.info("loading Fxml file");
        loader.setLocation(getClass().getResource("/Main.fxml"));
        logger.info("Done Loading main.fxml file");
        final Parent root = loader.load();

        final Scene scene = new Scene(root, 640, 400);
    
        stage.setTitle("PathGenerator By Nicholas Blackburn");
        stage.setScene(scene);
        stage.show();
        logger.warning("Successfully displaying Main page");
        
        logger.warning("Starting Init Function");
       
    }

}
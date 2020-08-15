package org.usfirst.frc.team5740;

import java.io.FileInputStream;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Logger logger =  Logger.getLogger(Main.class.getName());
    private FXMLLoader loader = new FXMLLoader();
    
    // Starts JavaFX Gui
    @Override
    public void start(Stage stage) throws Exception {
        logger.info("loading Fxml file");
        loader.setLocation(getClass().getResource("/Main.fxml"));
        logger.info("Done Loading main.fxml file");

        Parent root = loader.load(); 

        Scene scene = new Scene(root, 640, 400);
    
        stage.setTitle("PathGenerator By Nicholas Blackburn");
        stage.setScene(scene);
        stage.show();
        logger.warning("Successfully displaying Main page");
    }
   
}
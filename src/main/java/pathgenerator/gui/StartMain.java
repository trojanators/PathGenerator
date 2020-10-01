package pathgenerator.gui;

import pathgenerator.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Nicholas Blackburn
 * @email nickblackburn02@gmail.com
 * @create date 2020-08-16 19:30:28
 * @modify date 2020-08-16 19:30:28
 * @desc this class is to recall the main startup window without creating new main instance 
 */
public class StartMain extends Application{
    private FXMLLoader loader = new FXMLLoader();
  
    
    // Starts JavaFX Gui
    @Override
    public void start(Stage stage) throws Exception {
        Main.logger.info("loading Fxml file");
        loader.setLocation(getClass().getResource("/Main.fxml"));
       Main.logger.info("Done Loading main.fxml file");
        Parent root = loader.load(); 
       

        Scene scene = new Scene(root, 640, 400);
    
        stage.setTitle("PathGenerator By Nicholas Blackburn");
        stage.setScene(scene);
        stage.show();
        Main.logger.warning("Successfully displaying Main page");
        
        Main.logger.warning("Starting Init Function");

    }
       
}
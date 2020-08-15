package org.usfirst.frc.team5740.gui;

import java.net.URL;
import java.util.ResourceBundle;

import org.usfirst.frc.team5740.Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {
    private DropShadow shadow = new DropShadow();
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="exit"
    private Button exit; // Value injected by FXMLLoader

    @FXML // fx:id="start"
    private Button start; // Value injected by FXMLLoader

   
    
    public void initialize() {

        // Start Button Action
        start.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				if(!start.isPressed()){
                    Main.logger.info("Starting Path Generator");
                }
				
			}
            
        }); 
        
        // Closes PathGenerator
        exit.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				if(!exit.isPressed()){
                    Main.logger.info("Exiting Bye!");
                    System.exit(0);
                }
				
			}
            
        }); 
    }   
}
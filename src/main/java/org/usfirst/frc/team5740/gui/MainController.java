package org.usfirst.frc.team5740.gui;

import java.net.URL;
import java.util.ResourceBundle;

import org.usfirst.frc.team5740.Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.stage.Stage;

/**
 * This class is the Main controller class for the Javafx Windows
 * 
 * @author Nicholas Blackburn
 */

public class MainController {
    private StartPathData path = new StartPathData();
    private StartSettings settings = new StartSettings();
    private Stage stage = new Stage();

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
        start.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (!start.isPressed()) {
                    Main.logger.info("Starting Path Generator");
                    // Cheks to see if pathData is hidden
                    try {
                        if (!stage.isShowing()) {
                            stage.show();
                        }
                        path.start(stage);
                    } catch (Exception e) {
                        Main.logger.warning(e.getLocalizedMessage());
                    }
                }

            }

        });

        // Closes PathGenerator
        exit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (!exit.isPressed()) {
                    Main.logger.info("Exiting Bye!");
                    System.exit(0);
                }

            }

        });
    }

}
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

    public Stage stage2 = new Stage();

    private StartPathData path = new StartPathData();
    private StartSettings set = new StartSettings();
    private Stage stage = new Stage();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="exit"
    private Button exit; // Value injected by FXMLLoader

    @FXML // fx:id="start"
    private Button start; // Value injected by FXMLLoader

    @FXML
    private Button settings;

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
                            path.start(stage);
                        }
                    } catch (Exception e) {
                        Main.logger.warning(e.getMessage().toString());
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
        // opens settings menu for app
        settings.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (!settings.isPressed()) {
                    Main.logger.info("Starting Settings");
                    // Cheks to see if pathData is hidden
                    try {
                        if (!stage2.isShowing()) {
                            set.start(stage2);
                            stage2.show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                
            }

        });

    }

}
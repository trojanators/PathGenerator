package org.usfirst.frc.team5740.gui;

/**
 * This Class is the javafx controller for Path Entry Window in java fx 
 * @author Nicholas Blackburn
 */
import java.util.Set;

import org.usfirst.frc.team5740.Main;
import org.usfirst.frc.team5740.util.PathListCell;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PathDataPaneController {
   

    @FXML
    private AnchorPane save_entrys;

    @FXML
    private Button new_waypoint;

    @FXML
    private Button remove_path;

    @FXML
    private Button preview_graph;

    @FXML
    private Button save_path;

    @FXML
    private Button exit;

    @FXML
    private ListView<String> display_path_entrys;
    

    @FXML
    public void initialize() {
        // Runs when new waypoint button pressed
        new_waypoint.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (!new_waypoint.isPressed()) {
                    // TODO: add enty to display_path_entrys
                    Main.logger.info("new Waypoint");
                    
                }

            }

        });

        remove_path.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (!remove_path.isPressed()) {
                    // TODO: add enty to display_path_entrys
                    Main.logger.info("removed PAth");
                    
                    
                }
                

            }
            

        });
        save_path.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (!save_path.isPressed()) {
                    // TODO: add enty to display_path_entrys
                    Main.logger.info("saved path");
                    
                }

            }

        });
        exit.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                Stage stage = (Stage) exit.getScene().getWindow();
                
                if (!exit.isPressed()) {
                    // TODO: add enty to display_path_entrys
                    Main.logger.info("exited path menu");
                    stage.hide();
                }

            }

        });

    }
}

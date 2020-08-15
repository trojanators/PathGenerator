package org.usfirst.frc.team5740.gui;

import org.usfirst.frc.team5740.Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

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
    private ListView<?> display_path_entrys;

    public void initialize(){
        // Runs when new waypoint button pressed
        new_waypoint.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				if(!new_waypoint.isPressed()){
                    Main.logger.info("new waypoint is added");
                }
				
			}
        });
    }

}

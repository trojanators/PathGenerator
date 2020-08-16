package org.usfirst.frc.team5740.gui;

/**
 * This Class is the javafx controller for Path Entry Window in java fx 
 * @author Nicholas Blackburn
 */
import java.util.Set;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;

import org.usfirst.frc.team5740.Main;
import org.usfirst.frc.team5740.util.WaypointTableData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PathDataPaneController {
    private int i = 0;

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
    private TableView<WaypointTableData> waypoint_table;

    @FXML
    public void initialize() {
         waypoint_table.setEditable(true);
         
        // creates columns based on waypoint id , x and y
        TableColumn waypoint_id = new TableColumn("id");
        waypoint_id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn waypoint_x = new TableColumn("x");
        waypoint_x.setCellValueFactory(new PropertyValueFactory<>("x"));

        TableColumn waypoint_y = new TableColumn("y");
        waypoint_x.setCellValueFactory(new PropertyValueFactory<>("y"));

        waypoint_table.getColumns().addAll(waypoint_id, waypoint_x,waypoint_y);
    
       
        // Runs when new waypoint button pressed
        new_waypoint.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (!new_waypoint.isPressed()) {
                    // TODO: add Handler / creator to add Data to Listview 
                    Main.logger.info("new Waypoint");
                    WaypointTableData data = new WaypointTableData(i ++,0,0);
                    waypoint_table.getItems().add(data);
                    
                }

            }

        });

        remove_path.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (!remove_path.isPressed()) {
                    // TODO: remove data from Waypoint listview
                    Main.logger.info("removed PAth");
                    
                    
                }
                

            }
            

        });
        save_path.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (!save_path.isPressed()) {
                    // TODO: actually Save path and export to csv
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

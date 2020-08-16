package org.usfirst.frc.team5740.gui;

/**
 * This Class is the javafx controller for Path Entry Window in java fx 
 * @author Nicholas Blackburn
 */
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

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
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PathDataPaneController {
    public double x;
    public double y;
    public double theta;

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
    private TextField waypoint_x_input;

    @FXML
    private TextField waypoint_y_input;

    @FXML
    private TextField waypoint_theta_input;

    @FXML
    private TableView<WaypointTableData> waypoint_table;

   /**
    *  this function sets up cells names and vars to be called and sets up text formater
    */
    public void cellSetup() {

        // creates columns based on waypoint id , x and y
        TableColumn waypoint_id = new TableColumn("id");
        waypoint_id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn waypoint_x = new TableColumn("x");
        waypoint_x.setCellValueFactory(new PropertyValueFactory<>("x"));

        TableColumn waypoint_y = new TableColumn("y");
        waypoint_y.setCellValueFactory(new PropertyValueFactory<>("y"));

        TableColumn waypoint_theta = new TableColumn("theta");
        waypoint_theta.setCellValueFactory(new PropertyValueFactory<>("theta"));

        waypoint_table.getColumns().addAll(waypoint_id, waypoint_x, waypoint_y, waypoint_theta);

        Pattern pattern = Pattern.compile("\\d*(\\.\\d*)?");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        TextFormatter formatter1 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        TextFormatter formatter2 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        waypoint_x_input.setTextFormatter(formatter);
        waypoint_y_input.setTextFormatter(formatter1);
        waypoint_theta_input.setTextFormatter(formatter2);
    }

    @FXML
    public void initialize() {
         waypoint_table.setEditable(true);
         cellSetup();

        // Runs when new waypoint button pressed
        new_waypoint.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (!new_waypoint.isPressed()) {
                    // TODO: add Handler / creator to add Data to Listview 
                    Main.logger.info("new Waypoint");
                    /** allows only doubles */
                    x = Double.parseDouble(waypoint_x_input.getText());
                    y = Double.parseDouble(waypoint_y_input.getText());
                    theta = Double.parseDouble(waypoint_theta_input.getText());
                    
                    
                    WaypointTableData data = new WaypointTableData(i ++,x,y,theta);
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

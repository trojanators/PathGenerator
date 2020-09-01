package org.usfirst.frc.team5740.gui;

import java.util.ArrayList;
import java.util.List;
/**
 * This Class is the javafx controller for Path Entry Window in java fx 
 * @author Nicholas Blackburn
 */
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;

import org.usfirst.frc.team5740.Main;
import org.usfirst.frc.team5740.util.WaypointManagement;
import org.usfirst.frc.team5740.util.WaypointTableData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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

    private final WaypointManagement wayManage = new WaypointManagement();

    private double x;
    private double y;
    private double theta;
    private double acc;
    private double velocity;
    private double jerk;
    private double dt;

    // Counters for Inc
    private int i = 0;
    private int output;
    private int countor = 0;

    private Boolean enable;

    @FXML
    private AnchorPane save_entrys;

    @FXML
    private Button new_waypoint;

    @FXML
    private Button remove_path;

    @FXML
    private Button preview_graph;

    @FXML
    private CheckBox generate_path;

    @FXML
    private Button exit;

    @FXML
    private TextField waypoint_x_input;

    @FXML
    private TextField waypoint_y_input;

    @FXML
    private TextField waypoint_theta_input;

    @FXML
    private TextField waypoint_acc_input;

    @FXML
    private TextField waypoint_jerk_input;

    @FXML
    private TextField waypoint_velocity_imput;

    @FXML
    private TextField waypoint_dt_input;

    @FXML
    private TableView<WaypointTableData> waypoint_table;

    private WaypointTableData data;

    /**
     * this function sets up cells names and vars to be called and sets up text
     * formater
     */
    public void cellSetup() {

        // creates columns based on waypoint id , x and y
        final TableColumn waypoint_id = new TableColumn("id");
        waypoint_id.setCellValueFactory(new PropertyValueFactory<>("id"));

        final TableColumn waypoint_x = new TableColumn("x");
        waypoint_x.setCellValueFactory(new PropertyValueFactory<>("x"));

        final TableColumn waypoint_y = new TableColumn("y");
        waypoint_y.setCellValueFactory(new PropertyValueFactory<>("y"));

        final TableColumn waypoint_theta = new TableColumn("theta");
        waypoint_theta.setCellValueFactory(new PropertyValueFactory<>("theta"));

        final TableColumn waypoint_acc = new TableColumn("maxAcc");
        waypoint_acc.setCellValueFactory(new PropertyValueFactory<>("acc"));

        final TableColumn waypoint_jerk = new TableColumn("maxJerk");
        waypoint_jerk.setCellValueFactory(new PropertyValueFactory<>("jerk"));

        final TableColumn waypoint_vel = new TableColumn("maxVelocity");
        waypoint_vel.setCellValueFactory(new PropertyValueFactory<>("Velocity"));

        final TableColumn waypoint_dt = new TableColumn("Dt");
        waypoint_dt.setCellValueFactory(new PropertyValueFactory<>("Dt"));

        waypoint_table.getColumns().addAll(waypoint_id, waypoint_x, waypoint_y, waypoint_theta, waypoint_acc,
                waypoint_vel, waypoint_jerk, waypoint_dt);

        final Pattern pattern = Pattern.compile("\\d*(\\.\\d*)?");
        final TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        final TextFormatter formatter1 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        final TextFormatter formatter2 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        final TextFormatter formatter3 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        final TextFormatter formatter4 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        final TextFormatter formatter5 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        final TextFormatter formatter6 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        waypoint_x_input.setTextFormatter(formatter);
        waypoint_y_input.setTextFormatter(formatter1);
        waypoint_theta_input.setTextFormatter(formatter2);
        waypoint_acc_input.setTextFormatter(formatter3);
        waypoint_jerk_input.setTextFormatter(formatter4);
        waypoint_velocity_imput.setTextFormatter(formatter5);
        waypoint_dt_input.setTextFormatter(formatter6);
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
                    i++;

                    Main.logger.info("new Waypoint");
                    /** allows only doubles */
                    x = Double.parseDouble(waypoint_x_input.getText());
                    y = Double.parseDouble(waypoint_y_input.getText());
                    theta = Double.parseDouble(waypoint_theta_input.getText());

                    // Additonal math for data
                    acc = Double.parseDouble(waypoint_acc_input.getText());
                    jerk = Double.parseDouble(waypoint_jerk_input.getText());
                    velocity = Double.parseDouble(waypoint_velocity_imput.getText());
                    dt = Double.parseDouble(waypoint_dt_input.getText());

                    data = new WaypointTableData(i - countor, x, y, theta, acc, jerk, velocity, dt);
                    waypoint_table.getItems().add(data);

                    try {
                        wayManage.createWaypoint(data);
                    } catch (final Exception e) {
                        Main.logger.log(Level.WARNING,
                                "Sorry I broke it Again... Dont hate me hate The Math ... \n" + e.getMessage(), e);

                    }

                }

            }

        });

        remove_path.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (!remove_path.isPressed()) {
                    countor++;
                    // TODO: remove data from Waypoint listview
                    Main.logger.info("removed waypoint");
                    waypoint_table.getItems().remove(data);

                    Main.logger.info("waypoint removed successfull!y");

                }

            }

        });

        generate_path.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (generate_path.isSelected()) {
                    // TODO: remove data from Waypoint listview
                    Main.logger.info("starting to generate path");
                    wayManage.generatePAth = true;
                }

            }

        });

        exit.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                final Stage stage = (Stage) exit.getScene().getWindow();

                if (!exit.isPressed()) {
                    // TODO: add enty to display_path_entrys
                    Main.logger.info("exited path menu");
                    stage.hide();

                }

            }

        });

    }
}

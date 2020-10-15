package pathgenerator.gui;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pathgenerator.Main;
import pathgenerator.util.WaypointManagement;
import pathgenerator.util.WaypointTableData;

public class PathDataPaneController {
    private String PathName;

    private double x;
    private double y;
    private double theta;
    private double acc;
    private double velocity;
    private double jerk;
    private double dt;
    private double seqSize;
    private Random rando = new Random();
  
    // Counters for Inc
    private int i = 0;
    private int output;
    private int countor = 0;

    private Boolean enable_Neg_Pi = false;
    private Boolean enable_Pi = false;
    private Boolean genpath;
    private boolean enableRando;
    private Boolean enableTestMode = true;
    @FXML
    private AnchorPane save_entrys;

    @FXML
    private Button new_waypoint;

    @FXML
    private Button remove_path;



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
    private TextField path_name;

    @FXML
    private TextField robot_wheelbase;

    @FXML
    private TextField pathfile_path;

    @FXML
    private CheckBox pi_enable;

    @FXML
    private CheckBox neg_pi;

    @FXML
    private CheckBox invert_y;

    @FXML
    private TableView<WaypointTableData> waypoint_table;

    @FXML
    public Pane canvas = new Pane();

    @FXML
    private ImageView backgroundImage;

    private WaypointTableData data= new WaypointTableData();
    private WaypointManagement wayManage = new WaypointManagement();
  
    private DrawWaypoints drawWaypoints = new DrawWaypoints(canvas,data);
    
    @FXML
    public void initialize() {
        
        cellSetup();
        
        Image image = new Image("/2020-Field.png");
        backgroundImage.setImage(image);
        
        waypoint_table.setEditable(true);
       
        // enables pi calc on theta
        pi_enable.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(final ActionEvent event) {

                if (pi_enable.isSelected()) {
                    // TODO: add enty to display_path_entrys
                    Main.logger.info("Enabled Pi calc");
                    enable_Pi = true;
                } else {
                    enable_Pi = false;
                }

            }

        });

        neg_pi.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {

                if (neg_pi.isSelected()) {
                    // TODO: add enty to display_path_entrys
                    Main.logger.info("Enabled Pi calc neg");
                    enable_Neg_Pi = true;
                } else {
                    enable_Neg_Pi = false;
                }

            }

        });

        // Runs when new waypoint button pressed
        new_waypoint.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                // TODO: Remove comment and enable code again

                if (!new_waypoint.isPressed() && !generate_path.isSelected()) {
                
                    enableRando = true;
                    Main.logger.info("new Waypoint");

                    x = Double.parseDouble(waypoint_x_input.getText());
                    y = Double.parseDouble(waypoint_y_input.getText());
                    theta = Double.parseDouble(waypoint_theta_input.getText()); // Additonal math for
                    acc = Double.parseDouble(waypoint_acc_input.getText());
                    jerk = Double.parseDouble(waypoint_jerk_input.getText());
                    velocity = Double.parseDouble(waypoint_velocity_imput.getText());
                    dt = Double.parseDouble(waypoint_dt_input.getText());
                
                    data = new WaypointTableData(i - countor, x, y, theta, acc, jerk, velocity, dt);
                    waypoint_table.getItems().add(data);
                    PathName = path_name.getText();
                    wayManage.createWaypoint(data, enableRando, enable_Pi, enable_Neg_Pi, getRobotWheelbase(),
                            getPathName(), getPathSaveLocal(), genpath, (int) seqSize);
                    i++;
                    Main.logger.info("increment" + i);
                    
                } else {
                    enableRando = false;
                }

                /**
                 * This is for testing
                 */
                // TODO:Remove From Production Release
                if (!new_waypoint.isPressed() && generate_path.isSelected() && enableTestMode) {
                    enableRando = true;
                  
                    x = getRandomNum();
                    y = getRandomNum();
                    theta = getRandomNum();
                    acc = getRandomNum();
                    jerk = getRandomNum();
                    velocity = getRandomNum();
                    dt = getRandomNum();
                    data = new WaypointTableData(i - countor, x, y, theta, acc, jerk, velocity, dt);
                    waypoint_table.getItems().add(data);

                    PathName = path_name.getText();
                    Main.logger.info("Waypoint Table info"+waypoint_table.getItems().get(i).toString());
                
                    wayManage.createWaypoint(data, enableRando, enable_Pi, enable_Neg_Pi, getRobotWheelbase(),
                            getPathName(), getPathSaveLocal(), genpath, (int) seqSize);

                    drawWaypoints.drawWaypoints(i);
                            
                    i++;
                    Main.logger.info("increment" + i);

                } else {
                    enableRando = false;
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

                    genpath = true;
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

    
        invert_y.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {

                if (invert_y.isSelected()) {
                    // TODO: add invert y fucntion
                    Main.logger.info("NOT ADDED YET WAIT plz. INVERT y needs fixed!");
                } else {
                    enable_Neg_Pi = false;
                }

            }

        });
    }


    /**
     * Sets up Canvas Sizeeing
     */
    private void setupCanvasPaneSizing() {
        canvas.setPrefHeight(canvas.getMaxHeight());
        canvas.setPrefWidth(canvas.getMaxWidth());
        canvas.setLayoutX(canvas.getLayoutX());
        canvas.setLayoutY(canvas.getLayoutY());
        canvas.setScaleX(canvas.getScaleX());
        canvas.setScaleY(canvas.getScaleY());
    }

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

        waypoint_x_input.setTextFormatter(new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        }));
        waypoint_y_input.setTextFormatter(new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        }));
        waypoint_theta_input.setTextFormatter(new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        }));
        waypoint_acc_input.setTextFormatter(new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        }));
        waypoint_jerk_input.setTextFormatter(new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        }));
        waypoint_velocity_imput.setTextFormatter(new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        }));
        waypoint_dt_input.setTextFormatter(new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        }));
        robot_wheelbase.setTextFormatter(new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        }));
      
    }

    public String getPathName() {
        return PathName;
    }

    private double getRobotWheelbase() {
        return Double.valueOf(robot_wheelbase.getText());
    }

    private String getPathSaveLocal() {
        return pathfile_path.getText();
    }

    public WaypointTableData getData() {
        return data;
    }

    private double getRandomNum() {

	    return rando.nextInt(3000);
    }
}

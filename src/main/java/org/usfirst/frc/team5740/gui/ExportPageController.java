
package org.usfirst.frc.team5740.gui;

import org.usfirst.frc.team5740.Main;
import org.usfirst.frc.team5740.util.WaypointTableData;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ExportPageController {
    private Stage stage = new Stage();
    private StartMain mainpage = new StartMain();
    
    private Boolean enableMathPi;
    private Boolean enableNegMathPi;
   
    @FXML
    private Button save;

    @FXML
    private CheckBox display_waypoint_graph;

    @FXML // fx:id="gen_code"
    private CheckBox gen_csv; // Value injected by FXMLLoader

    @FXML
    private Button exit;

    @FXML // fx:id="graph_name"
    private TextField csv_location; // Value injected by FXMLLoader

    @FXML
    private TextField robot_wheelbase_input;

    @FXML
    private TextField path_name_input;

    @FXML
    private CheckBox enable_mathpi;

    @FXML
    private CheckBox enable_neg_mathpi;

    
    public void initialize() {
        

        // generates csv file Action
        gen_csv.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (gen_csv.isSelected()) {
                    // TODO: add csv Generation Function with waypoints and alll maths
                    Main.logger.info("Setting -> Generating Csv to true");
                    try{
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }

            }

        });

        // Generates Java code to Read the sv
        save.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (!save.isPressed()) {
                    Main.logger.info("Settings -> Save Settings to true");
                
                }

            }

        });

        display_waypoint_graph.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (display_waypoint_graph.isSelected()) {
                    // TODO: add csv Generation Function with waypoints and alll maths
                    Main.logger.info("Setting -> Enableing Display of graph");

                }

            }

        });
        exit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (!exit.isPressed()) {
                    Main.logger.info("Going back to main");
                    try {
                        mainpage.start(stage);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                  } 

              }

          });

          enable_mathpi.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (enable_mathpi.isSelected()) {
                    Main.logger.info("setEnableAMthPi True");
                    enableMathPi = true;
                }else{
                    enableMathPi = false;
                    Main.logger.info("setEnableAMthPi False");
                }
            }
          });
          enable_neg_mathpi.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (enable_neg_mathpi.isSelected()) {
                    Main.logger.info("setEnable Neg MthPi True");
                    enableNegMathPi = true;
                }else{
                    Main.logger.info("setEnable Neg MthPi False");
                    enableNegMathPi = false;
                }
            }
          });

         
    }   

    public String getPathName(){
        return path_name_input.getText();
    }

    public double getRobotWheelBase(){
        return Double.parseDouble(robot_wheelbase_input.getText());
    }
    
    public Boolean getEnableMathPi(){
        return enableMathPi;
        
    }
    
    public Boolean getEnableNegMathPi(){
        return enableNegMathPi;
        
    }
}
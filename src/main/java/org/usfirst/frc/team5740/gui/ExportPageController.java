
package org.usfirst.frc.team5740.gui;

import org.usfirst.frc.team5740.Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class ExportPageController {

    @FXML
    private Button save;

    @FXML // fx:id="display_color_graph"
    private CheckBox display_color_graph; // Value injected by FXMLLoader

    @FXML // fx:id="gen_code"
    private CheckBox gen_csv; // Value injected by FXMLLoader

    @FXML // fx:id="graph_name"
    private TextArea csv_location; // Value injected by FXMLLoader

    public void initialize() {

          // generates csv file Action
          gen_csv.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(final ActionEvent event) {
                  if (!gen_csv.isSelected()) {
                      //TODO: add csv Generation Function with waypoints and alll maths
                      Main.logger.info("Setting -> Generating Csv to true");

                  }

              }

          });

          // Generates Java code to Read the sv
          save.setOnAction(new EventHandler<ActionEvent>() {

              @Override
              public void handle(final ActionEvent event) {
				if(!save.isPressed()){
                    Main.logger.info("Settings -> Save Settings to true");
                    
                }
				
			}
            
        }); 
    }   
}

package org.usfirst.frc.team5740.gui;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import org.usfirst.frc.team5740.Main;
import org.usfirst.frc.team5740.util.WaypointTableData;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

/**
 * Controller for JavaFx Settings window 
 * @author Nicholas Blackburn
 * @param getPathName Returns The user inputted String 
 * @param getRobotWheelBaseWidth Return entered Wheel base width Double
 * @param getEnablePi enables pI calculation on theta
 * @param getEnableNegPi enables negative pi calculation on theta
 * 
 */
public class ExportPageController {
    private StartSettings setting = new StartSettings();

    private Boolean enableMathPi;
    private Boolean enableNegMathPi;

    private String wheelBase;
    private String pathName;
    private String csvpath;

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

    @FXML
    private Button save;

    public void initialize() {

        // to format robot width input
        Pattern pattern = Pattern.compile("\\d*(\\.\\d*)?");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        robot_wheelbase_input.setTextFormatter(formatter);

        /**
         * Enables Math.PI calc for a Trajectory TODO: Write Docs on how to use tool.
         */
        enable_mathpi.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (enable_mathpi.isSelected()) {
                    Main.logger.info("setEnableAMthPi True");
                    enableMathPi = true;
                } else {
                    enableMathPi = false;
                    Main.logger.info("setEnableAMthPi False");
                }
            }
        });
        /**
         * Enables Negative Math.PI calc for a Trajectory TODO: Write Docs on how to use
         * tool.
         */
        enable_neg_mathpi.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                if (enable_neg_mathpi.isSelected()) {
                    Main.logger.info("setEnable Neg MthPi True");
                    enableNegMathPi = true;
                } else {
                    Main.logger.info("setEnable Neg MthPi False");
                    enableNegMathPi = false;
                }
            }
        });
        /**
         * Closes Settings WIndow TODO: Fix Data persitisance Issue With Inputed data
         * fater window closed
         */
        save.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                pathName = path_name_input.getText();
                wheelBase=robot_wheelbase_input.getText();
                csvpath=csv_location.getText();
            }
        });

    }

    public String getPathName() {
        return pathName;
    }

    public String getCsvLocation(){
        return csvpath;
    }

    public double getRobotWheelBase() {
        return 90.0;
    }

    public Boolean getEnableMathPi() {
        return enableMathPi;

    }

    public Boolean getEnableNegMathPi() {
        return enableNegMathPi;

    }
}
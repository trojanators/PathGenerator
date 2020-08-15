package org.usfirst.frc.team5740.gui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class SettingsPageController {
    
    @FXML // fx:id="displaygraph"
    private CheckBox displaygraph; // Value injected by FXMLLoader

    @FXML // fx:id="display_color_graph"
    private CheckBox display_color_graph; // Value injected by FXMLLoader

    @FXML // fx:id="gen_code"
    private CheckBox gen_code; // Value injected by FXMLLoader

    @FXML // fx:id="gen_comments"
    private CheckBox gen_comments; // Value injected by FXMLLoader

    @FXML // fx:id="namespace"
    private TextArea namespace; // Value injected by FXMLLoader

    @FXML // fx:id="use_namespace"
    private CheckBox use_namespace; // Value injected by FXMLLoader

    @FXML // fx:id="graph_name"
    private TextArea graph_name; // Value injected by 
}
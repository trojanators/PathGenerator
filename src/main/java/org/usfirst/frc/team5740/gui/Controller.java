package org.usfirst.frc.team5740.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class Controller {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="save_entrys"
    private AnchorPane save_entrys; // Value injected by FXMLLoader

    @FXML // fx:id="new_paths"
    private Button new_paths; // Value injected by FXMLLoader

    @FXML // fx:id="remove_path"
    private Button remove_path; // Value injected by FXMLLoader

    @FXML // fx:id="preview_graph"
    private Button preview_graph; // Value injected by FXMLLoader

    @FXML // fx:id="save_path"
    private Button save_path; // Value injected by FXMLLoader

    @FXML // fx:id="display_path_entrys"
    private ListView<?> display_path_entrys; // Value injected by FXMLLoader

    @FXML // fx:id="exit"
    private Button exit; // Value injected by FXMLLoader

    @FXML // fx:id="start"
    private Button start; // Value injected by FXMLLoader

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


    public void initialize() {
        
    }
}
package org.usfirst.frc.team5740.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class PathDataController {
    

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

}
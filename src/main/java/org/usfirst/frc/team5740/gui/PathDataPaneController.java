package org.usfirst.frc.team5740.gui;

/**
 * This Class is the javafx controller for Path Entry Window in java fx 
 * @author Nicholas Blackburn
 */
import java.util.Set;

import org.usfirst.frc.team5740.Main;
import org.usfirst.frc.team5740.util.PathListCell;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class PathDataPaneController {

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
    private ListView<String> display_path_entrys;
    
    @FXML
    private Set<String> stringSet;
    ObservableList observableList = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        // Runs when new waypoint button pressed
        new_waypoint.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent event) {
                if (!new_waypoint.isPressed()) {
                    // TODO: add enty to display_path_entrys
                    Main.logger.info("new Waypoint");
                //etListView();
                }

            }

        });
    }

    public void setListView()
    {   
       int i = 0;
      
        stringSet.add("waypoint"+ Integer.toString(i+1));
        stringSet.add("x pos");
        stringSet.add("y pos");
        stringSet.add("theta");

        observableList.setAll(stringSet);
        display_path_entrys.setItems(observableList);
        display_path_entrys.setCellFactory(new Callback<ListView<String>, javafx.scene.control.ListCell<String>>()
        {
            @Override
            public ListCell<String> call(ListView<String> listView)
            {
                return new PathListCell();
            }
        });
    }
}

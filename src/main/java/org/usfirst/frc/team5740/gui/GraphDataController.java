package org.usfirst.frc.team5740.gui;

import org.usfirst.frc.team5740.util.WaypointTableData;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class GraphDataController {
    private WaypointTableData data = new WaypointTableData();
    private NumberAxis xAxis = new NumberAxis();
    private NumberAxis yAxis = new NumberAxis();

    @FXML
    private LineChart<NumberAxis, NumberAxis> path;
    private XYChart.Series series = new XYChart.Series();
    

    @FXML
    public void initialize() {
        
        xAxis.setLabel("Path X");
        yAxis.setLabel("Path y");
        series.setName("Robot Path"); 

        for(int i=0; i < 10; i++){
            series.getData().add(new XYChart.Data(data.getWaypointIdArrayEntry(i),data.getWaypointXArrayEntry(i),data.getWaypointYArrayEntry(i)));
        }
        path.getData().add(series);

        
       
    
    
        
    }
}

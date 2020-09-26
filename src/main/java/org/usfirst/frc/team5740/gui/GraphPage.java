package org.usfirst.frc.team5740.gui;

import org.usfirst.frc.team5740.Main;
import org.usfirst.frc.team5740.util.WaypointTableData;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class GraphPage extends Application{

    private FXMLLoader loader = new FXMLLoader();
    private WaypointTableData data = new WaypointTableData();

    // Starts JavaFX Gui
    @Override
    public void start(Stage stage) throws Exception {
        loader.setLocation(getClass().getResource("/Path_display.fxml"));
        stage.setTitle("PathGenerator by Nicholas Blackburn");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Path");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Robot Path");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Robot Path");
        //populating the series with data
        for(int i =0; i <= 10;){
        series.getData().add(new XYChart.Data(data.getWaypointYArrayEntry(i),data.getWaypointXArrayEntry(i)));
        series.getData().add(new XYChart.Data(data.getWaypointXArrayEntry(i),data.getWaypointYArrayEntry(i)));
        }
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }
 
    
}

package org.usfirst.frc.team5740.gui;

import org.usfirst.frc.team5740.Main;
import org.usfirst.frc.team5740.util.WaypointTableData;

import de.gsi.chart.XYChart;
import de.gsi.chart.axes.spi.DefaultNumericAxis;
import de.gsi.dataset.spi.DoubleDataSet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GraphPage extends Application {

   
    @Override
    public void start(final Stage primaryStage) {
        final StackPane root = new StackPane();

        final XYChart chart = new XYChart(new DefaultNumericAxis(), new DefaultNumericAxis());
        root.getChildren().add(chart);

        DoubleDataSet dataSet1 = new DoubleDataSet("data set #1");
        
        // lineChartPlot.getDatasets().add(dataSet1); // for single data set
        chart.getDatasets().add(1,dataSet1); // two data sets

       for(int i=0; i <= 10;){
        dataSet1.set(1,(double)WaypointTableData.getWaypointXArrayEntry(i), (double)WaypointTableData.getWaypointYArrayEntry(i));
        i++;
       }
        final Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle(this.getClass().getSimpleName());
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(evt -> System.exit(0));
        primaryStage.show();
    }

}
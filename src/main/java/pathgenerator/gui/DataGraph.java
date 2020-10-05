package pathgenerator.gui;

import java.util.ArrayList;

import de.gsi.chart.XYChart;
import de.gsi.chart.axes.AxisLabelOverlapPolicy;
import de.gsi.chart.axes.spi.CategoryAxis;
import de.gsi.chart.axes.spi.DefaultNumericAxis;
import de.gsi.chart.plugins.DataPointTooltip;
import de.gsi.chart.plugins.EditAxis;
import de.gsi.chart.plugins.ParameterMeasurements;
import de.gsi.chart.plugins.Zoomer;
import de.gsi.chart.renderer.LineStyle;
import de.gsi.chart.renderer.spi.ErrorDataSetRenderer;
import de.gsi.dataset.DataSet;
import de.gsi.dataset.spi.DefaultErrorDataSet;
import de.gsi.dataset.utils.ProcessingProfiler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pathgenerator.trajectory.Path;
import pathgenerator.trajectory.Trajectory;

public class DataGraph extends Application {

    private Trajectory traj = new Trajectory();
    private Path path = new Path();
    
    private static ArrayList<Double> leftWheelX = new ArrayList<>(1);
    private static ArrayList<Double> rightWheelX = new ArrayList<>(1);
    private static ArrayList<Double> leftWheelY = new ArrayList<>(1);
    private static ArrayList<Double> rightWheelY = new ArrayList<>(1);
    @Override
    public void start(final Stage primaryStage) {
        ProcessingProfiler.setVerboseOutputState(true);
        ProcessingProfiler.setLoggerOutputState(true);
        ProcessingProfiler.setDebugState(false);

        final BorderPane root = new BorderPane();
        final Scene scene = new Scene(root, 800, 600);

        final DefaultNumericAxis xAxis1 = new DefaultNumericAxis("time", "iso");
        xAxis1.setOverlapPolicy(AxisLabelOverlapPolicy.SKIP_ALT);
        final DefaultNumericAxis yAxis1 = new DefaultNumericAxis("y-axis", "a.u.");

        final XYChart chart = new XYChart(xAxis1, yAxis1);
        chart.legendVisibleProperty().set(true);
     
        chart.getPlugins().add(new EditAxis());
       
        // set them false to make the plot faster
        chart.setAnimated(false);

        xAxis1.setAutoRangeRounding(false);
        // xAxis1.invertAxis(true); TODO: bug inverted time axis crashes when zooming
        xAxis1.setTimeAxis(true);
        yAxis1.setAutoRangeRounding(true);

        final DefaultErrorDataSet dataSet = new DefaultErrorDataSet("TestData");

        for(int i=0; i< 10; i++){
            leftWheelX.add(path.getLeftWheelTrajectory().getX());
            leftWheelY.add(path.getLeftWheelTrajectory().getY());
            dataSet.add(leftWheelX.get(i), leftWheelY.get(i));
        }

        long startTime = ProcessingProfiler.getTimeStamp();
        chart.getDatasets().add(dataSet);
        ProcessingProfiler.getTimeDiff(startTime, "adding data to chart");

        startTime = ProcessingProfiler.getTimeStamp();
        root.setCenter(chart);
        ProcessingProfiler.getTimeDiff(startTime, "adding chart into StackPane");

        startTime = ProcessingProfiler.getTimeStamp();
        primaryStage.setTitle(this.getClass().getSimpleName());
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(evt -> Platform.exit());
        primaryStage.show();
        ProcessingProfiler.getTimeDiff(startTime, "for showing");
    }

    /**

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        Application.launch(args);
    }
    
}

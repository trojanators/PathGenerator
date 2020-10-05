package pathgenerator.gui;

import de.gsi.chart.XYChart;
import de.gsi.chart.axes.AxisLabelOverlapPolicy;
import de.gsi.chart.axes.spi.CategoryAxis;
import de.gsi.chart.axes.spi.DefaultNumericAxis;
import de.gsi.chart.plugins.EditAxis;
import de.gsi.chart.plugins.ParameterMeasurements;
import de.gsi.chart.plugins.Zoomer;
import de.gsi.chart.renderer.LineStyle;
import de.gsi.chart.renderer.spi.ErrorDataSetRenderer;
import de.gsi.dataset.spi.DefaultErrorDataSet;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pathgenerator.trajectory.Path;
import pathgenerator.trajectory.Trajectory;

public class DataGraph extends Application {

    private Trajectory traj = new Trajectory();
    private Path path = new Path();
    private double leftX=0;
    private double leftY=0;
    private double rightX=0;
    private double rightY=0;
    @Override
    public void start(final Stage primaryStage) {
        final StackPane root = new StackPane();
        final CategoryAxis xAxis = new CategoryAxis("months");
        // xAxis.setTickLabelRotation(90);
        // alt:
        xAxis.setOverlapPolicy(AxisLabelOverlapPolicy.SHIFT_ALT);
        xAxis.setMaxMajorTickLabelCount(1);
        final DefaultNumericAxis yAxis = new DefaultNumericAxis("yAxis");

        final XYChart lineChartPlot = new XYChart(xAxis, yAxis);
        // set them false to make the plot faster
        lineChartPlot.setAnimated(false);
        lineChartPlot.getRenderers().clear();
        // lineChartPlot.getRenderers().add(new ReducingLineRenderer());
        final ErrorDataSetRenderer renderer = new ErrorDataSetRenderer();
        renderer.setPolyLineStyle(LineStyle.NORMAL);
        renderer.setPolyLineStyle(LineStyle.HISTOGRAM);
        lineChartPlot.getRenderers().add(renderer);
        lineChartPlot.legendVisibleProperty().set(true);

        lineChartPlot.getPlugins().add(new ParameterMeasurements());
        lineChartPlot.getPlugins().add(new EditAxis());
        final Zoomer zoomer = new Zoomer();
        // zoomer.setSliderVisible(false);
        // zoomer.setAddButtonsToToolBar(false);
        lineChartPlot.getPlugins().add(zoomer);

        final DefaultErrorDataSet dataSet = new DefaultErrorDataSet("myData");
        final Scene scene = new Scene(root, 800, 600);

    
      
        for (int n = 0; n < 10; n++) {
           leftX = path.getLeftWheelTrajectory().getX();
           leftY = path.getLeftWheelTrajectory().getY();
           rightX = path.getLeftWheelTrajectory().getX();
           rightY = path.
        
        }

        // setting the axis categories to null forces the first data set's
        // category
        // enable this if you want to use the data set's categories
        // xAxis.setCategories(null);

        lineChartPlot.getDatasets().add(dataSet);
        root.getChildren().add(lineChartPlot);

        primaryStage.setTitle(this.getClass().getSimpleName());
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(evt -> Platform.exit());
        primaryStage.show();
    }
    
}

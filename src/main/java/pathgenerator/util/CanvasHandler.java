package pathgenerator.util;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import pathgenerator.Main;
import pathgenerator.trajectory.Path;
import pathgenerator.trajectory.WaypointSequence;
import pathgenerator.trajectory.WaypointSequence.Waypoint;

public class CanvasHandler {

    private static ArrayList<Double>nodeXArray = new ArrayList<>();
    private static ArrayList<Double>nodeYArray = new ArrayList<>();
    private Pane pane;

    public CanvasHandler(Pane pane){
        this.pane = pane;
    }

    public void addFillArray(int index, Path path){
        nodeXArray.add(path.getRightWheelTrajectory().getSegment(index).x);
        nodeYArray.add(path.getRightWheelTrajectory().getSegment(index).y);
        Main.logger.info("Stuff in arrays for graph are"+ nodeXArray.toString() + "\n" + nodeYArray.toString());
    }

    public void drawPoint(){
       Circle circle = new Circle(1, 1, 1,Color.RED);
       circle.relocate(20, 20);
       pane.getChildren().addAll(circle);
    
    }

    
}

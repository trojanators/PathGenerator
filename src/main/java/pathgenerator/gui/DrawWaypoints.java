package pathgenerator.gui;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import pathgenerator.Main;
import pathgenerator.trajectory.Path;
import pathgenerator.trajectory.WaypointSequence.Waypoint;
import pathgenerator.util.WaypointTableData;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import pathgenerator.trajectory.WaypointSequence.Waypoint;

public class DrawWaypoints {

    private Pane canvas;
    private Path path;
    private WaypointTableData data;
    private ArrayList<Circle> waypointPointArray = new ArrayList<>();

    public DrawWaypoints(Pane canvas, WaypointTableData data){
        this.canvas = canvas;
        this.data = data;
    }
    
    
    // drawing waypoints based on checking to se if out of bounds
    public void drawWaypoints(int i){
        waypointPointArray.add(i, new Circle(5,Color.WHITE));
     
        double pathX = data.getX();
        double pathY = data.getY();

        if(checkBounds(pathX, pathY)){
            this.waypointPointArray.get(i).relocate(pathX,-pathY);
            this.canvas.getChildren().addAll(waypointPointArray.get(i));
        } else{
            Main.logger.severe("ERROR CANNOT DRAW WAYPOINT PATH IS "+pathX + pathY);
        }
    }

    public boolean checkBounds(double x, double y) {
        //Convert waypoint convention to JavaFX
        return this.canvas.getLayoutBounds().contains(x, -y);
    }
      
    private void setSelectionColor(int i){
        waypointPointArray.get(i).setFill(Color.GOLD);
    }
   

}
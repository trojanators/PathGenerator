package pathgenerator.gui;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import pathgenerator.Main;
import pathgenerator.trajectory.Path;
import pathgenerator.trajectory.WaypointSequence.Waypoint;
import pathgenerator.util.WaypointTableData;

/**
 * This class is for Handling Drawling the Waypoint's on the Pane
 * 
 * @author Nicholas Blackburn
 */
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
            waypointPointArray.get(i).relocate(pathX, pathY);
            this.canvas.getChildren().add(waypointPointArray.get(i));
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

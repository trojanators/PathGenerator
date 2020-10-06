package pathgenerator.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import pathgenerator.trajectory.Path;
import pathgenerator.trajectory.Trajectory;
import pathgenerator.trajectory.WaypointSequence;
import pathgenerator.trajectory.WaypointSequence.Waypoint;

/**
 * this class is for Handling the data being drawn on the Canvas for displaying
 * path data
 */
public class GraphDrawing {

    private Canvas can;
    private GraphicsContext gc;
    private Trajectory.Segment seg;
    private WaypointSequence seq;
    private Waypoint way;

    private static ArrayList<Double> nodeXList = new ArrayList<>();
    private static ArrayList<Double> nodeYList = new ArrayList<>();

   
    public GraphDrawing(Canvas canvas, GraphicsContext graphicContext,WaypointSequence sequence, Waypoint waypoint){
        this.can = canvas;
        this.gc = graphicContext;
        this.seq = sequence;
        this.way = waypoint;
    }

    //updates nodes on the list
    public void updateNodes(){
        nodeXList.add((int) seq.getNumWaypoints(), way.x);
        nodeYList.add((int)seq.getNumWaypoints(), way.y);
    }

    // Draws Nodes on the Canvas
    public void drawNodes(){
        for(int i = 0; i<seq.num_waypoints_; i++){
        gc.strokeOval(nodeXList.get(i), nodeYList.get(i), 1, 1);
        }
    } 
}

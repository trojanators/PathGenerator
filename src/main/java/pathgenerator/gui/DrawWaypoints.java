package pathgenerator.gui;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import pathgenerator.Main;
import pathgenerator.trajectory.Path;

/**
 * This class is for Handling Drawling the Waypoint's on the Pane
 * 
 * @author Nicholas Blackburn
 */
public class DrawWaypoints {
    private Pane canvas;
    private Circle circle;


    public DrawWaypoints(Pane canvas,Circle circle){
        this.canvas = canvas;
        this.circle = circle;
    }
    
    
    // drawing waypoints based on checking to se if out of bounds
    public void drawWaypoints(Path path){

        double pathX = path.getLeftWheelTrajectory().getSegment(0).x;
        double pathY = path.getLeftWheelTrajectory().getSegment(0).y;

        if(canvas.getLayoutBounds().contains(pathX,-pathY)){
            circle.relocate(pathX, pathY);
        }else{
            Main.logger.severe("ERROR WAYPOINT CANNOT Be DISPLAYED");
        }  
        canvas.getChildren().addAll(circle);
    }
      
   


}

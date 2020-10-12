package pathgenerator.util;

import java.io.File;
import java.io.IOException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import pathgenerator.trajectory.Path;
import pathgenerator.trajectory.PathGenerator;
import pathgenerator.trajectory.Trajectory;
import pathgenerator.trajectory.TrajectoryGenerator;
import pathgenerator.trajectory.WaypointSequence;
import pathgenerator.trajectory.TrajectoryGenerator.Config;
import pathgenerator.trajectory.WaypointSequence.Waypoint;

import pathgenerator.Main;
import pathgenerator.gui.DrawWaypoints;

/**
 * this class manages waypoint creation
 * 
 * @author nicholas blackburn
 */

public class WaypointManagement {
    private Pane pane;
    private Circle circle;

    private static TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
    private static Trajectory trajectory = new Trajectory();
    private final FileGeneration fileGen = new FileGeneration();
    private final DrawWaypoints drawWaypoints = new DrawWaypoints(pane, circle);

 
   

    public WaypointManagement(Pane pane, Circle circle){
     this.pane = pane;
     this.circle = circle;
    }

    /**
     * Creates Waypoints using the data from the waypoint datatable class
     * 
     * 
     * @param WaypointTableData data
     * @param Boolan            enablePICalcPositive
     * @param Boolean
     * @param Double
     * @param pathName
     * @param String
     * @param Location
     * @param Boolean
     * @param Boolean
     */
    //TODO: FIX LOOPING ISSUE AND FIX OVERWRITE file issue
    public void createWaypoint(final WaypointTableData data, Boolean enableRando, Boolean enablePiCalc,
            Boolean enableNegPi, final double wheebase, String pathName, String Location, Boolean genpath, int Seqnum) {

        final WaypointSequence sequence = new WaypointSequence();
        int waypointId = data.getId();

        // Mapping Data from Waypoint Table to config
        config.max_acc = data.getAcc();
        config.max_vel = data.getVelocity();
        config.max_jerk = data.getJerk();
        config.dt = data.getDt();

        // Creates a waypoint without MathPi cal
        if (enableRando && !enableNegPi && !enablePiCalc) {
            sequence.addWaypoint(new WaypointSequence.Waypoint(data.getX()/ 12, data.getY() / 12, data.getTheta()), waypointId);
            Main.logger.info("WayPoints in Created Waypoint Sequ is" + sequence.getNumWaypoints());
            Main.logger.warning("waypoint sequ list" + sequence.getWaypoint(waypointId));
            
            
        }
        if (enableRando && enablePiCalc) {
            sequence.addWaypoint(new WaypointSequence.Waypoint(data.getX() / 12.0, data.getY() / 12.0, Math.PI / data.getTheta()), waypointId);
            Main.logger.warning("Waypoint" + data.getX() / 12.0 + "" + data.getY() / 12.0 + " data.getTheta()" + Math.PI / data.getTheta());
            
        }

        if (enableRando && enableNegPi) {
            sequence.addWaypoint(new WaypointSequence.Waypoint(data.getX() / 12.0, data.getY() / 12.0, -Math.PI / data.getTheta()), waypointId);
            Main.logger.warning("Waypoint" + data.getX() / 12.0 + "," + data.getY() / 12.0 + "data.getTheta()" + -Math.PI / data.getTheta());
          
        }

        if (sequence.getNumWaypoints() == 10) {
            // Before Gen path Print out all data
            Main.logger.warning("Data" + wheebase + "," + Location + "," + pathName);
          
            createPath(Seqnum,sequence, config, wheebase, Location, pathName);
        }
    }

    /**
     * Creates Path Via data from createWaypoint Functions
     * @param seqnum
     * @param sequence
     * @param config
     * @param wheelBase
     * @param location
     * @param PathName
     */
    // TODO: Fix NullPointer in Path
    private void createPath(int seqnum, final WaypointSequence sequence, final Config config, final double wheelBase,
            final String location, final String PathName) {

        Main.logger.info("Generatring Path to File");
        Path path = PathGenerator.makePath(sequence, config, wheelBase, PathName);
        
        // Draws Point 
        drawWaypoints.drawWaypoints(path);
    }
}
package pathgenerator.util;

import java.io.File;
import java.io.IOException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import pathgenerator.trajectory.Path;
import pathgenerator.trajectory.PathGenerator;
import pathgenerator.trajectory.Trajectory;
import pathgenerator.trajectory.TrajectoryGenerator;
import pathgenerator.trajectory.WaypointSequence;
import pathgenerator.trajectory.TrajectoryGenerator.Config;
import pathgenerator.trajectory.WaypointSequence.Waypoint;

import pathgenerator.Main;

/**
 * this class manages waypoint creation
 * 
 * @author nicholas blackburn
 */

public class WaypointManagement {
    private static TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
    private static Trajectory trajectory = new Trajectory();
    private final FileGeneration fileGen = new FileGeneration();
    private final WaypointSequence sequence = new WaypointSequence();
    private Path path;
   

    public WaypointManagement(){
   
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
   
    public void createWaypoint(final WaypointTableData data, Boolean enableRando, Boolean enablePiCalc, Boolean enableNegPi, final double wheebase, String pathName, String Location, Boolean genpath, int Seqnum) {
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

    private void createPath(int seqnum, final WaypointSequence sequence, final Config config, final double wheelBase,
            final String location, final String PathName) {
        Main.logger.info("Generatring Path to File");
        this.path = PathGenerator.makePath(sequence, config, wheelBase, PathName);
        fileGen.writeFiles("test", location, PathName, path);
     
    }
    
    public Path getGeneratedPath(){
        return this.path;
    }

    public int getWaypointSequence(){
        return sequence.getNumWaypoints();
    }
}
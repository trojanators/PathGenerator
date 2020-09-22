package org.usfirst.frc.team5740.util;

import java.io.File;

import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.PathGenerator;
import com.team254.lib.trajectory.TrajectoryGenerator;
import com.team254.lib.trajectory.WaypointSequence;
import com.team254.lib.trajectory.TrajectoryGenerator.Config;
import com.team254.lib.trajectory.WaypointSequence.Waypoint;

import org.usfirst.frc.team5740.Main;


public class WaypointManagement {

    private static TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
    private final FileGeneration fileGen = new FileGeneration();

    /**
     * Creates Waypoints using the data from the waypoint datatable class
     * 
     * @author Nicholas Blackburn
     * @param WaypointTableData data
     * @param Boolan            enablePICalcPositive
     * @param Boolean           enablePiCalcNegative
     */
    public void createWaypoint(final WaypointTableData data, final Boolean enablePiCalc, final Boolean enableNegPi,
            final double wheebase, final String pathName, final String Location, final Boolean genpath) {
        
        final WaypointSequence sequence = new WaypointSequence(10);
        int waypointId = data.getId();
        double x = data.getWaypointXArrayEntry(waypointId);
        double y = data.getWaypointYArrayEntry(waypointId);
        double theta  =  data.getWaypointThetaArrayEntry(waypointId);

        // Mapping Data from Waypoint Table to config
        config.max_acc = data.getWaypointMaxACCArrayEntry(waypointId);
        config.max_vel = data.getWaypointMaxVelArrayEntry(waypointId);
        config.max_jerk = data.getWaypointMaxJerkArrayEntry(waypointId);
        config.dt = data.getWaypointDTArrayEntry(waypointId);
        while(sequence.getNumWaypoints() < 10){
        // Creates a waypoint without MathPi cal
        if (waypointId >= 0 && !enableNegPi) {
            sequence.addWaypoint(new Waypoint(x / 12.0, y / 12.0, theta), waypointId);
            Main.logger.info("WaypointID"+sequence.getNumWaypoints());
            
        } else {

        /*    if (waypointId >= 0 && enablePiCalc) {
                sequence.addWaypoint(new Waypoint(x / 12.0, y / 12.0, Math.PI / theta));
                Main.logger.warning("Waypoint" + x / 12.0 + "" + y / 12.0 + " THeta"
                        + Math.PI / theta);
            }

            if (waypointId >= 0 && enableNegPi) {
                sequence.addWaypoint(new Waypoint(x / 12.0, y / 12.0, -Math.PI / theta));
                Main.logger.warning("Waypoint" + x / 12.0 + "," + y / 12.0 + "THeta"
                        + -Math.PI / theta);
            }   
            */

        }
    }

    if (genpath) {
        // Before Gen path Print out all data
        Main.logger.warning("Data" + wheebase + "," + Location + "," + pathName);
        createPath(sequence, config, wheebase, Location, pathName);
    }
}
    
    
        
    /**
     * Creates Path Via data from createWaypoint Functions
     */
    // TODO: Fix NullPointer in Path
    private void createPath(final WaypointSequence sequence, final Config config, final double wheelBase,
            final String location, final String PathName) {
        Main.logger.info("Generatring Path to File");
        Path path = PathGenerator.makePath(sequence, config, wheelBase, PathName);
        Main.logger.info("path is Generateing");
        FileGeneration.writeFiles(location+PathName, path);

    }
}
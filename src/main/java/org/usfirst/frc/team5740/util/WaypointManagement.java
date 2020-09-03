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
    private final WaypointSequence sequence = new WaypointSequence(10);

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

        final int waypointId = data.getId();
        // Mapping Data from Waypoint Table to config
        config.max_acc = data.getAcc();
        config.max_vel = data.getVelocity();
        config.max_jerk = data.getJerk();
        config.dt = data.getDt();

        // Creates a waypoint without MathPi cal
        if (waypointId > 0) {
            sequence.addWaypoint(new Waypoint(data.getX() / 12.0, data.getY() / 12.0, data.getTheta()));

        } else {

            if (waypointId >= 0 && enablePiCalc) {
                sequence.addWaypoint(new Waypoint(data.getX() / 12.0, data.getY() / 12.0, Math.PI / data.getTheta()));
                Main.logger.warning("Waypoint" + data.getX() / 12.0 + "" + data.getY() / 12.0 + " THeta"
                        + Math.PI / data.getTheta());
            }

            if (waypointId >= 0 && enableNegPi) {
                sequence.addWaypoint(new Waypoint(data.getX() / 12.0, data.getY() / 12.0, -Math.PI / data.getTheta()));
                Main.logger.warning("Waypoint" + data.getX() / 12.0 + "," + data.getY() / 12.0 + "THeta"
                        + -Math.PI / data.getTheta());
            }
        }
        if (genpath) {
            // Before Gen path Print out all data
            Main.logger.warning("Data" + wheebase + "," + Location + "," + pathName);
            createPath(sequence, config, wheebase, Location, pathName);
        } else {
            Main.logger.info("DID NOT CLICK gen");
        }

    }

    /**
     * Creates Path Via data from createWaypoint Functions
     */
    // TODO: Fix NullPointer in Path
    private void createPath(final WaypointSequence sequence, final Config config, final double wheelBase,
            final String location, final String PathName) {
        Main.logger.info("Generatring Path to File");
        final Path path = PathGenerator.makePath(sequence, config, wheelBase, PathName);
        Main.logger.info("path is Generateing");
        FileGeneration.writeFiles(location+PathName, path);

    }
}
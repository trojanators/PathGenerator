package org.usfirst.frc.team5740.util;

import java.io.File;

import org.usfirst.frc.team5740.Main;
import org.usfirst.frc.team5740.gui.ExportPageController;
import org.usfirst.frc.team5740.trajectory.Path;
import org.usfirst.frc.team5740.trajectory.PathGenerator;
import org.usfirst.frc.team5740.trajectory.TrajectoryGenerator;
import org.usfirst.frc.team5740.trajectory.WaypointSequence;
import org.usfirst.frc.team5740.trajectory.TrajectoryGenerator.Config;
import org.usfirst.frc.team5740.trajectory.WaypointSequence.Waypoint;

public class WaypointManagement {

    private static TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
    private WaypointSequence sequence = new WaypointSequence(10);
    private ExportPageController exportPageController = new ExportPageController();
    private FileGeneration fileGen = new FileGeneration();
    public Boolean generatePAth;
    

    /**
     * Creates Waypoints using the data from the waypoint datatable class
     * 
     * @author Nicholas Blackburn
     * @param WaypointTableData data
     * @param Boolan            enablePICalcPositive
     * @param Boolean           enablePiCalcNegative
     */
    public void createWaypoint(WaypointTableData data) {
        
    
        int waypointId = data.getId();
        // Mapping Data from Waypoint Table to config
        config.max_acc = data.getAcc();
        config.max_vel = data.getVelocity();
        config.max_jerk = data.getJerk();
        config.dt = data.getDt();

        // Creates a waypoint without MathPi cal
        if (waypointId > 0) {
            sequence.addWaypoint(new Waypoint(data.getX() / 12.0, data.getY() / 12.0, data.getTheta()));
            Main.logger.warning("Waypoint" + data.getX() / 12.0 + "" + data.getY() / 12.0 + " THeta" + data.getTheta());
        } else {

            if (waypointId >= 0 && exportPageController.getEnableMathPi()) {
                sequence.addWaypoint(new Waypoint(data.getX() / 12.0, data.getY() / 12.0, Math.PI / data.getTheta()));
                Main.logger.warning("Waypoint" + data.getX() / 12.0 + "" + data.getY() / 12.0 + " THeta"
                        + Math.PI / data.getTheta());
            }

            if (waypointId >= 0 && exportPageController.getEnableNegMathPi()) {
                sequence.addWaypoint(new Waypoint(data.getX() / 12.0, data.getY() / 12.0, -Math.PI / data.getTheta()));
                Main.logger.warning("Waypoint" + data.getX() / 12.0 + "," + data.getY() / 12.0 + "THeta"+ -Math.PI / data.getTheta());
            }
        }
        if (generatePAth) {
            createPath(sequence, config,exportPageController.getRobotWheelBase(),exportPageController.getCsvLocation(),exportPageController.getPathName());
        }

    }

    /**
     * Creates Path Via data from createWaypoint Functions
     */
    // TODO: Fix NullPointer in Path
    private void createPath(WaypointSequence sequence, Config config, double wheelBase, String location, String pathnameString) {
        Main.logger.info("Generatring Path to File");
        final Path path = PathGenerator.makePath(sequence, config, wheelBase,
                exportPageController.getPathName());
        Main.logger.info("path is Generateing");
        FileGeneration.writeFiles(location+pathnameString, path);

    }
}
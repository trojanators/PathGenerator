package org.usfirst.frc.team5740.util;

import org.usfirst.frc.team5740.gui.ExportPageController;
import org.usfirst.frc.team5740.trajectory.Path;
import org.usfirst.frc.team5740.trajectory.PathGenerator;
import org.usfirst.frc.team5740.trajectory.TrajectoryGenerator;
import org.usfirst.frc.team5740.trajectory.WaypointSequence;
import org.usfirst.frc.team5740.trajectory.WaypointSequence.Waypoint;

public class WaypointManagement {

    private static TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
    private WaypointSequence sequence = new WaypointSequence(10);
    private ExportPageController exportPageController = new ExportPageController();
    private Path path;
    /**
     * Creates Waypoints using the data from the waypoint datatable class
     * @param WaypointTableData data
     * @param Boolan enablePICalcPositive
     * @param Boolean enablePiCalcNegative
     */
    public void createWaypoint(WaypointTableData data, Boolean enablePiCalcPositive, boolean enablePiCalcNegative){
        int waypointId = data.getId();
        // Mapping Data from Waypoint Table
        config.max_acc = data.getAcc();
        config.max_vel = data.getVelocity();
        config.max_jerk = data.getJerk();
        config.dt = data.getDt();

        // Creates a waypoint without MathPi cal
        if(waypointId >= 0){
        sequence.addWaypoint(new Waypoint(data.getX() / 12.0 , data.getY() / 12.0, data.getTheta()));
        }

        if(waypointId >= 0 && enablePiCalcPositive){
            sequence.addWaypoint(new Waypoint(data.getX() / 12.0 , data.getY() / 12.0, Math.PI/ data.getTheta()));
        }

        if(waypointId >= 0 && enablePiCalcNegative){
            sequence.addWaypoint(new Waypoint(data.getX() / 12.0 , data.getY() / 12.0, -Math.PI/ data.getTheta()));
        }

        path = PathGenerator.makePath(sequence, config, exportPageController.getRobotWheelBase(), exportPageController.getPathName());
    }
}
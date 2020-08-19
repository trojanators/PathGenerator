package org.usfirst.frc.team5740.util;

import org.usfirst.frc.team5740.Main;
import org.usfirst.frc.team5740.trajectory.TrajectoryGenerator;
import org.usfirst.frc.team5740.trajectory.WaypointSequence;

/**
 * This class is for Waypoint Data Management / Creatiom
 * 
 * @author Nicholas Blackburn @
 */
public class WaypointTableData {
    
    private TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();

    private int waypointId = 0;
    private double waypointX = 0;
    private double waypointY = 0;
    private double waypointTheta = 0;
    private double waypointMaxAcc = 0;
    private double waypointMaxJerk = 0;
    private double waypointMaxVelocity = 0;
    private double waypointDt = 0;

    // Waypoint data constructor
    public WaypointTableData(int waypoint_id,double waypoint_x, double waypoint_y, double theta, double maxAcc, double maxJerk, double maxVelocity, double dt){
        this.waypointId = waypoint_id;
        this.waypointX = waypoint_x;
        this.waypointY = waypoint_y;
        this.waypointTheta = theta;
        this.waypointMaxAcc = maxAcc;
        this.waypointMaxJerk = maxJerk;
        this.waypointMaxVelocity = maxVelocity;
        this.waypointDt = dt;
    }




    public int getId(){
        return waypointId;
    }

    public double getX(){
        return waypointX;
    }

    public double getY(){
        return waypointY;
    }

    public double getTheta(){
        return waypointTheta;
    }  
    public double getAcc(){
        return waypointMaxAcc;
    }

    public double getJerk(){
        return waypointMaxJerk;
    }

    public double getVelocity(){
        return waypointMaxVelocity;
    }

    public double getDt(){
        return waypointDt;
    }

}
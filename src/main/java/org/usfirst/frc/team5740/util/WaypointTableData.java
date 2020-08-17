package org.usfirst.frc.team5740.util;
/**
 * This class is for Waypoint Data Management / Creatiom
 * @author Nicholas Blackburn
 */
public class WaypointTableData {
    private int waypoint_id;
    private double waypoint_x;
    private double waypoint_y;
    private double waypoint_theta;

    // Waypoint data constructor
    public WaypointTableData(int waypoint_id,double waypoint_x, double waypoint_y, double theta){
        this.waypoint_id = waypoint_id;
        this.waypoint_x = waypoint_x;
        this.waypoint_y = waypoint_y;
        this.waypoint_theta = theta;
    }

    public int getId(){
        return waypoint_id;
    }

    public double getX(){
        return waypoint_x;
    }

    public double getY(){
        return waypoint_y;
    }

    public double getTheta(){
        return waypoint_theta;
    }

}
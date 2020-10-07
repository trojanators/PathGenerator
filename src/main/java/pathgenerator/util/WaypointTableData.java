package pathgenerator.util;

import java.util.ArrayList;

import pathgenerator.Main;
import pathgenerator.trajectory.TrajectoryGenerator;


/**
 * This class is for Waypoint Data Management / Creatiom
 * 
 * @author Nicholas Blackburn @
 */
public class WaypointTableData {
    private int Id;
    private double x;
    private double y;
    private double theta;
    private double acc;
    private double jerk;
    private double vel;
    private double dt;

    public WaypointTableData(int waypoint_id, double waypoint_x, double waypoint_y, double theta, double maxAcc,
            double maxJerk, double maxVelocity, double dt) {
        this.Id = waypoint_id;
        this.x = waypoint_x;
        this.y = waypoint_y;
        this.theta = theta;
        this.acc = maxAcc;
        this.jerk = maxJerk;
        this.vel = maxVelocity;
        this.dt = dt;

    }

    public WaypointTableData(){

    }
    
   

    public int getId() {
        return Id;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getTheta(){
        return theta;
    }

    public double getAcc(){
        return acc;
    }

    public double getJerk(){
        return jerk;
    }

    public double getVelocity(){
        return vel;
    }

    public double getDt(){
        return dt;
    }

    @Override
    public String toString(){
        return ""+Id+"  "+x+""+y+""+theta+""+vel+""+dt+""+""+jerk;
    }
    
}


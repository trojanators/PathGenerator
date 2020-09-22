package org.usfirst.frc.team5740.util;

import java.util.ArrayList;

import com.team254.lib.trajectory.TrajectoryGenerator;

import org.usfirst.frc.team5740.Main;


/**
 * This class is for Waypoint Data Management / Creatiom
 * 
 * @author Nicholas Blackburn @
 */
public class WaypointTableData {
    private int Id ;
    private double x;
    private double y; 
    private double theta;
    private double acc;
    private double jerk;
    private double vel;
    private double dt;
    private TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();

    private ArrayList waypointIdArray = new ArrayList<Double>();
    private ArrayList waypointXArray = new ArrayList<Double>();
    private ArrayList waypointYArray = new ArrayList<Double>();
    private ArrayList waypointThetaArray = new ArrayList<Double>();
    private ArrayList waypointMaxAccArray = new ArrayList<Double>();
    private ArrayList waypointMaxJerkArray = new ArrayList<Double>();
    private ArrayList waypointMaxVelocityArray = new ArrayList<Double>();
    private ArrayList waypointDtArray = new ArrayList<Double>();

    // Waypoint data constructor
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

    public void addData(){
       
        
        waypointXArray.add(x);
        waypointYArray.add(y);
        waypointThetaArray.add(theta);
        waypointMaxAccArray.add(acc);
        waypointMaxJerkArray.add(jerk);
        waypointMaxVelocityArray.add(vel);
        waypointDtArray.add(dt);

        Main.logger.warning("Data from all the arrays"+"X array"+waypointXArray.size()+" "+ "Y array"+waypointYArray.size());
    }

   /**
    * Gets Waypoint data based on waypoint id 
    * @param waypointId
    * @return
    */
    public Double getWaypointXArrayEntry(int waypointId) {
        return (double) waypointXArray.get(waypointId);
    }

    /**
     * gets data from y array basedon waypoint id 
     * @param waypointId
     * @return
     */
    public Double getWaypointYArrayEntry(int waypointId){
        return (double) waypointYArray.get(waypointId);
    }


    /**
     * gets data from theta` array basedon waypoint id 
     * @param waypointId
     * @return
     */
    public Double getWaypointThetaArrayEntry(int waypointId){
        return (double) waypointThetaArray.get(waypointId);
    }



    /**
     * gets data from MaxAcc array basedon waypoint id 
     * @param waypointId
     * @return
     */
    public Double getWaypointMaxACCArrayEntry(int waypointId){
        return (double) waypointMaxAccArray.get(waypointId);
    }

    /**
     * gets data from MaxJerk array basedon waypoint id 
     * @param waypointId
     * @return
     */
    public Double getWaypointMaxJerkArrayEntry(int waypointId){
        return (double) waypointMaxJerkArray.get(waypointId);
    }

    /**
     * gets data from MaxVel array basedon waypoint id 
     * @param waypointId
     * @return
     */
    public Double getWaypointMaxVelArrayEntry(int waypointId){
        return (double) waypointMaxVelocityArray.get(waypointId);
    }

      /**
     * gets data from  dt array basedon waypoint id 
     * @param waypointId
     * @return
     */
    public Double getWaypointDTArrayEntry(int waypointId){
        return (double) waypointDtArray.get(waypointId);
    }
    public int getId(){
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

    public int getSize(){
        return waypointXArray.size();
    }

}


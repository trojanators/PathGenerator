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

    
    private static ArrayList waypointXArray = new ArrayList<Double>(10);
    private static ArrayList waypointYArray = new ArrayList<Double>(10);
    private static ArrayList waypointThetaArray = new ArrayList<Double>(10);
    private static ArrayList waypointMaxAccArray = new ArrayList<Double>(10);
    private static ArrayList waypointMaxJerkArray = new ArrayList<Double>(10);
    private static ArrayList waypointMaxVelocityArray = new ArrayList<Double>(10);
    private static ArrayList waypointDtArray = new ArrayList<Double>(10);
    private static ArrayList waypointIdArray = new ArrayList<Double>(10);

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
       
        waypointIdArray.add(Id,Id);
        waypointXArray.add(Id,x);
        waypointYArray.add(Id,y);
        waypointThetaArray.add(Id,theta);
        waypointMaxAccArray.add(Id,acc);
        waypointMaxJerkArray.add(Id,jerk);
        waypointMaxVelocityArray.add(Id,vel);
        waypointDtArray.add(Id,dt);
        Main.logger.warning("Data from all the arrays\n"+"ID"+waypointIdArray.toString()+"\n"+"X"+waypointXArray.toString()+"\n"+ "Y"+waypointYArray.toString()+"\n" + "Theta" + waypointThetaArray.toString() + "\n");
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

      /**
     * gets data from  dt array basedon waypoint id 
     * @param waypointId
     * @return
     */
    public Double getWaypointIdArrayEntry(int waypointId){
        return (double) waypointIdArray.get(waypointId);
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


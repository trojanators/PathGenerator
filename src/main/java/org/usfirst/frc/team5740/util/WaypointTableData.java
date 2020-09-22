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

    private TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();

    private int waypointId = 0;
    private double waypointX = 0;
    private double waypointY = 0;
    private double waypointTheta = 0;
    private double waypointMaxAcc = 0;
    private double waypointMaxJerk = 0;
    private double waypointMaxVelocity = 0;
    private double waypointDt = 0;

    private ArrayList waypointIdArray = new ArrayList<>();
    private ArrayList waypointXArray = new ArrayList<>();
    private ArrayList waypointYArray = new ArrayList<>();
    private ArrayList waypointThetaArray = new ArrayList<>();
    private ArrayList waypointMaxAccArray = new ArrayList<>();
    private ArrayList waypointMaxJerkArray = new ArrayList<>();
    private ArrayList waypointMaxVelocityArray = new ArrayList<>();
    private ArrayList waypointDtArray = new ArrayList<>();

    // Waypoint data constructor
    public WaypointTableData(int waypoint_id, double waypoint_x, double waypoint_y, double theta, double maxAcc,
    double maxJerk, double maxVelocity, double dt) {
        this.waypointId = waypoint_id;
        this.waypointX = waypoint_x;
        this.waypointY = waypoint_y;
        this.waypointTheta = theta;
        this.waypointMaxAcc = maxAcc;
        this.waypointMaxJerk = maxJerk;
        this.waypointMaxVelocity = maxVelocity;
        this.waypointDt = dt;

       addData();
    }
    /**
     * adds Data to Waypoint Arrays via the Waypoint Id number 
     */
    private void addData(){
        waypointIdArray.set(waypointId, waypointId);
        waypointXArray.set(waypointId, waypointX);
        waypointYArray.set(waypointId, waypointY);
        waypointThetaArray.set(waypointId, waypointTheta);
        waypointMaxAccArray.set(waypointId, waypointMaxAcc);
        waypointMaxJerkArray.set(waypointId, waypointMaxJerk);
        waypointMaxVelocityArray.set(waypointId, waypointMaxVelocity);
        waypointDtArray.set(waypointId,waypointDt);
   }

   /**
    * Gets Waypoint data based on waypoint id 
    * @param waypointId
    * @return
    */
    public Double getWaypointXArrayEntry(int waypointId) {
        return (Double) waypointXArray.get(waypointId);
    }

    /**
     * gets data from y array basedon waypoint id 
     * @param waypointId
     * @return
     */
    public Double getWaypointYArrayEntry(int waypointId){
        return (Double) waypointYArray.get(waypointId);
    }


    /**
     * gets data from theta` array basedon waypoint id 
     * @param waypointId
     * @return
     */
    public Double getWaypointThetaArrayEntry(int waypointId){
        return (Double) waypointThetaArray.get(waypointId);
    }



    /**
     * gets data from MaxAcc array basedon waypoint id 
     * @param waypointId
     * @return
     */
    public Double getWaypointMaxACCArrayEntry(int waypointId){
        return (Double) waypointMaxAccArray.get(waypointId);
    }

    /**
     * gets data from MaxJerk array basedon waypoint id 
     * @param waypointId
     * @return
     */
    public Double getWaypointMaxJerkArrayEntry(int waypointId){
        return (Double) waypointMaxJerkArray.get(waypointId);
    }

    /**
     * gets data from MaxVel array basedon waypoint id 
     * @param waypointId
     * @return
     */
    public Double getWaypointMaxVelArrayEntry(int waypointId){
        return (Double) waypointMaxVelocityArray.get(waypointId);
    }

     /**
     * gets data from MaxAcc array basedon waypoint id 
     * @param waypointId
     * @return
     */
    public int getWaypointIDArrayEntry(int waypointId){
        return (int) waypointIdArray.get(waypointId);
    }

      /**
     * gets data from  dt array basedon waypoint id 
     * @param waypointId
     * @return
     */
    public Double getWaypointDTArrayEntry(int waypointId){
        return (Double) waypointDtArray.get(waypointId);
    }
    public int getID(){
        return waypointId;
    }





}
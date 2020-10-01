package com.team254.lib.trajectory;

import java.util.ArrayList;

import com.team254.lib.util.ChezyMath;

import org.usfirst.frc.team5740.Main;

/**
 * A WaypointSequence is a sequence of Waypoints.  #whatdidyouexpect
 *
 * @author Art Kalb
 * @author Stephen Pinkerton
 * @author Jared341
 * Modified by @author Nicholas Blackburn
 */
public class WaypointSequence {

  public int num_waypoints_;
  public static class Waypoint {

    public Waypoint(double x, double y, double theta) {
      this.x = x;
      this.y = y;
      this.theta = theta;
    }
    
    public Waypoint(Waypoint tocopy) {
      this.x = tocopy.x;
      this.y = tocopy.y;
      this.theta = tocopy.theta;
    }

    public double x;
    public double y;
    public double theta;
  }

  static ArrayList<Waypoint> waypoints_ = new ArrayList<Waypoint>();

  /***
   * Created an new Way of Incrementing and waypoint Storage
   * @param w
   * @param WaypointID
   */
  public void addWaypoint(Waypoint w, int WaypointID) {
      waypoints_.add(WaypointID, w);
      Main.logger.warning("Waypoints In list" + waypoints_.size());
      num_waypoints_ = WaypointID;
    
  }

  public int getNumWaypoints() {
    return num_waypoints_;
  }

  public Waypoint getWaypoint(int index) {
    if (index >= 0 && index < getNumWaypoints()) {
      return waypoints_.get(index);
    } else {
      return null;
    }
  }
  
  //TODO: fix Invert Y
  /*
  public WaypointSequence invertY() {
    WaypointSequence inverted = new WaypointSequence(waypoints_.length);
    inverted.num_waypoints_ = num_waypoints_;
    for (int i = 0; i < num_waypoints_; ++i) {
      inverted.waypoints_[i] = waypoints_[i];
      inverted.waypoints_[i].y *= -1;
      inverted.waypoints_[i].theta = ChezyMath.boundAngle0to2PiRadians(
              2*Math.PI - inverted.waypoints_[i].theta);
    }
    
    return inverted;
  }
  */
}

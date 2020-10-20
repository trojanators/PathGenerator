package pathgenerator.trajectory;

import java.util.ArrayList;

import pathgenerator.Main;
import pathgenerator.util.ChezyMath;

/**
 * A WaypointSequence is a sequence of Waypoints. #whatdidyouexpect
 *
 * @author Art Kalb
 * @author Stephen Pinkerton
 * @author Jared341 Modified by @author Nicholas Blackburn
 */
public class WaypointSequence {

    // private int _num_waypoints;

    public static class Waypoint 
    {
        public Waypoint(double x, double y, double theta) 
        {
            this.x = x;
            this.y = y;
            this.theta = theta;
        }

        /**
         * Copy constructor
         * @param tocopy
         */
        public Waypoint(Waypoint tocopy) 
        {
            this.x = tocopy.x;
            this.y = tocopy.y;
            this.theta = tocopy.theta;
        }

        public double x;
        public double y;
        public double theta;
    }

    static ArrayList<Waypoint> _waypoints = new ArrayList<Waypoint>();

    /***
     * Created an new Way of Incrementing and waypoint Storage
     * 
     * @param w
     * @param WaypointID
     */
    public void addWaypoint(Waypoint wypt, int WaypointID) 
    {
        _waypoints.add(WaypointID, wypt);
        Main.logger.warning("Waypoints In list: " + _waypoints.size());
        // _num_waypoints = WaypointID;
    }

    public int getNumWaypoints() 
    {
        return _waypoints.size();
    }

    public Waypoint getWaypoint(int index) 
    {
        if (index >= 0 && index < getNumWaypoints()) 
        {
            return _waypoints.get(index);
        } 
        else 
        {
            return null;
        }
    }

    // TODO: fix Invert Y

    public WaypointSequence invertY() 
    { 
        WaypointSequence inverted = new WaypointSequence();
        for(int i =0; i < _waypoints.size(); i++)
        {
            inverted.addWaypoint(_waypoints.get(i), i);
        }
        return inverted;
    }
}

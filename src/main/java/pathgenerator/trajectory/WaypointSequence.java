package pathgenerator.trajectory;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

import pathgenerator.Main;
import pathgenerator.util.ChezyMath;

/* Initial authors: Art Kalb, Stephen Pinkerton, Jared341 */


/**
 * The WaypointSequence contains a sequence of Waypoints. A waypoint is an x, y, and angle (theta) value.
 * The class also offers methods to access the waypoints sequence, add, and delete waypoints.
 * 
 */
public class WaypointSequence 
{
    public static class Waypoint 
    {
        private double x;
        private double y;
        private double theta;

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

        public double getX()
        {
            return x;
        }
        
        public double getY()
        {
            return y;
        }

        public double getTheta()
        {
            return theta;
        }

    }

    // this List holds the series of waypoints
    private static LinkedList<Waypoint> _waypoints = new LinkedList<Waypoint>();


    /**
     * Enter a Waypoint to the list of waypoints. If the waypoint index value is greater than the current count 
        of Waypoints, the new element is added at the end, regardless of the index value.
    * @param wypt An X, Y, and angle (theta) value
    * @param WaypointID The desired position in the list to add the new waypoint.
    */
    public void addWaypoint(Waypoint wypt, int WaypointID) 
    {
        // Is the data structure empty?
        if (_waypoints.size() == 0)
        {
            _waypoints.add(wypt);
            return; // This is probably redundant, but I want to make sure it exits here.
        }

        if (WaypointID <= 0)
        {
            _waypoints.addFirst(wypt);
        }
        else if (WaypointID > _waypoints.size())
        {
            _waypoints.add(wypt);
        }
        else
        {
            _waypoints.add(WaypointID, wypt);
        }
    }

    /**
     * Remove the waypoint element at the specified ID
     * @param waypointID Index of the element to remove from the list of waypoints
     * @return the waypoint value that was removed 
     */
    public Waypoint removeWaypoint(int waypointID)
    {
        // Is the data structure empty?
        if (_waypoints.size() == 0)
        {
            return null;
        }

        if (waypointID <= 0)
        {
            return _waypoints.removeFirst();
        }
        else if (waypointID > _waypoints.size())
        {
           return _waypoints.removeLast();
        }
        else
        {
            return _waypoints.remove(waypointID);
        }
    }

    /**
     * Getter for waypoints list size.
     * @return Waypoints list size (int)
     */
    public int getNumWaypoints() 
    {
        return _waypoints.size();
    }

    /**
     * Clears the waypoints sequence of all values
     */
    public void clearWaypointsSequence()
    {
        _waypoints.clear();
    }

    /**
     * Access the waypoint value at the specified index
     * @param index Waypoint index in the list to obtain
     * @return the Waypoint at the specified index.
     */
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

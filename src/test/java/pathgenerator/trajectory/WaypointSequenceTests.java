package pathgenerator.trajectory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pathgenerator.util.ChezyMath;
import pathgenerator.trajectory.WaypointSequence;



public class WaypointSequenceTests {
    // Arrange, Act, Assert


    @Test
    public void Add_One_Waypoint_With_Index_0()
    {
        // Arrange
        WaypointSequence sequence = new WaypointSequence();
        sequence.clearWaypointsSequence();

        // Act
        var pt = new WaypointSequence.Waypoint(1.0, 5.0, 757.0);
        int initialWaypointID = 0;
        sequence.addWaypoint(pt, initialWaypointID);

        var count = sequence.getNumWaypoints();
        //System.out.println("In the Successfully_Add_One_Waypoint method.");

        // Assert
        //assert(expected, actual)
        assertEquals(1, count);
    }

    @Test
    public void Add_One_Waypoint_With_Index_Greater_Than_Sequence_Size()
    {
        WaypointSequence sequence = new WaypointSequence();
        sequence.clearWaypointsSequence();

        var pt = new WaypointSequence.Waypoint(1.0, 5.0, 757.0);
        int initialWaypointID = 3;
        sequence.addWaypoint(pt, initialWaypointID);
        sequence.addWaypoint(pt, initialWaypointID);

        var count = sequence.getNumWaypoints();

        //assert(expected, actual)
        assertEquals(2, count);
    }

    @Test
    public void Add_A_Waypoint_In_The_Middle_Of_A_Sequence()
    {
        WaypointSequence sequence = new WaypointSequence();
        sequence.clearWaypointsSequence();

        sequence.addWaypoint(new WaypointSequence.Waypoint(1.0, 5.0, 757.0), 0);
        sequence.addWaypoint(new WaypointSequence.Waypoint(2.0, 7.0, 757.25), 1);
        sequence.addWaypoint(new WaypointSequence.Waypoint(6.82, 22.22, 0.757), 2);

        var somePt = sequence.getWaypoint(1);
        //assert(expected, actual)
        assertEquals(2.0, somePt.getX());
        assertEquals(7.0, somePt.getY());
        assertEquals(757.25, somePt.getTheta());
        assertEquals(3, sequence.getNumWaypoints());

        sequence.addWaypoint(new WaypointSequence.Waypoint(100.01, 700.01, 757.02503), 1);

        somePt = sequence.getWaypoint(1);
        assertEquals(100.01, somePt.getX());
        assertEquals(700.01, somePt.getY());
        assertEquals(757.02503, somePt.getTheta());
        assertEquals(4, sequence.getNumWaypoints());
    }



    @Test
    public void Remove_A_Waypoint_Index_Greater_Than_Sequence_Size()
    {
        WaypointSequence sequence = new WaypointSequence();
        sequence.clearWaypointsSequence();

        sequence.addWaypoint(new WaypointSequence.Waypoint(1.0, 5.0, 757.0), 0);
        sequence.addWaypoint(new WaypointSequence.Waypoint(2.0, 7.0, 757.25), 1);
        sequence.addWaypoint(new WaypointSequence.Waypoint(100.01, 700.01, 757.02503), 2);
        sequence.addWaypoint(new WaypointSequence.Waypoint(6.82, 22.22, 0.757), 3);

        var waypt = sequence.removeWaypoint(10);

        //assert(expected, actual)
        assertEquals(6.82, waypt.getX());
        assertEquals(22.22, waypt.getY());
        assertEquals(0.757, waypt.getTheta());

        assertEquals(3, sequence.getNumWaypoints());
    }


    @Test
    public void Remove_A_Waypoint_Index_Less_Than_Sequence_Size()
    {
        WaypointSequence sequence = new WaypointSequence();
        sequence.clearWaypointsSequence();

        sequence.addWaypoint(new WaypointSequence.Waypoint(1.0, 5.0, 757.0), 0);
        sequence.addWaypoint(new WaypointSequence.Waypoint(2.0, 7.0, 757.25), 1);
        sequence.addWaypoint(new WaypointSequence.Waypoint(100.01, 700.01, 757.02503), 2);
        sequence.addWaypoint(new WaypointSequence.Waypoint(6.82, 22.22, 0.757), 3);

        var waypt = sequence.removeWaypoint(-1);

        //assert(expected, actual)
        assertEquals(1.0, waypt.getX());
        assertEquals(5.0, waypt.getY());
        assertEquals(757.0, waypt.getTheta());

        assertEquals(3, sequence.getNumWaypoints());
    }



    @Test
    public void Remove_A_Waypoint_From_An_Empty_Sequence()
    {
        WaypointSequence sequence = new WaypointSequence();
        sequence.clearWaypointsSequence();

        // sequence.addWaypoint(new WaypointSequence.Waypoint(1.0, 5.0, 757.0), 0);
        // sequence.addWaypoint(new WaypointSequence.Waypoint(2.0, 7.0, 757.25), 1);
        // sequence.addWaypoint(new WaypointSequence.Waypoint(100.01, 700.01, 757.02503), 2);
        // sequence.addWaypoint(new WaypointSequence.Waypoint(6.82, 22.22, 0.757), 3);

        var waypt = sequence.removeWaypoint(1);

        //assert(expected, actual)
        assertEquals(null, waypt);
        // assertEquals(null, waypt);
        // assertEquals(null, waypt);

        assertEquals(0, sequence.getNumWaypoints());
    }


    @Test
    public void Remove_One_Waypoint_From_A_Sequence()
    {
        // Arrange
        WaypointSequence sequence = new WaypointSequence();
        sequence.clearWaypointsSequence();

        sequence.addWaypoint(new WaypointSequence.Waypoint(1.0, 5.0, 757.0), 0);
        sequence.addWaypoint(new WaypointSequence.Waypoint(2.0, 7.0, 757.25), 1);
        sequence.addWaypoint(new WaypointSequence.Waypoint(100.01, 700.01, 757.02503), 2);
        sequence.addWaypoint(new WaypointSequence.Waypoint(6.82, 22.22, 0.757), 3);

        var waypt = sequence.removeWaypoint(1);

        //assert(expected, actual)
        assertEquals(2.0, waypt.getX());
        assertEquals(7.0, waypt.getY());
        assertEquals(757.25, waypt.getTheta());

        assertEquals(3, sequence.getNumWaypoints());
    }
}



package pathgenerator.trajectory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import pathgenerator.util.ChezyMath;
import pathgenerator.trajectory.WaypointSequence;



public class WaypointSequenceTests {
    // Arrange, Act, Assert

    @Test
    public void Successfully_Add_One_Waypoint()
    {
        WaypointSequence sequence = new WaypointSequence();
        var pt = new WaypointSequence.Waypoint(1.0, 5.0, 757.0);
        int initialWaypointID = 0;
        sequence.addWaypoint(pt, initialWaypointID);

        var count = sequence.getNumWaypoints();
        System.out.println("In the Successfully_Add_One_Waypoint method.");
        assertEquals(1, count);
        assertEquals(1, count);
    }
}

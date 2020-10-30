package pathgenerator.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import pathgenerator.util.ChezyMath;
import pathgenerator.util.WaypointManagement;

/***
 * This Test class is here to set the waypoint management Functionality 
 * @author Nicholas Blackburn
 * @author Seth Peasly
 */
public class WaypointManagementTests 
{

    @Test
    public void Create_A_Single_Element_Sequence()
    {
        // Arrange
        var tableData = new WaypointTableData(0, 2.5, 2.0, 0.5, 2.4, 0.1, 1.0, 0.25);
        
        Boolean rando = true;
        Boolean piCalc = false;
        Boolean negPi = false;
        
        double whelbs = 120.0;
        String pathName = "./here";
        String loc = "./there";
        Boolean genPath = false;
        int sqnm = 1;

        // Act
        WaypointManagement wypMgt = new WaypointManagement();
        var sequence = wypMgt.getWaypointSequence(); 
        sequence.clearWaypointsSequence();
        wypMgt.createWaypoint(tableData, rando, piCalc, negPi, whelbs, pathName, loc, genPath, sqnm);
        
        // Assert
        //assert(expected, actual)
        var somePt = sequence.getWaypoint(0);
        assertEquals(2.5/12, somePt.getX());
        assertEquals(2.0/12, somePt.getY());
        assertEquals(0.5, somePt.getTheta());
        assertEquals(1, sequence.getNumWaypoints());

    }

    @Test
    public void Create_Waypoint_With_A_Negitive_PI_Theta_Sequence()
    {
        //Arrange, Act, Assert

        // Arrange
        var tableData = new WaypointTableData(0, 3.5, 4.0, 5.5, 6.4, 7.1, 8.0, 9.25);
        
        Boolean rando = true;
        Boolean piCalc = false;
        Boolean negPi = true;
        
        double whelbs = 120.0;
        String pathName = "./here";
        String loc = "./there";
        Boolean genPath = false;
        int sqnm = 1;

        // Act
        WaypointManagement wypMgt = new WaypointManagement();
        var sequence = wypMgt.getWaypointSequence(); 
        sequence.clearWaypointsSequence();
        wypMgt.createWaypoint(tableData, rando, piCalc, negPi, whelbs, pathName, loc, genPath, sqnm);

        // Assert
        //assert(expected, actual)
        var somePt = sequence.getWaypoint(0);
        assertEquals(3.5/12, somePt.getX());
        assertEquals(4.0/12, somePt.getY());
        assertEquals(- Math.PI /5.5, somePt.getTheta());
        assertEquals(1, sequence.getNumWaypoints());

    }


    @Test
    public void Create_Waypoint_With_A_Postive_PI_Theta_Sequence()
    {
        //Arrange, Act, Assert

        // Arrange' sall the data that is needed 
        var tableData = new WaypointTableData(0, 10.5, 11.0, 12.5, 13.4, 14.1, 15.0, 16.25);
        
        Boolean rando = true;
        Boolean piCalc = true;
        Boolean negPi = false;
        
        double whelbs = 120.0;
        String pathName = "./here";
        String loc = "./there";
        Boolean genPath = false;
        int sqnm = 1;

        // Act's up on the Data 
        WaypointManagement wypMgt = new WaypointManagement();
        var sequence = wypMgt.getWaypointSequence(); 
        sequence.clearWaypointsSequence();
        wypMgt.createWaypoint(tableData, rando, piCalc, negPi, whelbs, pathName, loc, genPath, sqnm);

        // Assert checks the data 
        //assert(expected, actual)
        var somePt = sequence.getWaypoint(0);
        assertEquals(10.5/12, somePt.getX());
        assertEquals(11.0/12, somePt.getY());
        assertEquals(Math.PI /12.5, somePt.getTheta());
        assertEquals(1, sequence.getNumWaypoints());

    }

}

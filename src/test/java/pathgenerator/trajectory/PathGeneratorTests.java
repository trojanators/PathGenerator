package pathgenerator.trajectory;

import pathgenerator.trajectory.Trajectory.Segment;
//import static pathgenerator.trajectory.TrajectoryGeneratorTest.test;

import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import pathgenerator.util.ChezyMath;

/* Initial authors: Art Kalb, Stephen Pinkerton, Jared341 */

/**
 * Test class for Path Generator
 */
public class PathGeneratorTests 
{
    static double distanceToClosest(Trajectory traj, WaypointSequence.Waypoint waypoint,
                                    Trajectory.Segment closest_segment) 
    {
        double closest = Double.MAX_VALUE;
        //int closest_id = -1;
        for (int i = 0; i < traj.getNumSegments(); ++i) 
        {
            Segment segment = traj.getSegment(i);
            double distance = Math.sqrt((segment.x - waypoint.getX()) * (segment.x - waypoint.getX())
                                        + (segment.y - waypoint.getY()) * (segment.y - waypoint.getY()));
            if (distance < closest) 
            {
                closest = distance;
                closest_segment.x = waypoint.getX();
                closest_segment.y = waypoint.getY();
                closest_segment.heading = segment.heading;
                //closest_id = i;
            }
        }

        // System.out.println("Closest point segment #: " + closest_id);
        // System.out.println("Closest point distance: " + closest);
        // System.out.println("Closest point heading difference: "
        //                    + ChezyMath.getDifferenceInAngleRadians(closest_segment.heading, 
        //                    waypoint.getTheta()));

        return closest;
    }

    static void test(WaypointSequence path) 
    {
        TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
        config.dt = .01;
        config.max_acc = 250.0;
        config.max_jerk = 1250.0;
        config.max_vel = 100.0;
        Trajectory traj = PathGenerator.generateFromPath(path, config);

        // System.out.print(traj.toString());
        // System.out.print(traj.toStringEuclidean());
        // System.out.println("Final distance=" + traj.getSegment(traj.getNumSegments() - 1).pos);

        // The trajectory should be close (allowing for loss of precision) to each
        // desired waypoint.
        for (int i = 0; i < path.getNumWaypoints(); ++i) 
        {
            WaypointSequence.Waypoint waypoint = path.getWaypoint(i);
            Segment closest = new Segment();

            assertTrue(1 > distanceToClosest(traj, waypoint, closest));
            
            double heading_diff = Math.abs(ChezyMath.getDifferenceInAngleRadians(closest.heading, waypoint.getTheta()));
            // System.out.println("Heading diff: " + heading_diff);

            assertTrue(heading_diff < 1E-2);
        }

        Trajectory.Pair output = PathGenerator.makeLeftAndRightTrajectories(traj, 20.0);

        // System.out.println("LEFT PROFILE:");
        // System.out.println(output.left.toString());
        // System.out.println(output.left.toStringEuclidean());
        // System.out.println("RIGHT PROFILE:");
        // System.out.println(output.right.toString());
        // System.out.println(output.right.toStringEuclidean());

        // At all points, the distance from left to right should equal the wheelbase
        // width and the angle of the line between them should be 90 degrees off the
        // heading.
        for (int i = 0; i < traj.getNumSegments(); ++i) 
        {
            Segment left = output.left.getSegment(i);
            Segment right = output.right.getSegment(i);
            
            var tempstor = Math.abs(Math.sqrt((left.x - right.x) * (left.x - right.x)
                                    + (left.y - right.y) * (left.y - right.y)) - 20.0);
            assertTrue(tempstor < 1E-3);

            double angle_left_to_right = Math.atan2(left.y - right.y, left.x - right.x);
            
            tempstor = Math.abs(ChezyMath.getDifferenceInAngleRadians(angle_left_to_right,
                                traj.getSegment(i).heading + Math.PI / 2));
            assertTrue(tempstor < 1E-3);
        }
    }

    public PathGeneratorTests() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSimplePath() 
    {
        WaypointSequence p = new WaypointSequence();
        p.clearWaypointsSequence();

        p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0), 0);
        p.addWaypoint(new WaypointSequence.Waypoint(100, 0, 0), 1);
        p.addWaypoint(new WaypointSequence.Waypoint(150, 50, Math.PI / 4), 2);
        test(p);
    }

    @Test
    public void testSCurveLikePath() {
        WaypointSequence p = new WaypointSequence();
        p.clearWaypointsSequence();

        p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0), 0);
        p.addWaypoint(new WaypointSequence.Waypoint(10 * 12, 0, 0), 1);
        test(p);
        p.addWaypoint(new WaypointSequence.Waypoint(15 * 12, 5 * 12, Math.PI / 4), 2);
        test(p);
        p.addWaypoint(new WaypointSequence.Waypoint(20 * 12, 10 * 12, Math.PI / 4), 3);
        test(p);
        p.addWaypoint(new WaypointSequence.Waypoint(30 * 12, 10 * 12, 0), 4);
        test(p);
    }

    @Test
    public void testZigZag() {
        WaypointSequence p = new WaypointSequence();
        p.clearWaypointsSequence();

        p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0), 0);
        p.addWaypoint(new WaypointSequence.Waypoint(10, 5, 0), 1);
        p.addWaypoint(new WaypointSequence.Waypoint(30, -5, 0), 2);
        p.addWaypoint(new WaypointSequence.Waypoint(40, 0, 0), 3);
        test(p);
    }

    @Test
    public void testZigZagWithHeadings() {
        WaypointSequence p = new WaypointSequence();
        p.clearWaypointsSequence();

        p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0), 0);
        p.addWaypoint(new WaypointSequence.Waypoint(5, 2.5, Math.PI / 5), 1);
        p.addWaypoint(new WaypointSequence.Waypoint(25, -2.5, -Math.PI / 5),2);
        p.addWaypoint(new WaypointSequence.Waypoint(40, 0, 0), 3);
        test(p);
    }

    @Test
    public void testRealishAutoMode() {
        WaypointSequence p = new WaypointSequence();
        p.clearWaypointsSequence();
        
        p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0),0);
        p.addWaypoint(new WaypointSequence.Waypoint(5 * 12, 0, 0),1);
        p.addWaypoint(new WaypointSequence.Waypoint(16 * 12, 12 * 12, 0),2);
        p.addWaypoint(new WaypointSequence.Waypoint(18 * 12, 12 * 12, 0),3);
        test(p);
    }

    @Test
    public void testDiscontinuity() {
        WaypointSequence p = new WaypointSequence();
        p.clearWaypointsSequence();

        p.addWaypoint(new WaypointSequence.Waypoint(0, 0, 0),0);
        p.addWaypoint(new WaypointSequence.Waypoint(60, 0, 0),1);
        p.addWaypoint(new WaypointSequence.Waypoint(200, 100, 0),2);
        test(p);
    }
}

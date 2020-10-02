
package pathgenerator.trajectory.io;

import pathgenerator.trajectory.WaypointSequence;
import pathgenerator.trajectory.WaypointSequence.Waypoint;
import pathgenerator.trajectory.PathGenerator;
import pathgenerator.trajectory.Trajectory;
import pathgenerator.trajectory.TrajectoryGenerator;
import pathgenerator.trajectory.io.JavaSerializer;
import pathgenerator.trajectory.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 *
 * @author jarussell
 */
public class SerializationDeserializationTest {

  public SerializationDeserializationTest() {
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
  public void testJavaSerialization() {
    WaypointSequence p = new WaypointSequence();
    p.addWaypoint(new Waypoint(0, 0, 0), 0);
    p.addWaypoint(new Waypoint(10, 0, 0), 1);
    p.addWaypoint(new Waypoint(20, 20, Math.PI / 4), 2);

    TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
    config.dt = .01;
    config.max_acc = 1000.0;
    config.max_jerk = 5000.0;
    config.max_vel = 100.0;

    Path path = PathGenerator.makePath(p, config, 25.0, "TestPath");

    JavaSerializer js = new JavaSerializer();
    String serialized = js.serialize(path);
    System.out.print(serialized);

    //Assertions.assertEquals(serialized, kGoldenOutput);
  }
  
  public boolean almostEqual(double a, double b) {
    return Math.abs(a-b) < 1E-6;
  }
  
  public void checkSegmentsEqual(Trajectory.Segment a, Trajectory.Segment b) {
    Assertions.assertTrue(almostEqual(a.acc, b.acc));
    Assertions.assertTrue(almostEqual(a.jerk, b.jerk));
    Assertions.assertTrue(almostEqual(a.vel, b.vel));
    Assertions.assertTrue(almostEqual(a.pos, b.pos));
    Assertions.assertTrue(almostEqual(a.heading, b.heading));
    Assertions.assertTrue(almostEqual(a.dt, b.dt));
    Assertions.assertTrue(almostEqual(a.x, b.x));
    Assertions.assertTrue(almostEqual(a.y, b.y));
  }
  
  @Test
  public void testTextFileSerialization() {
    WaypointSequence p = new WaypointSequence();
    p.addWaypoint(new Waypoint(0, 0, 0), 0);
    p.addWaypoint(new Waypoint(10, 0, 0), 1);
    p.addWaypoint(new Waypoint(20, 20, Math.PI / 4), 2);

    TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
    config.dt = .01;
    config.max_acc = 1000.0;
    config.max_jerk = 5000.0;
    config.max_vel = 100.0;

    Path path = PathGenerator.makePath(p, config, 25.0, "TestPath");

    TextFileSerializer tf = new TextFileSerializer();
    String serialized = tf.serialize(path);
    System.out.print(serialized);
    
    TextFileDeserializer tfd = new TextFileDeserializer();
    Path deserialized = tfd.deserialize(serialized);
    
    Assertions.assertEquals("TestPath", deserialized.getName());
    Assertions.assertEquals(deserialized.getLeftWheelTrajectory().getNumSegments(), 
            path.getLeftWheelTrajectory().getNumSegments());
    Assertions.assertEquals(deserialized.getRightWheelTrajectory().getNumSegments(), 
            path.getRightWheelTrajectory().getNumSegments());
    
    // Check segments as well
    for (int i = 0; i < path.getLeftWheelTrajectory().getNumSegments(); ++i) {
      System.out.println("Checking segment " + i);
      Trajectory.Segment left_serialized = 
              path.getLeftWheelTrajectory().getSegment(i);
      Trajectory.Segment right_serialized = 
              path.getRightWheelTrajectory().getSegment(i);
      Trajectory.Segment left_deserialized = 
              deserialized.getLeftWheelTrajectory().getSegment(i);
      Trajectory.Segment right_deserialized = 
              deserialized.getRightWheelTrajectory().getSegment(i);
      checkSegmentsEqual(left_serialized, left_deserialized);
      checkSegmentsEqual(right_serialized, right_deserialized);
    }
  }
  
  @Test
  public void testJavaStringSerializerDeserializer() {
    WaypointSequence p = new WaypointSequence();
    p.addWaypoint(new Waypoint(0, 0, 0), 0);
    p.addWaypoint(new Waypoint(10, 0, 0), 1);
    p.addWaypoint(new Waypoint(20, 20, Math.PI / 4), 2);

    TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
    config.dt = .01;
    config.max_acc = 1000.0;
    config.max_jerk = 5000.0;
    config.max_vel = 100.0;

    Path path = PathGenerator.makePath(p, config, 25.0, "TestPath");

    JavaStringSerializer tf = new JavaStringSerializer();
    String serialized = tf.serialize(path);
    System.out.print(serialized);
  }
}

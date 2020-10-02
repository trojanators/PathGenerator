
package pathgenerator.trajectory;

import pathgenerator.trajectory.Trajectory;
import pathgenerator.trajectory.TrajectoryGenerator;

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
public class TrajectoryGeneratorTest {

  static void test(double start_vel, double goal_vel, double goal_distance, TrajectoryGenerator.Strategy strategy) {
    TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
    config.dt = .01;
    config.max_acc = 250.0;
    config.max_jerk = 1250.0;
    config.max_vel = 100.0;

    Trajectory traj = TrajectoryGenerator.generate(config, strategy, start_vel, -75.0, goal_distance, goal_vel, 75.0);

    System.out.print(traj.toString());

    Trajectory.Segment last = traj.getSegment(traj.getNumSegments() - 1);
    Assertions.assertFalse(Math.abs(last.pos - goal_distance) > 1.0);
    Assertions.assertFalse(Math.abs(last.vel - goal_vel) > 1.0);
    Assertions.assertFalse(Math.abs(last.heading - 75.0) > 1.0);
  }

  public TrajectoryGeneratorTest() {
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

  // Zero velocity endpoints
  @Test
  public void testP2PStep() {
    test(100, 100, 120, TrajectoryGenerator.StepStrategy);
  }

  @Test
  public void testP2PShortStep() {
    test(100, 100, 30, TrajectoryGenerator.StepStrategy);
  }

  @Test
  public void testP2PTrapezoid() {
    test(0, 0, 120, TrajectoryGenerator.TrapezoidalStrategy);
  }

  @Test
  public void testP2PShortTrapezoid() {
    test(0, 0, 30, TrajectoryGenerator.TrapezoidalStrategy);
  }

  @Test
  public void testP2PSCurves() {
    test(0, 0, 120, TrajectoryGenerator.SCurvesStrategy);
  }

  @Test
  public void testP2PShortSCurves() {
    test(0, 0, 30, TrajectoryGenerator.SCurvesStrategy);
  }

  // Non-zero velocity endpoints
  @Test
  public void testRampUp() {
    test(0, 100, 120, TrajectoryGenerator.TrapezoidalStrategy);
  }

  @Test
  public void testSlowRampUp() {
    test(0, 50, 120, TrajectoryGenerator.TrapezoidalStrategy);
  }

  @Test
  public void testRampDown() {
    test(100, 0, 120, TrajectoryGenerator.TrapezoidalStrategy);
  }

  @Test
  public void testSlowRampDown() {
    test(50, 0, 120, TrajectoryGenerator.TrapezoidalStrategy);
  }

  @Test
  public void testRampUpDown() {
    test(50, 50, 120, TrajectoryGenerator.TrapezoidalStrategy);
  }

  @Test
  public void testConstantVelTrapezoid() {
    test(100, 100, 120, TrajectoryGenerator.TrapezoidalStrategy);
  }
}

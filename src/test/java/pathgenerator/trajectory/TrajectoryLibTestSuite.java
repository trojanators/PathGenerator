
package pathgenerator.trajectory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.runner.*;
/**
 * Test suite.
 *
 * @author Jared341
 */
@RunWith(Suite.class)
 ({ pathgenerator.trajectory.TrajectoryGeneratorTest.class, pathgenerator.trajectory.SplineTest.class,
    pathgenerator.trajectory.PathGeneratorTest.class,
    pathgenerator.trajectory.io.SerializationDeserializationTest.class })
public class TrajectoryLibTestSuite {

  @BeforeAll
  public static void setUpClass() throws Exception {
  }

  @AfterAll
  public static void tearDownClass() throws Exception {
  }

  @BeforeEach
  public void setUp() throws Exception {
  }

  @AfterEach
  public void tearDown() throws Exception {
  }

}

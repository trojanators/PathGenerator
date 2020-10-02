
package pathgenerator.trajectory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite.
 *
 * @author Jared341
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  pathgenerator.trajectory.TrajectoryGeneratorTest.class,
  pathgenerator.trajectory.SplineTest.class,
  pathgenerator.trajectory.PathGeneratorTest.class,
  pathgenerator.trajectory.io.SerializationDeserializationTest.class})
public class TrajectoryLibTestSuite {

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

}

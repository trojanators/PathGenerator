package org.usfirst.frc.team5740.trajectory.io;

import org.usfirst.frc.team5740.trajectory.Trajectory;
import org.usfirst.frc.team5740.trajectory.Path;

/**
 * Serialize a path to a Java file that can be compiled into a J2ME project.
 *
 * @author Jared341
 */
public class JavaSerializer implements IPathSerializer {

  /**
   * Generate a Java source code file from a Path
   * 
   * For example output, see the unit test.
   * 
   * @param path The path to serialize.
   * @return A complete Java file as a string.
   */
  public String serialize(Path path) {
    String contents = "package org.usfirst.frc.team5740.auto.arrays;\n\n";
    contents += "import org.usfirst.frc.team5740.pathfollower.Trajectory.Segment;\n";
    contents += "import org.usfirst.frc.team5740.pathfollower.Trajectory;\n\n";
    contents += "public class " + path.getName() + " {\n\n";
    contents += "\tpublic static final Trajectory[] trajectoryArray =  {\n";
    path.goLeft();
    contents += serializeTrajectory("kLeftWheel", 
            path.getLeftWheelTrajectory());
    contents += ",\n\n\t\t// Right wheel Trajectory\n";
    contents += serializeTrajectory("kRightWheel", 
            path.getRightWheelTrajectory());
    contents += "\n\t\t};\n\n";

    contents += "}\n";
    return contents;
  }
  
  private String serializeTrajectory(String name, Trajectory traj) {
    String contents = "\t\tnew Trajectory(new Segment[]{\n";
    for (int i = 0; i < traj.getNumSegments(); ++i) {
      Trajectory.Segment seg = traj.getSegment(i);
      contents += String.format(
              "\t\t\tnew Segment(%.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f),\n", 
              seg.pos, seg.vel, seg.acc, seg.jerk,
              seg.heading, seg.dt, seg.x, seg.y);
    }
    Trajectory.Segment seg = traj.getSegment(traj.getNumSegments()-1);
    contents += String.format(
            "\t\t\tnew Segment(%.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f, %.3f)})", 
            seg.pos, seg.vel, seg.acc, seg.jerk,
            seg.heading, seg.dt, seg.x, seg.y);
    return contents;
  }

}

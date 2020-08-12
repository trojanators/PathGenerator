package org.usfirst.frc.team5740.trajectory.io;

import org.usfirst.frc.team5740.trajectory.Path;

/**
 * Interface for methods that serialize a Path or Trajectory.
 *
 * @author Jared341
 */
public interface IPathSerializer {

  public String serialize(Path path);
}

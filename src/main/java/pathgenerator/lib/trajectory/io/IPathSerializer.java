package pathgenerator.lib.trajectory.io;

import pathgenerator.lib.trajectory.Path;

/**
 * Interface for methods that serialize a Path or Trajectory.
 *
 * @author Jared341
 */
public interface IPathSerializer {

  public String serialize(Path path);
}

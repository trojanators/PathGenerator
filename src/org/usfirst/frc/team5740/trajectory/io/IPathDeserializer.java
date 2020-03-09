package org.usfirst.frc.team5740.trajectory.io;

import org.usfirst.frc.team5740.trajectory.Path;

/**
 * Interface for methods that deserializes a Path or Trajectory.
 * 
 * @author Jared341
 */
public interface IPathDeserializer {
  
  public Path deserialize(String serialized);
}

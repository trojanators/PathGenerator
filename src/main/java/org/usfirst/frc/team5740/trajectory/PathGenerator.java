package org.usfirst.frc.team5740.trajectory;
/**
 * Generate a smooth Trajectory from a Path.
 *
 * @author Art Kalb
 * @author Stephen Pinkerton
 * @author Jared341
 */
public class PathGenerator {
  /**
   * Generate a path for autonomous driving. 
   * 
   * @param waypoints The waypoints to drive to (FOR THE "GO LEFT" CASE!!!!)
   * @param config Trajectory config.
   * @param wheelbase_width Wheelbase separation; units must be consistent with
   * config and waypoints.
   * @param name The name of the new path.  THIS MUST BE A VALID JAVA CLASS NAME
   * @return The path.
   */
  public static Path makePath(final WaypointSequence waypoints, final TrajectoryGenerator.Config config,
      final double wheelbase_width, final String name) {
    return new Path(name, generateLeftAndRightFromSeq(waypoints, config, wheelbase_width));
  }

  static Trajectory.Pair generateLeftAndRightFromSeq(final WaypointSequence path,
      final TrajectoryGenerator.Config config, final double wheelbase_width) {
    /*
     * return makeLeftAndRightTrajectories(generateFromTrapezoidalPath(path,
     * config), wheelbase_width);
     */
    return makeLeftAndRightTrajectories(generateFromPath(path, config), wheelbase_width);
  }

  /*
   * public static Path makeSplitPath(WaypointSequence waypoints1,
   * WaypointSequence waypoints2, TrajectoryGenerator.Config config, double
   * wheelbase_width, String name, double split_vel) {
   * 
   * return new Path(name, generateLeftAndRightFromSplitSeq(waypoints1,
   * waypoints2, config, wheelbase_width, split_vel)); }
   * 
   * static Trajectory.Pair generateLeftAndRightFromSplitSeq(WaypointSequence
   * path1, WaypointSequence path2, TrajectoryGenerator.Config config, double
   * wheelbase_width, double split_vel) { //Trajectory traj1 =
   * generateFromTrapezoidalPath(path1, config, 0.0, split_vel); //Trajectory
   * traj2 = generateFromTrapezoidalPath(path2, config, split_vel, 0.0);
   * traj1.append(traj2); return makeLeftAndRightTrajectories(traj1,
   * wheelbase_width); }
   */

  static Trajectory generateFromPath(final WaypointSequence path, final TrajectoryGenerator.Config config) {
    if (path.getNumWaypoints() < 2) {
      return null;
    }

    // Compute the total length of the path by creating splines for each pair
    // of waypoints.
    final Spline[] splines = new Spline[path.getNumWaypoints() - 1];
    final double[] spline_lengths = new double[splines.length];
    double total_distance = 0;
    for (int i = 0; i < splines.length; ++i) {
      splines[i] = new Spline();
      if (!Spline.reticulateSplines(path.getWaypoint(i), path.getWaypoint(i + 1), splines[i], Spline.QuinticHermite)) {
        return null;
      }
      spline_lengths[i] = splines[i].calculateLength();
      total_distance += spline_lengths[i];
    }

    // Generate a smooth trajectory over the total distance.
    final Trajectory traj = TrajectoryGenerator.generate(config, TrajectoryGenerator.SCurvesStrategy, 0.0,
        path.getWaypoint(0).theta, total_distance, 0.0, path.getWaypoint(0).theta);

    // Assign headings based on the splines.
    int cur_spline = 0;
    double cur_spline_start_pos = 0;
    double length_of_splines_finished = 0;
    for (int i = 0; i < traj.getNumSegments(); ++i) {
      final double cur_pos = traj.getSegment(i).pos;

      boolean found_spline = false;
      while (!found_spline) {
        final double cur_pos_relative = cur_pos - cur_spline_start_pos;
        if (cur_pos_relative <= spline_lengths[cur_spline]) {
          final double percentage = splines[cur_spline].getPercentageForDistance(cur_pos_relative);
          traj.getSegment(i).heading = splines[cur_spline].angleAt(percentage);
          final double[] coords = splines[cur_spline].getXandY(percentage);
          traj.getSegment(i).x = coords[0];
          traj.getSegment(i).y = coords[1];
          found_spline = true;
        } else if (cur_spline < splines.length - 1) {
          length_of_splines_finished += spline_lengths[cur_spline];
          cur_spline_start_pos = length_of_splines_finished;
          ++cur_spline;
        } else {
          traj.getSegment(i).heading = splines[splines.length - 1].angleAt(1.0);
          final double[] coords = splines[splines.length - 1].getXandY(1.0);
          traj.getSegment(i).x = coords[0];
          traj.getSegment(i).y = coords[1];
          found_spline = true;
        }
      }
    }

    return traj;
  }

  static Trajectory generateFromTrapezoidalPath(final WaypointSequence path, final TrajectoryGenerator.Config config) {
    if (path.getNumWaypoints() < 2) {
      return null;
    }

    // Compute the total length of the path by creating splines for each pair
    // of waypoints.
    final Spline[] splines = new Spline[path.getNumWaypoints() - 1];
    final double[] spline_lengths = new double[splines.length];
    double total_distance = 0;
    for (int i = 0; i < splines.length; ++i) {
      splines[i] = new Spline();
      if (!Spline.reticulateSplines(path.getWaypoint(i), path.getWaypoint(i + 1), splines[i], Spline.QuinticHermite)) {
        return null;
      }
      spline_lengths[i] = splines[i].calculateLength();
      total_distance += spline_lengths[i];
    }

    // Generate a smooth trajectory over the total distance.
    final Trajectory traj = TrajectoryGenerator.generate(config, TrajectoryGenerator.UnevenTrapezoidalStrategy, 0,
        path.getWaypoint(0).theta, total_distance, 0, path.getWaypoint(0).theta);

    // Assign headings based on the splines.
    int cur_spline = 0;
    double cur_spline_start_pos = 0;
    double length_of_splines_finished = 0;

    for (int i = 0; i < traj.getNumSegments(); ++i) {
      final double cur_pos = traj.getSegment(i).pos;

      boolean found_spline = false;
      while (!found_spline) {
        final double cur_pos_relative = cur_pos - cur_spline_start_pos;
        if (cur_pos_relative <= spline_lengths[cur_spline]) {
          final double percentage = splines[cur_spline].getPercentageForDistance(cur_pos_relative);
          traj.getSegment(i).heading = splines[cur_spline].angleAt(percentage);
          final double[] coords = splines[cur_spline].getXandY(percentage);
          traj.getSegment(i).x = coords[0];
          traj.getSegment(i).y = coords[1];
          found_spline = true;
        } else if (cur_spline < splines.length - 1) {
          length_of_splines_finished += spline_lengths[cur_spline];
          cur_spline_start_pos = length_of_splines_finished;
          ++cur_spline;
        } else {
          traj.getSegment(i).heading = splines[splines.length - 1].angleAt(1.0);
          final double[] coords = splines[splines.length - 1].getXandY(1.0);
          traj.getSegment(i).x = coords[0];
          traj.getSegment(i).y = coords[1];
          found_spline = true;
        }
      }
    }

    return traj;
  }

  static Trajectory generateFromSplitPath(final WaypointSequence path1, final WaypointSequence path2,
      final TrajectoryGenerator.Config config) {
    if (path1.getNumWaypoints() < 2 || path2.getNumWaypoints() < 2) {
      return null;
    }

    // Compute the total length of the path by creating splines for each pair
    // of waypoints.
    final Spline[] splines1 = new Spline[path1.getNumWaypoints() - 1];
    final double[] spline_lengths1 = new double[splines1.length];
    double total_distance1 = 0;
    for (int i = 0; i < splines1.length; ++i) {
      splines1[i] = new Spline();
      if (!Spline.reticulateSplines(path1.getWaypoint(i), path1.getWaypoint(i + 1), splines1[i],
          Spline.QuinticHermite)) {
        return null;
      }
      spline_lengths1[i] = splines1[i].calculateLength();
      total_distance1 += spline_lengths1[i];
    }

    final Spline[] splines2 = new Spline[path1.getNumWaypoints() - 1];
    final double[] spline_lengths2 = new double[splines2.length];
    double total_distance2 = 0;
    for (int i = 0; i < splines2.length; ++i) {
      splines2[i] = new Spline();
      if (!Spline.reticulateSplines(path1.getWaypoint(i), path1.getWaypoint(i + 1), splines2[i],
          Spline.QuinticHermite)) {
        return null;
      }
      spline_lengths2[i] = splines2[i].calculateLength();
      total_distance2 += spline_lengths2[i];
    }

    // Generate a smooth trajectory over the total distance.
    final Trajectory traj1 = TrajectoryGenerator.generate(config, TrajectoryGenerator.TrapezoidalStrategy, 0.0,
        path1.getWaypoint(0).theta, total_distance1, 5.0, path1.getWaypoint(0).theta);

    // Generate a smooth trajectory over the total distance.
    final Trajectory traj2 = TrajectoryGenerator.generate(config, TrajectoryGenerator.TrapezoidalStrategy, 5.0,
        path2.getWaypoint(0).theta, total_distance2, 0.0, path2.getWaypoint(0).theta);

    // Assign headings based on the splines.
    int cur_spline = 0;
    double cur_spline_start_pos = 0;
    double length_of_splines_finished = 0;
    for (int i = 0; i < traj1.getNumSegments(); ++i) {
      final double cur_pos = traj1.getSegment(i).pos;

      boolean found_spline = false;
      while (!found_spline) {
        final double cur_pos_relative = cur_pos - cur_spline_start_pos;
        if (cur_pos_relative <= spline_lengths1[cur_spline]) {
          final double percentage = splines1[cur_spline].getPercentageForDistance(cur_pos_relative);
          traj1.getSegment(i).heading = splines1[cur_spline].angleAt(percentage);
          final double[] coords = splines1[cur_spline].getXandY(percentage);
          traj1.getSegment(i).x = coords[0];
          traj1.getSegment(i).y = coords[1];
          found_spline = true;
        } else if (cur_spline < splines1.length - 1) {
          length_of_splines_finished += spline_lengths1[cur_spline];
          cur_spline_start_pos = length_of_splines_finished;
          ++cur_spline;
        } else {
          traj1.getSegment(i).heading = splines1[splines1.length - 1].angleAt(1.0);
          final double[] coords = splines1[splines1.length - 1].getXandY(1.0);
          traj1.getSegment(i).x = coords[0];
          traj1.getSegment(i).y = coords[1];
          found_spline = true;
        }
      }
    }
    cur_spline = 0;
    // cur_spline_start_pos = 0;
    // length_of_splines_finished = 0;
    for (int i = 0; i < traj2.getNumSegments(); ++i) {
      final double cur_pos = traj2.getSegment(i).pos;

      boolean found_spline = false;
      while (!found_spline) {
        final double cur_pos_relative = cur_pos - cur_spline_start_pos;
        if (cur_pos_relative <= spline_lengths2[cur_spline]) {
          final double percentage = splines2[cur_spline].getPercentageForDistance(cur_pos_relative);
          traj2.getSegment(i).heading = splines2[cur_spline].angleAt(percentage);
          final double[] coords = splines2[cur_spline].getXandY(percentage);
          traj2.getSegment(i).x = coords[0];
          traj2.getSegment(i).y = coords[1];
          found_spline = true;
        } else if (cur_spline < splines2.length - 1) {
          length_of_splines_finished += spline_lengths2[cur_spline];
          cur_spline_start_pos = length_of_splines_finished;
          ++cur_spline;
        } else {
          traj2.getSegment(i).heading = splines2[splines2.length - 1].angleAt(1.0);
          final double[] coords = splines2[splines2.length - 1].getXandY(1.0);
          traj2.getSegment(i).x = coords[0];
          traj2.getSegment(i).y = coords[1];
          found_spline = true;
        }
      }
    }

    traj1.append(traj2);

    return traj1;
  }

  /**
   * Generate left and right wheel trajectories from a reference.
   *
   * @param input           The reference trajectory.
   * @param wheelbase_width The center-to-center distance between the left and
   *                        right sides.
   * @return [0] is left, [1] is right
   */
  static Trajectory.Pair makeLeftAndRightTrajectories(final Trajectory input, final double wheelbase_width) {
    final Trajectory[] output = new Trajectory[2];
    output[0] = input.copy();
    output[1] = input.copy();
    final Trajectory left = output[0];
    final Trajectory right = output[1];

    for (int i = 0; i < input.getNumSegments(); ++i) {
      final Trajectory.Segment current = input.getSegment(i);
      final double cos_angle = Math.cos(current.heading);
      final double sin_angle = Math.sin(current.heading);

      final Trajectory.Segment s_left = left.getSegment(i);
      s_left.x = current.x - wheelbase_width / 2 * sin_angle;
      s_left.y = current.y + wheelbase_width / 2 * cos_angle;
      if (i > 0) {
        // Get distance between current and last segment
        final double dist = Math.sqrt((s_left.x - left.getSegment(i - 1).x) * (s_left.x - left.getSegment(i - 1).x)
            + (s_left.y - left.getSegment(i - 1).y) * (s_left.y - left.getSegment(i - 1).y));
        s_left.pos = left.getSegment(i - 1).pos + dist;
        s_left.vel = dist / s_left.dt;
        s_left.acc = (s_left.vel - left.getSegment(i - 1).vel) / s_left.dt;
        s_left.jerk = (s_left.acc - left.getSegment(i - 1).acc) / s_left.dt;
      }

      final Trajectory.Segment s_right = right.getSegment(i);
      s_right.x = current.x + wheelbase_width / 2 * sin_angle;
      s_right.y = current.y - wheelbase_width / 2 * cos_angle;
      if (i > 0) {
        // Get distance between current and last segment
        final double dist = Math.sqrt((s_right.x - right.getSegment(i - 1).x)
                * (s_right.x - right.getSegment(i - 1).x)
                + (s_right.y - right.getSegment(i - 1).y)
                * (s_right.y - right.getSegment(i - 1).y));
        s_right.pos = right.getSegment(i - 1).pos + dist;
        s_right.vel = dist / s_right.dt;
        s_right.acc = (s_right.vel - right.getSegment(i - 1).vel) / s_right.dt;
        s_right.jerk = (s_right.acc - right.getSegment(i - 1).acc) / s_right.dt;
      }
    }

    return new Trajectory.Pair(output[0], output[1]);
  }
}

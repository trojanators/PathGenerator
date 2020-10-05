# PathGenerator Output File Specification
## 1.0 Introduction 

#### The PathGenerator tool saves its output as in a file. This file is used for additional processing by other programs, for example, the FRC Robot Control Software. This specification details the kinds of data to be stored in the file. 

## 1.1 Short Description of the PathGenerator File.

### The PathGenerator tool is used by the FRC team to plan the pathing for a robot during the autonomous period of an FRC Match. The paths are planned by team members prior to a match then uploaded to the robot for use during the game. This planning allows for a more fine-grained approach to autonomous robot behavior. The team members plan the path of the robot across the field, then deconstruct that path into a series of nodes, which are also called waypoints. Each waypoint of the path includes the various values the robot's control system needs to move it along the path to the next waypoint. These values include, desired velocity (speed and direction), k-constants for the PID loops, and a free-form text field that is human readable. 

### The output of the PathGenerator is the file described here. 

## 2.0 Description of the Data 
### The information contained in the file includes the node index, which is 0-based, and monotonically increasing. Each element also include the following information: 
  - ### Desired Position 
  - ### Desired Velocity
  - ### Desired Acceleration
  - ### Jerk
  - ### Desired Heading
  - ### Elapsed time (delta-T)
  - ### Actual X-coordinate
  - ### Actual Y-coordinate
  - ### Human-readable comment


## 2.1 Node Index
### The node index starts at 0 and increases monotonically (1, 2, 3...). The node is stored as a whole number. Node values less than 0 are dis-allowed.
    Symbol: Id  
    Type:   Integer  
    Unit:   N/A  
    Range:  0 -> MaxInt-1  
    Notes:  It is not recommended to exceed more than 100 nodes. This would be indicative of an issue with route planning.  

## 2.2 Position
### Describes the position of the next waypoint. The position value is relative to the current position of the robot.
    Symbol: Pos  
    Type:   Double-Precision floating point number  
    Unit:   meters  
    Range:  0 - 100  
    Notes:  It's unlikely to exceed 100 meters of travel during autonomous.  


## 2.3 Velocity
### Describes the desired speed of the robot's motors while it travels to the next waypoint. 
    Symbol: Vel  
    Type:   Double-Precision floating point number  
    Unit:   Unitless;  
    Range:  -1.0 to +1.0  
    Notes:  A value between -1.0 and +1.0, where 0.0 is no motor movement, -1.0 is maximum reverse speed of the motor, and +1.0 is the maximum | forward speed of the motor.  



## 2.4 Acceleration
### Describes the desired rate-of-change of speed. For the robot to speed up, acceleration is a positive, to slow down, the acceleration is a negative
    Symbol: Acc
    Type:   Double-Precision floating point number
    Unit:   Unitless (???)
    Range:  It's probably a value for the PID loops?
    Notes:  N/A


``` 
    Jerk: An Double for accounting for Minor Movements in Robot Motion / unknown purpose Units: Unknown 

    Heading: An Double for a Unknown Use. Units Unknown
    
    Dt: An Double used for setting the period of your loop. Units Mills/ Unknown

    X: An Double used for setting UNknown Units: Unknown 

    Y: An Double Used For Unknown  Unis: Unknown

    UserComment: An String For user to Write a 255 Char Limited Message

```
## 3.0 Data Structure 

#### This is the Example of the Data Structure  
``` 
[Waypoint ID]
    [UserComment]
    [Pos]
    [Vel]
    [Acc]
    [Jerk]
    [Heading]
    [Dt]
    [X]
    [Y]
```

## 3.1.0 Data Structure Continued 

#### The Data Structure of this file uses a top - down approach. This means that the Path Data file reads, linearly Starting With the Waypoint Id and ending with the Y value of the Waypoint. Refer to figure 1.2 Data Structure for the file layout. The Liner Layout of the DataStructure makes it  easy to comply with human readable format standard.


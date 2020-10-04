# PathGenerator Output file Specification

## 1.0  Path File Definition 

#### This file is for Storing the Generated Path Data from the PathGenerator tool. 
### -- 

## 1.0.1 Short Description of the PathGenerator File.

#### The PathGenerator File holds the generated data created by the PathGenerator tool. This FIle holds waypoints starting from numerical 0 to the signed 32 bit integer limit 2,147,483,647. This file holds the Generated Data of **Id**, **Pos**, **Vel**, **Acc**,  **Jerk**, **Heading**, **Dt**, **X**, **Y** and **User Comments**

### --

## 1.1 Description of the Data 
#### This file includes the following Data:

``` 
    Id: An Int For Numbering Waypoints Units: NA

    Pos: An Double for Defining the Encoder Pos of Waypoint Units: Encoder Ticks


    Vel: An Double for Controlling Speed of Robot Units: Unknown

    
    Acc: An Double for Controlling The Acceleration of the Robot Units: Unknown


    Jerk: An Double for accounting for Minor Movements in Robot Motion / unknown purpose Units: Unknown 


    Heading: An Double for a Unknown Use. Units Unknown
    

    Dt: An Double used for setting the period of your loop. Units Mills/ Unknown


    X: An Double used for setting UNknown Units: Unknown 


    Y: An Double Used For Unknown  Unis: Unknown

    UserComment: An String For user to Write a 255 Char Limited Message

```
### -- 

## 1.2 Data Structure 

#### This is the Example of the Data Structure of 
``` 
[UserComment]
[Waypoint ID]
[Pos]
[Vel]
[Acc]
[Jerk]
[Heading]
[Dt]
[X]
[Y]
```
### --
## 1.2.1 Data Structure Continued 

#### The Data Structure of this file uses a top - down approach. This means that the Path Data file reads, linearly Starting With the User Comment and ending with the Y value of the Waypoint. Refer to figure 1.2 Data Structure for the file layout. 
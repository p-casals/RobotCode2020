/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  // === ROBOT PORTS === //

  // DRIVE MOTORS
  public static final int FRONT_LEFT_DRIVE_MOTOR = 0;
  public static final int BACK_LEFT_DRIVE_MOTOR = 1;
  public static final int FRONT_RIGHT_DRIVE_MOTOR = 2;
  public static final int BACK_RIGHT_DRIVE_MOTOR = 3;
  
  // INTAKE
  public static final int WHEEL_INTAKE_MOTOR = 4;
  public static final int DEPLOY_INTAKE_MOTOR = 5;
  
  // PISTONS
  public static final int CLIMBER_PISTON_LIFT_PISTON_1 = 0;
  public static final int CLIMBER_PISTON_LIFT_PISTON_2 = 1;
  public static final int CLIMBER_ROBOT_LIFT_PISTON_1 = 2;
  public static final int CLIMBER_ROBOT_LIFT_PISTON_2 = 3;

  public static final int INTAKE_PISTON_1 = 4;
  public static final int INTAKE_PISTON_2 = 5;

  // ENCODERS
  public static final int LEFT_ENCODER_PORT = 0; // port 1 is reserved
  public static final int RIGHT_ENCODER_PORT = 2; // port 3 is reserved
  
  // SHOOTERS
  public static final int SHOOTER_MOTOR_1 = 7;
  public static final int SHOOTER_MOTOR_2 = 8;
  public static final int SHOOTER_MOTOR_3 = 9;

  // === CONTROLLERS === //
  
  // STICKS
  public static final int HORIZ_AXIS_LEFT = 0;
  public static final int FORWARD_AXIS_LEFT = 1;
  public static final int FORWARD_AXIS_RIGHT = 2;
  public static final int HORIZ_AXIS_RIGHT = 3;

  // TRIGGERS
  public static final int LEFT_TRIGGER_AXIS = 7;

  // CONTROLLERS
  public static final int DRIVER_CONTROLLER = 0;
  public static final int OPERATOR_CONTROLLER = 1;

  // CLIMBING
  public static final int CLIMB_BUTTON = 1;
  public static final int RAISE_HOOKS_BUTTON = 2;
  public static final int RAISE_CLIMB_PISTONS_BUTTON  = 3;
  public static final int LOWER_CLIMB_PISTONS_BUTTON = 4;

  // SHOOTING
  
  // TODO: Change this number
  public static final int SHOOT_BUTTON = 7;

  // INTAKE

  // TODO: MAKE TOGGLEABLE???
  public static final int DEPLOY_INTAKE = 5;
  public static final int RETRACT_INTAKE = 6;
  public static final int END_INTAKE = 8;
  public static final int RUN_INTAKE = 9;


  
  // === SPEED CONSTANTS === //
  
  // INTAKE
  public static final double DEPLOY_INTAKE_SPEED = 0.5;
  public static final double WHEEL_INTAKE_SPEED = 0.5;
  
}


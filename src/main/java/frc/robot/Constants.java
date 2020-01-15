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

  // MOTORS
  public static final int FRONT_LEFT_DRIVE_MOTOR = 0;
  public static final int BACK_LEFT_DRIVE_MOTOR = 1;
  public static final int FRONT_RIGHT_DRIVE_MOTOR = 2;
  public static final int BACK_RIGHT_DRIVE_MOTOR = 3;
  
  public static final int WHEEL_INTAKE_MOTOR = 4;
  public static final int DEPLOY_INTAKE_MOTOR = 5;

  // ENCODERS
  public static final int LEFT_ENCODER_PORT = 0; // port 1 is reserved
  public static final int RIGHT_ENCODER_PORT = 2; // port 3 is reserved

  // === CONTROLLERS === //
  public static final int HORIZ_AXIS_LEFT = 0;
  public static final int FORWARD_AXIS_LEFT = 1;
  public static final int FORWARD_AXIS_RIGHT = 2;
  public static final int HORIZ_AXIS_RIGHT = 3;

  // === SPEED CONSTANTS === //
  public static final double DEPLOY_INTAKE_SPEED = 0.5;
  public static final double WHEEL_INTAKE_SPEED = 0.5;
}

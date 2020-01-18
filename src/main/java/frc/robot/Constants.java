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
  // PISTONS
  public static final int SECONDARY_PISTON_1 = 0;
  public static final int SECONDARY_PISTON_2 = 1;
  public static final int PRIMARY_PISTON_1 = 2;
  public static final int PRIMARY_PISTON_2 = 3;

  // CONTROLLERS
  public static final int OPERATOR_CONTROLLER = 1;

  // CLIMBING
   // CLIMBING
   public static final int RAISE_OR_LOWER_CLIMB_PISTONS = 5;
   public static final int CLIMB_OR_LOWER = 6;
}


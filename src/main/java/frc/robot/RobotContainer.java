/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DrivetrainCommand;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  // Subsystems
  private Drivetrain DRIVETRAIN;

  // Commands
  private DrivetrainCommand driveCommand;

  // Controllers
  public static Joystick driverController, opController;
  public static JoystickButton buttonA, buttonB, buttonX, buttonY;

  // Robot Ports
  public static final int FRONT_LEFT_DRIVE_MOTOR = 0;
  public static final int BACK_LEFT_DRIVE_MOTOR = 1;
  public static final int FRONT_RIGHT_DRIVE_MOTOR = 2;
  public static final int BACK_RIGHT_DRIVE_MOTOR = 3;

  // Controller Constants
  public static final int FORWARD_AXIS_LEFT = 0;
  public static final int HORIZ_AXIS_LEFT = 1;
  public static final int FORWARD_AXIS_RIGHT = 2;
  public static final int HORIZ_AXIS_RIGHT = 3;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureButtonBindings();
    initializeSubsystems();
    initializeCommands();

    scheduleCommands();
  }

  private void initializeSubsystems() {
    DRIVETRAIN = new Drivetrain();
  }

  private void initializeCommands() {
    driveCommand = new DrivetrainCommand(DRIVETRAIN);
  }

  private void scheduleCommands() {
    driveCommand.schedule();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    driverController = new Joystick(0);
    opController = new Joystick(1);

    buttonA = new JoystickButton(opController, 0);
    buttonB = new JoystickButton(opController, 1);
    buttonX = new JoystickButton(opController, 2);
    buttonY = new JoystickButton(opController, 3);
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null; //TODO- create auto command
  }
}

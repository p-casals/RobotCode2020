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
import frc.robot.commands.auto.routines.TestAutoCommandGroup;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  // Subsystems
  private final Drivetrain DRIVETRAIN = new Drivetrain();

  // Commands

  // Controllers
  private Joystick driverController, opController;
  private JoystickButton buttonA, buttonB, buttonX, buttonY;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureButtonBindings();
  }

  public double getVerticalAxisLeft() {
    return driverController.getRawAxis(Constants.FORWARD_AXIS_LEFT);
  }

  public double getHorizontalAxisRight() {
    return driverController.getRawAxis(Constants.HORIZ_AXIS_RIGHT);
  }

  public double getLeftTrigger() {
    return opController.getRawAxis(Constants.LEFT_TRIGGER_AXIS);
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

  public Drivetrain getDrivetrain() {
    return this.DRIVETRAIN;
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new TestAutoCommandGroup(DRIVETRAIN); //TODO- create auto command
  }
}

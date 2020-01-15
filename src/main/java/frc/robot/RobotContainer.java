
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.auto.routines.TestAutoCommandGroup;
import frc.robot.subsystems.*;

public class RobotContainer {

    // SUBSYSTEMS
    private final Drivetrain DRIVETRAIN = new Drivetrain();
    private final Climber CLIMBER = new Climber();
    private final Shooter SHOOTER = new Shooter();

    // CLIMBER COMMANDS

    private final StartEndCommand climb = new StartEndCommand(
            //runnable on init
            () -> CLIMBER.reverseHookPiston(),
            //runnable on end
            () -> CLIMBER.stopHookPiston(),
            CLIMBER
    );

    private final StartEndCommand raiseHooks = new StartEndCommand(
            //runnable on init
            () -> CLIMBER.raiseHooks(),
            //runnable on end
            () -> CLIMBER.stopHookPiston(),
            CLIMBER
    );

    private final StartEndCommand raiseClimbPistons = new StartEndCommand(
            //runnable on init
            () -> CLIMBER.raiseClimber(),
            //runnable on end
            () -> CLIMBER.stopRaisePiston(),
            CLIMBER
    );

    private final StartEndCommand lowerClimbPistons = new StartEndCommand(
            //runnable on init
            () -> CLIMBER.reverseRaisePiston(),
            //runnable on end
            () -> CLIMBER.stopRaisePiston(),
            CLIMBER
    );

    // SHOOTER COMMANDS
    
    private final StartEndCommand shootAtSpeed = new StartEndCommand(
        // TODO: change setspeed parameter to variable if vision processing works.
        
        //Runnable on initialise
        () -> SHOOTER.setSpeed(1),
        //Runnable on end
        () -> SHOOTER.makeZero(),
        SHOOTER
    );

    // CONTROLLERS

    private final Joystick driverController = new Joystick(0), opController = new Joystick(1);

    private final JoystickButton climbButton = new JoystickButton(opController, 1),
            raiseHooksButton = new JoystickButton(opController, 2),
            raiseClimbPistonsButton = new JoystickButton(opController, 3),
            lowerClimbPistonsButton = new JoystickButton(opController, 4);

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

    private void configureButtonBindings() {
        climbButton.whenPressed(climb.withTimeout(8));
        raiseHooksButton.whenPressed(raiseHooks.withTimeout(6));
        raiseClimbPistonsButton.whenPressed(raiseClimbPistons.withTimeout(2));
        lowerClimbPistonsButton.whenPressed(lowerClimbPistons.withTimeout(2));

        // TODO- add buttons for intake and change climb buttons
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
        return new TestAutoCommandGroup(DRIVETRAIN);

    }

}
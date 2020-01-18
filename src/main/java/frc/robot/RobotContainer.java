
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
import frc.robot.subsystems.*;

import static frc.robot.Constants.*;

public class RobotContainer {

    // SUBSYSTEMS
    private final Climber CLIMBER = new Climber();

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

    // MAKE A NEW JOYSTICK

    public final Joystick opController = new Joystick(OPERATOR_CONTROLLER);
  
    // CONFIG BUTTON BINDINGS (See constants.java to change specific ports etc.)

    // CLIMB BUTTONS

    private final JoystickButton climbButton = new JoystickButton(opController, CLIMB_BUTTON),
                                 raiseHooksButton = new JoystickButton(opController, RAISE_HOOKS_BUTTON),
                                 raiseClimbPistonsButton = new JoystickButton(opController, RAISE_CLIMB_PISTONS_BUTTON),
                                 lowerClimbPistonsButton = new JoystickButton(opController, LOWER_CLIMB_PISTONS_BUTTON);

    public RobotContainer() {
        configureButtonActions();
    }

    /**
     * Config button actions: it changes what does each button do. Don't touch this to change bindings
     */
    private void configureButtonActions() {
        // CLIMB BUTTONS
        climbButton.whenPressed(climb.withTimeout(8));
        raiseHooksButton.whenPressed(raiseHooks.withTimeout(6));
        raiseClimbPistonsButton.whenPressed(raiseClimbPistons.withTimeout(2));
        lowerClimbPistonsButton.whenPressed(lowerClimbPistons.withTimeout(2));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */

    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return null;
    }

}
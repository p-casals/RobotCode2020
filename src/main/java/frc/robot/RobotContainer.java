
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

import static frc.robot.Constants.*;

public class RobotContainer {

    // SUBSYSTEMS
    private final Drivetrain DRIVETRAIN = new Drivetrain();
    private final Climber CLIMBER = new Climber();
    private final Shooter SHOOTER = new Shooter();
    private final Intake INTAKE = new Intake();

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
        
    // TODO: change set speed parameter to variable if vision processing works.
        
        //Runnable on initialise
        () -> SHOOTER.setSpeed(1),
        //Runnable on end
        () -> SHOOTER.makeZero(),
        SHOOTER


    );

    // INTAKE COMMANDS

    private final StartEndCommand deployIntake = new StartEndCommand(
        () -> INTAKE.deploySpeed(DEPLOY_INTAKE_SPEED),
        () -> INTAKE.deploySpeed(0),
        INTAKE
    );

    private final InstantCommand intakeOn = new InstantCommand(
        () -> INTAKE.wheelSpeed(WHEEL_INTAKE_SPEED),
        INTAKE
    );

    private final InstantCommand intakeOff = new InstantCommand(
        () -> INTAKE.wheelSpeed(0),
        INTAKE
    );

    // HAILEY'S TODO: FINISH STREAMLINING INTAKE STUFF: RUNINTAKE, ENDINTAKE.



  
    // MAKE A NEW JOYSTICK

    public final Joystick driverController = new Joystick(DRIVER_CONTROLLER), opController = new Joystick(OPERATOR_CONTROLLER);
  
    // CONFIG BUTTON BINDINGS (See constants.java to change specific ports etc.)

    // CLIMB BUTTONS

    private final JoystickButton climbButton = new JoystickButton(opController, CLIMB_BUTTON),
                                 raiseHooksButton = new JoystickButton(opController, RAISE_HOOKS_BUTTON),
                                 raiseClimbPistonsButton = new JoystickButton(opController, RAISE_CLIMB_PISTONS_BUTTON),
                                 lowerClimbPistonsButton = new JoystickButton(opController, LOWER_CLIMB_PISTONS_BUTTON);

    // SHOOT BUTTON (TOGGLEABLE)

    private final JoystickButton shootButton = new JoystickButton(opController, SHOOT_BUTTON);

    // INTAKE BUTTONS
    // TODO: what intake-y things do we actually need?

    private final JoystickButton deployIntakeButton = new JoystickButton(opController,DEPLOY_INTAKE);
    private final JoystickButton retractIntakeButton = new JoystickButton(opController, RETRACT_INTAKE);
    

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */

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

        // SHOOT BUTTONS
        // TODO - this might not work. Check (I think it does... see Button.class)
        shootButton.toggleWhenPressed(shootAtSpeed);

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

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

    // SECOND LEVEL CLIMBER COMMANDS
    // TODO - check timeout times - I kind of made them up
    private final ConditionalCommand climbOrLower = new ConditionalCommand(
        raiseHooks.withTimeout(6), climb.withTimeout(6).andThen(lowerClimbPistons.withTimeout(3)), CLIMBER.hasClimbedBooleanSupplier
    );

    private final ConditionalCommand pistonUpOrDown = new ConditionalCommand(
        lowerClimbPistons.withTimeout(2), raiseClimbPistons.withTimeout(2), CLIMBER.pistonUpSupplier
    );

    // SHOOTER COMMANDS
    
    private final StartEndCommand shootAtSpeed = new StartEndCommand(
        
    // TODO: change set speed parameter to variable if vision processing works.
        
        //Runnable on initialise
        () -> SHOOTER.setSpeed(1),
        //Runnable on end
        () -> SHOOTER.stopShooter(),
        SHOOTER
    );

    // INTAKE COMMANDS


    private final InstantCommand intakeOn = new InstantCommand(
        () -> INTAKE.wheelSpeed(WHEEL_INTAKE_SPEED),
        INTAKE
    );

    private final InstantCommand intakeOff = new InstantCommand(
        () -> INTAKE.wheelSpeed(0),
        INTAKE
    );

    private final StartEndCommand pistonDeploy = new StartEndCommand(
        () -> INTAKE.deployPiston(), 
        () -> INTAKE.pistonOff(),
        INTAKE
    );

    private final StartEndCommand pistonRetract = new StartEndCommand(
        () -> INTAKE.retractPiston(),
        () -> INTAKE.pistonOff(),
        INTAKE
    );


    // SECOND LEVEL INTAKE COMMANDS

    private final ConditionalCommand finalDeployPiston = new ConditionalCommand(
        intakeOn, pistonDeploy.andThen(intakeOn), INTAKE.isDeployedSupplier
    );


  
    // MAKE A NEW JOYSTICK

    public final Joystick driverController = new Joystick(DRIVER_CONTROLLER), opController = new Joystick(OPERATOR_CONTROLLER);
  
    // CONFIG BUTTON BINDINGS (See constants.java to change specific ports etc.)

    // CLIMB BUTTONS

    private final JoystickButton pistonUpOrDownButton = new JoystickButton(opController, RAISE_OR_LOWER_BOTH_PISTONS),
                                 climbButton = new JoystickButton(opController, CLIMB_OR_LOWER);
                                 
    // SHOOT BUTTON (TOGGLEABLE)

    private final JoystickButton shootButton = new JoystickButton(opController, SHOOT_BUTTON);

    // PISTON-Y INTAKE BUTTONS

    private final JoystickButton pistonDeployIntakeButton = new JoystickButton(opController, DEPLOY_INTAKE),
                                 pistonRetractIntakeButton = new JoystickButton(opController, RETRACT_INTAKE);
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

        climbButton.whenPressed(climbOrLower);
        pistonUpOrDownButton.whenPressed(pistonUpOrDown);

        // SHOOT BUTTONS
        shootButton.toggleWhenPressed(shootAtSpeed);

        // PISTON-Y INTAKE BUTTONS
        pistonDeployIntakeButton.whileHeld(finalDeployPiston);
        pistonRetractIntakeButton.whenPressed(intakeOff.andThen(pistonRetract.withTimeout(1)));

    }

    public Drivetrain getDrivetrain() {
        return this.DRIVETRAIN;
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     * @return the command to run in autonomous
     */

    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new TestAutoCommandGroup(DRIVETRAIN);

    }

}
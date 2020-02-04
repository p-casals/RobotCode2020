/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.*;

import frc.robot.subsystems.*;

import static frc.robot.Constants.*;

/**
 * For robot commands because RobotContainer is frighteningly messy
 */
public class RobotCommands{

    // CREATE SUBSYSTEMS
    public final Climber CLIMBER = new Climber();
    public final Shooter SHOOTER = new Shooter();
    public final  Intake INTAKE = new Intake();
    public final ControlPanel CONTROL_PANEL = new ControlPanel();
    public final Drivetrain DRIVETRAIN = new Drivetrain();
    public final Storage STORAGE = new Storage();
    public final ColorSensor COLOR_SENSOR = new ColorSensor();


    // == COMMANDS == //

    // BASE COMMANDS
 
    // SHOOTER COMMANDS
    // TODO: change set speed parameter to variable if vision processing works.
     public final StartEndCommand shootAtSpeed = new StartEndCommand(
        () -> SHOOTER.setSpeed(1), 
        () -> SHOOTER.setSpeed(0), 
        SHOOTER
    );

    // INTAKE COMMANDS
    public final StartEndCommand intakeOn = new StartEndCommand(
        () -> INTAKE.wheelSpeed(WHEEL_INTAKE_SPEED), 
        () -> INTAKE.wheelSpeed(0),
        INTAKE
    );
    public final InstantCommand intakeOff = new InstantCommand(
        () -> INTAKE.wheelSpeed(0), 
        INTAKE
    );
    public final StartEndCommand pistonDeploy = new StartEndCommand(
        () -> INTAKE.deploy(), () -> INTAKE.stopPistons(), 
        INTAKE
    );
    public final StartEndCommand pistonRetract = new StartEndCommand(
        () -> INTAKE.retract(), () -> INTAKE.stopPistons(), 
        INTAKE
    );

    // CONTROL PANEL COMMANDS
    public final StartEndCommand controlLift = new StartEndCommand(
        () -> CONTROL_PANEL.lifterOnUp(),
        () -> CONTROL_PANEL.lifterOff(),
        CONTROL_PANEL
    );
    public final StartEndCommand controlDrop = new StartEndCommand(
        () -> CONTROL_PANEL.lifterOnDown(),
        () -> CONTROL_PANEL.lifterOff(),
        CONTROL_PANEL
    );
    public final StartEndCommand controlSpin = new StartEndCommand(
        () -> CONTROL_PANEL.spinnerOn(),
        () -> CONTROL_PANEL.spinnerOff(),
        CONTROL_PANEL
    );

    public final InstantCommand controlStop = new InstantCommand(
        () -> CONTROL_PANEL.spinnerOff(),
        CONTROL_PANEL
    );

    // CLIMBER COMMANDS
    public final StartEndCommand raiseLifter = new StartEndCommand(
        () -> CLIMBER.setLifterSpeed(0.75),
        () -> CLIMBER.setLifterSpeed(0),
        CLIMBER
    );
    
    public final StartEndCommand lowerLifter = new StartEndCommand(
        () -> CLIMBER.setLifterSpeed(-0.75),
        () -> CLIMBER.setLifterSpeed(0),
        CLIMBER
    );

    public final StartEndCommand gearClimb = new StartEndCommand(
        () -> CLIMBER.setGearSpeed(0.75),
        () -> CLIMBER.setGearSpeed(0),
        CLIMBER
    );
    
    
    // STORAGE COMMANDS
    public final StartEndCommand storageGate = new StartEndCommand(
        () -> STORAGE.gateSpeed(),
        () -> STORAGE.gateZero(),
        STORAGE
    );

    // SECOND LEVEL COMMANDS

    // SECOND LEVEL INTAKE COMMANDS
    public final ConditionalCommand finalDeployPiston = new ConditionalCommand(
        intakeOn, pistonDeploy.andThen(intakeOn), INTAKE.isDeployedSupplier
    );
    public final SequentialCommandGroup finalRetractIntake = new SequentialCommandGroup(
        intakeOff,
        pistonRetract.withTimeout(1)
        //TODO - timeout times again
    );
    public final ConditionalCommand deployOrRetractIntake = new ConditionalCommand(
        finalRetractIntake, finalDeployPiston, INTAKE.isDeployedSupplier
    );

    public final ConditionalCommand onOrOffIntake = new ConditionalCommand(
        intakeOff, intakeOn, INTAKE.isOnSupplier
    );

    // SECOND LEVEL CONTROL COMMANDS
    public final ConditionalCommand liftControlMaybe = new ConditionalCommand(
        controlLift.withTimeout(3), controlDrop.withTimeout(3).andThen(), 
        CONTROL_PANEL.controlUpSupplier
    );

    // COLOR SENSOR (CONTROL PANEL) SECOND LEVEL COMMANDS
    public final ConditionalCommand controlSpinIfNoMatch = new ConditionalCommand(
        controlStop,
        controlSpin,
        COLOR_SENSOR.colorMatchSupplier
    );

    public final ConditionalCommand spinSetTimes = new ConditionalCommand(
        controlSpin,
        controlStop,
        COLOR_SENSOR.keepSpinningSupplier
    );

    
    
    // MAKE A DRIVETRAIN

    public Drivetrain getDrivetrain() {
        return this.DRIVETRAIN;
    }



}

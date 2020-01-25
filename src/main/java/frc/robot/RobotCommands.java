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
    private Climber CLIMBER;
    private Shooter SHOOTER;
    private Intake INTAKE;
    private ControlPanel CONTROL;

    public RobotCommands(Climber climber, Shooter shooter, Intake intake, ControlPanel control){
        CLIMBER = climber;
        SHOOTER = shooter;
        INTAKE = intake;
        CONTROL = control;
    }



    // == COMMANDS == //



    // BASE CLIMBER COMMANDS
    public final StartEndCommand climbCommand = new StartEndCommand(
        () -> CLIMBER.reversePrimary(), 
        () -> CLIMBER.stopPrimary(), 
        CLIMBER
    );
    public final StartEndCommand raisePrimaryCommand = new StartEndCommand(
        () -> CLIMBER.raisePrimary(), 
        () -> CLIMBER.stopPrimary(), 
        CLIMBER
    );
    public final StartEndCommand raiseSecondaryCommand = new StartEndCommand(
        () -> CLIMBER.raiseSecondary(), 
        () -> CLIMBER.stopSecondary(), 
        CLIMBER
    );
    public final StartEndCommand reverseSecondaryCommand = new StartEndCommand(
        () -> CLIMBER.reverseSecondary(), 
        () -> CLIMBER.stopSecondary(), 
        CLIMBER
    );


 
    // SHOOTER COMMANDS
    // TODO: change set speed parameter to variable if vision processing works.
     public final StartEndCommand shootAtSpeed = new StartEndCommand(
        () -> SHOOTER.setSpeed(1), 
        () -> SHOOTER.setSpeed(0), 
        SHOOTER
    );


    // INTAKE COMMANDS
    public final InstantCommand intakeOn = new InstantCommand(
        () -> INTAKE.wheelSpeed(WHEEL_INTAKE_SPEED), 
        INTAKE
    );
    public final InstantCommand intakeOff = new InstantCommand(
        () -> INTAKE.wheelSpeed(0), 
        INTAKE
    );
    public final StartEndCommand pistonDeploy = new StartEndCommand(
        () -> INTAKE.deploy(), () -> INTAKE.stop(), 
        INTAKE
    );
    public final StartEndCommand pistonRetract = new StartEndCommand(
        () -> INTAKE.retract(), () -> INTAKE.stop(), 
        INTAKE
    );


    // SECOND LEVEL INTAKE COMMANDS
    public final ConditionalCommand finalDeployPiston = new ConditionalCommand(
        intakeOn, pistonDeploy.andThen(intakeOn), INTAKE.isDeployedSupplier
    );

    public final SequentialCommandGroup finalRetractIntake = new SequentialCommandGroup(
        intakeOff,
        pistonRetract.withTimeout(1)
        //TODO - timeout times again
    );


    // SECOND LEVEL CLIMBER COMMANDS

    // TODO - check timeout times - I kind of made them up
    public final ConditionalCommand climbOrLower = new ConditionalCommand(
        raisePrimaryCommand.withTimeout(6), climbCommand.withTimeout(6).andThen(reverseSecondaryCommand.withTimeout(3)), 
        CLIMBER.hasClimbedBooleanSupplier
    );
    public final ConditionalCommand pistonUpOrDown = new ConditionalCommand(
        reverseSecondaryCommand.withTimeout(2), 
        raiseSecondaryCommand.withTimeout(2), 
        CLIMBER.pistonUpSupplier
    );

    // CONTROL PANEL COMMANDS

    public final StartEndCommand controlLift = new StartEndCommand(
        () -> CONTROL.lifterOnUp(),
        () -> CONTROL.lifterOff(),
        CONTROL
    );

    public final StartEndCommand controlDrop = new StartEndCommand(
        () -> CONTROL.lifterOnDown(),
        () -> CONTROL.lifterOff(),
        CONTROL
    );

    public final StartEndCommand controlSpin = new StartEndCommand(
        () -> CONTROL.spinnerOn(),
        () -> CONTROL.spinnerOff(),
        CONTROL
    );
        
}
        



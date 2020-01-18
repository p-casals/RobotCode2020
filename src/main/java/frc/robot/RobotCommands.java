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
public class RobotCommands extends CommandBase{

    // CREATE SUBSYSTEMS
    private final Climber CLIMBER = new Climber();
    private final Shooter SHOOTER = new Shooter();
    private final Intake INTAKE = new Intake();



    // == COMMANDS == //



    // BASE CLIMBER COMMANDS
    private final StartEndCommand climbCommand = new StartEndCommand(
        () -> CLIMBER.reversePrimary(), 
        () -> CLIMBER.stopPrimary(), 
        CLIMBER
    );
    private final StartEndCommand raisePrimaryCommand = new StartEndCommand(
        () -> CLIMBER.raisePrimary(), 
        () -> CLIMBER.stopPrimary(), 
        CLIMBER
    );
    private final StartEndCommand raiseSecondaryCommand = new StartEndCommand(
        () -> CLIMBER.raiseSecondary(), 
        () -> CLIMBER.stopSecondary(), 
        CLIMBER
    );
    private final StartEndCommand reverseSecondaryCommand = new StartEndCommand(
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
    private final InstantCommand intakeOn = new InstantCommand(
        () -> INTAKE.wheelSpeed(WHEEL_INTAKE_SPEED), 
        INTAKE
    );
    private final InstantCommand intakeOff = new InstantCommand(
        () -> INTAKE.wheelSpeed(0), 
        INTAKE
    );
    private final StartEndCommand pistonDeploy = new StartEndCommand(
        () -> INTAKE.deploy(), () -> INTAKE.stop(), 
        INTAKE
    );
    private final StartEndCommand pistonRetract = new StartEndCommand(
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
        


}

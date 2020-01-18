
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


    // CREATE SUBSYSTEMS
    private final Drivetrain DRIVETRAIN = new Drivetrain();
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


    // SECOND LEVEL CLIMBER COMMANDS
    // TODO - check timeout times - I kind of made them up
    private final ConditionalCommand climbOrLower = new ConditionalCommand(
        raisePrimaryCommand.withTimeout(6), climbCommand.withTimeout(6).andThen(reverseSecondaryCommand.withTimeout(3)), 
        CLIMBER.hasClimbedBooleanSupplier
    );
    private final ConditionalCommand pistonUpOrDown = new ConditionalCommand(
        reverseSecondaryCommand.withTimeout(2), 
        raiseSecondaryCommand.withTimeout(2), 
        CLIMBER.pistonUpSupplier
    );
        


    // SHOOTER COMMANDS
    // TODO: change set speed parameter to variable if vision processing works.
     private final StartEndCommand shootAtSpeed = new StartEndCommand(
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
    private final ConditionalCommand finalDeployPiston = new ConditionalCommand(
        intakeOn, pistonDeploy.andThen(intakeOn), INTAKE.isDeployedSupplier
    );





    // == JOYSTICK & BUTTON BINDINGS == //




  
    // NEW JOYSTICK
    public final Joystick driverController = new Joystick(DRIVER_CONTROLLER), opController = new Joystick(OPERATOR_CONTROLLER);
  
    // CONFIG BUTTON BINDINGS (See constants.java to change specific ports etc.)

                            // CLIMB BUTTONS
    private final JoystickButton pistonUpOrDownButton = new JoystickButton(opController, RAISE_OR_LOWER_CLIMB_PISTONS),
                                 climbButton = new JoystickButton(opController, CLIMB_OR_LOWER),
                            // SHOOT BUTTON (TOGGLEABLE)
                                 flywheelToggleButton = new JoystickButton(opController, SHOOTER_WHEEL_TOGGLE),
                            // PISTON-Y INTAKE BUTTONS
                                 deployIntakeButton = new JoystickButton(opController, DEPLOY_INTAKE),
                                 retractIntakeButton = new JoystickButton(opController, RETRACT_INTAKE);
   
   
   
   
   

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */

    public RobotContainer() {
        configureButtonActions();
    }




    /**
     * Config button actions: it changes what does each button do. Don't touch this to change bindings.
     */


    private void configureButtonActions() {
        
        // CLIMB BUTTONS
        climbButton.whenPressed(climbOrLower);
        pistonUpOrDownButton.whenPressed(pistonUpOrDown);

        // SHOOT BUTTONS
        flywheelToggleButton.toggleWhenPressed(shootAtSpeed);

        // PISTON-Y INTAKE BUTTONS
        deployIntakeButton.whileHeld(finalDeployPiston);
        retractIntakeButton.whenPressed(intakeOff.andThen(pistonRetract.withTimeout(1)));
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
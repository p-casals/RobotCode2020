
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

    // IMPORTING STUFF AND STUFF
    RobotCommands Command = new RobotCommands();
    private final Drivetrain DRIVETRAIN = new Drivetrain();


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
        climbButton.whenPressed(Command.climbOrLower);
        pistonUpOrDownButton.whenPressed(Command.pistonUpOrDown);

        // SHOOT BUTTONS
        flywheelToggleButton.toggleWhenPressed(Command.shootAtSpeed);

        // PISTON-Y INTAKE BUTTONS
        deployIntakeButton.whileHeld(Command.finalDeployPiston);
        retractIntakeButton.whenPressed(Command.finalRetractIntake);
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
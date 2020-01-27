
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.auto.ControlLiftCommand;
import frc.robot.commands.auto.routines.TestAutoCommandGroup;
import frc.robot.subsystems.*;

public class RobotContainer {

    // IMPORTING STUFF AND STUFF

    private final RobotCommands robotCommands = new RobotCommands();

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
            retractIntakeButton = new JoystickButton(opController, RETRACT_INTAKE),
    // CONTROL PANEL BUTTONS
    controlSpinButton = new JoystickButton(opController, SPIN_MOTOR),
            controlLiftButton = new JoystickButton(opController, LIFT_MOTOR);

    // ROBOT CONTAINER
    public RobotContainer() {
        configureButtonActions();
    }


    // CONFIG BUTTON ACTIONS
    private void configureButtonActions() {

        // CLIMB BUTTONS
        climbButton.whenPressed(robotCommands.climbOrLower);
        pistonUpOrDownButton.whenPressed(robotCommands.pistonUpOrDown);

        // SHOOT BUTTONS
        flywheelToggleButton.toggleWhenPressed(robotCommands.shootAtSpeed);

        // PISTON-Y INTAKE BUTTONS
        deployIntakeButton.whileHeld(robotCommands.finalDeployPiston);
        retractIntakeButton.whenPressed(robotCommands.finalRetractIntake);

        // CONTROL PANEL BUTTONS
        controlSpinButton.whenHeld(robotCommands.controlSpin);
        controlLiftButton.whenPressed(new ControlLiftCommand(robotCommands.CONTROL_PANEL).withTimeout(3));
    }


    public Drivetrain getDrivetrain() {
        return robotCommands.DRIVETRAIN;
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new TestAutoCommandGroup(getDrivetrain());

    }

}
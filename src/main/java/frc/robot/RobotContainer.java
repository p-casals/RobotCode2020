
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
import frc.robot.commands.auto.routines.TestAutoCommandGroup;
import frc.robot.subsystems.*;

public class RobotContainer {

    // IMPORTING STUFF AND STUFF
    private final Drivetrain DRIVETRAIN = new Drivetrain();
    private final Climber CLIMBER = new Climber();
    private final Shooter SHOOTER = new Shooter();
    private final Intake INTAKE = new Intake();
    private final ControlPanel CONTROLPANEL = new ControlPanel();

    private final RobotCommands Command = new RobotCommands(CLIMBER, SHOOTER, INTAKE, CONTROLPANEL);


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
        climbButton.whenPressed(Command.climbOrLower);
        pistonUpOrDownButton.whenPressed(Command.pistonUpOrDown);

        // SHOOT BUTTONS
        flywheelToggleButton.toggleWhenPressed(Command.shootAtSpeed);

        // PISTON-Y INTAKE BUTTONS
        deployIntakeButton.whileHeld(Command.finalDeployPiston);
        retractIntakeButton.whenPressed(Command.finalRetractIntake);

        // CONTROL PANEL BUTTONS
        controlSpinButton.whenHeld(Command.controlSpin);
        int x = 0;
        controlLiftButton.cancelWhenPressed(Command.controlDrop);
        controlLiftButton.whenPressed(Command.controlLift.withTimeout(3));
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
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto.routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotCommands;
import frc.robot.commands.auto.MoveCommand;
import frc.robot.commands.auto.TurnCommand;
import frc.robot.subsystems.Drivetrain;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:

// Auto routine #1
public class Auto extends SequentialCommandGroup {

    /**
     * Creates a new Auto.
     */
    public Auto(RobotCommands COMMANDS, Drivetrain DRIVETRAIN) {

        //shoot
        //turn -37.69 degrees
        //move forwards 109.460974781 in
        // turn 37.69 degrees
        // move forwards 108 in

        super(
                COMMANDS.shootAtSpeed,
                new TurnCommand(DRIVETRAIN, -37.69, 0.5),
                new MoveCommand(DRIVETRAIN, 109.461, 0.5),
                new TurnCommand(DRIVETRAIN, 37.69, 0.5),
                new MoveCommand(DRIVETRAIN, 108, 0.5)
        );

    }
}

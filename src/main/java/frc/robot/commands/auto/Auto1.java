/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.RunShooterCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.PatrickSensor;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Auto1 extends SequentialCommandGroup {
  /**
   * Creates a new Auto2.
   */
  public Auto1(Drivetrain train, PatrickSensor paddy, ShooterSubsystem shooter) {
    super(
      //Align and drive to target. Shoot
      //new Align(train, paddy), 
      //new RunShooterCommand(shooter, 1, 5)
      //TODO- shooter commands (feed, shoot, etc)

      //Turn and move to bottom wall
      new MoveCommand(train, -17, 0.5), 
      new TurnCommand(train, 90, 0.5), 
      new MoveCommand(train, 136, 0.5), 

      //Move to position
      new MoveCommand(train, -27.75, 0.5), 
      new TurnCommand(train, 90, 0.5), 

      //move all the way to the third ball and collect them allnew CollectionAuto1(train), 
      new CollectionAuto1(train), 
      new CollectionOff(), 

      //Turn and move to target
      new TurnCommand(train, 180, 0.5), 
      //new Align(train, paddy), 

      //Shoot
      //new RunShooterCommand(shooter, 1));
  }
}

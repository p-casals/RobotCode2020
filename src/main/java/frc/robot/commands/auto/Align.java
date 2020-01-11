/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.PatrickSensor;

public class Align extends CommandBase {
  
  private final Drivetrain drive;
  private final PatrickSensor detector;
  private double distance;
  private double angle;

  public Align(Drivetrain train, PatrickSensor m) {
    this.drive = train;
    this.detector = m;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    distance = detector.getDistance();
    angle = detector.getAngle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (angle>=0){
        angle = 90 - angle;
    }
    else if (angle<0){
        angle += 90;
        angle *= -1;
    }
    new TurnCommand(drive, angle, 0.5);
    new MoveCommand(drive, distance, 0.5);
    new MoveCommand(drive, 18, 0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

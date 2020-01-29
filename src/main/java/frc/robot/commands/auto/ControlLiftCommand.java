/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;
import static frc.robot.Constants.*;

public class ControlLiftCommand extends CommandBase {
  /**
   * Creates a new CotrolLiftCommand.
   */
  private ControlPanel control;
  public ControlLiftCommand(ControlPanel cp) {
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(cp);
    control = cp;
  }

  // TODO- please make this a proper toggle- this will not work properly if being used twice

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(LIFTER_VAR == 0){
      control.lifterOnUp();
      LIFTER_VAR = 1;
    }
    else{
      control.lifterOnDown();
      LIFTER_VAR = 0;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    control.lifterOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class Climber extends SubsystemBase {
  /**
   * Creates a new climber.
   */
   
   private DoubleSolenoid pistonLiftPiston, robotLiftPiston;
   
  public Climber() {
    pistonLiftPiston = new DoubleSolenoid(CLIMBER_PISTON_LIFT_PISTON_1, CLIMBER_PISTON_LIFT_PISTON_2);
    robotLiftPiston = new DoubleSolenoid(CLIMBER_PISTON_LIFT_PISTON_1, CLIMBER_PISTON_LIFT_PISTON_2);
  }

  public void raiseClimber() {
    //extends the secondary piston
    pistonLiftPiston.set(DoubleSolenoid.Value.kForward);
  }

  public void stopRaisePiston() {
    //stops the secondary piston
    pistonLiftPiston.set(DoubleSolenoid.Value.kOff);
  }

  public void reverseRaisePiston() {
    //retracts the secondary piston
    pistonLiftPiston.set(DoubleSolenoid.Value.kReverse);
  }
  
  public void raiseHooks() {
    //extends the primary piston
    robotLiftPiston.set(DoubleSolenoid.Value.kForward);
  }

  public void stopHookPiston() {
    //stops the primary piston
    robotLiftPiston.set(DoubleSolenoid.Value.kOff);
  }

  public void reverseHookPiston() {
    //retracts the primary piston
    robotLiftPiston.set(DoubleSolenoid.Value.kReverse);
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

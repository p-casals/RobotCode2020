/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {
 
  private DoubleSolenoid pistonExtendContract;
  public Pneumatics() {
    pistonExtendContract = new DoubleSolenoid(0, 1);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void extendPiston(){
    pistonExtendContract.set(DoubleSolenoid.Value.kForward);
  }
  public void contractPiston(){
    pistonExtendContract.set(DoubleSolenoid.Value.kReverse);
  }
  public void offPiston(){ 
    pistonExtendContract.set(DoubleSolenoid.Value.kOff);
  }
}

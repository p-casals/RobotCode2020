/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterMotors extends SubsystemBase {
  
  private VictorSP leftMotor, rightMotor;
  public ShooterMotors(){
    leftMotor = new VictorSP(0);
    rightMotor = new VictorSP(1);
  }

  /**
   * Creates a new Motors.
   */
  

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  

  public void motors(double motorSpeed){
    leftMotor.set(motorSpeed);
    rightMotor.set(motorSpeed/-1);
  }
}

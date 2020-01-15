/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  private VictorSP shooterMotorOne, shooterMotorTwo, shooterMotorThree;
  
  /**
   * Creates a new ShooterSubsytem.
   */
  public ShooterSubsystem() {
    shooterMotorOne = new VictorSP(7);
    shooterMotorTwo = new VictorSP(7);
    shooterMotorThree = new VictorSP(7);

  }
  public void setSpeed(double shooterSpeed) {
    shooterMotorOne.set(shooterSpeed);
    shooterMotorTwo.set(shooterSpeed);
    shooterMotorThree.set(shooterSpeed);

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

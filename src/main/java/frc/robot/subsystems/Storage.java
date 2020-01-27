/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.VictorSP;
import static frc.robot.Constants.*;

public class Storage extends SubsystemBase {
  
  private VictorSP motor;
  
  /**
   * Creates a new Storage.
   */
  public Storage() {
    motor = new VictorSP(STORAGE_GATE_MOTOR);
  }

  // TODO: does motorspeed need to be a constant?

  public void setGateSpeed(double motorSpeed) {
    motor.set(motorSpeed);
  }

  public void gateSpeed() {
    motor.set(GATE_SPEED);
  }

  public void gateZero() {
    motor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

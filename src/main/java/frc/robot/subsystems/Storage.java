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

// Ball storage: this class controls the gate opening onto the shooter. 
// It could also be an extra motor: that much is unclear. 

  // TODO: make gate button part of shooter?

public class Storage extends SubsystemBase {
  
  private VictorSP motor;
  
  /**
   * Creates a new Storage.
   */
  public Storage() {
    motor = new VictorSP(STORAGE_GATE_MOTOR);
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

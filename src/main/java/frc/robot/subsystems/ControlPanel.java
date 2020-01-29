/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

import java.util.function.BooleanSupplier;

public class ControlPanel extends SubsystemBase {
  /**
   * Creates a new ControlPanel.
   */

   /** TODO: change liftmotor and accompanying stuff to a piston. 
    When the piston is fully extended, the control panel mechanism is deployed.
    When the piston is fully retracted, the control panel mechanism is un-deployed.
   */
  

  private final DoubleSolenoid liftPiston;
  private VictorSP spinMotor;
  private boolean isUp = false;

  public ControlPanel() {
    liftPiston = new DoubleSolenoid(LIFT_PISTON_1, LIFT_PISTON_2);
    spinMotor = new VictorSP(SPIN_MOTOR);
  }

  public void lifterOnUp() {
    liftPiston.set(DoubleSolenoid.Value.kForward);
    isUp = true;
  }

  public void spinnerOn() {
    spinMotor.set(0.5);
  }

  public void lifterOff() {
    liftPiston.set(DoubleSolenoid.Value.kOff);
  }

  public void spinnerOff() {
    spinMotor.set(0);
  }

  public void lifterOnDown() {
    liftPiston.set(DoubleSolenoid.Value.kReverse);
    isUp = false;
  }
  public BooleanSupplier controlUpSupplier = () -> isUp;


  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

}

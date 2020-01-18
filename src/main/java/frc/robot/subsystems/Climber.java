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

import java.util.function.BooleanSupplier;

public class Climber extends SubsystemBase {
  /**
   * Creates a new climber.
   */
   
   private DoubleSolenoid secondaryPiston, primaryPiston;
  private boolean hasClimbed = false;
  private boolean pistonUp = false;
   
  public Climber() {
    secondaryPiston = new DoubleSolenoid(SECONDARY_PISTON_1, SECONDARY_PISTON_2);
    primaryPiston = new DoubleSolenoid(PRIMARY_PISTON_1, PRIMARY_PISTON_2);
  }

  // SECONDARY PISTON BASE COMMANDS

  public void raisePiston() {
    //extends the secondary piston
    secondaryPiston.set(DoubleSolenoid.Value.kForward);
    pistonUp = true;
  }

  public void stopPiston() {
    //stops the secondary piston
    secondaryPiston.set(DoubleSolenoid.Value.kOff);
  }

  public void reversePiston() {
    //retracts the secondary piston
    secondaryPiston.set(DoubleSolenoid.Value.kReverse);
    pistonUp = false;
  }
  
  // PRIMARY PISTON BASE COMMANDS

  public void raiseHooks() {
    //extends the primary piston
    primaryPiston.set(DoubleSolenoid.Value.kForward);
    hasClimbed = true;
  }

  public void stopHooks() {
    //stops the primary piston
    primaryPiston.set(DoubleSolenoid.Value.kOff);
  }

  public void reverseHooks() {
    //retracts the primary piston
    primaryPiston.set(DoubleSolenoid.Value.kReverse);
    hasClimbed = false;
  }

  // BOOLEANSUPPLIERS: For robotContainer climber secondary commands
   
  public BooleanSupplier hasClimbedBooleanSupplier = () -> hasClimbed;
  public BooleanSupplier pistonUpSupplier = () -> pistonUp;

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

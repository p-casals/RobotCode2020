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


    // FIELDS ETC.
    private final DoubleSolenoid secondaryPiston;
  private final DoubleSolenoid primaryPiston;
    private boolean hasClimbed = false;
    private boolean pistonUp = false;


    // CONSTRUCTOR
    public Climber() {
        secondaryPiston = new DoubleSolenoid(SECONDARY_PISTON_1, SECONDARY_PISTON_2);
        primaryPiston = new DoubleSolenoid(PRIMARY_PISTON_1, PRIMARY_PISTON_2);
    }


    // SECONDARY PISTON BASE COMMANDS
    public void raiseSecondary() {
        secondaryPiston.set(DoubleSolenoid.Value.kForward);
        pistonUp = true;
    }

    public void stopSecondary() {
        secondaryPiston.set(DoubleSolenoid.Value.kOff);
    }

    public void reverseSecondary() {
        secondaryPiston.set(DoubleSolenoid.Value.kReverse);
        pistonUp = false;
    }


    // PRIMARY PISTON BASE COMMANDS
    public void raisePrimary() {
        primaryPiston.set(DoubleSolenoid.Value.kForward);
        hasClimbed = true;
    }

    public void stopPrimary() {
        primaryPiston.set(DoubleSolenoid.Value.kOff);
    }

    public void reversePrimary() {
        primaryPiston.set(DoubleSolenoid.Value.kReverse);
        hasClimbed = false;
    }


    // BOOLEANSUPPLIERS: For robotContainer climber secondary commands
    public final BooleanSupplier hasClimbedBooleanSupplier = () -> hasClimbed;
    public final BooleanSupplier pistonUpSupplier = () -> pistonUp;

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}

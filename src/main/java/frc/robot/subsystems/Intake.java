/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.DoubleSolenoid;


public class Intake extends SubsystemBase {

    // Define/create a motor and a doublesolenoid.
    // The wheel motor is to run the intake - that is, actually take in the balls
    // The piston is to extend and retract the intake.

    private final VictorSP wheelMotor;
    private final DoubleSolenoid intakePiston;

    private boolean isDeployed = false;
    private boolean isOn = false;

    public final BooleanSupplier isDeployedSupplier = () -> isDeployed;
    public final BooleanSupplier isOnSupplier = () -> isOn;


    //CONSTRUCTOR: defining and creating the intake
    public Intake() {
        wheelMotor = new VictorSP(WHEEL_INTAKE_MOTOR);
        intakePiston = new DoubleSolenoid(INTAKE_PISTON_1, INTAKE_PISTON_2);
    }

    // MOTORS:
    /**
     * Set the spinner to a certain speed
     * 
     * @param speed Desired speed of the spinner
     */ 
    public void wheelSpeed(double speed) {
        wheelMotor.set(speed);
        if(Math.abs(speed) >=.001) {
            isOn = true;
        } else{
            isOn = false;
        }
    }

    // PISTONS:
    /**
     * Deploy the intake: extend the pistons
     */
    public void deploy() {
        intakePiston.set(DoubleSolenoid.Value.kForward);
        isDeployed = true;

    }

    /**
     * Retract the intake: retract the pistons
     */
    public void retract() {
        intakePiston.set(DoubleSolenoid.Value.kReverse);
        isDeployed = false;
    }

    /** 
    * Stop the pistons from either extending further
    * or from retracting further:
    * use once extended and retracted to heart's content
    */
    public void stopPistons() {
        intakePiston.set(DoubleSolenoid.Value.kOff);
    }

    public void updateBooleans() {
        BooleanSupplier isDeployedSupplier = () -> isDeployed;
    }



    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // BOOLEANSUPPLIER: For RobotContainer's second-level intake commands (for toggleability function)
        // Again, a boolsup is basically a boolean and this line of code converts it.
                

    }
}

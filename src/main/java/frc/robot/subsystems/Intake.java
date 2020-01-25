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

    private final VictorSP wheelMotor;
    private final DoubleSolenoid intakePiston;

    private boolean isDeployed = false;

    //CONSTRUCTOR
    public Intake() {
        wheelMotor = new VictorSP(WHEEL_INTAKE_MOTOR);
        intakePiston = new DoubleSolenoid(INTAKE_PISTON_1, INTAKE_PISTON_2);
    }

    // MOTORS
    public void wheelSpeed(double speed) {
        wheelMotor.set(speed);
    }

    public void deploy() {
        intakePiston.set(DoubleSolenoid.Value.kForward);
        isDeployed = true;
    }

    // PISTONS
    public void retract() {
        intakePiston.set(DoubleSolenoid.Value.kReverse);
        isDeployed = false;
    }

    public void stop() {
        intakePiston.set(DoubleSolenoid.Value.kOff);
    }

    // BOOLEANSUPPLIER: For RobotContainer's second-level intake commands (for toggleability function)
    public final BooleanSupplier isDeployedSupplier = () -> isDeployed;


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

// Creates a new shooter
public class Shooter extends SubsystemBase {

    // FIELDS
    // Create three motors: they should work in tandem to rotate
    // the flywheel at the same time: gives it extra power
    private final VictorSP motor1;
    private final VictorSP motor2;
    private final VictorSP motor3;

    public Shooter() {

        // refer to constants.java for motor ports
        motor1 = new VictorSP(SHOOTER_MOTOR_1);
        motor2 = new VictorSP(SHOOTER_MOTOR_2);
        motor3 = new VictorSP(SHOOTER_MOTOR_3);
    }

    // TODO: shooterSpeed can become an outside variable when vision tracking works

    /**
     * Sets the speed of the three shooter motors to the desired speed
     * 
     * @param shooterSpeed Desired speed of the three shooter motors
     */
    public void setSpeed(double shooterSpeed) {
        motor1.set(shooterSpeed);
        motor2.set(shooterSpeed);
        motor3.set(shooterSpeed);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
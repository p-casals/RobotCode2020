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

public class ControlPanel extends SubsystemBase {
    /**
     * Creates a new ControlPanel.
     */
    private final VictorSP liftMotor;
    private final VictorSP spinMotor;

    public ControlPanel() {
        liftMotor = new VictorSP(LIFT_MOTOR);
        spinMotor = new VictorSP(SPIN_MOTOR);
    }

    public void lifterOnUp() {
        liftMotor.set(0.5);
    }

    public void spinnerOn() {
        spinMotor.set(0.5);
    }

    public void lifterOff() {
        liftMotor.set(0);
    }

    public void spinnerOff() {
        liftMotor.set(0);
    }

    public void lifterOnDown() {
        liftMotor.set(-0.5);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}

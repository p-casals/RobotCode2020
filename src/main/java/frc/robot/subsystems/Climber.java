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

public class Climber extends SubsystemBase {


    // Initialise fields
    private VictorSP lifterMotor;
    private VictorSP gearBoxMotor1, gearBoxMotor2;


    public Climber() {
        lifterMotor = new VictorSP(LIFTER_MOTOR);
        gearBoxMotor1 = new VictorSP(GEAR_MOTOR1);
        gearBoxMotor2 = new VictorSP(GEAR_MOTOR2);
    }

    public void setLifterSpeed(double speed){ lifterMotor.set(speed); }
    public void setGearSpeed(double speed){ gearBoxMotor1.set(speed); gearBoxMotor2.set(speed); }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        
    
    }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//hi
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;


public class Drivetrain extends SubsystemBase {
  
  private WPI_TalonFX frontLeft, frontRight, backLeft, backRight;

  public Drivetrain() {
    frontLeft = new WPI_TalonFX(RobotContainer.FRONT_LEFT_DRIVE_MOTOR);
    backLeft = new WPI_TalonFX(RobotContainer.BACK_LEFT_DRIVE_MOTOR);
    frontRight = new WPI_TalonFX(RobotContainer.FRONT_RIGHT_DRIVE_MOTOR);
    backRight = new WPI_TalonFX(RobotContainer.BACK_RIGHT_DRIVE_MOTOR);
  }

  public void tankDrive(double leftSpeed, double rightSpeed){
    setLeftSpeed(-leftSpeed);
    setRightSpeed(rightSpeed);
  }
  public void arcadeDrive(double x, double z){
    x *= Math.abs(x);
    z *= Math.abs(z);

    tankDrive(x-z, x+z);
  }

  private void setLeftSpeed(double speed) {
    frontLeft.set(speed);
    backLeft.set(speed);
  }

  private void setRightSpeed(double speed) {
    frontRight.set(speed);
    backRight.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
}
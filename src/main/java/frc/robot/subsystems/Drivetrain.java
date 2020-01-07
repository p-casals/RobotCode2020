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


public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  private WPI_TalonFX frontLeft;
  private WPI_TalonFX frontRight;
  private WPI_TalonFX backLeft;
  private WPI_TalonFX backRight;



  public Drivetrain() {
    
    //GUYS NEED TO EDIT THIS AND ROBOT CONTAINER FOR APPROPRIATE VALUES PLEASE
    
    /* 
    frontLeft = new WPI_TalonFX(RobotContainer.FRONT_LEFT_DRIVE_MOTOR);
    backLeft = new WPI_TalonFX(RobotContainer.BACK_LEFT_DRIVE_MOTOR);
    frontRight = new WPI_TalonFX(RobotContainer.FRONT_RIGHT_DRIVE_MOTOR);
    backRight = new WPI_TalonFX(RobotContainer.BACK_RIGHT_DRIVE_MOTOR);
    */
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
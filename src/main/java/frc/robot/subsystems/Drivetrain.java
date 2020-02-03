/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//hi
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

public class Drivetrain extends SubsystemBase {

    // Static fields
    public static final double MM_TO_IN = 0.0393701;
    public static final double WHEEL_TO_WHEEL_DIAMETER_INCHES = 320 * MM_TO_IN;
    public static final double WHEEL_DIAMETER_INCHES = 4;

    public static final double PULSES_PER_ROTATION = 256;
    
    // Fields
    private final Encoder leftEncoder;
    private final Encoder rightEncoder;

    private final SpeedControllerGroup left;
    private final SpeedControllerGroup right;

    public Drivetrain() {
        WPI_TalonFX frontLeft = new WPI_TalonFX(FRONT_LEFT_DRIVE_MOTOR);
        WPI_TalonFX backLeft = new WPI_TalonFX(BACK_LEFT_DRIVE_MOTOR);
        WPI_TalonFX frontRight = new WPI_TalonFX(FRONT_RIGHT_DRIVE_MOTOR);
        WPI_TalonFX backRight = new WPI_TalonFX(BACK_RIGHT_DRIVE_MOTOR);

        left = new SpeedControllerGroup(frontLeft, backLeft);
        right = new SpeedControllerGroup(frontRight, backRight);

        // Since encoders return pulses, set the proper distance using wheel diameter.
        leftEncoder = new Encoder(LEFT_ENCODER_PORT, LEFT_ENCODER_PORT + 1);
        leftEncoder.setDistancePerPulse((2 * Math.PI * WHEEL_DIAMETER_INCHES) / PULSES_PER_ROTATION);
        rightEncoder = new Encoder(RIGHT_ENCODER_PORT, RIGHT_ENCODER_PORT + 1);
        rightEncoder.setDistancePerPulse((2 * Math.PI * WHEEL_DIAMETER_INCHES) / PULSES_PER_ROTATION);
    }
    /**
     * Envokes methods to change speeds of left and right motors on the robot to desired amount
     * 
     * @param leftSpeed desired speed of the left side of the robot
     * @param rightSpeed desired speed of the right side of the robot
     */
    public void tankDrive(double leftSpeed, double rightSpeed) {
        setLeftSpeed(-leftSpeed);
        setRightSpeed(rightSpeed);
    }

    /**
     * Takes raw value from the joysticks and manipulates it before giving it to tankdrive
     * in order to now jolt forward/accelerate really fast and topple
     * 
     * @param x x value of the joystick [0, 1] for forward/backward
     * @param z z value of the joystick [0, 1] for left/right (turning)
     */
    public void arcadeDrive(double x, double z) {
        x *= Math.abs(x);
        z *= Math.abs(z);

        tankDrive(x - z, x + z);
    }

    /**
     * Getter method for the left encoder distance
     * 
     * @return Returns the distance that the left encoder has recorded (traveled) in units
     */
    public double getLeftDistance() {
        return leftEncoder.getDistance();
    }

    /**
     * Getter method for the right encoder distance
     * 
     * @return Returns the distance that the right encoder has recorded (traveled) in units
     */
    public double getRightDistance() {
        return rightEncoder.getDistance();
    }

    /**
     * Resets the values of both encoders (left and right)
     */
    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    /**
     * Sets the left side of the robot to the desired speed
     * 
     * @param speed Desired speed of the left side of the robot
     */
    private void setLeftSpeed(double speed) {
        left.set(speed);
    }

    /**
     * Sets the right side of the robot to the desired speed
     * 
     * @param speed Desired speed of the right side of the robot
     */
    private void setRightSpeed(double speed) {
        right.set(speed);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }
}
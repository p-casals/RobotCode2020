/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.BooleanSupplier;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import edu.wpi.first.wpilibj.DriverStation;



public class ColorSensor extends SubsystemBase{
  
  String colorToGet;
  ColorSensorV3 sensor;
  public int numberOfTimesToSpin;
  public Color detectedColor;
  boolean colorMatches;
  String colorToStopOn;
  Boolean keepSpinning;

  private final ColorMatch m_colorMatcher = new ColorMatch();
  
  private final Color kBlueTarget = ColorMatch.makeColor(0.163, 0.481, 0.355);
  private final Color kGreenTarget = ColorMatch.makeColor(0.222, 0.587, 0.190);
  private final Color kRedTarget = ColorMatch.makeColor(0.654, 0.289, 0.058);
  private final Color kYellowTarget = ColorMatch.makeColor(0.415, 0.509, 0.076);


  public BooleanSupplier colorMatchSupplier = () -> colorMatches;
  public BooleanSupplier keepSpinningSupplier = () -> keepSpinning;

  public ColorSensor() {
    sensor  = new ColorSensorV3(Port.kOnboard);

  }

  @Override
  public void periodic() {
    
    /**
     * The below code is for the color matching stage. 
     */
    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if (gameData.length() > 0) {
      switch (gameData.charAt(0)) {
        case 'B':
          colorToGet = "B";
        case 'G':
          colorToGet = "G";
        case 'R':
          colorToGet = "R";
        case 'Y':
          colorToGet = "Y";
        default:
          break;
      }
    } else {
      // No data recieved yet
    }
    
    detectedColor = sensor.getColor();

    /**
     * The method GetColor() returns a normalized color value from the sensor and can be
     * useful if outputting the color to an RGB LED or similar. To
     * read the raw color, use GetRawColor().
     */

    /**
     * Run the color match algorithm on our detected color
     */

    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) {
      colorString = "B";
    } else if (match.color == kRedTarget) {
      colorString = "R";
    } else if (match.color == kGreenTarget) {
      colorString = "G";
    } else if (match.color == kYellowTarget) {
      colorString = "Y";
    } else {
      colorString = "Unknown";
    }

    /**
     * Open Smart Dashboard or Shuffleboard to see the color detected by the 
     * sensor.
     */
    SmartDashboard.putNumber("R", detectedColor.red);
    SmartDashboard.putNumber("G", detectedColor.green);
    SmartDashboard.putNumber("B", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);

    /**
     * In addition to RGB IR values, the color sensor can also return an 
     * infrared proximity value. The chip contains an IR led which will emit
     * IR pulses and measure the intensity of the return. When an object is 
     * close the value of the proximity will be large (max 2047 with default
     * settings) and will approach zero when the object is far away.
     * 
     * Proximity can be used to roughly approximate the distance of an object
     * or provide a threshold for when an object is close enough to provide
     * accurate color values.
     */
    int proximity = sensor.getProximity();

    SmartDashboard.putNumber("Proximity", proximity);
  
    if (colorString == colorToGet) {
      colorMatches = true;
    } else {
      colorMatches = false;
    }

    colorMatchSupplier = () -> colorMatches;


    /** 
     * The below code is for spinning the control panel 4 times
     */

    // String colorToStopOn = colorString;

    // for(int i = 0; i < 4; i++) {
    //   while(colorToStopOn == colorString) {
    //     keepSpinning = true;
    //     keepSpinningSupplier = () -> keepSpinning;
    //   }
    //   while(colorToStopOn != colorString) {
    //     keepSpinning = true;
    //     keepSpinningSupplier = () -> keepSpinning;
    //   }
    //   keepSpinning = false;
    //   keepSpinningSupplier = () -> keepSpinning;
    // }
    // keepSpinningSupplier = () -> keepSpinning;
    
  }

}

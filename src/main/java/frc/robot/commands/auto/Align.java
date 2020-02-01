/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import io.github.pseudoresonance.pixy2api.links.I2CLink;

/**
 * Add your docs here.
 */
// height IS THE DISTANCE FROM THE FLOOR TO THE HIGH SHOOTING GOAL IN INCHES
  // knownD IS THE DISTANCE FROM THE SHOOTING GOAL TO WHERE WE WANT THE ROBOT TO SHOOT FROM IN INCHES
  // WE CAN ALSO HAVE height AND knownD BE PARAMETERS IN THE CONSTRUCTER IF YOU WANT!!!!!!!!!!!!!

  public class Align extends SequentialCommandGroup{
      
    public Align(Drivetrain train, I2CLink i2c, double height, double knownD) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());

    
    super(

          new TurnCommand(train, (i2c.getPixy().x)*60-30, 0.5),
          new MoveCommand(train, (height / Math.tan(i2c.getPixy().y*40-20)-knownD), 0.5)
    );
  }


}

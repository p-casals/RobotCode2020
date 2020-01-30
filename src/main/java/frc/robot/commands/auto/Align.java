/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Align extends SequentialCommandGroup {
  /**
   * Creates a new Align.
   */

  
  // == PATRICK  LOOK HERE ==
  // height IS THE DISTANCE FROM THE FLOOR TO THE HIGH SHOOTING GOAL IN INCHES
  // knownD IS THE DISTANCE FROM THE SHOOTING GOAL TO WHERE WE WANT THE ROBOT TO SHOOT FROM IN INCHES
  // WE CAN ALSO HAVE height AND knownD BE PARAMETERS IN THE CONSTRUCTER IF YOU WANT!!!!!!!!!!!!!

  public Align(Drivetrain train, M_I2C i2c, double height, double knownD) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
          new TurnCommand(train, (i2c.getPixy().x)*60-30),
          new MoveCommand(train, (height / tan(i2c.getPixy().y*40-20)-knownD))
    );
  }
}


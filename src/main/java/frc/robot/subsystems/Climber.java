/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;
import java.util.function.BooleanSupplier;

// Climber class: for endgame climbing 
public class Climber extends SubsystemBase {


    // Initialise fields

    // Secondary piston: the smaller pistons that work to raise the primary piston
    private final DoubleSolenoid secondaryPiston;
    
    // Primary piston: the larger pistons that extend and retract to make the robot climb.
    private final DoubleSolenoid primaryPiston;

    // Booleans: hasClimbed checks whether or not the robot is in the climbed position
    private boolean hasClimbed = false;

    // Booleans: pistonUp checks whether or not the primary pistons are up and therefore are in position to climb.
    private boolean pistonUp = false;



    // Constructor: initialise the climber
    public Climber() {
        secondaryPiston = new DoubleSolenoid(SECONDARY_PISTON_1, SECONDARY_PISTON_2);
        primaryPiston = new DoubleSolenoid(PRIMARY_PISTON_1, PRIMARY_PISTON_2);
    }


    // SECONDARY PISTON BASE COMMANDS

    // Raise the secondary piston
    public void raiseSecondary() {
        secondaryPiston.set(DoubleSolenoid.Value.kForward);
        pistonUp = true;
    }

    // Freeze the secondary piston
    public void stopSecondary() {
        secondaryPiston.set(DoubleSolenoid.Value.kOff);
    }

    // Reverse the secondary piston
    public void reverseSecondary() {
        secondaryPiston.set(DoubleSolenoid.Value.kReverse);
        pistonUp = false;
    }


    // PRIMARY PISTON BASE COMMANDS

    // Raise the primary piston
    public void raisePrimary() {
        primaryPiston.set(DoubleSolenoid.Value.kForward);
        hasClimbed = true;
    }

    // Freeze the primary piston
    public void stopPrimary() {
        primaryPiston.set(DoubleSolenoid.Value.kOff);
    }

    // Reverse the primary piston
    public void reversePrimary() {
        primaryPiston.set(DoubleSolenoid.Value.kReverse);
        hasClimbed = false;
    }

    // BOOLEANSUPPLIERS: This is for ConditionalCommand() and I don't understand why they don't just use a boolean
    // In any case, it's essentially the same as a boolean; these two lines are to convert each boolean to booleanSuppliers
    public final BooleanSupplier hasClimbedBooleanSupplier = () -> hasClimbed;
    public final BooleanSupplier pistonUpSupplier = () -> pistonUp;


    // BOOLEANSUPPLIERS: For robotContainer climber secondary commands

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        
    
    }
}

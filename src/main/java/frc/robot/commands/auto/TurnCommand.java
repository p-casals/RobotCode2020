package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

// Turn command for the robot drivetrain
public class TurnCommand extends CommandBase {


    private final Drivetrain drivetrain;
    private final double degrees;
    private final double speed;
    private final double distanceToTurn;

    public TurnCommand(Drivetrain drivetrain, double degrees, double speed) {
       
        // Initialise variables as per constructor
        this.drivetrain = drivetrain;
        this.degrees = degrees;
        this.speed = speed;
        
        // Calculate the distance to turn based off angle (see isFinished).
        this.distanceToTurn = Drivetrain.WHEEL_TO_WHEEL_DIAMETER_INCHES * 2 * Math.PI * (degrees / 360);

        // Requires a drivetrain to work
        addRequirements(drivetrain);
    }

    // Called when code is first run
    @Override
    public void initialize() {
        drivetrain.resetEncoders();

        // See isFinished() for explanation of motor direction.
        if (degrees > 0) { // positive left, negative right
            drivetrain.tankDrive(speed, -speed);
        } else { // negative left, positive right
            drivetrain.tankDrive(-speed, speed);
        }
    }

    // Called after initialise()
    @Override
    public void execute() {

    }


    @Override
    public boolean isFinished() {
        // In order to find the distance to move, we need the robot circumference multiplied by the fraction of the circle.
        // The fraction is degrees / 360.

        // If turning left (-degrees), left motors are -, and right motors are +.
        // If turning right (degrees), left motors are +, and right motors are -.
        boolean isFinished;

        // If distance is greater than distance to turn (w/ respect to direction), end.
        if (degrees > 0) { // positive left, negative right
            isFinished = drivetrain.getLeftDistance() > distanceToTurn && drivetrain.getRightDistance() < -distanceToTurn;
        } else { // negative left, positive right
            isFinished = drivetrain.getLeftDistance() < -distanceToTurn && drivetrain.getRightDistance() > distanceToTurn;
        }

        return isFinished;
    }

    // Runs if the code is interrupted or when it's finished
    @Override
    public void end(boolean interrupted) {
        // Stop motors.
        drivetrain.tankDrive(0, 0); 
    }
}

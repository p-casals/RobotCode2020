package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;


public class RunShooterCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private double speed;

    public RunShooterCommand(ShooterSubsystem shooterSubsystem, double speed) {
        this.shooterSubsystem = shooterSubsystem;
        this.speed = speed;

        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {
        shooterSubsystem.setShooterSpeed(speed);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.setShooterSpeed(0);
    }
}

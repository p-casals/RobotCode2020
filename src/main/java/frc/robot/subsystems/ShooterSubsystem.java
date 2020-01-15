package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

    private WPI_TalonFX leftMotor, rightMotor;

    public ShooterSubsystem() {
        this.leftMotor = new WPI_TalonFX(0);
        this.rightMotor = new WPI_TalonFX(1);
    }

    public void setShooterSpeed(double shooterSpeed) {
        leftMotor.set(shooterSpeed);
        rightMotor.set(-shooterSpeed);
    }

}


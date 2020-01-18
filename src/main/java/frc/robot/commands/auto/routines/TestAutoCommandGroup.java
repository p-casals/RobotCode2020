package frc.robot.commands.auto.routines;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auto.MoveCommand;
import frc.robot.commands.auto.TurnCommand;
import frc.robot.subsystems.Drivetrain;

public class TestAutoCommandGroup extends SequentialCommandGroup {
    public TestAutoCommandGroup(Drivetrain drivetrain) {
        super(new MoveCommand(drivetrain, 24, 0.5),
                new TurnCommand(drivetrain, 180, 0.5),
                new MoveCommand(drivetrain, 24, 0.5));
    }
}

// TODO: this doesn't need to be its own thing: it can be inlined unless it's going to be much more complicated
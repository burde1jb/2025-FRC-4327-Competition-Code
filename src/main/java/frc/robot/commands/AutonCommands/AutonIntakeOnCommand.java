package frc.robot.commands.AutonCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralintakeSubsystem;

public class AutonIntakeOnCommand extends Command   {
    
    CoralintakeSubsystem intakeSubsystem;

    public AutonIntakeOnCommand(CoralintakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }
    
    @Override
    public void execute()   {
        intakeSubsystem.intakeOn(true);
    }

}

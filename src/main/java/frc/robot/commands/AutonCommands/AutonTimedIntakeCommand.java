package frc.robot.commands.AutonCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralintakeSubsystem;

public class AutonTimedIntakeCommand extends Command {
    
    CoralintakeSubsystem intakeSubsystem;
    Timer timer;

    public AutonTimedIntakeCommand(CoralintakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        timer = new Timer();
        addRequirements(intakeSubsystem);
    }
    
    @Override
    public void initialize()    {
        timer.restart();
    }

    @Override
    public void execute()   {
        intakeSubsystem.intakeOn(true);
    }

    @Override
    public boolean isFinished() {
        if (timer.get() >= 2.5)  {
            intakeSubsystem.intakeOff();
            return true;
        }
        return false;
    }

}

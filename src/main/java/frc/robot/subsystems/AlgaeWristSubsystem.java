package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.config.SparkFlexConfig;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotConstants;

public class AlgaeWristSubsystem extends SubsystemBase {
    private SparkFlex wristMotor;
    private DutyCycleEncoder wristEncoder;
    private final double rangeOffset = RobotConstants.AlgaeWristrangeOffset;
    private final double encoderOffset = RobotConstants.AlgaeWristencoderOffset;
    private SparkFlexConfig wristmotorConfig;
    private Encoder wristQuadEncoder;

    public AlgaeWristSubsystem() {
        wristMotor = new SparkFlex(RobotConstants.AlgaeWristmotorCANid, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
        wristEncoder = new DutyCycleEncoder(RobotConstants.AlgaeWristEncoderDIOid);
        wristQuadEncoder = new Encoder(RobotConstants.AlagaeWristQuadEncoder1,  RobotConstants.AlagaeWristQuadEncoder2);
    }

    public void goTo(double encoderGoal, double extremaValue)  {
        if ((encoderGoal + encoderOffset) % 1 > (extremaValue + encoderOffset) % 1 && (wristEncoder.get() + encoderOffset) % 1 < (encoderGoal - rangeOffset + encoderOffset) % 1)   {
            this.retract();
        }
        else if ((encoderGoal + encoderOffset) % 1 > (extremaValue + encoderOffset) % 1 && (wristEncoder.get() + encoderOffset) % 1 > (encoderGoal + rangeOffset + encoderOffset) % 1)    {
            this.extend();
        }
        else if ((encoderGoal + encoderOffset) % 1 < (extremaValue + encoderOffset) % 1 && (wristEncoder.get() + encoderOffset) % 1 > (encoderGoal + rangeOffset + encoderOffset) % 1)  {
            this.extend();
        }
        else if ((encoderGoal + encoderOffset) % 1 < (extremaValue + encoderOffset) % 1 && (wristEncoder.get() + encoderOffset) % 1 < (encoderGoal - rangeOffset + encoderOffset) % 1)  {
            this.retract();
        }
        else    {
            this.stop();
        }
    }

    public boolean wentTo(double encoderGoal, double extremaValue)  {
        if ((encoderGoal + encoderOffset) % 1 > (extremaValue + encoderOffset) % 1 && (wristEncoder.get() + encoderOffset) % 1 < (encoderGoal - rangeOffset + encoderOffset) % 1)   {
            this.retract();
            return false;
        }
        else if ((encoderGoal + encoderOffset) % 1 > (extremaValue + encoderOffset) % 1 && (wristEncoder.get() + encoderOffset) % 1 > (encoderGoal + rangeOffset + encoderOffset) % 1)    {
            this.extend();
            return false;
        }
        else if ((encoderGoal + encoderOffset) % 1 < (extremaValue + encoderOffset) % 1 && (wristEncoder.get() + encoderOffset) % 1 > (encoderGoal + rangeOffset + encoderOffset) % 1)  {
            this.extend();
            return false;
        }
        else if ((encoderGoal + encoderOffset) % 1 < (extremaValue + encoderOffset) % 1 && (wristEncoder.get() + encoderOffset) % 1 < (encoderGoal - rangeOffset + encoderOffset) % 1)  {
            this.retract();
            return false;
        }
        else    {
            this.stop();
            return true;
        }
    }

    public void extend()   {
        wristMotor.set(RobotConstants.AlgaeWristExtendpower);
    }

    public void retract()    {
        wristMotor.set(RobotConstants.AlgaeWristRetractpower);
    }

    public void stop()  {
        wristMotor.stopMotor();
    }

    public boolean encoderCheck(double distance)    {
        if (wristEncoder.get() == distance)  {
            return true;
        }
        return false;
    }

    @Override
    public void periodic()  {
        SmartDashboard.putNumber("Algae Wrist Encoder", (wristEncoder.get()));
        SmartDashboard.putNumber("Algae Wrist Quad Encoder", (wristQuadEncoder.get()));
    }
}
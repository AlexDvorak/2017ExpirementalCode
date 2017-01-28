package org.usfirst.frc.team1114.robot.subsystems;

import org.usfirst.frc.team1114.robot.RobotMap;
import org.usfirst.frc.team1114.robot.commands.Intake;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSub extends Subsystem {
	
	CANTalon intakeMotor = new CANTalon(RobotMap.intakeMotor);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Intake());
    }
    
   public void intake() {
	   intakeMotor.set(.5);
   }
}
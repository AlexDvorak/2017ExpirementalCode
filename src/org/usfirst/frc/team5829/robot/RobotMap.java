package org.usfirst.frc.team5829.robot;

import edu.wpi.first.wpilibj.TalonSRX;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	TalonSRX frontLeftMotor = new TalonSRX(1);
	TalonSRX frontRightMotor = new TalonSRX(2);
	TalonSRX backRightMotor = new TalonSRX(3);
	TalonSRX backLeftMotor = new TalonSRX(4);
	}

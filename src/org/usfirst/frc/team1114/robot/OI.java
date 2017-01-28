package org.usfirst.frc.team1114.robot;

import org.usfirst.frc.team1114.robot.commands.Aim;
import org.usfirst.frc.team1114.robot.commands.DriveCommand;
import org.usfirst.frc.team1114.robot.commands.Shoot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick xbox = new Joystick(0);
	//public Joystick xbox2 = new Joystick(1);
	Button btnA = new JoystickButton(xbox, 1);
	Button btnB = new JoystickButton(xbox, 2);
	Button btnX = new JoystickButton(xbox, 3);
	Button btnY = new JoystickButton(xbox, 4);
	Button btnL1 = new JoystickButton(xbox, 5);
	Button btnR1 = new JoystickButton(xbox, 6);
	Button btnBack = new JoystickButton(xbox, 7);
	Button btnStart = new JoystickButton(xbox, 8);
	Button btnL3 = new JoystickButton(xbox, 9);
	Button btnR3 = new JoystickButton(xbox, 10);
	
	/*
	// 2nd Controller for shooting (if necessary)
	Button btn2A = new JoystickButton(xbox2, 1);
	Button btn2B = new JoystickButton(xbox2, 2);
	Button btn2X = new JoystickButton(xbox2, 3);
	Button btn2Y = new JoystickButton(xbox2, 4);
	Button btn2L1 = new JoystickButton(xbox2, 5);
	Button btn2R1 = new JoystickButton(xbox2, 6);
	Button btn2Back = new JoystickButton(xbox2, 7);
	Button btn2Start = new JoystickButton(xbox2, 8);
	Button btn2L3 = new JoystickButton(xbox2, 9);
	Button btn2R3 = new JoystickButton(xbox2, 10);
	*/
	
	public OI() {
		btnB.whenPressed(new Aim());
		//btnA.whenPressed(new Shoot());
		//new DriveCommand();
	}
}
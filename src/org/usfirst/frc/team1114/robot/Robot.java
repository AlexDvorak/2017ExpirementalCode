
package org.usfirst.frc.team1114.robot;

import org.usfirst.frc.team1114.robot.commands.AutoCommand;
import org.usfirst.frc.team1114.robot.subsystems.Drive;
import org.usfirst.frc.team1114.robot.subsystems.IntakeSub;
import org.usfirst.frc.team1114.robot.subsystems.Shooter;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final Shooter shooter = new Shooter();
    public static final IntakeSub intakeSub = new IntakeSub();
    public static final Drive drivesub = new Drive();
	public static OI oi;
	public static AHRS navx = new AHRS(SerialPort.Port.kMXP);
	public static Preferences prefs;
	public static NetworkTable table;
	

	Command autonomousCommand;
	SendableChooser<Integer> num = new SendableChooser<Integer>();
	double[] sxs = {
		0,
		162,
		48,
		-48,
		-162,
		0
	};
	public static double sx;
	public static double worldScale = 39.4; // navx units * worldScale = inches
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	double boxX;
	double boxY;
	double boxA;
	double imgH;
	double imgW;

	public void robotInit() {
		oi = new OI();
		prefs = Preferences.getInstance();
		num.addObject("RedLeft",1);
		num.addObject("RedMiddle",2);
		num.addObject("RedRight",3);
		num.addObject("BlueLeft",4);
		num.addObject("BlueMiddle",5);
		num.addObject("BlueRight",6);
		SmartDashboard.putData("Auto mode", num);
		table = NetworkTable.getTable("Vision");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */

	public void disabledInit() {

	}


	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		navx.zeroYaw();
		int sel = num.getSelected();
		autonomousCommand = new AutoCommand(sel);
		sx = sxs[sel];
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	

	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	

	public void testPeriodic() {
		LiveWindow.run();
	}
}
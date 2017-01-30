package org.usfirst.frc.team1114.robot.subsystems;

import org.usfirst.frc.team1114.robot.Robot;
import org.usfirst.frc.team1114.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {
	
	double p = .7;
	double i = 0;
	double d = 0;
	double f = 0.0;
	
	boolean shoot = false;
	boolean autoaim = false;

	static double bx = 0;
	static double by = 0;
	static double sx = Robot.sx*Robot.worldScale;
	static double sy = 20*Robot.worldScale;

	CANTalon shooterMotorOne = new CANTalon(RobotMap.shooterMotorOne);
	CANTalon shooterMotorTwo = new CANTalon(RobotMap.shooterMotorTwo);
	
	NetworkTable table = Robot.table;
	double camang = 120;
	
	double boxX = 0;
	double boxY = 0;
	double boxA = 0;
	double imgH = 0;
	double imgW = 0;
	
	public void updateDashboard() {
		double motorOutput2 = shooterMotorTwo.getOutputVoltage() / shooterMotorTwo.getBusVoltage();
		double motorOutput = shooterMotorOne.getOutputVoltage() / shooterMotorOne.getBusVoltage();
		SmartDashboard.putNumber("Motor One Speed", shooterMotorOne.getSpeed());
		SmartDashboard.putNumber("Motor Two Speed", shooterMotorTwo.getSpeed());
		SmartDashboard.putNumber("Motor One Output", motorOutput);
		SmartDashboard.putNumber("Motor Two Output", motorOutput2);
		
	}
	
	public void shoot(double speed) {
		shooterMotorOne.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative); 
		shooterMotorTwo.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
	
	
		shooterMotorOne.reverseSensor(false);
		shooterMotorTwo.reverseSensor(true);
	
	
		shooterMotorOne.changeControlMode(TalonControlMode.Speed);
		shooterMotorTwo.changeControlMode(TalonControlMode.Speed); 
	
	
	/*double shooterP = Robot.prefs.getDouble("shooterP", 0.3);
	double shooterI = Robot.prefs.getDouble("shooterI", 0);
	double shooterD = Robot.prefs.getDouble("shooterD", 0);
	double shooterF = Robot.prefs.getDouble("shooterF", 0);
	double prefspeed = Robot.prefs.getDouble("prefspeed", speed);
	*/

		Robot.shooter.shooterMotorOne.setP(p);
		Robot.shooter.shooterMotorOne.setI(i);
		Robot.shooter.shooterMotorOne.setD(d);
		Robot.shooter.shooterMotorOne.setF(f); 
	
		Robot.shooter.shooterMotorTwo.setP(p);
		Robot.shooter.shooterMotorTwo.setI(i);
		Robot.shooter.shooterMotorTwo.setD(d);
		Robot.shooter.shooterMotorTwo.setF(f);
	
	
		shooterMotorOne.configPeakOutputVoltage(+12.0f, -12.0f);
		shooterMotorOne.configNominalOutputVoltage(+0.0f, -0.0f);
		shooterMotorTwo.configPeakOutputVoltage(+12.0f, -12.0f);
		shooterMotorTwo.configNominalOutputVoltage(+0.0f, -0.0f);


		shooterMotorOne.set(-speed);
		shooterMotorTwo.set(speed);	
		updateDashboard();
		

	}

	public void aim() {
		double ang = 180;
		while (ang > 5) {
			double xdis = (sx + (Robot.navx.getDisplacementX()*Robot.worldScale))-bx;
			double ydis = (sy + (Robot.navx.getDisplacementY()*Robot.worldScale))-by;
			double mag = Math.sqrt((xdis*xdis)+(ydis*ydis));
			double dir = Math.atan(ydis/xdis) - Robot.navx.getYaw();
			
			SmartDashboard.putNumber("DisX", Robot.navx.getDisplacementX()*Robot.worldScale);
			SmartDashboard.putNumber("DisY", Robot.navx.getDisplacementY()*Robot.worldScale);
			SmartDashboard.putNumber("ShootDistance", mag);
			SmartDashboard.putNumber("ShootDirection", dir);
			
			Robot.drivesub.turnHelp(dir);
			Timer.delay(.2);
		
			table.getNumber("COG_X",boxX);
			//table.getNumber("COG_Y",boxY);
			//table.getNumber("COG_AREA",boxA);
			//table.getNumber("IMAGE_HEIGHT",imgH);
			table.getNumber("IMAGE_WIDTH",imgW);
			ang = ((boxX/imgW)-0.5)*camang;
		}
		double s = 999;
		for (int i=0;i<10;i++) {
			table.getNumber("COG_AREA",boxA);
			double ss = Math.sqrt(boxA);
			if (ss < s) {
				s = ss;
			}
			Timer.delay(.1);
		}
	}
	public void toggleShoot(boolean sh) {
		if (sh) {
			shoot(2000);
		} else {
			shoot(0);
		}
	}
	public void toggleAutoAim(boolean aa) {
		if (aa) {
			autoaim = true;
			while (autoaim) {
				aim();
				Timer.delay(.2);
			}
		} else {
			autoaim = false;
		}
	}

    public void initDefaultCommand() {
        
    }
}
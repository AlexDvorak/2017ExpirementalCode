package org.usfirst.frc.team1114.robot.subsystems;

import org.usfirst.frc.team1114.robot.Robot;
import org.usfirst.frc.team1114.robot.RobotMap;
import org.usfirst.frc.team1114.robot.commands.Shoot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {
	
	double p = .7;
	double i = 0;
	double d = 0;
	double f = 0.0;
	
	boolean shoot = true;
	boolean autoaim = true;

	static double bx = 0;
	static double by = 0;
	static double sx = Robot.sx*Robot.worldScale;
	static double sy = 20*Robot.worldScale;

	CANTalon shooterMotorOne = new CANTalon(RobotMap.shooterMotorOne);
	CANTalon shooterMotorTwo = new CANTalon(RobotMap.shooterMotorTwo);
	
	public void shoot(double speed) {
		shooterMotorOne.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative); 
		shooterMotorTwo.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
	
	
		shooterMotorOne.reverseSensor(true);
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
	
	//Robot.shooter.shooterMotorTwo.setP(p);
	//Robot.shooter.shooterMotorTwo.setI(i);
	//Robot.shooter.shooterMotorTwo.setD(d);
	//Robot.shooter.shooterMotorTwo.setF(f);
	
	
		shooterMotorOne.configPeakOutputVoltage(+12.0f, -12.0f);
		shooterMotorOne.configNominalOutputVoltage(+0.0f, -0.0f);
		shooterMotorTwo.configPeakOutputVoltage(+12.0f, -12.0f);
		shooterMotorTwo.configNominalOutputVoltage(+0.0f, -0.0f);


		shooterMotorOne.set(speed);
	//shooterMotorTwo.set(-speed);

	}

	public void aim() {
		double xdis = (sx + (Robot.navx.getDisplacementX()*Robot.worldScale))-bx;
		double ydis = (sy + (Robot.navx.getDisplacementY()*Robot.worldScale))-by;
		double mag = Math.sqrt((xdis*xdis)+(ydis*ydis));
		double dir = Math.atan(ydis/xdis) + 180 - Robot.navx.getYaw();

		SmartDashboard.putNumber("DisX", Robot.navx.getDisplacementX()*Robot.worldScale);
		SmartDashboard.putNumber("DisY", Robot.navx.getDisplacementY()*Robot.worldScale);
		SmartDashboard.putNumber("ShootDistance", mag);
		SmartDashboard.putNumber("ShootDirection", dir);
		// mag is the distance to the boiler from the robot
		// dir is the direction towards the boiler relative to the robot
	}
	public void toggleShoot() {
		shoot = !shoot;
		if (shoot) {
			shoot(0.1);
		} else {
			shoot(0);
		}
	}
	public void toggleAutoAim() {
		autoaim = !autoaim;
		while (autoaim) {
			Timer.delay(.2);
			aim();
		}
	}

    public void initDefaultCommand() {
        setDefaultCommand(new Shoot());
    }
}
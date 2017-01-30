package org.usfirst.frc.team1114.robot.subsystems;

import org.usfirst.frc.team1114.robot.Robot;
import org.usfirst.frc.team1114.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {
	
	CANTalon fl = new CANTalon(RobotMap.leftFrontMotor);
	CANTalon fr = new CANTalon(RobotMap.rightFrontMotor);
	CANTalon bl = new CANTalon(RobotMap.leftBackMotor);
	CANTalon br = new CANTalon(RobotMap.rightBackMotor);
	
	public void drive(double thr, double str) {
		double lt = Math.max(-1,Math.min(thr + str,1))*0.95;
		double rt = Math.max(-1,Math.min(thr - str,1))*0.95;
		fl.set(lt);
		bl.set(lt);
		fr.set(rt);
		br.set(rt);
	}
	
	static double worldScale = Robot.worldScale;
	
    public void driveHelp(double inch) {
    	double lx = 0;
    	double ly = 0;
    	double lt = Timer.getFPGATimestamp();    	
    	
    	double dist = 0;
    	double thr = inch/Math.abs(inch);
    	double lastyaw = Robot.navx.getYaw();
    	boolean driving = true;
    	while (driving) {
    		double distx = (Robot.navx.getDisplacementX() - lx)*worldScale;
    		double disty = (Robot.navx.getDisplacementY() - ly)*worldScale;
    		
    		double nt = Timer.getFPGATimestamp();
    		double edist = Math.sqrt((distx*distx)+(disty*disty));
    		dist += edist;
    		double speed = edist/(nt-lt);
    		double rem = inch - dist;
    		
    		
    		double str = (lastyaw-Robot.navx.getYaw())/90; // experimental
    		if (speed>rem) {
    			if (rem<0.4) {
    				driving = false;
    			} else {
    				thr = thr/2;
    			}
    		}
    		
    		lastyaw = Robot.navx.getYaw();
    		lx = Robot.navx.getDisplacementX();
    		ly = Robot.navx.getDisplacementY();
    		lt = nt;
    		
    		drive(thr,str);
    		Timer.delay(.1);
    	}
    	drive(0,0);
    }
    public void turnHelp(double deg) {
    	double ly = Robot.navx.getYaw();
    	double gyaw = ly+deg;
    	double dir = deg/Math.abs(deg);
    	double lt = Timer.getFPGATimestamp();
    	double yaw = ly;
    	
    	boolean on = true;
    	while (on) {
    		double yc = Robot.navx.getYaw() - ly;
    		yaw += yc;
    		double rem = gyaw - yaw;
    		
    		double nt = Timer.getFPGATimestamp();
    		double speed = yc/(nt-lt);
    		
    		if (speed > rem) {
    			if (rem < 10) {
    				on = false;
    			} else {
    				dir = dir/2;
    			}
    		}
    		
    		lt = nt;
    		ly = Robot.navx.getYaw();
    		drive(0,dir);
    		Timer.delay(.1);
    	}
    	drive(0,0);
    }

    public void initDefaultCommand() {
        
    }
}

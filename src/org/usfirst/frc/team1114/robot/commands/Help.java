package org.usfirst.frc.team1114.robot.commands;

import org.usfirst.frc.team1114.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Help extends Command {
	
	private boolean driving;
	private double amt;

    public Help(boolean x, double y) {
    	requires(Robot.drivesub);
    	driving = x;
    	amt = y;
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (driving) {
    		Robot.drivesub.driveHelp(amt);
    	} else {
    		Robot.drivesub.turnHelp(amt);
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
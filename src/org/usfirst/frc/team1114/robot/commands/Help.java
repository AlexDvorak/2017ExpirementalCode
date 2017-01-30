package org.usfirst.frc.team1114.robot.commands;

import org.usfirst.frc.team1114.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Help extends Command {

    public Help(boolean x, double y) {
    	requires(Robot.drivesub);
    	if (x) {
    		Robot.drivesub.driveHelp(y);
    	} else {
    		Robot.drivesub.turnHelp(y);
    	}
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
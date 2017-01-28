package org.usfirst.frc.team1114.robot.commands;

import org.usfirst.frc.team1114.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCommand extends Command {

    public DriveCommand() {
    	requires(Robot.drivesub);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.drivesub.drive(-Robot.oi.xbox.getRawAxis(5),Robot.oi.xbox.getRawAxis(4));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
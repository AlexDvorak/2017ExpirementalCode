package org.usfirst.frc.team1114.robot.commands;

import org.usfirst.frc.team1114.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {

	private boolean shooting = false;
	
    public Shoot(boolean x) {
        requires(Robot.shooter);
        shooting = x;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooter.toggleShoot(shooting);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
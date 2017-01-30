package org.usfirst.frc.team1114.robot.commands;

import org.usfirst.frc.team1114.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Aim extends Command {
	boolean autoaim = false;

    public Aim(boolean aa) {
        requires(Robot.shooter);
        autoaim = aa;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooter.toggleAutoAim(autoaim);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
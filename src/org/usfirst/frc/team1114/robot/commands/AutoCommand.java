package org.usfirst.frc.team1114.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCommand extends CommandGroup {

    public AutoCommand(int n) {
    	switch(n) {
    	case 1: AutoRL(); break;
    	case 2: AutoRM(); break;
    	case 3: AutoRR(); break;
    	case 4: AutoBL(); break;
    	case 5: AutoBM(); break;
    	case 6: AutoBR(); break;
    	}
    }
    
    void AutoRL() {
    	
    }
    void AutoRM() {
    	addSequential(new Help(true,50));
    	
    	addSequential(new Help(true,-25));
    	addSequential(new Help(false,-30));
    	addSequential(new Help(true,199));
    	addSequential(new Help(false,-60));
    	addSequential(new Help(true,50));
    }
    void AutoRR() {
    	addSequential(new Help(true,51));
    	addSequential(new Help(false,-60));
    	
    	addSequential(new Help(true,98));
    	
    	addSequential(new Help(true,-30));
    	addSequential(new Help(false,150));
    	addSequential(new Help(true,-92));
    }
    void AutoBL() {
    	addSequential(new Help(true,51));
    	addSequential(new Help(false,60));
    	
    	addSequential(new Help(true,98));
    	
    	addSequential(new Help(true,-30));
    	addSequential(new Help(false,-150));
    	addSequential(new Help(true,-92));
    }
    void AutoBM() {
    	addSequential(new Help(true,50));
    	
    	addSequential(new Help(true,-25));
    	addSequential(new Help(false,30));
    	addSequential(new Help(true,199));
    	addSequential(new Help(false,60));
    	addSequential(new Help(true,50));
    }
    void AutoBR() {
    	
    }
}
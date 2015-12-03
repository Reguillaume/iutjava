package edu.iut.gui.modele;

import java.util.ArrayList;

import edu.iut.app.Classroom;

public class ModeleClassroom extends ArrayList<Classroom> {
	private static ModeleClassroom classrooms=null;
	
	public static ModeleClassroom instance() {
		if(classrooms==null) classrooms=new ModeleClassroom();
		return classrooms;
	}
}

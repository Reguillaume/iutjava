package edu.iut.gui.modele;

import java.util.ArrayList;

/**
 * Classe permettant de stocker toutes les salles de classe.
 * @Classroom
 * @author Guizmo
 *
 */
public class ListeClassroom extends ArrayList<ModeleClassroom> {
	private static ListeClassroom classrooms=null;
	
	public static ListeClassroom instance() {
		if(classrooms==null) classrooms=new ListeClassroom();
		return classrooms;
	}
}

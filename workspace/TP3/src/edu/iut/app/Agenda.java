package edu.iut.app;

import java.util.ArrayList;

/**
 * Classe repr�sentant une liste d'examen
 * @see ExamEvent
 * @author Guizmo
 *
 */
public class Agenda extends ArrayList<ExamEvent> {

	public Agenda() {		
	}
	
	public void addCheckedEvent(ExamEvent examEvent) {
		this.add(examEvent);
	}

}

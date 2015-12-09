package edu.iut.filters;

import java.util.ArrayList;

import edu.iut.app.ExamEvent;

/**
 * Classe permettant de filtrer les examens par salle de classe.
 * @see ExamEvent
 * @see Classroom
 * @author Guizmo
 *
 */
public class CritereClassroom implements Critere {
	private String num;
	
	@Override
	public ArrayList<ExamEvent> meetCritere(ArrayList<ExamEvent> ex) {
		ArrayList<ExamEvent> newEx=new ArrayList<ExamEvent>();
		
		for(ExamEvent e : ex) {
			if(e.getClassroom().getClassRoomNumber()==num) newEx.add(e);
		}
		
		return newEx;
	}

	public void setNum(String num) {
		this.num = num;
	}
}

package edu.iut.filters;

import java.util.ArrayList;

import edu.iut.gui.modele.ModeleExamEvent;

/**
 * Classe permettant de filtrer les examens par salle de classe.
 * @see ModeleExamEvent
 * @see ModeleClassroom
 * @author Guizmo
 *
 */
public class CritereClassroom implements Critere {
	private String num;
	
	@Override
	public ArrayList<ModeleExamEvent> meetCritere(ArrayList<ModeleExamEvent> ex) {
		ArrayList<ModeleExamEvent> newEx=new ArrayList<ModeleExamEvent>();
		
		for(ModeleExamEvent e : ex) {
			if(e.getClassroom().getClassRoomNumber()==num) newEx.add(e);
		}
		
		return newEx;
	}

	public void setNum(String num) {
		this.num = num;
	}
}

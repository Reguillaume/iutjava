package edu.iut.filters;

import java.util.ArrayList;


import java.util.Date;

import edu.iut.gui.modele.ModeleExamEvent;

/**
 * Classe permettant de filtrer les examens par date.
 * @see ModeleExamEvent
 * @see java.util.Date
 * @author Guizmo
 *
 */
public class CritereDate implements Critere{
	private Date date;
	
	@Override
	public ArrayList<ModeleExamEvent> meetCritere(ArrayList<ModeleExamEvent> ex) {
		ArrayList<ModeleExamEvent> newEx=new ArrayList<ModeleExamEvent>();
		
		for(ModeleExamEvent e : ex) {
			if(e.getExamDate().equals(date)) newEx.add(e);
		}
		return newEx;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}

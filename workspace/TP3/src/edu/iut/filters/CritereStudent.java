package edu.iut.filters;

import java.util.ArrayList;

import edu.iut.app.ExamEvent;
import edu.iut.filters.Critere;;

public class CritereStudent implements Critere {
	private String nom;
	private String prenom;
	
	@Override
	public ArrayList<ExamEvent> meetCritere(ArrayList<ExamEvent> ex) {
		ArrayList<ExamEvent> newEx=new ArrayList<ExamEvent>();
		
		for(ExamEvent e : ex) {
			if(e.getStudent().getFirstname()==prenom && e.getStudent().getLastname()==nom) newEx.add(e);
		}
		return newEx;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}

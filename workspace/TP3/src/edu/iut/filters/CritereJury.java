package edu.iut.filters;

import java.util.ArrayList;

import edu.iut.app.ExamEvent;
import edu.iut.app.Person;

/**
 * Classe permettant de filtrer les examens par jury.
 * @see ExamEvent
 * @see Person
 * @author Guizmo
 *
 */
public class CritereJury implements Critere {
	
	private String nom;
	private String prenom;
	
	@Override
	public ArrayList<ExamEvent> meetCritere(ArrayList<ExamEvent> ex) {
		ArrayList<ExamEvent> newEx=new ArrayList<ExamEvent>();
		
		for(ExamEvent e : ex) {
			for(Person p : e.getJury()) {
				if(p.getFirstname()==prenom && p.getLastname()==nom) newEx.add(e);
			}
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

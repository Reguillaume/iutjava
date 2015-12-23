package edu.iut.filters;

import java.util.ArrayList;

import edu.iut.filters.Critere;
import edu.iut.gui.modele.ModeleExamEvent;

/**
 * Classe permettant de filter les examens par �tudiant.
 * @see ModeleExamEvent
 * @see ModelePerson
 * @author Guizmo
 *
 */
public class CritereStudent implements Critere {
	private String nom;
	private String prenom;
	
	@Override
	public ArrayList<ModeleExamEvent> meetCritere(ArrayList<ModeleExamEvent> ex) {
		ArrayList<ModeleExamEvent> newEx=new ArrayList<ModeleExamEvent>();
		
		for(ModeleExamEvent e : ex) {
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

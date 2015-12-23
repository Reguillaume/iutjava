package edu.iut.filters;

import java.util.ArrayList;

import edu.iut.gui.modele.ModeleExamEvent;
import edu.iut.gui.modele.ModelePerson;

/**
 * Classe permettant de filtrer les examens par jury.
 * @see ModeleExamEvent
 * @see ModelePerson
 * @author Guizmo
 *
 */
public class CritereJury implements Critere {
	
	private String nom;
	private String prenom;
	
	@Override
	public ArrayList<ModeleExamEvent> meetCritere(ArrayList<ModeleExamEvent> ex) {
		ArrayList<ModeleExamEvent> newEx=new ArrayList<ModeleExamEvent>();
		
		for(ModeleExamEvent e : ex) {
			for(ModelePerson p : e.getJury()) {
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

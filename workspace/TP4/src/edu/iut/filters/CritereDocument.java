package edu.iut.filters;

import java.util.ArrayList;

import edu.iut.gui.modele.ModeleDocument;
import edu.iut.gui.modele.ModeleExamEvent;

/**
 * Classe permettant de filtrer les examens par document.
 * @see ModeleExamEvent
 * @see ModeleDocument
 * @author Guizmo
 *
 */
public class CritereDocument implements Critere {
	private String doc;
	
	@Override
	public ArrayList<ModeleExamEvent> meetCritere(ArrayList<ModeleExamEvent> ex) {
		ArrayList<ModeleExamEvent> newEx=new ArrayList<ModeleExamEvent>();
		
		for(ModeleExamEvent e : ex) {
			for(ModeleDocument d : e.getDocuments()) {
				if(d.getDocumentURI().equals(doc)) newEx.add(e);
			}
		}
		return newEx;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

}

package edu.iut.filters;

import java.util.ArrayList;

import edu.iut.gui.modele.ModeleExamEvent;

/**
 * Interface pour filtrer un examen.
 * @see ModeleExamEvent
 * @author Guizmo
 *
 */
public interface Critere {
	public ArrayList<ModeleExamEvent> meetCritere(ArrayList<ModeleExamEvent> ex);
}

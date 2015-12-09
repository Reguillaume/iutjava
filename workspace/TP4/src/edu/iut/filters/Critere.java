package edu.iut.filters;

import java.util.ArrayList;

import edu.iut.app.ExamEvent;

/**
 * Interface pour filtrer un examen.
 * @see ExamEvent
 * @author Guizmo
 *
 */
public interface Critere {
	public ArrayList<ExamEvent> meetCritere(ArrayList<ExamEvent> ex);
}

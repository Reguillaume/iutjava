package edu.iut.filters;

import java.util.ArrayList;

import edu.iut.app.ExamEvent;

public interface Critere {
	public ArrayList<ExamEvent> meetCritere(ArrayList<ExamEvent> ex);
}

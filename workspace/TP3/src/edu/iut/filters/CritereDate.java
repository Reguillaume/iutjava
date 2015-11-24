package edu.iut.filters;

import java.util.ArrayList;


import java.util.Date;

import edu.iut.app.ExamEvent;

public class CritereDate implements Critere{
	
	public ArrayList<ExamEvent> meetCritere(ArrayList<ExamEvent> ex, Date d) {
		ArrayList<ExamEvent> newEx=new ArrayList<ExamEvent>();
		
		for(ExamEvent e : ex) {
			if(e.getExamDate().equals(d)) newEx.add(e);
		}
		return newEx;
	}
}

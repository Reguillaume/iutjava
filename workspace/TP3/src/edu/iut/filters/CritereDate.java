package edu.iut.filters;

import java.util.ArrayList;


import java.util.Date;

import edu.iut.app.ExamEvent;

public class CritereDate implements Critere{
	private Date date;
	
	@Override
	public ArrayList<ExamEvent> meetCritere(ArrayList<ExamEvent> ex) {
		ArrayList<ExamEvent> newEx=new ArrayList<ExamEvent>();
		
		for(ExamEvent e : ex) {
			if(e.getExamDate().equals(date)) newEx.add(e);
		}
		return newEx;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}

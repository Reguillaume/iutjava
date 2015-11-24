package edu.iut.filters;

import java.util.ArrayList;
import java.util.Date;

import edu.iut.app.ExamEvent;

public class CritereStudent {
	public class CritereDate {
		
		public ArrayList<ExamEvent> critere(ArrayList<ExamEvent> ex, Date d) {
			ArrayList<ExamEvent> newEx=new ArrayList<ExamEvent>();
			
			for(ExamEvent e : ex) {
				if(e.getExamDate().equals(d)) newEx.add(e);
			}
			return newEx;
		}
}

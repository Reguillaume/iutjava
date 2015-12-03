package edu.iut.filters;

import java.util.ArrayList;

import edu.iut.app.Document;
import edu.iut.app.ExamEvent;

public class CritereDocument implements Critere {
	private String doc;
	
	@Override
	public ArrayList<ExamEvent> meetCritere(ArrayList<ExamEvent> ex) {
		ArrayList<ExamEvent> newEx=new ArrayList<ExamEvent>();
		
		for(ExamEvent e : ex) {
			for(Document d : e.getDocuments()) {
				if(d.getDocumentURI().equals(doc)) newEx.add(e);
			}
		}
		return newEx;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

}

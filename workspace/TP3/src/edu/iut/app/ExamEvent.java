<<<<<<< HEAD
package edu.iut.app;

import java.util.ArrayList;
import java.util.Date;

public class ExamEvent {
	public ExamEvent() {		
	}
	
	public ExamEvent(Date date, Person person, ArrayList<Person> jury,
					Classroom classRoom, ArrayList<Document> document) {
		this.examDate=date;
		this.student=person;
		this.jury=jury;
		this.classroom=classRoom;
		this.documents=document;
	}
	
	/** EX2: FAITE LES ACCESSEURS DES ATTRIBUTS, AJOUTER DES ATTRIBUT ? **/
	protected Date examDate;
	protected Person student;
	protected ArrayList<Person> jury;
	protected Classroom classroom;
	protected ArrayList<Document> documents;
	
	public Date getExamDate() {
		return examDate;
	}

	public Person getStudent() {
		return student;
	}

	public ArrayList<Person> getJury() {
		return jury;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public ArrayList<Document> getDocuments() {
		return documents;
	}
		
}
=======
package edu.iut.app;

import java.util.ArrayList;
import java.util.Date;

public class ExamEvent {
	public ExamEvent() {		
	}
	
	public ExamEvent(Date date, Person person, ArrayList<Person> jury,
					Classroom classRoom, ArrayList<Document> document) {
		this.examDate=date;
		this.student=person;
		this.jury=jury;
		this.classroom=classRoom;
		this.documents=document;
	}
	
	/** EX2: FAITE LES ACCESSEURS DES ATTRIBUTS, AJOUTER DES ATTRIBUT ? **/
	protected Date examDate;
	protected Person student;
	protected ArrayList<Person> jury;
	protected Classroom classroom;
	protected ArrayList<Document> documents;
	
	public Date getExamDate() {
		return examDate;
	}

	public Person getStudent() {
		return student;
	}

	public ArrayList<Person> getJury() {
		return jury;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public ArrayList<Document> getDocuments() {
		return documents;
	}
	 
}
>>>>>>> 606d363c0a78d99f0fd25a55d8f1fb14a6ccb183
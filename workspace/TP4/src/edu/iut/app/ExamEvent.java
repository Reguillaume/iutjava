package edu.iut.app;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe représentant un examen.
 * @author Guizmo
 *
 */
public class ExamEvent {
	public ExamEvent() {		
	}
	
	/**
	 * Construit un examen à partir des paramètres.
	 * @param date
	 * 	Une date sous forme de Date
	 * @see java.util.Date
	 * @param person
	 * 	Un étudiant sous forme de Person.
	 * @param jury
	 * 	Une liste de jurys sous forme d'un ArrayList de Person.
	 * @see Person
	 * @param classRoom
	 *	Une salle de classe sous forme de Classroom.
	 * @see Classroom
	 * @param document
	 * 	Une liste de documents sous forme d'ArrayList de Document.
	 * @see Document
	 */
	public ExamEvent(Date date, Person person, ArrayList<Person> jury,
					Classroom classRoom, ArrayList<Document> document) {
		this.examDate=date;
		this.student=person;
		this.jury=jury;
		this.classroom=classRoom;
		this.documents=document;
	}
	
	/**
	 * Une date sous forme java.util.Date.
	 * @see java.util.Date
	 */
	protected Date examDate;
	
	/**
	 * Un étudiant sous forme de Person.
	 * @see Person
	 */
	protected Person student;
	
	/**
	 * Une liste de jurys sous forme d'un ArrayList de Person.
	 * @see Person
	 */
	protected ArrayList<Person> jury;
	
	/**
	 * Une salle de classe sous forme de Classroom.
	 * @see Classroom
	 */
	protected Classroom classroom;
	
	/**
	 * Une liste de documents sous forme d'un ArrayList de Document.
	 * @see Document.
	 */
	protected ArrayList<Document> documents;
	
	/**
	 * Retourne la date de l'examen.
	 * @return Date de l'examen sous forme java.util.Date.
	 * @see java.util.Date
	 */
	public Date getExamDate() {
		return examDate;
	}
	
	/**
	 * Retourne l'étudiant de l'examen.
	 * @return L'étudiant de l'examen sous forme de Person.
	 * @see Person
	 */
	public Person getStudent() {
		return student;
	}

	/**
	 * Retourne les jurys de l'examen.
	 * @return La liste des jurys sous forme d'un ArrayList de Person.
	 * @see Person
	 */
	public ArrayList<Person> getJury() {
		return jury;
	}

	/**
	 * Retourn la salle de l'examen.
	 * @return Salle de l'examen sous forme de Classroom.
	 * @see Classroom
	 */
	public Classroom getClassroom() {
		return classroom;
	}

	/**
	 * Retourne les documents de l'examen.
	 * @return La liste des documents de l'examen sous forme d'un ArrayList de Document.
	 * @see Document
	 */
	public ArrayList<Document> getDocuments() {
		return documents;
	}
		
}

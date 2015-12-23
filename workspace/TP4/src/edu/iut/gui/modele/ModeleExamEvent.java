package edu.iut.gui.modele;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe repr�sentant un examen.
 * @author Guizmo
 *
 */
public class ModeleExamEvent {
	public ModeleExamEvent() {		
	}
	
	/**
	 * Construit un examen � partir des param�tres.
	 * @param date
	 * 	Une date sous forme de Date
	 * @see java.util.Date
	 * @param person
	 * 	Un �tudiant sous forme de Person.
	 * @param jury
	 * 	Une liste de jurys sous forme d'un ArrayList de Person.
	 * @see ModelePerson
	 * @param classRoom
	 *	Une salle de classe sous forme de Classroom.
	 * @see ModeleClassroom
	 * @param document
	 * 	Une liste de documents sous forme d'ArrayList de Document.
	 * @see ModeleDocument
	 */
	public ModeleExamEvent(Date date, ModelePerson person, ArrayList<ModelePerson> jury,
					ModeleClassroom classRoom, ArrayList<ModeleDocument> document) {
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
	 * Un �tudiant sous forme de Person.
	 * @see ModelePerson
	 */
	protected ModelePerson student;
	
	/**
	 * Une liste de jurys sous forme d'un ArrayList de Person.
	 * @see ModelePerson
	 */
	protected ArrayList<ModelePerson> jury;
	
	/**
	 * Une salle de classe sous forme de Classroom.
	 * @see ModeleClassroom
	 */
	protected ModeleClassroom classroom;
	
	/**
	 * Une liste de documents sous forme d'un ArrayList de Document.
	 * @see Document.
	 */
	protected ArrayList<ModeleDocument> documents;
	
	/**
	 * Retourne la date de l'examen.
	 * @return Date de l'examen sous forme java.util.Date.
	 * @see java.util.Date
	 */
	public Date getExamDate() {
		return examDate;
	}
	
	/**
	 * Retourne l'�tudiant de l'examen.
	 * @return L'�tudiant de l'examen sous forme de Person.
	 * @see ModelePerson
	 */
	public ModelePerson getStudent() {
		return student;
	}

	/**
	 * Retourne les jurys de l'examen.
	 * @return La liste des jurys sous forme d'un ArrayList de Person.
	 * @see ModelePerson
	 */
	public ArrayList<ModelePerson> getJury() {
		return jury;
	}

	/**
	 * Retourn la salle de l'examen.
	 * @return Salle de l'examen sous forme de Classroom.
	 * @see ModeleClassroom
	 */
	public ModeleClassroom getClassroom() {
		return classroom;
	}

	/**
	 * Retourne les documents de l'examen.
	 * @return La liste des documents de l'examen sous forme d'un ArrayList de Document.
	 * @see ModeleDocument
	 */
	public ArrayList<ModeleDocument> getDocuments() {
		return documents;
	}
		
}

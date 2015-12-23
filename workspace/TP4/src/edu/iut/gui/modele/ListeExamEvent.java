package edu.iut.gui.modele;

import java.util.ArrayList;
import java.util.Date;

import edu.iut.gui.modele.ListeClassroom;
import edu.iut.gui.modele.ListeDocument;
import edu.iut.gui.modele.ListePerson;
import edu.iut.gui.modele.ModelePerson.PersonFunction;

/**
 * Classe permettant de stocker tous les examens.
 * @see ModeleExamEvent
 * @author Guizmo
 *
 */
public class ListeExamEvent extends ArrayList<ModeleExamEvent> {
	private static ListeExamEvent agenda=null;
	
	static public ListeExamEvent instance() {
		if(agenda==null) {
			agenda=new ListeExamEvent();/*
			//On ajoute un exam pour tester
			Date date=new Date();
			Person student=new Person(PersonFunction.STUDENT, "Zidane", "Zinédine", "zidane.zinedine@super-star.foot", "010101");
			ModelePerson.instance().add(student);
			Person jury=new Person(PersonFunction.JURY, "Yung", "Lean", "yung.lean@sadboys.arizona", "666");
			ModelePerson.instance().add(jury);
			ArrayList<Person> juryArray=new ArrayList<>();
			juryArray.add(jury);
			Document doc=new Document("URI-YungCLOUD");
			ModeleDocument.instance().add(doc);
			ArrayList<Document> docArray=new ArrayList<>();
			docArray.add(doc);
			Classroom classroom=new Classroom("heaven");
			ModeleClassroom.instance().add(classroom);
			ExamEvent examEvent=new ExamEvent(date, student, juryArray, classroom, docArray);
			agenda.add(examEvent);*/
		}
		return agenda;
	}
}

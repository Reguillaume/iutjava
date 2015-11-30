package edu.iut.gui.widget.agenda;

import java.util.ArrayList;
import java.util.Date;

import edu.iut.app.Agenda;
import edu.iut.app.Classroom;
import edu.iut.app.Document;
import edu.iut.app.ExamEvent;
import edu.iut.app.Person;
import edu.iut.app.Person.PersonFunction;

public class ModelAgenda extends Agenda {
	private static ModelAgenda agenda=null;
	
	static public ModelAgenda instance() {
		if(agenda==null) {
			agenda=new ModelAgenda();
			//On ajoute un exam pour tester
			Date date=new Date();
			Person student=new Person(PersonFunction.STUDENT, "Zidane", "Zinédine", "zidane.zinedine@super-star.foot", "010101");
			Person jury=new Person(PersonFunction.JURY, "Yung", "Lean", "yung.lean@sadboys.arizona", "666");
			ArrayList<Person> juryArray=new ArrayList<>();
			juryArray.add(jury);
			Document doc=new Document("URI-YungCLOUD");
			ArrayList<Document> docArray=new ArrayList<>();
			docArray.add(doc);
			Classroom classroom=new Classroom("heaven");
			ExamEvent examEvent=new ExamEvent(date, student, juryArray, classroom, docArray);
			agenda.add(examEvent);
		}
		return agenda;
	}
	
}

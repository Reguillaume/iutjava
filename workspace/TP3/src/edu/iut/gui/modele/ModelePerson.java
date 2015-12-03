package edu.iut.gui.modele;

import java.util.ArrayList;

import edu.iut.app.Person;

public class ModelePerson extends ArrayList<Person> {
	private static ModelePerson persons=null;
	
	public static ModelePerson instance() {
		if(persons==null) {
			persons=new ModelePerson();
		}
		return persons;
	}	
}

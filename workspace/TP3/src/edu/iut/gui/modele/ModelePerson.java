package edu.iut.gui.modele;

import java.util.ArrayList;

import edu.iut.app.Person;

/**
 * Classe permettant de stocker tous les étudiants et jurys.
 * @see Person
 * @author Guizmo
 *
 */
public class ModelePerson extends ArrayList<Person> {
	private static ModelePerson persons=null;
	
	public static ModelePerson instance() {
		if(persons==null) {
			persons=new ModelePerson();
		}
		return persons;
	}	
}

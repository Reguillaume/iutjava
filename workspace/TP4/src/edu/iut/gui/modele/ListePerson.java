package edu.iut.gui.modele;

import java.util.ArrayList;

/**
 * Classe permettant de stocker tous les �tudiants et jurys.
 * @see ModelePerson
 * @author Guizmo
 *
 */
public class ListePerson extends ArrayList<ModelePerson> {
	private static ListePerson persons=null;
	
	public static ListePerson instance() {
		if(persons==null) {
			persons=new ListePerson();
		}
		return persons;
	}
}

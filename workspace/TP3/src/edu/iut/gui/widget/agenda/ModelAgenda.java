package edu.iut.gui.widget.agenda;

import edu.iut.app.Agenda;

public class ModelAgenda extends Agenda {
	private static ModelAgenda agenda=null;
	
	static public ModelAgenda instance() {
		if(agenda==null) {
			agenda=new ModelAgenda();
		}
		return agenda;
	}
	
}

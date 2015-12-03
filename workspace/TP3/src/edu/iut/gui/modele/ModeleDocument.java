package edu.iut.gui.modele;

import java.util.ArrayList;

import edu.iut.app.Document;

public class ModeleDocument extends ArrayList<Document> {
	private static ModeleDocument documents=null;
	
	public static ModeleDocument instance() {
		if(documents==null) documents=new ModeleDocument();
		return documents;
	}
}

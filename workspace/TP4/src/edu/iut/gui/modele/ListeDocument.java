package edu.iut.gui.modele;

import java.util.ArrayList;

/**
 * Classe permettant de stocker tous les documents.
 * @see ModeleDocument
 * @author Guizmo
 *
 */
public class ListeDocument extends ArrayList<ModeleDocument> {
	private static ListeDocument documents=null;
	
	public static ListeDocument instance() {
		if(documents==null) documents=new ListeDocument();
		return documents;
	}
}

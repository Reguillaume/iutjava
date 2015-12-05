package edu.iut.app;

/**
 * Classe représentant un document.
 * @author Guizmo
 *
 */
public class Document {
	
	/**
	 * Initialise un document dont l'URI est vide.
	 */
	public Document() {
		documentURI="";
	}
	
	/**
	 * Initialise un document dont l'URI est le nom en paramètre.
	 * @param documentURI
	 * 	L'URI du document sous forme de chaîne de caractères.
	 */
	public Document(String documentURI) {
		this.documentURI=documentURI;
	}
	
	/**
	 * Remplace l'URI du document par l'URI en paramètre.
	 * @param number
	 * 	L'URI du document sous forme de chaîne de caractères.
	 */
	public void setDocumentURI(String number) {
		this.documentURI = number;
	}
	
	/**
	 * Retourne l'URI du document.
	 * @return L'URI du document sous forme de chaîne de caractères.
	 */
	public String getDocumentURI() {
		return documentURI;
	}
	
	/**
	 * URI du document sous forme de chaîne de caractères.
	 */
	protected String documentURI;
}

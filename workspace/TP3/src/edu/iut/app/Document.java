package edu.iut.app;

/**
 * Classe repr�sentant un document.
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
	 * Initialise un document dont l'URI est le nom en param�tre.
	 * @param documentURI
	 * 	L'URI du document sous forme de cha�ne de caract�res.
	 */
	public Document(String documentURI) {
		this.documentURI=documentURI;
	}
	
	/**
	 * Remplace l'URI du document par l'URI en param�tre.
	 * @param number
	 * 	L'URI du document sous forme de cha�ne de caract�res.
	 */
	public void setDocumentURI(String number) {
		this.documentURI = number;
	}
	
	/**
	 * Retourne l'URI du document.
	 * @return L'URI du document sous forme de cha�ne de caract�res.
	 */
	public String getDocumentURI() {
		return documentURI;
	}
	
	/**
	 * URI du document sous forme de cha�ne de caract�res.
	 */
	protected String documentURI;
}

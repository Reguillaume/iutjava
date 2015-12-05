package edu.iut.app;

/**
 * Classe représentant un document.
 * @author Guizmo
 *
 */
public class Document {
	
	public Document() {
		documentURI="";
	}
	public Document(String documentURI) {
		this.documentURI=documentURI;
	}
	
	public void setDocumentURI(String number) {
		this.documentURI = number;
	}
	public String getDocumentURI() {
		return documentURI;
	}
	
	protected String documentURI;
}

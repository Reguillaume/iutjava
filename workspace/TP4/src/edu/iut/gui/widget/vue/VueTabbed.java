package edu.iut.gui.widget.vue;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Classe regroupant tous les panels sous forme d'onglets.
 * @see VueTabNavigation
 * @see VueTabPerson
 * @see VueCreerClassroom
 * @see VueCreerDocument
 * @author Guizmo
 *
 */
public class VueTabbed extends JPanel {
	private JTabbedPane onglets;
	
	public VueTabbed() {
		onglets=new JTabbedPane();
		
		VueTabNavigation navigationTab=new VueTabNavigation(onglets);
		onglets.addTab("Navigation", navigationTab);
		
		VueTabPerson personTab=new VueTabPerson();
		onglets.addTab("Personne", personTab);
		
		VueCreerClassroom creerClassroomPanel=new VueCreerClassroom();
		onglets.addTab("Salle", creerClassroomPanel);
		
		VueCreerDocument creerDocumentPanel=new VueCreerDocument();
		onglets.addTab("Document", creerDocumentPanel);
		
		add(onglets);
	}

	public JTabbedPane getOnglets() {
		return onglets;
	}
}

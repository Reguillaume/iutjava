package edu.iut.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import edu.iut.app.Person;
import edu.iut.app.Person.PersonFunction;
import edu.iut.gui.widget.vue.VueTabPerson;
import edu.iut.gui.modele.ModelePerson;

/**
 * Contrôleur permettant de gérer le panel pour créer une personne.
 * @see VueTabPerson
 * @author Guizmo
 *
 */
public class ControlTabPerson implements ActionListener {
	VueTabPerson vue;
	
	public ControlTabPerson(VueTabPerson vue) {
		this.vue=vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Créer" :
			String msg;
			Person person=null;
			if(vue.getFonctionBox().getSelectedItem()==PersonFunction.JURY.toString()) {
				msg="Êtes-vous sûr de vouloir créer le jury "+vue.getNomField().getText()+" "+vue.getPrenomField().getText()+" ?";
				person=new Person(PersonFunction.JURY, vue.getNomField().getText(), vue.getPrenomField().getText(), vue.getEmailField().getText(), vue.getPhoneField().getText());
			}
			else {
				msg="Êtes-vous sûr de vouloir créer l'étudiant "+vue.getNomField().getText()+" "+vue.getPrenomField().getText()+" ?";
				person=new Person(PersonFunction.STUDENT, vue.getNomField().getText(), vue.getPrenomField().getText(), vue.getEmailField().getText(), vue.getPhoneField().getText());
			}
			if(JOptionPane.showConfirmDialog(null, msg, "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				ModelePerson.instance().add(person);
				vue.initialiserListe();
				vue.listePage();
				vue.nettoyerChampsCreer();
			}
			break;
		case "Ajouter" :
			vue.creerPage();
			break;
			
		case "Supprimer" :
			if(JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de supprimer "+vue.getPersonList().getSelectedValue()+" ?", "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) vue.supprimerSelectionListe();
			break;
			
		case "Effacer": 
			vue.nettoyerChampsCreer();
			break;
			
		case "<" :
			vue.listePage();
			break;
		
		case "Rechercher" :
			vue.rechercherListe();
			break;
		}		
	}
}

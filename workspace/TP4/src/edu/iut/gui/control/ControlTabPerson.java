package edu.iut.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import edu.iut.gui.widget.vue.VueTabPerson;
import edu.iut.gui.modele.ListePerson;
import edu.iut.gui.modele.ModelePerson;
import edu.iut.gui.modele.ModelePerson.PersonFunction;

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
			ModelePerson person=null;
			if(vue.getNomField().getText().isEmpty() || vue.getPrenomField().getText().isEmpty() || vue.getPhoneField().getText().isEmpty() || vue.getEmailField().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Il faut remplir tous les champs.", "Erreur", JOptionPane.OK_OPTION);
			}
			else {
				String prenom=vue.getPrenomField().getText().substring(0, 1).toUpperCase() + vue.getPrenomField().getText().substring(1);
				String nom=vue.getNomField().getText().toUpperCase();
				if(vue.getFonctionBox().getSelectedItem()==PersonFunction.JURY.toString()) {
					msg="Êtes-vous sûr de vouloir créer le jury "+nom+" "+prenom+" ?";
					person=new ModelePerson(PersonFunction.JURY, nom, prenom, vue.getEmailField().getText(), vue.getPhoneField().getText());
				}
				else {
					msg="Êtes-vous sûr de vouloir créer l'étudiant "+nom+" "+prenom+" ?";
					person=new ModelePerson(PersonFunction.STUDENT, nom, prenom, vue.getEmailField().getText(), vue.getPhoneField().getText());
				}
				if(JOptionPane.showConfirmDialog(null, msg, "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					ListePerson.instance().add(person);
					vue.initialiserListe();
					vue.listePage();
					vue.nettoyerChampsCreer();
				}
			}
			break;
		case "Ajouter" :
			vue.creerPage();
			break;
			
		case "Supprimer" :
			if(!vue.getPersonList().isSelectionEmpty()) {
				if(JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de supprimer "+vue.getPersonList().getSelectedValue()+" ?", "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) vue.supprimerSelectionListe();
			}
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

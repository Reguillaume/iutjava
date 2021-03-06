package edu.iut.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.PrimitiveIterator.OfDouble;

import javax.swing.JOptionPane;

import edu.iut.gui.modele.ModeleExamEvent;
import edu.iut.gui.modele.ListeExamEvent;
import edu.iut.gui.widget.vue.VueCreerExam;

/**
 * Contrôleur permettant de gérer le panel pour créer un examen.
 * @see VueCreerExam
 * @author Guizmo
 *
 */
public class ControlCreerExam implements ActionListener {
	private VueCreerExam vue;
	private static int modification=-1;
	
	public ControlCreerExam(VueCreerExam vue) {
		this.vue=vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Modifier" :
			if(!vue.getExamList().isSelectionEmpty()) {
				modification=vue.getExamList().getSelectedIndex();
				vue.showDocumentSelect(vue.getExamArray().get(modification).getDocuments());
				vue.showClassroomSelect(vue.getExamArray().get(modification).getClassroom());
				vue.showJurySelect(vue.getExamArray().get(modification).getJury());
				vue.showEtudiantSelect(vue.getExamArray().get(modification).getStudent());
			}
			break;
		case "Ajouter" :
			vue.showEtudiantSelect();
			break;
		case "Suivant" :
			switch(vue.getCurrentPanel()) {
			case "selectEtudiant" :
				if(modification!=-1) vue.showJurySelect(vue.getExamArray().get(modification).getJury());
				else vue.showJurySelect();
				break;
			case "selectJury" :
				if(modification!=-1) vue.showClassroomSelect(vue.getExamArray().get(modification).getClassroom());
				else vue.showClassroomSelect();
				break;
			case "selectClassroom" :
				if(modification!=-1) vue.showDocumentSelect(vue.getExamArray().get(modification).getDocuments());
				else vue.showDocumentSelect();
				break;
			}
			break;
		case "Retour" :
			switch(vue.getCurrentPanel()) {
			case "selectEtudiant" :
				if(modification!=-1) {
					if(JOptionPane.showConfirmDialog(null, "Les modifications ne seront pas enregistrées, êtes-vous sûr ?", "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
						modification=-1;
						vue.showExamList();
					}
				}
				else vue.showExamList();
				break;
			case "selectJury" :
				if(modification!=-1) vue.showEtudiantSelect(vue.getExamArray().get(modification).getStudent());
				else vue.showEtudiantSelect();
				break;
			case "selectClassroom" :
				if(modification!=-1) vue.showJurySelect(vue.getExamArray().get(modification).getJury());
				else vue.showJurySelect();
				break;
			case "selectDocument" :
				if(modification!=-1) vue.showClassroomSelect(vue.getExamArray().get(modification).getClassroom());
				else vue.showClassroomSelect();
				break;
			}
			break;
		case "<" :
			if(modification!=-1) {
				if(JOptionPane.showConfirmDialog(null, "Les modifications ne seront pas enregistrées, êtes-vous sûr ?", "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					modification=-1;
					vue.showExamList();
				}
			}
			else vue.showExamList();
			break;
		case ">>" :
			switch(vue.getCurrentPanel()) {
			case "selectJury" :
				vue.ajouterSelectJury();
				break;
			case "selectDocument" :
				vue.ajouterSelectDocument();
				break;
			}
			break;
		case "<<" :
			switch(vue.getCurrentPanel()) {
			case "selectJury" :
				vue.enleverSelectJury();
				break;
			case "selectDocument" :
				vue.enleverSelectDocument();
				break;
			}
			break;
		case "Supprimer" :
			if(!vue.getExamList().isSelectionEmpty()) {
				if(JOptionPane.showConfirmDialog(null, "Êtes vous sur de vouloir supprimer l'examen de "+vue.getExamList().getSelectedValue(), "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					vue.supprimerSelectionListe();
				}
			}
			break;
		case "Créer" :
			if(vue.getEtudiantList().isSelectionEmpty()) {
				JOptionPane.showMessageDialog(null, "Il faut selectionner un étudiant.", "Erreur", JOptionPane.OK_OPTION);
			}
			else if(vue.getSelectJury().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Il faut selectionner au moins un jury.", "Erreur", JOptionPane.OK_OPTION);
			}
			else if(vue.getClassroomList().isSelectionEmpty()) {
				JOptionPane.showMessageDialog(null, "Il faut selectionner une salle.", "Erreur", JOptionPane.OK_OPTION);
			}
			else if(vue.getSelectDocument().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Il faut selectionner au moins un document.", "Erreur", JOptionPane.OK_OPTION);
			}
			else {
				ModeleExamEvent exam=new ModeleExamEvent(new Date((Integer) vue.getDateSpinner().getValue(), vue.getMonthCombo().getSelectedIndex(), vue.getNumDaysCombo().getSelectedIndex(), (Integer) vue.getHeureCombo().getSelectedItem(), 0), vue.getSelectEtudiant(), vue.getSelectJury(), vue.getSelectClassroom(), vue.getSelectDocument());
				if(modification!=-1) {
					if(JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir modifier l'examen ?", "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
						ListeExamEvent.instance().remove(vue.getExamArray().get(modification));
						ListeExamEvent.instance().add(exam);
						vue.initialiserExamList();
						vue.showExamList();
					}
				}
				else if(JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir créer l'examen ?", "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					ListeExamEvent.instance().add(exam);
					vue.initialiserExamList();
					vue.showExamList();
				}
			}
			break;
		case "Rafraîchir" :
			switch(vue.getCurrentPanel()) {
			case "selectEtudiant" :
				vue.initialiserEtudiantList();
				break;
			case "selectJury" :
				vue.initialiserJuryList();
				break;
			case "selectClassroom" :
				vue.initialiserClassroomList();
				break;
			case "selectDocument" :
				vue.initialiserDocumentList();
				break;
			}
			break;
		case "Créer un étudiant" :
			vue.goCreerEtudiantTab();
			break;
		case "Créer un jury" :
			vue.goCreerJuryTab();
			break;
		case "Créer une salle" :
			vue.goCreerClassroomTab();
			break;
		case "Créer un document" :
			vue.goCreerDocumentTab();
			break;
		case "Rechercher" :
			switch(vue.getCurrentPanel()) {
			case "creer" :
				vue.rechercherExam();
				break;
			case "selectEtudiant" :
				vue.rechercherEtudiant();
				break;
			case "selectJury" :
				vue.rechercherJury();
				break;
			case "selectClassroom" :
				vue.rechercherClassroom();
				break;
			case "selectDocument" :
				vue.rechercherDocument();
				break;
			}
			break;
		}
	}

}

package edu.iut.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import edu.iut.app.ExamEvent;
import edu.iut.gui.widget.agenda.ModelAgenda;
import edu.iut.gui.widget.vue.VueCreerExam;

public class ControlCreerExam implements ActionListener {
	private VueCreerExam vue;
	
	public ControlCreerExam(VueCreerExam vue) {
		this.vue=vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Ajouter" :
			vue.showEtudiantSelect();
			break;
		case "Suivant" :
			switch(vue.getCurrentPanel()) {
			case "selectEtudiant" :
				vue.showJurySelect();
				break;
			case "selectJury" :
				vue.showClassroomSelect();
				break;
			case "selectClassroom" :
				vue.showDocumentSelect();
				break;
			}
			break;
		case "Retour" :
			switch(vue.getCurrentPanel()) {
			case "selectEtudiant" :
				vue.showExamList();
				break;
			case "selectJury" :
				vue.showEtudiantSelect();
				break;
			case "selectClassroom" :
				vue.showJurySelect();
				break;
			case "selectDocument" :
				vue.showClassroomSelect();
				break;
			}
			break;
		case "<" :
			vue.showExamList();
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
			vue.supprimerSelectionListe();
			break;
		case "Créer" :
			if(JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir créer l'examen ?", "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				vue.initialiserExamList();
				ExamEvent exam=new ExamEvent(new Date(), vue.getSelectEtudiant(), vue.getSelectJury(), vue.getSelectClassroom(), vue.getSelectDocument());
				ModelAgenda.instance().add(exam);
				vue.showExamList();
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
		}
	}

}

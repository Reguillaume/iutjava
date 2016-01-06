package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.VueNbStage;

/**
 * Classe controlant la vue "Etudiant"
 * @author guillaumemartinez
 *
 */
public class ControlVueStage implements ActionListener {
	private VueNbStage vue;
	
	public ControlVueStage(VueNbStage vue) {
		this.vue=vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Chercher" :
			vue.setTextResLabel("Il y a eu "+vue.getNbEtudiantSansStage()+" étudiant(s) sans stage en "+(Integer) vue.getAnneeSpinner().getValue()+".");
			break;
		}
	}

}

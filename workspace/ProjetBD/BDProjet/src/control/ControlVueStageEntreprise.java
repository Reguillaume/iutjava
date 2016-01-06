package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.VueNbStageEntreprise;

/**
 * Classe controlant la vue "Enteprise"
 * @author guillaumemartinez
 *
 */
public class ControlVueStageEntreprise implements ActionListener {
	private VueNbStageEntreprise vue;
	
	public ControlVueStageEntreprise(VueNbStageEntreprise vue) {
		this.vue=vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Chercher" :
			vue.setNbStagiaire();
			vue.setNbMoyStagiaire();
			vue.setStagiaire();
			vue.setTextDesc("Durant les "+(Integer) vue.getAnneeSpinner().getValue()+" dernières années :");
			break;
		}
	}

}

package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.VueNbStageEntreprise;

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
			break;
		}
	}

}

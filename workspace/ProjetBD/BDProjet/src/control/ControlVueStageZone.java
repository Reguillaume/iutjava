package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vue.VueNbStageParZone;

public class ControlVueStageZone implements ActionListener {
	private VueNbStageParZone vue;
	
	public ControlVueStageZone(VueNbStageParZone vue) {
		this.vue=vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Chercher" :
			vue.setTextResLabel("Il y a "+vue.getNbStage()+" stages.");
			break;
		}
	}
	
}

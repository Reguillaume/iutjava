package edu.iut.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import edu.iut.app.Document;
import edu.iut.gui.modele.ModeleDocument;
import edu.iut.gui.widget.vue.VueCreerDocument;

public class ControlCreerDocument implements ActionListener {
	private VueCreerDocument vue;
	
	public ControlCreerDocument(VueCreerDocument vue) {
		this.vue=vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Créer" :
			if(JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir créer le document "+vue.getUriField().getText()+" ?", "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				ModeleDocument.instance().add(new Document(vue.getUriField().getText()));
			}
			vue.nettoyerChamps();
			break;
		case "Effacer" :
			vue.nettoyerChamps();
			break;
		}
		
	}

	
}

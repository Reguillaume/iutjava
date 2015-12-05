package edu.iut.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import edu.iut.app.Document;
import edu.iut.gui.modele.ModeleDocument;
import edu.iut.gui.widget.vue.VueCreerDocument;

/**
 * Contrôleur permettant de gérer le panel pour créer un document.
 * @see VueCreerDocument
 * @author Guizmo
 *
 */
public class ControlCreerDocument implements ActionListener {
	private VueCreerDocument vue;
	
	public ControlCreerDocument(VueCreerDocument vue) {
		this.vue=vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "CrÃ©er" :
			if(JOptionPane.showConfirmDialog(null, "ÃŠtes-vous sÃ»r de vouloir crÃ©er le document "+vue.getUriField().getText()+" ?", "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
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

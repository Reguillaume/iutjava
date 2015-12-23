package edu.iut.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import edu.iut.gui.modele.ModeleDocument;
import edu.iut.gui.modele.ListeDocument;
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
		case "Créer" :
			if(vue.getUriField().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Il faut entrer l'URI d'un document.", "Erreur", JOptionPane.OK_OPTION);
			}
			else if(JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir créer le document "+vue.getUriField().getText()+" ?", "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				ListeDocument.instance().add(new ModeleDocument(vue.getUriField().getText()));
			}
			vue.nettoyerChamps();
			break;
		case "Effacer" :
			vue.nettoyerChamps();
			break;
		}
		
	}

	
}

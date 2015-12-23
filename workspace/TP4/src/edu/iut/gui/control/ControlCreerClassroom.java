package edu.iut.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import edu.iut.gui.widget.vue.VueCreerClassroom;
import edu.iut.gui.modele.ModeleClassroom;
import edu.iut.gui.modele.ListeClassroom;

/**
 * Contrôleur permettant de gérer le panel pour créer une salle de classe.
 * @see VueCreerClassroom
 * @author Guizmo
 *
 */
public class ControlCreerClassroom implements ActionListener {
	private VueCreerClassroom vue;
	
	public ControlCreerClassroom(VueCreerClassroom vue){
		this.vue=vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Créer" :
			if(vue.getNomField().getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Il faut entrer un nom de salle.", "Erreur", JOptionPane.OK_OPTION);
			}
			else if(JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir créer la salle "+vue.getNomField().getText()+" ?", "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				ListeClassroom.instance().add(new ModeleClassroom(vue.getNomField().getText()));
			}
			vue.nettoyerChamps();
			break;
		case "Effacer" :
			vue.nettoyerChamps();
			break;
		}
	}
}

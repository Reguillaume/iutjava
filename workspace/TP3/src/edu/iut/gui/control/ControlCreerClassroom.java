package edu.iut.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import edu.iut.app.Classroom;
import edu.iut.gui.widget.vue.VueCreerClassroom;
import edu.iut.gui.modele.ModeleClassroom;

/**
 * Contr�leur permettant de g�rer le panel pour cr�er une salle de classe.
 * @see VueCr�erClassroom
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
			if(JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir créer la salle "+vue.getNomField().getText()+" ?", "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				ModeleClassroom.instance().add(new Classroom(vue.getNomField().getText()));
			}
			vue.nettoyerChamps();
			break;
		case "Effacer" :
			vue.nettoyerChamps();
			break;
		}
	}
}

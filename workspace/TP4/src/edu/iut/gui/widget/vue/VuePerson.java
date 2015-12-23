package edu.iut.gui.widget.vue;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.iut.gui.modele.ModelePerson;

/**
 * Classe permettant d'afficher un jury ou un �tudiant.
 * @see ModelePerson
 * @author Guizmo
 *
 */
public class VuePerson extends JPanel {
	public VuePerson(ModelePerson p) {
		setLayout(new GridLayout(5, 2));
		add(new JLabel("fonction"));
		add(new JLabel(p.getFunction().name()));
		add(new JLabel("nom"));
		add(new JLabel(p.getLastname()));
		add(new JLabel("prénom"));
		add(new JLabel(p.getFirstname()));
		add(new JLabel("email"));
		add(new JLabel(p.getEmail()));
		add(new JLabel("téléphone"));
		add(new JLabel(p.getPhone()));
	}

}

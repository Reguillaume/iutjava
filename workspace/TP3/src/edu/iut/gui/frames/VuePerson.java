package edu.iut.gui.frames;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.iut.app.Person;

public class VuePerson extends JPanel {
	public VuePerson(Person p) {
		setLayout(new GridLayout(4, 2));
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

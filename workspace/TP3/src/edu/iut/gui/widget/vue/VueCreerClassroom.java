package edu.iut.gui.widget.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.iut.gui.control.ControlCreerClassroom;

public class VueCreerClassroom extends JPanel {
	private JTextField nomField;
	
	private JButton creerButton;
	private JButton effacerButton;
	
	public VueCreerClassroom() {
		JPanel classPanel=new JPanel();
		classPanel.setLayout(new BoxLayout(classPanel, BoxLayout.X_AXIS));
		classPanel.add(new JLabel("numero"));
		classPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		nomField=new JTextField();
		classPanel.add(nomField);
		
		JPanel buttonsPanel=new JPanel(new GridLayout(1, 2));
		creerButton=new JButton("Créer");
		effacerButton=new JButton("Effacer");
		buttonsPanel.add(creerButton);
		buttonsPanel.add(effacerButton);
		ControlCreerClassroom control=new ControlCreerClassroom(this);
		creerButton.addActionListener(control);
		effacerButton.addActionListener(control);
		
		setLayout(new BorderLayout());
		add(classPanel);
		add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	public JTextField getNomField() {
		return nomField;
	}

	public void nettoyerChamps() {
		nomField.setText("");
	}
}

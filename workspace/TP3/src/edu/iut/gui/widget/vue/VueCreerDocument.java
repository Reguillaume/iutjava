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

import edu.iut.gui.control.ControlCreerDocument;

public class VueCreerDocument extends JPanel {
	private JTextField uriField;
	private JButton creerButton;
	private JButton effacerButton;
	
	public VueCreerDocument() {
		JPanel docPanel=new JPanel();
		docPanel.setLayout(new BoxLayout(docPanel, BoxLayout.X_AXIS));
		uriField=new JTextField();
		docPanel.add(new JLabel("URI"));
		docPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		docPanel.add(uriField);
		
		
		JPanel buttonsPanel=new JPanel(new GridLayout(1, 2));
		creerButton=new JButton("Créer");
		effacerButton=new JButton("Effacer");
		buttonsPanel.add(creerButton);
		buttonsPanel.add(effacerButton);
		ControlCreerDocument control=new ControlCreerDocument(this);
		creerButton.addActionListener(control);
		effacerButton.addActionListener(control);
		
		setLayout(new BorderLayout());
		add(docPanel);
		add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	public void nettoyerChamps() {
		uriField.setText("");
	}
	
	public JTextField getUriField() {
		return uriField;
	}
}

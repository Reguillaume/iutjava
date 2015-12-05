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

/**
 * Classe permettant d'afficher des widgets pour créer un document.
 * @see Document
 * @author Guizmo
 *
 */
public class VueCreerDocument extends JPanel {
	/**
	 * Champ de texte pour entrer l'URI du document.
	 */
	private JTextField uriField;
	
	/**
	 * Bouton pour créer un document à partir du champ de texte.
	 */
	private JButton creerButton;
	
	/**
	 * Bouton pour effacer le contenu du champ de texte.
	 */
	private JButton effacerButton;
	
	/**
	 * Construit le panel.
	 */
	public VueCreerDocument() {
		JPanel docPanel=new JPanel();
		docPanel.setLayout(new BoxLayout(docPanel, BoxLayout.X_AXIS));
		uriField=new JTextField();
		docPanel.add(new JLabel("URI"));
		docPanel.add(Box.createRigidArea(new Dimension(10, 10)));
		docPanel.add(uriField);
		
		
		JPanel buttonsPanel=new JPanel(new GridLayout(1, 2));
		creerButton=new JButton("CrÃ©er");
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
	
	/**
	 * Fonction pour effacer le contenu du champ de texte.
	 */
	public void nettoyerChamps() {
		uriField.setText("");
	}
	
	/**
	 * Retourne le champ de texte.
	 * @return Champ de texte sous forme de JTextField.
	 */
	public JTextField getUriField() {
		return uriField;
	}
}

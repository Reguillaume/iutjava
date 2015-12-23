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

/**
 * Classe permettant d'afficher des widgets pour cr�er une salle de classe.
 * @see ModeleClassroom
 * @author Guizmo
 *
 */
public class VueCreerClassroom extends JPanel {
	/**
	 * Champ de texte pour entrer le nom de la salle.
	 */
	private JTextField nomField;
	
	/**
	 * Bouton pour cr�er la salle de classe � partir du champ de texte.
	 */
	private JButton creerButton;
	
	/**
	 * Bouton pour effacer le contenu du champ de texte.
	 */
	private JButton effacerButton;
	
	/**
	 * Construit le panel.
	 */
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
	
	/**
	 * Retourne le champ de texte.
	 * @return Champ de texte sous forme de JTextField.
	 */
	public JTextField getNomField() {
		return nomField;
	}
	
	/**
	 * Fonction permettant de vider le contenu du champ de texte.
	 */
	public void nettoyerChamps() {
		nomField.setText("");
	}
}

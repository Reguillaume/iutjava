package edu.iut.gui.widget.vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.security.spec.DSAGenParameterSpec;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.iut.gui.control.ControlTabPerson;
import edu.iut.gui.modele.ListePerson;
import edu.iut.gui.modele.ModelePerson;
import edu.iut.gui.modele.ModelePerson.PersonFunction;

/**
 * Classe permettant d'afficher la liste des �tudiants et la possibilit� d'en cr�er.
 * @author Guizmo
 *
 */
public class VueTabPerson extends JPanel {
	//Liste
	private JPanel personListePanel;
	private JTextField rechercherField;
	private JButton rechercherButton;
	private JButton creerListeButton;
	private JButton supprimerButton;
	private JList<String> personList=null;
	private DefaultListModel<String> personModel=new DefaultListModel<>();
	private CardLayout gestionnaire;
	private ArrayList<ModelePerson> personArray=new ArrayList<>();
	private JPanel descPanel;
	
	//Creer
	private JComboBox<String> fonctionBox;
	private JTextField nomField;
	private JTextField prenomField;
	private JTextField phoneField;
	private JTextField emailField;
	private JButton retourButton;
	private JButton creerButton;
	private JButton effacerButton;
	
	/**
	 * Construit le panel.
	 */
	public VueTabPerson() {
		//LISTE

		personListePanel=new JPanel(new BorderLayout()); 
		//Rechercher
		JPanel rechercherPanel=new JPanel(new BorderLayout());
		rechercherField=new JTextField();
		rechercherButton=new JButton("Rechercher");
		rechercherPanel.add(rechercherField, BorderLayout.CENTER);
		rechercherPanel.add(rechercherButton, BorderLayout.EAST);
		ControlTabPerson control=new ControlTabPerson(this);
		rechercherButton.addActionListener(control);
		//Liste
		JPanel listePanel=new JPanel(new BorderLayout());
		descPanel=new JPanel();
		personList=new JList<String>(personModel);
		initialiserListe();
		personList.addListSelectionListener(new ListSelectionListener() {		
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!personList.isSelectionEmpty()) {
					descPanel.removeAll();
					descPanel.add(new VuePerson(personArray.get(personList.getSelectedIndex())));
					listePanel.add(descPanel, BorderLayout.CENTER);	
					descPanel.revalidate();
					descPanel.repaint();
				}
			}
		});
		listePanel.add(personList, BorderLayout.WEST);
		//Boutons
		JPanel boutonsPanel=new JPanel(new GridLayout(1, 2));
		creerListeButton=new JButton("Ajouter");
		supprimerButton=new JButton("Supprimer");
		boutonsPanel.add(creerListeButton);
		boutonsPanel.add(supprimerButton);
		creerListeButton.addActionListener(control);
		supprimerButton.addActionListener(control);
		//Mise en forme Liste
		personListePanel.add(rechercherPanel, BorderLayout.NORTH);
		personListePanel.add(listePanel, BorderLayout.CENTER);
		personListePanel.add(boutonsPanel, BorderLayout.SOUTH);	
	
		//CREER
		
		JPanel creerPersonPanel=new JPanel(new BorderLayout());
		//Bouton '<'
		retourButton=new JButton("<");
		JPanel retourPanel=new JPanel(new BorderLayout());
		retourPanel.add(retourButton, BorderLayout.WEST);
		retourButton.addActionListener(control);
		//ComboBox
		JPanel comboPanel=new JPanel();
		DefaultComboBoxModel<String> fonctionModel=new DefaultComboBoxModel<String>();
		fonctionModel.addElement(PersonFunction.JURY.toString()); 
		fonctionModel.addElement(PersonFunction.STUDENT.toString());
		fonctionBox=new JComboBox<String>(fonctionModel);
		comboPanel.add(fonctionBox);
		//Autres
		JPanel autresPanel=new JPanel(new GridLayout(4,2));
		autresPanel.add(new JLabel("Nom"));
		nomField=new JTextField();
		autresPanel.add(nomField);
		autresPanel.add(new JLabel("Prénom"));
		prenomField=new JTextField();
		autresPanel.add(prenomField);
		autresPanel.add(new JLabel("Email"));
		emailField=new JTextField();
		autresPanel.add(emailField);
		autresPanel.add(new JLabel("Téléphone"));
		phoneField=new JTextField();
		autresPanel.add(phoneField);
		//Boutons
		JPanel buttonsPanel=new JPanel(new GridLayout(1, 2));
		creerButton=new JButton("Créer");
		effacerButton=new JButton("Effacer");
		buttonsPanel.add(creerButton);
		buttonsPanel.add(effacerButton);
		creerButton.addActionListener(control);
		effacerButton.addActionListener(control);
		//Mise en forme Creer
		JPanel formulairePanel=new JPanel(new BorderLayout());
		formulairePanel.add(comboPanel, BorderLayout.NORTH);
		formulairePanel.add(autresPanel, BorderLayout.CENTER);
		formulairePanel.add(buttonsPanel, BorderLayout.SOUTH);
		creerPersonPanel.add(retourPanel, BorderLayout.NORTH);
		creerPersonPanel.add(formulairePanel, BorderLayout.CENTER);
		
		//MISE EN FORME
		
		gestionnaire=new CardLayout();
		setLayout(gestionnaire);
		add(personListePanel, "liste");
		add(creerPersonPanel, "creer");
	}
	
	/**
	 * Initialise la liste d'�tudiants ou de jurys � partir des personnes stock�es dans l'application.
	 * @see ModelePerson
	 * @see ListePerson
	 */
	public void initialiserListe() {
		personModel.clear();
		personArray.clear();
		for(ModelePerson p : ListePerson.instance()) {
			personModel.addElement(p.getLastname()+" "+p.getFirstname());
			personArray.add(p);
		}
	}
	
	/**
	 * Efface le panel o� se trouve la description des personnes.
	 */
	public void effacerDesc() {
		descPanel.removeAll();
		descPanel.revalidate();
		descPanel.repaint();
	}
	
	/**
	 * Supprime la personne selectionn� dans la liste.
	 */
	public void supprimerSelectionListe() {
		ListePerson.instance().remove(personArray.get(personList.getSelectedIndex()));
		personModel.remove(personList.getSelectedIndex());
		effacerDesc();
	}
	
	/**
	 * Affiche la page pour cr�er une personne.
	 */
	public void creerPage() {
		gestionnaire.show(this, "creer");
	}
	
	/**
	 * Affiche le panel o� il y a la liste des personnes.
	 */
	public void listePage() {
		gestionnaire.show(this, "liste");
	}
	
	/**
	 * Efface le contenu de tous les champs du panel pour cr�er une personne.
	 */
	public void nettoyerChampsCreer() {
		nomField.setText("");
		prenomField.setText("");
		emailField.setText("");
		phoneField.setText("");
	}

	/**
	 * Modifie la liste de personne en fonction du contenu du champ de texte de recherche.
	 */
	public void rechercherListe() {
		effacerDesc();
		personArray.clear();
		personModel.clear();
		if(!(rechercherField.getText().equals(""))) {
			for(ModelePerson p : ListePerson.instance()) {
				if(p.getLastname().equals(rechercherField.getText().replace(" ", "").toUpperCase())) {
					personArray.add(p);
					personModel.addElement(p.getLastname()+" "+p.getFirstname());
				}
			}
		}
		else {
			initialiserListe();
		}
	}
	
	public JComboBox<String> getFonctionBox() {
		return fonctionBox;
	}

	public JTextField getNomField() {
		return nomField;
	}

	public JTextField getPrenomField() {
		return prenomField;
	}

	public JTextField getPhoneField() {
		return phoneField;
	}

	public JTextField getEmailField() {
		return emailField;
	}

	public JList<String> getPersonList() {
		return personList;
	}
}

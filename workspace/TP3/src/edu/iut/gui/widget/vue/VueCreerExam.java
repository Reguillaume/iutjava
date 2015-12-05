package edu.iut.gui.widget.vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicToolBarUI.DockingListener;

import edu.iut.app.Classroom;
import edu.iut.app.Document;
import edu.iut.app.ExamEvent;
import edu.iut.app.Person;
import edu.iut.app.Person.PersonFunction;
import edu.iut.gui.control.ControlCreerExam;
import edu.iut.gui.modele.ModeleClassroom;
import edu.iut.gui.modele.ModeleDocument;
import edu.iut.gui.modele.ModelePerson;
import edu.iut.gui.modele.ModeleExam;

/**
 * Classe permettant d'afficher des widgets pour créer et voir des examens.
 * @see ExamEvent
 * @author Guizmo
 *
 */
public class VueCreerExam extends JPanel {
	/**
	 * L'onglet parent.
	 */
	private JTabbedPane onglets;
	
	private DefaultListModel<String> examModel=new DefaultListModel<>();
	private JList<String> examList=new JList<String>(examModel);
	private ArrayList<ExamEvent> examArray=new ArrayList<ExamEvent>();
	
	//Liste
	private JPanel descPanel=new JPanel();
	private JTextField rechercherExamField=new JTextField();
	private JButton rechercherButton=new JButton("Rechercher");
	private JButton ajouterButton=new JButton("Ajouter");
	private JButton modifierButton=new JButton("Modifier");
	private JButton supprimerButton=new JButton("Supprimer");
	
	//Choix
	private JPanel buttonsSelectPanel=new JPanel(new GridLayout(5, 1));
	private JPanel rechercherPanel=new JPanel(new BorderLayout());
	private JPanel buttonsChoixPanel=new JPanel(new GridLayout(1, 2));
	private JPanel quitterPanel=new JPanel(new BorderLayout());
	private JButton quitterButton=new JButton("<");
	private JButton rafraichirButton=new JButton("RafraÃ®chir");
	private JButton suivantButton=new JButton("Suivant");
	private JButton retourButton=new JButton("Retour");
	private JButton creerButton=new JButton("CrÃ©er");
	private JButton ajouterSelectButton=new JButton(">>");
	private JButton enleverSelectButton=new JButton("<<");
	//Etudiant
	private JTextField rechercherEtudiantField=new JTextField();
	private DefaultListModel<String> etudiantModel=new DefaultListModel<String>();
	private JList<String> etudiantList=new JList<>(etudiantModel);
	private ArrayList<Person> etudiantArray=new ArrayList<>();
	//Jury
	private JTextField rechercherJuryField=new JTextField();
	private DefaultListModel<String> allJuryModel=new DefaultListModel<>();
	private JList<String> allJuryList=new JList<String>(allJuryModel);
	private ArrayList<Person> allJuryArray=new ArrayList<>();
	private DefaultListModel<String> selectJuryModel=new DefaultListModel<>();
	private JList<String> selectJuryList=new JList<String>(selectJuryModel);
	private ArrayList<Person> selectJuryArray=new ArrayList<>();
	//Classroom
	private JTextField rechercherClassroomField=new JTextField();
	private DefaultListModel<String> classroomModel=new DefaultListModel<>();
	private JList<String> classroomList=new JList<String>(classroomModel);
	private ArrayList<Classroom> classroomArray=new ArrayList<>();
	//Document
	private JTextField rechercherDocumentField=new JTextField();
	private DefaultListModel<String> allDocumentModel=new DefaultListModel<>();
	private JList<String> allDocumentList=new JList<String>(allDocumentModel);
	private ArrayList<Document> allDocumentArray=new ArrayList<>();
	private DefaultListModel<String> selectDocumentModel=new DefaultListModel<>();
	private JList<String> selectDocumentList=new JList<String>(selectDocumentModel);
	private ArrayList<Document> selectDocumentArray=new ArrayList<>();
	
	//CARDLAYOUT
	CardLayout gestionnaire=new CardLayout();
	
	/**
	 * Le nom du layout que le panel affiche sous forme de chaîne de caractères.
	 */
	private String currentPanel=null;
	
	/**
	 * Construit le panel.
	 * @param onglets
	 * 	L'onglet parent d'où se trouve le panel.
	 */
	public VueCreerExam(JTabbedPane onglets) {
		this.onglets=onglets;
		
		//LISTE
		
		JPanel examListePanel=new JPanel(new BorderLayout());
		//panel rechercher
		initialiserFormulairePanel("creer");
		//panel liste+desc
		JPanel listePanel=new JPanel(new GridLayout(1,2));
		listePanel.add(examList);
		listePanel.add(descPanel);
		examList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!examList.isSelectionEmpty()) {
					descPanel.removeAll();
					descPanel.add(new VueExam(examArray.get(examList.getSelectedIndex())));
				}
				else descPanel.removeAll();
				rafraichir();
			}
		});
		//Boutons
		JPanel buttonsPanel=new JPanel(new GridLayout(1, 3));
		buttonsPanel.add(ajouterButton);
		buttonsPanel.add(modifierButton);
		buttonsPanel.add(supprimerButton);
		ControlCreerExam control=new ControlCreerExam(this);
		ajouterButton.addActionListener(control);
		modifierButton.addActionListener(control);
		supprimerButton.addActionListener(control);
		quitterButton.addActionListener(control);
		suivantButton.addActionListener(control);
		retourButton.addActionListener(control);
		ajouterSelectButton.addActionListener(control);
		enleverSelectButton.addActionListener(control);
		//Mise en forme
		examListePanel.add(rechercherPanel, BorderLayout.NORTH);
		examListePanel.add(listePanel, BorderLayout.CENTER);
		examListePanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		//CHOISIR ETUDIANT
		JPanel choixEtudiantPanel=new JPanel(new BorderLayout());
		initialiserFormulairePanel("selectEtudiant");
		//Liste
		JPanel etudiantListPanel=new JPanel(new BorderLayout());
		etudiantListPanel.add(rechercherPanel, BorderLayout.NORTH);
		etudiantListPanel.add(etudiantList, BorderLayout.CENTER);
		JPanel creerEtudiantPanel=new JPanel(new BorderLayout());
		creerEtudiantPanel.add(new JLabel("Choisissez un Ã©tudiant"), BorderLayout.CENTER);
		JButton creerEtudiantButton=new JButton("CrÃ©er un Ã©tudiant");
		creerEtudiantButton.addActionListener(control);
		creerEtudiantPanel.add(creerEtudiantButton, BorderLayout.EAST);
		etudiantListPanel.add(creerEtudiantPanel, BorderLayout.SOUTH);
		//Mise en forme
		choixEtudiantPanel.add(quitterPanel, BorderLayout.NORTH);
		choixEtudiantPanel.add(etudiantListPanel, BorderLayout.CENTER);
		choixEtudiantPanel.add(buttonsChoixPanel, BorderLayout.SOUTH);
		
		//CHOISIR JURY
		JPanel choixJuryPanel=new JPanel(new BorderLayout());
		initialiserButtonsSelectPanel();
		initialiserFormulairePanel("selectJury");
		//Liste
		JPanel juryListPanel=new JPanel(new GridLayout(1, 3));
		JPanel allJuryListPanel=new JPanel(new BorderLayout());
		juryListPanel.add(allJuryListPanel);
		juryListPanel.add(buttonsSelectPanel);
		juryListPanel.add(selectJuryList);
		allJuryListPanel.add(rechercherPanel, BorderLayout.NORTH);
		allJuryListPanel.add(allJuryList, BorderLayout.CENTER);
		JPanel allJuryPanel=new JPanel(new BorderLayout());
		allJuryPanel.add(juryListPanel);
		JPanel creerJuryPanel=new JPanel(new BorderLayout());
		creerJuryPanel.add(new JLabel("Choisissez un ou plusieurs jurys"), BorderLayout.CENTER);
		JButton creerJuryButton=new JButton("CrÃ©er un jury");
		creerJuryButton.addActionListener(control);
		creerJuryPanel.add(creerJuryButton, BorderLayout.EAST);
		allJuryPanel.add(creerJuryPanel, BorderLayout.SOUTH);
		//Mise en forme
		choixJuryPanel.add(quitterPanel, BorderLayout.NORTH);
		choixJuryPanel.add(allJuryPanel, BorderLayout.CENTER);
		choixJuryPanel.add(buttonsChoixPanel, BorderLayout.SOUTH);
		
		//CHOISIR CLASSROOM
		JPanel choixClassroomPanel=new JPanel(new BorderLayout());
		initialiserFormulairePanel("selectClassroom");
		//Liste
		JPanel classroomListPanel=new JPanel(new BorderLayout());
		classroomListPanel.add(rechercherPanel, BorderLayout.NORTH);
		classroomListPanel.add(classroomList, BorderLayout.CENTER);
		JPanel creerClassroomPanel=new JPanel(new BorderLayout());
		creerClassroomPanel.add(new JLabel("Choisissez une salle"), BorderLayout.CENTER);
		JButton creerClassroomButton=new JButton("CrÃ©er une salle");
		creerClassroomButton.addActionListener(control);
		creerClassroomPanel.add(creerClassroomButton, BorderLayout.EAST);
		classroomListPanel.add(creerClassroomPanel, BorderLayout.SOUTH);
		//Mise en forme
		choixClassroomPanel.add(quitterPanel, BorderLayout.NORTH);
		choixClassroomPanel.add(classroomListPanel, BorderLayout.CENTER);
		choixClassroomPanel.add(buttonsChoixPanel, BorderLayout.SOUTH);
		
		//CHOISIR DOCUMENT
		JPanel choixDocumentPanel=new JPanel(new BorderLayout());
		initialiserButtonsSelectPanel();
		initialiserFormulairePanel("selectDocument");
		//Liste
		JPanel documentListPanel=new JPanel(new GridLayout(1, 3));
		JPanel allDocumentListPanel=new JPanel(new BorderLayout());
		documentListPanel.add(allDocumentListPanel);
		documentListPanel.add(buttonsSelectPanel);
		documentListPanel.add(selectDocumentList);
		allDocumentListPanel.add(rechercherPanel, BorderLayout.NORTH);
		allDocumentListPanel.add(allDocumentList, BorderLayout.CENTER);
		JPanel allDocumentPanel=new JPanel(new BorderLayout());
		allDocumentPanel.add(documentListPanel);
		JPanel creerDocumentPanel=new JPanel(new BorderLayout());
		creerDocumentPanel.add(new JLabel("Choisissez un ou plusieurs Documents"), BorderLayout.CENTER);
		JButton creerDocumentButton=new JButton("CrÃ©er un document");
		creerDocumentButton.addActionListener(control);
		creerDocumentPanel.add(creerDocumentButton, BorderLayout.EAST);
		allDocumentPanel.add(creerDocumentPanel, BorderLayout.SOUTH);
		//Boutons retour/creer
		JPanel buttonsFinPanel=new JPanel(new GridLayout(1, 2));
		buttonsFinPanel.add(retourButton);
		buttonsFinPanel.add(creerButton);
		creerButton.addActionListener(control);
		//Mise en forme
		choixDocumentPanel.add(quitterPanel, BorderLayout.NORTH);
		choixDocumentPanel.add(allDocumentPanel, BorderLayout.CENTER);
		choixDocumentPanel.add(buttonsFinPanel, BorderLayout.SOUTH);
		
		//CARDLAYOUT
		setLayout(gestionnaire);
		add(examListePanel, "creer");
		add(choixEtudiantPanel, "selectEtudiant");
		add(choixJuryPanel, "selectJury");
		add(choixClassroomPanel, "selectClassroom");
		add(choixDocumentPanel, "selectDocument");
		showExamList();
	}
	
	/**
	 * Initialise la liste des examens.
	 * @see ModeleExam
	 */
	public void initialiserExamList() {
		examArray.clear();
		examModel.clear();
		for(ExamEvent e : ModeleExam.instance()) {
			examModel.addElement(e.getStudent().getLastname()+" "+e.getStudent().getFirstname());
			examArray.add(e);
		}
	}
	
	/**
	 * Initialise la liste des étudiants.
	 * @see ModelePerson
	 */
	public void initialiserEtudiantList() {
		etudiantArray.clear();
		etudiantModel.clear();
		for(Person p : ModelePerson.instance()) {
			if(p.getFunction().equals(PersonFunction.STUDENT)) {
				etudiantModel.addElement(p.getLastname()+" "+p.getFirstname());
				etudiantArray.add(p);
			}
		}
	}
	
	/**
	 * Initialise la liste tous les jurys ainsi que la liste où se trouve les jurys selectionnés par l'utilisateur.
	 * @see ModelePerson
	 */
	public void initialiserJuryList() {
		allJuryArray.clear();
		allJuryModel.clear();
		selectJuryModel.clear();
		selectJuryArray.clear();
		for(Person p : ModelePerson.instance()) {
			if(p.getFunction().equals(PersonFunction.JURY)) {
				allJuryModel.addElement(p.getLastname()+" "+p.getFirstname());
				allJuryArray.add(p);
			}
		}
	}
	
	/**
	 * Initialise seulement la liste de tous les jurys de l'application.
	 * @see ModelePerson
	 */
	public void initialiserAllJuryList() {
		allJuryArray.clear();
		allJuryModel.clear();
		for(Person person : ModelePerson.instance()) {
			if(person.getFunction().equals(PersonFunction.JURY)) {
				allJuryArray.add(person);
				allJuryModel.addElement(person.getLastname()+" "+person.getFirstname());
			}
		}
	}
	
	/**
	 * Initialise la liste des salles de classe.
	 * @see ModeleClassroom
	 */
	public void initialiserClassroomList() {
		classroomArray.clear();
		classroomModel.clear();
		for(Classroom c : ModeleClassroom.instance()) {
			classroomArray.add(c);
			classroomModel.addElement(c.getClassRoomNumber());
		}
	}
	
	/**
	 * Initialise la liste de tous les documents ainsi que la liste où se trouve les documents selectionnés par l'utilisateur.
	 * @see Document
	 */
	public void initialiserDocumentList() {
		allDocumentModel.clear();
		allDocumentArray.clear();
		selectDocumentModel.clear();
		selectDocumentArray.clear();
		for(Document d : ModeleDocument.instance()) {
			allDocumentModel.addElement(d.getDocumentURI());
			allDocumentArray.add(d);
		}
	}
	
	/**
	 * Initialise seulement la liste où se trouve tous les documents de l'applications.
	 */
	public void initialiserAllDocumentList() {
		allDocumentArray.clear();
		allDocumentModel.clear();
		for(Document document : ModeleDocument.instance()) {
			allDocumentArray.add(document);
			allDocumentModel.addElement(document.getDocumentURI());
		}
	}
	
	/**
	 * Rafraichit le panel où se trouve la description des examens.
	 */
	public void rafraichir() {
		descPanel.revalidate();
		descPanel.repaint();
	}
	
	/**
	 * Fonction permettant de supprimer un examen selectionné dans la liste.
	 */
	public void supprimerSelectionListe() {
		if(!examList.isSelectionEmpty()) {
			ModeleExam.instance().remove(examArray.get(examList.getSelectedIndex()));
			examModel.remove(examList.getSelectedIndex());
			rafraichir();
		}
	}

	/**
	 * Fonction affichant la liste des examens.
	 */
	public void showExamList() {
		gestionnaire.show(this, "creer");
		currentPanel="creer";
		initialiserExamList();
	}
	
	/**
	 * Fonction affichant la liste des étudiants.
	 */
	public void showEtudiantSelect() {
		gestionnaire.show(this, "selectEtudiant");
		currentPanel="selectEtudiant";
		initialiserEtudiantList();
	}
	
	/**
	 * Fonction affichant la liste des étudiants pré-selectionnée par l'étudiant en paramètre.
	 * @param p
	 * 	Un étudiant sous forme de Person
	 * @see Person
	 */
	public void showEtudiantSelect(Person p) {
		gestionnaire.show(this, "selectEtudiant");
		currentPanel="selectEtudiant";
		initialiserEtudiantList();
		etudiantList.setSelectedIndex(etudiantArray.indexOf(p));
	}

	/**
	 * Fonction affichant la liste des jurys.
	 */
	public void showJurySelect() {
		gestionnaire.show(this, "selectJury");
		currentPanel="selectJury";
		initialiserJuryList();
	}
	
	/**
	 * Fonction affichant la liste des jurys pré-remplie par la liste de jurys en paramètre.
	 * @param p
	 * 	Liste de jurys sous forme d'un ArrayList de Person
	 * @see	Person
	 */
	public void showJurySelect(ArrayList<Person> p) {
		gestionnaire.show(this, "selectJury");
		currentPanel="selectJury";
		initialiserJuryList();
		for(Person person : p) {
			selectJuryModel.addElement(person.getLastname()+" "+person.getFirstname());
			selectJuryArray.add(person);
		}
	}
	
	/**
	 * Fonction affichant la liste des salles de classe.
	 */
	public void showClassroomSelect() {
		gestionnaire.show(this, "selectClassroom");
		currentPanel="selectClassroom";
		initialiserClassroomList();
	}
	
	/**
	 * Fonction affichant la liste des salles de classe pré-selectionné par la salle de classe en paramètre.
	 * @param c
	 * 	Une salle de classe sous forme de Classroom
	 * @see Classroom
	 */
	public void showClassroomSelect(Classroom c) {
		gestionnaire.show(this, "selectClassroom");
		currentPanel="selectClassroom";
		initialiserClassroomList();
		classroomList.setSelectedIndex(classroomArray.indexOf(c));
	}
	
	/** 
	 * Fonction affichant la liste des documents.
	 */
	public void showDocumentSelect() {
		gestionnaire.show(this, "selectDocument");
		currentPanel="selectDocument";
		initialiserDocumentList();
	}
	
	/**
	 * Fonction affichant la liste des documents pré-remplie par la liste de documents en paramètre.
	 * @param d
	 * 	Liste de documents sous forme d'un ArrayList de Document.
	 * @see Document
	 */
	public void showDocumentSelect(ArrayList<Document> d) {
		gestionnaire.show(this, "selectDocument");
		currentPanel="selectDocument";
		initialiserDocumentList();
		for(Document document : d) {
			selectDocumentModel.addElement(document.getDocumentURI());
			selectDocumentArray.add(document);
		}
	}
	
	/**
	 * Ajoute le jury selectionné à la lise des jurys de l'examen.
	 */
	public void ajouterSelectJury() {
		if(!allJuryList.isSelectionEmpty()) {
			selectJuryArray.add(allJuryArray.get(allJuryList.getSelectedIndex()));
			selectJuryModel.addElement(allJuryList.getSelectedValue());
		}
	}
	
	/**
	 * Enleve le jury selectionné de la liste des jurys de l'examen.
	 */
	public void enleverSelectJury() {
		if(!selectJuryList.isSelectionEmpty()) {
			selectJuryArray.remove(selectJuryList.getSelectedIndex());
			selectJuryModel.remove(selectJuryList.getSelectedIndex());
		}
	}
	
	/**
	 * Ajoute le document selectionné dans la liste des documents de l'examen.
	 */
	public void ajouterSelectDocument() {
		if(!allDocumentList.isSelectionEmpty()) {
			selectDocumentArray.add(allDocumentArray.get(allDocumentList.getSelectedIndex()));
			selectDocumentModel.addElement(allDocumentList.getSelectedValue());
		}
	}
	
	/**
	 * Enleve le document selectionné de la liste des documents de l'examen.
	 */
	public void enleverSelectDocument() {
		if(!selectDocumentList.isSelectionEmpty()) {
			selectDocumentArray.remove(selectDocumentList.getSelectedIndex());
			selectDocumentModel.remove(selectDocumentList.getSelectedIndex());
		}
	}
	
	/**
	 * Fonction initialisant tous les boutons et panel du formulaire pour créer un examen.
	 * @param panel
	 */
	public void initialiserFormulairePanel(String panel) {
		retourButton=new JButton("Retour");
		suivantButton=new JButton("Suivant");
		quitterButton=new JButton("<");
		rafraichirButton=new JButton("RafraÃ®chir");
		quitterPanel=new JPanel(new BorderLayout());
		quitterPanel.add(quitterButton, BorderLayout.WEST);
		quitterPanel.add(rafraichirButton, BorderLayout.EAST);
		
		buttonsChoixPanel=new JPanel(new GridLayout(1, 2));
		buttonsChoixPanel.add(retourButton);
		buttonsChoixPanel.add(suivantButton);
		
		rechercherButton=new JButton("Rechercher");
		rechercherPanel=new JPanel(new BorderLayout());
		rechercherPanel.add(rechercherButton, BorderLayout.EAST);
		
		ControlCreerExam control=new ControlCreerExam(this);
		retourButton.addActionListener(control);
		suivantButton.addActionListener(control);
		quitterButton.addActionListener(control);
		rechercherButton.addActionListener(control);
		rafraichirButton.addActionListener(control);
		
		switch(panel) {
		case "creer" :
			rechercherPanel.add(rechercherExamField, BorderLayout.CENTER);
			break;
		case "selectEtudiant" :
			rechercherPanel.add(rechercherEtudiantField, BorderLayout.CENTER);
			break;
		case "selectJury" :
			rechercherPanel.add(rechercherJuryField, BorderLayout.CENTER);
			break;
		case "selectClassroom" :
			rechercherPanel.add(rechercherClassroomField, BorderLayout.CENTER);
			break;
		case "selectDocument" :
			rechercherPanel.add(rechercherDocumentField, BorderLayout.CENTER);
			break;
		}
	}
	
	/**
	 * Fonction initialisant tous les boutons pour selectionner les jurys ou documents de l'examen.
	 */
	public void initialiserButtonsSelectPanel() {
		buttonsSelectPanel=new JPanel(new GridLayout(5,1));
		ajouterSelectButton=new JButton(">>");
		enleverSelectButton=new JButton("<<");
		
		buttonsSelectPanel.add(Box.createRigidArea(new Dimension(20,20)));
		buttonsSelectPanel.add(ajouterSelectButton);
		buttonsSelectPanel.add(Box.createRigidArea(new Dimension(20,20)));
		buttonsSelectPanel.add(enleverSelectButton);
		buttonsSelectPanel.add(Box.createRigidArea(new Dimension(20,20)));
		
		ControlCreerExam control=new ControlCreerExam(this);
		ajouterSelectButton.addActionListener(control);
		enleverSelectButton.addActionListener(control);
	}
	
	/**
	 * Fonction renvoyant à la page pour créer un étudiant.
	 */
	public void goCreerEtudiantTab() {
		onglets.setSelectedIndex(1);
		((VueTabPerson) onglets.getSelectedComponent()).getFonctionBox().setSelectedIndex(1);;
		((VueTabPerson) onglets.getSelectedComponent()).creerPage();
	}
	
	/**
	 * Fonction renvoyant à la page pour créer un jury.
	 */
	public void goCreerJuryTab() {
		onglets.setSelectedIndex(1);
		((VueTabPerson) onglets.getSelectedComponent()).getFonctionBox().setSelectedIndex(0);;
		((VueTabPerson) onglets.getSelectedComponent()).creerPage();
	}
	
	/**
	 * Fonction renvoyant à la page pour créer une salle de classe.
	 */
	public void goCreerClassroomTab() {
		onglets.setSelectedIndex(2);
	}
	
	/**
	 * Fonction renvoyant à la page pour créer un document.
	 */
	public void goCreerDocumentTab() {
		onglets.setSelectedIndex(3);
	}
	
	/**
	 * Fonction changeant la liste des examens en fonction du champ de recherche.
	 */
	public void rechercherExam() {
		if(!rechercherExamField.getText().replace(" ", "").equals("")) {
			examArray.clear();
			examModel.clear();
			for(ExamEvent event : ModeleExam.instance()) {
				if(rechercherExamField.getText().replace(" " , "").equals(event.getStudent().getLastname())) {
					examArray.add(event);
					examModel.addElement(event.getStudent().getLastname()+" "+event.getStudent().getFirstname());
				}
			}
		}
		else {
			descPanel.removeAll();
			rafraichir();
			initialiserExamList();
		}
	}
	
	/**
	 * Fonction changeant la liste des étudiants en fonction du champ de recherche.
	 */
	public void rechercherEtudiant() {
		if(!rechercherEtudiantField.getText().replace(" ", "").equals("")) {
			etudiantArray.clear();
			etudiantModel.clear();
			for(Person person : ModelePerson.instance()) {
				if(rechercherEtudiantField.getText().replace(" " , "").equals(person.getLastname()) && person.getFunction().equals(PersonFunction.STUDENT)) {
					etudiantArray.add(person);
					etudiantModel.addElement(person.getLastname()+" "+person.getFirstname());
				}
			}
		}
		else {
			initialiserEtudiantList();
		}
	}
	
	/**
	 * Fonction changeant la liste des jurys en fonction du champ de recherche.
	 */
	public void rechercherJury() {
		if(!rechercherJuryField.getText().replace(" ", "").equals("")) {
			allJuryArray.clear();
			allJuryModel.clear();
			for(Person person : ModelePerson.instance()) {
				if(rechercherJuryField.getText().replace(" " , "").equals(person.getLastname()) && person.getFunction().equals(PersonFunction.JURY)) {
					allJuryArray.add(person);
					allJuryModel.addElement(person.getLastname()+" "+person.getFirstname());
				}
			}
		}
		else {
			initialiserAllJuryList();
		}
	}
	
	/**
	 * Fonction changeant la liste des salles de classe en fonction du champ de recherche.
	 */
	public void rechercherClassroom() {
		if(!rechercherClassroomField.getText().replace(" ", "").equals("")) {
			classroomArray.clear();
			classroomModel.clear();
			for(Classroom classroom : ModeleClassroom.instance()) {
				if(rechercherClassroomField.getText().replace(" " , "").equals(classroom.getClassRoomNumber())) {
					classroomArray.add(classroom);
					classroomModel.addElement(classroom.getClassRoomNumber());
				}
			}
		}
		else {
			initialiserClassroomList();
		}
	}
	
	/**
	 * Fonction changeant la liste des documents en fonction du champ de recherche.
	 */
	public void rechercherDocument() {
		if(!rechercherDocumentField.getText().replace(" ", "").equals("")) {
			allDocumentArray.clear();
			allDocumentModel.clear();
			for(Document document : ModeleDocument.instance()) {
				if(rechercherDocumentField.getText().replace(" " , "").equals(document.getDocumentURI())) {
					allDocumentArray.add(document);
					allDocumentModel.addElement(document.getDocumentURI());
				}
			}
		}
		else {
			initialiserAllDocumentList();
		}
	}
	
	/**
	 * Retourne l'étudiant selectionné.
	 * @return
	 * 	L'étudiant selectionné sous forme de Person.
	 * @see Person
	 */
	public Person getSelectEtudiant() {
		return etudiantArray.get(etudiantList.getSelectedIndex());
	}
	
	/**
	 * Retourne la liste des jurys selectionnés.
	 * @return Liste des jurys selectionnés sous forme d'un ArrayList de Person.
	 * @see Person
	 */
	public ArrayList<Person> getSelectJury() {
		return selectJuryArray;
	}
	
	/**
	 * Retourne la salle de classe selectionnée.
	 * @return Salle de classe selectionnée sous forme de Classroom.
	 * @see Classroom
	 */
	public Classroom getSelectClassroom() {
		return classroomArray.get(classroomList.getSelectedIndex());
	}
	
	/**
	 * Retourne la liste des documents selectionnés.
	 * @return Liste de documents selectionnés sous forme d'un ArrayList de Document.
	 * @see Document
	 */
	public ArrayList<Document> getSelectDocument() {
		return selectDocumentArray;
	}
	
	/**
	 * Retourne la liste des examens.
	 * @return Liste des examens sous forme de JList de chaîne de caractères.
	 */
	public JList<String> getExamList() {
		return examList;
	}
	
	/**
	 * Retourne le layout que le panel affiche.
	 * @return Layout que le panel affiche sous forme de chaîne de caractères.
	 */
	public String getCurrentPanel() {
		return currentPanel;
	}

	public JButton getQuitterButton() {
		return quitterButton;
	}
	
	/**
	 * Retourne la liste des examens.
	 * @return Liste des examens sous forme d'un ArrayList de ExamEvent.
	 * @see ExamEvent
	 */
	public ArrayList<ExamEvent> getExamArray() {
		return examArray;
	}

}

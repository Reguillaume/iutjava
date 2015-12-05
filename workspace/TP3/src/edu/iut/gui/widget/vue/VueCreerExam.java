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

public class VueCreerExam extends JPanel {
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
	private JButton rafraichirButton=new JButton("Rafraîchir");
	private JButton suivantButton=new JButton("Suivant");
	private JButton retourButton=new JButton("Retour");
	private JButton creerButton=new JButton("Créer");
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
	
	private String currentPanel=null;
	
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
		creerEtudiantPanel.add(new JLabel("Choisissez un étudiant"), BorderLayout.CENTER);
		JButton creerEtudiantButton=new JButton("Créer un étudiant");
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
		JButton creerJuryButton=new JButton("Créer un jury");
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
		JButton creerClassroomButton=new JButton("Créer une salle");
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
		JButton creerDocumentButton=new JButton("Créer un document");
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
	
	public void initialiserExamList() {
		examArray.clear();
		examModel.clear();
		for(ExamEvent e : ModeleExam.instance()) {
			examModel.addElement(e.getStudent().getLastname()+" "+e.getStudent().getFirstname());
			examArray.add(e);
		}
	}
	
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
	
	public void initialiserClassroomList() {
		classroomArray.clear();
		classroomModel.clear();
		for(Classroom c : ModeleClassroom.instance()) {
			classroomArray.add(c);
			classroomModel.addElement(c.getClassRoomNumber());
		}
	}
	
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
	
	public void initialiserAllDocumentList() {
		allDocumentArray.clear();
		allDocumentModel.clear();
		for(Document document : ModeleDocument.instance()) {
			allDocumentArray.add(document);
			allDocumentModel.addElement(document.getDocumentURI());
		}
	}
	
	public void rafraichir() {
		descPanel.revalidate();
		descPanel.repaint();
	}
	
	public void supprimerSelectionListe() {
		if(!examList.isSelectionEmpty()) {
			ModeleExam.instance().remove(examArray.get(examList.getSelectedIndex()));
			examModel.remove(examList.getSelectedIndex());
			rafraichir();
		}
	}

	public void showExamList() {
		gestionnaire.show(this, "creer");
		currentPanel="creer";
		initialiserExamList();
	}
	
	public void showEtudiantSelect() {
		gestionnaire.show(this, "selectEtudiant");
		currentPanel="selectEtudiant";
		initialiserEtudiantList();
	}
	
	public void showEtudiantSelect(Person p) {
		gestionnaire.show(this, "selectEtudiant");
		currentPanel="selectEtudiant";
		initialiserEtudiantList();
		etudiantList.setSelectedIndex(etudiantArray.indexOf(p));
	}

	public void showJurySelect() {
		gestionnaire.show(this, "selectJury");
		currentPanel="selectJury";
		initialiserJuryList();
	}
	
	public void showJurySelect(ArrayList<Person> p) {
		gestionnaire.show(this, "selectJury");
		currentPanel="selectJury";
		initialiserJuryList();
		for(Person person : p) {
			selectJuryModel.addElement(person.getLastname()+" "+person.getFirstname());
			selectJuryArray.add(person);
		}
	}
	
	public void showClassroomSelect() {
		gestionnaire.show(this, "selectClassroom");
		currentPanel="selectClassroom";
		initialiserClassroomList();
	}
	
	public void showClassroomSelect(Classroom c) {
		gestionnaire.show(this, "selectClassroom");
		currentPanel="selectClassroom";
		initialiserClassroomList();
		classroomList.setSelectedIndex(classroomArray.indexOf(c));
	}
	
	public void showDocumentSelect() {
		gestionnaire.show(this, "selectDocument");
		currentPanel="selectDocument";
		initialiserDocumentList();
	}
	
	public void showDocumentSelect(ArrayList<Document> d) {
		gestionnaire.show(this, "selectDocument");
		currentPanel="selectDocument";
		initialiserDocumentList();
		for(Document document : d) {
			selectDocumentModel.addElement(document.getDocumentURI());
			selectDocumentArray.add(document);
		}
	}
	
	public void ajouterSelectJury() {
		if(!allJuryList.isSelectionEmpty()) {
			selectJuryArray.add(allJuryArray.get(allJuryList.getSelectedIndex()));
			selectJuryModel.addElement(allJuryList.getSelectedValue());
		}
	}
	
	public void enleverSelectJury() {
		if(!selectJuryList.isSelectionEmpty()) {
			selectJuryArray.remove(selectJuryList.getSelectedIndex());
			selectJuryModel.remove(selectJuryList.getSelectedIndex());
		}
	}
	
	public void ajouterSelectDocument() {
		if(!allDocumentList.isSelectionEmpty()) {
			selectDocumentArray.add(allDocumentArray.get(allDocumentList.getSelectedIndex()));
			selectDocumentModel.addElement(allDocumentList.getSelectedValue());
		}
	}
	
	public void enleverSelectDocument() {
		if(!selectDocumentList.isSelectionEmpty()) {
			selectDocumentArray.remove(selectDocumentList.getSelectedIndex());
			selectDocumentModel.remove(selectDocumentList.getSelectedIndex());
		}
	}
	
	public void initialiserFormulairePanel(String panel) {
		retourButton=new JButton("Retour");
		suivantButton=new JButton("Suivant");
		quitterButton=new JButton("<");
		rafraichirButton=new JButton("Rafraîchir");
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
	
	public void goCreerEtudiantTab() {
		onglets.setSelectedIndex(1);
		((VueTabPerson) onglets.getSelectedComponent()).getFonctionBox().setSelectedIndex(1);;
		((VueTabPerson) onglets.getSelectedComponent()).creerPage();
	}
	
	public void goCreerJuryTab() {
		onglets.setSelectedIndex(1);
		((VueTabPerson) onglets.getSelectedComponent()).getFonctionBox().setSelectedIndex(0);;
		((VueTabPerson) onglets.getSelectedComponent()).creerPage();
	}
	
	public void goCreerClassroomTab() {
		onglets.setSelectedIndex(2);
	}
	
	public void goCreerDocumentTab() {
		onglets.setSelectedIndex(3);
	}
	
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
	
	public Person getSelectEtudiant() {
		return etudiantArray.get(etudiantList.getSelectedIndex());
	}
	
	public ArrayList<Person> getSelectJury() {
		return selectJuryArray;
	}
	
	public Classroom getSelectClassroom() {
		return classroomArray.get(classroomList.getSelectedIndex());
	}
	
	public ArrayList<Document> getSelectDocument() {
		return selectDocumentArray;
	}
	
	public JList<String> getExamList() {
		return examList;
	}
	
	public String getCurrentPanel() {
		return currentPanel;
	}

	public JButton getQuitterButton() {
		return quitterButton;
	}

	public void setQuitterButton(JButton quitterButton) {
		this.quitterButton = quitterButton;
	}
	
	public ArrayList<ExamEvent> getExamArray() {
		return examArray;
	}

}

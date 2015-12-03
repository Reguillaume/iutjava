package edu.iut.gui.widget.vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.iut.app.Document;
import edu.iut.app.ExamEvent;
import edu.iut.app.Person;

public class VueExam extends JPanel {	
	public VueExam(ExamEvent exam) {
		//DATE
		JPanel dateBorderPanel=new JPanel(new BorderLayout());
		dateBorderPanel.add(new JLabel("DATE"), BorderLayout.NORTH);
		JPanel dateGridPanel=new JPanel(new GridLayout(1, 2));
		dateGridPanel.add(new JLabel("date"));
		dateGridPanel.add(new JLabel(exam.getExamDate().toString()));
		dateBorderPanel.add(dateGridPanel, BorderLayout.CENTER);
		
		//STUDENT
		JPanel studentBorderPanel=new JPanel(new BorderLayout());
		studentBorderPanel.add(new JLabel("ETUDIANT"), BorderLayout.NORTH);
		studentBorderPanel.add(new VuePerson(exam.getStudent()), BorderLayout.CENTER);
		
		//JURYS
		JPanel juryBorderPanel=new JPanel(new BorderLayout());
		juryBorderPanel.add(new JLabel("JURYS"), BorderLayout.NORTH);
		JPanel juryGridPanel=new JPanel(new GridLayout(exam.getJury().size(), 1, 5, 5));
		for(Person j : exam.getJury()) juryGridPanel.add(new VuePerson(j));
		juryBorderPanel.add(juryGridPanel, BorderLayout.CENTER);
		
		//CLASSROOM
		JPanel classBorderPanel=new JPanel(new BorderLayout());
		classBorderPanel.add(new JLabel("SALLE"), BorderLayout.NORTH);
		JPanel classGridPanel=new JPanel(new GridLayout(1, 2));
		classGridPanel.add(new JLabel("salle"));
		classGridPanel.add(new JLabel(exam.getClassroom().getClassRoomNumber()));
		classBorderPanel.add(classGridPanel, BorderLayout.CENTER);
		
		//DOC
		JPanel docBorderPanel=new JPanel(new BorderLayout());
		docBorderPanel.add(new JLabel("DOCUMENTS"), BorderLayout.NORTH);
		JPanel docGridPanel=new JPanel(new GridLayout(exam.getDocuments().size(), 1));
		for(Document d : exam.getDocuments()) docGridPanel.add(new JLabel(d.getDocumentURI()));
		docBorderPanel.add(docGridPanel, BorderLayout.CENTER);
		
		//MISE EN FORME
		setLayout(new GridLayout(5, 1, 10, 10));
		add(dateBorderPanel);
		add(studentBorderPanel);
		add(juryBorderPanel);
		add(classBorderPanel);
		add(docBorderPanel);
	}
}

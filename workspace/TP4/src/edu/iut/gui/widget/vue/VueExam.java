package edu.iut.gui.widget.vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.iut.gui.modele.ModeleDocument;
import edu.iut.gui.modele.ModeleExamEvent;
import edu.iut.gui.modele.ModelePerson;

/**
 * Classe permettant d'afficher un examen.
 * @see ModeleExamEvent
 * @author Guizmo
 *
 */
public class VueExam extends JPanel {	
	public VueExam(ModeleExamEvent exam) {
		//DATE
		JPanel dateBorderPanel=new JPanel(new BorderLayout());
		dateBorderPanel.add(new JLabel("DATE"), BorderLayout.NORTH);
		JPanel dateGridPanel=new JPanel(new GridLayout(1, 2));
		dateGridPanel.add(new JLabel("date"));
		dateGridPanel.add(new JLabel(String.valueOf(exam.getExamDate().getDate()+1)+"/"+String.valueOf(exam.getExamDate().getMonth()+1)+"/"+exam.getExamDate().getYear()+" à "+exam.getExamDate().getHours()+"h"));
		dateBorderPanel.add(dateGridPanel, BorderLayout.CENTER);
		
		//STUDENT
		JPanel studentBorderPanel=new JPanel(new BorderLayout());
		studentBorderPanel.add(new JLabel("ETUDIANT"), BorderLayout.NORTH);
		studentBorderPanel.add(new VuePerson(exam.getStudent()), BorderLayout.CENTER);
		
		//JURYS
		JPanel juryBorderPanel=new JPanel(new BorderLayout());
		juryBorderPanel.add(new JLabel("JURYS"), BorderLayout.NORTH);
		JPanel juryGridPanel=new JPanel(new GridLayout(exam.getJury().size(), 1));
		for(ModelePerson j : exam.getJury()) juryGridPanel.add(new VuePerson(j));
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
		for(ModeleDocument d : exam.getDocuments()) docGridPanel.add(new JLabel(d.getDocumentURI()));
		docBorderPanel.add(docGridPanel, BorderLayout.CENTER);
		
		//MISE EN FORME
		setLayout(new GridLayout(5, 1));
		add(dateBorderPanel);
		add(studentBorderPanel);
		add(juryBorderPanel);
		add(classBorderPanel);
		add(docBorderPanel);
	}
}

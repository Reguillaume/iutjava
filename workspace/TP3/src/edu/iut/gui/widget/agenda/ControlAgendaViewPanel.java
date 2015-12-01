package edu.iut.gui.widget.agenda;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.iut.app.ApplicationSession;
import edu.iut.app.ExamEvent;
import edu.iut.gui.frames.VueExam;

public class ControlAgendaViewPanel extends JPanel {

	CardLayout agendaViewLayout;
	JPanel contentPane;
	
	int selectedYear;
	int selectedMonth;
	int selectedDay;
	
	public ControlAgendaViewPanel(CardLayout layerLayout, final JPanel contentPane) {

		//Spinner+Combo
		this.agendaViewLayout = layerLayout;
		this.contentPane = contentPane;
		SpinnerNumberModel spinnerModelDate=new SpinnerNumberModel(2015, 2010, 2020, 1);
		JSpinner spinnerDate=new JSpinner(spinnerModelDate);
		
		JComboBox<String> comboDays=new JComboBox<>(ApplicationSession.instance().getDays());
		
		JComboBox<String> comboMonth=new JComboBox<>(ApplicationSession.instance().getMonths());

		GregorianCalendar cal=new GregorianCalendar();
		cal.setTime(new Date());
		int todayDay=cal.get(Calendar.DAY_OF_WEEK);
		if(todayDay==1) comboDays.setSelectedIndex(6);
		else comboDays.setSelectedIndex(todayDay-2);
		
		comboMonth.setSelectedIndex(cal.get(Calendar.MONTH));
		
		setLayout(new BorderLayout());
		
		JPanel controlPanel=new JPanel(new GridLayout(1, 3));
		controlPanel.add(comboDays);
		controlPanel.add(comboMonth);
		controlPanel.add(spinnerDate);
		this.add(controlPanel, BorderLayout.NORTH);
		
		//ExamEvent+List
		JPanel gridExam=new JPanel(new BorderLayout());
		
		ArrayList<ExamEvent> examModel=new ArrayList<>();
		DefaultListModel<String> examListModel=new DefaultListModel<>();
		for(ExamEvent e : ModelAgenda.instance()) {
			examModel.add(e);
			examListModel.addElement(e.getStudent().getLastname().toUpperCase()+" "+e.getStudent().getFirstname());
		}
		JList<String> examList=new JList<>(examListModel);
		gridExam.add(examList, BorderLayout.WEST);
		examList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!examList.isSelectionEmpty()) {
					gridExam.add(new VueExam(examModel.get(examList.getSelectedIndex())), BorderLayout.CENTER);
					gridExam.repaint();
					gridExam.revalidate();
				}
			}
		});
		this.add(gridExam, BorderLayout.CENTER);
		
		//Boutons
		JPanel buttonsPanel=new JPanel(new GridLayout(1, 2));
		JButton supprimerButton=new JButton("Supprimer");
		supprimerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!examList.isSelectionEmpty()) {
					if(JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer l'examen de "+examList.getSelectedValue()+" ?", "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
						examModel.remove(examList.getSelectedIndex());
						ModelAgenda.instance().remove(examList.getSelectedIndex());
						examListModel.remove(examList.getSelectedIndex());
						examList.setModel(examListModel);
						gridExam.add(new JPanel(), BorderLayout.CENTER);
						gridExam.repaint();
						gridExam.revalidate();
					}
				}
			}
		});
		JButton creerButton=new JButton("Créer");
		creerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,  "La fonction 'Créer' n'est pas encore gérée.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		buttonsPanel.add(creerButton);
		buttonsPanel.add(supprimerButton);
		this.add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	public int getYear() {
		return selectedYear;
	}
	public int getMonth() {
		return selectedMonth;
	}
	public int getDay() {
		return selectedDay;
	}
	
}

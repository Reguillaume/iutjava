package edu.iut.gui.widget.vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;

import edu.iut.app.ApplicationSession;

public class VueTabNavigation extends JPanel {
	private JComboBox<String> daysCombo;
	private JComboBox<String> monthCombo;
	private JSpinner dateSpinner;
	private JComboBox<Integer> numDaysCombo;
	
	public VueTabNavigation(JTabbedPane onglets) {
		//Spinner+ComboBox
		SpinnerNumberModel spinnerModelDate=new SpinnerNumberModel(2015, 2010, 2020, 1);
		dateSpinner=new JSpinner(spinnerModelDate);
		
		daysCombo=new JComboBox<>(ApplicationSession.instance().getDays());
		
		monthCombo=new JComboBox<>(ApplicationSession.instance().getMonths());

		GregorianCalendar cal=new GregorianCalendar();
		cal.setTime(new Date());
		int todayDay=cal.get(Calendar.DAY_OF_WEEK);
		if(todayDay==1) daysCombo.setSelectedIndex(6);
		else daysCombo.setSelectedIndex(todayDay-2);
		
		monthCombo.setSelectedIndex(cal.get(Calendar.MONTH));
		
		DefaultComboBoxModel<Integer> numDaysModel=new DefaultComboBoxModel<>();
		for(int i=1; i<=cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			numDaysModel.addElement(i);
		}
		numDaysCombo=new JComboBox<Integer>(numDaysModel);
		
		setLayout(new BorderLayout());
		
		JPanel controlPanel=new JPanel(new GridLayout(1, 4));
		controlPanel.add(numDaysCombo);
		controlPanel.add(daysCombo);
		controlPanel.add(monthCombo);
		controlPanel.add(dateSpinner);
		this.add(controlPanel, BorderLayout.NORTH);
		
		VueCreerExam creerExamPanel=new VueCreerExam(onglets);
		add(creerExamPanel);
	}
}
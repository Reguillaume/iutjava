package edu.iut.gui.widget.vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import edu.iut.app.ApplicationSession;

/**
 * Classe permettant d'afficher des widgets pour selectionner un jour et afficher la liste des examens.
 * @see VueCreerExam
 * @author Guizmo
 *
 */
public class VueTabNavigation extends JPanel {
	private JComboBox<String> monthCombo;
	private JSpinner dateSpinner;
	private JComboBox<Integer> numDaysCombo;
	private DefaultComboBoxModel<Integer> numDaysModel=new DefaultComboBoxModel<>();
	private GregorianCalendar cal=new GregorianCalendar();
	private JComboBox<Integer> heureCombo;
	
	private VueCreerExam creerExamPanel;
	
	public VueTabNavigation(JTabbedPane onglets) {
		//Spinner+ComboBox
		cal.setTime(new Date());
		
		SpinnerNumberModel spinnerModelDate=new SpinnerNumberModel(cal.get(Calendar.YEAR), 2010, 2020, 1);
		dateSpinner=new JSpinner(spinnerModelDate);
		
		monthCombo=new JComboBox<>(ApplicationSession.instance().getMonths());
		monthCombo.setSelectedIndex(cal.get(Calendar.MONTH));
		monthCombo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				Date selectedDate=new Date((Integer) dateSpinner.getValue(), monthCombo.getSelectedIndex(), 1);
				cal.setTime(selectedDate);
				numDaysModel.removeAllElements();
				for(int i=1; i<=cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
					numDaysModel.addElement(i);
				}
			}
		});
				
		for(int i=1; i<=cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			numDaysModel.addElement(i);
		}
		numDaysCombo=new JComboBox<Integer>(numDaysModel);
		numDaysCombo.setSelectedIndex(cal.get(Calendar.DAY_OF_MONTH)-1);
		
		DefaultComboBoxModel<Integer> heureModel=new DefaultComboBoxModel<>();
		for(int i=0; i<24; i++) heureModel.addElement(i);
		heureCombo=new JComboBox<>(heureModel);
		
		setLayout(new BorderLayout());
		
		JPanel controlPanel=new JPanel(new GridLayout(1, 6));
		controlPanel.add(numDaysCombo);
		controlPanel.add(monthCombo);
		controlPanel.add(dateSpinner);
		JLabel aJLabel=new JLabel("à");
		aJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		controlPanel.add(aJLabel);
		
		controlPanel.add(heureCombo);
		controlPanel.add(new JLabel("h"));
		
		this.add(controlPanel, BorderLayout.NORTH);
		
		creerExamPanel=new VueCreerExam(onglets);
		add(creerExamPanel);
	}

	public JComboBox<String> getMonthCombo() {
		return monthCombo;
	}

	public JSpinner getDateSpinner() {
		return dateSpinner;
	}

	public JComboBox<Integer> getNumDaysCombo() {
		return numDaysCombo;
	}

	public JComboBox<Integer> getHeureCombo() {
		return heureCombo;
	}

	public VueCreerExam getCreerExamPanel() {
		return creerExamPanel;
	}
}
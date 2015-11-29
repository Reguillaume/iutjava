package edu.iut.gui.widget.agenda;

import java.awt.CardLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.spi.CalendarNameProvider;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import edu.iut.app.ApplicationSession;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;
import edu.iut.gui.widget.agenda.WeekPanel.WeekDayNames;

public class ControlAgendaViewPanel extends JPanel {

	CardLayout agendaViewLayout;
	JPanel contentPane;
	
	int selectedYear;
	int selectedMonth;
	int selectedDay;
	
	public ControlAgendaViewPanel(CardLayout layerLayout, final JPanel contentPane) {

		this.agendaViewLayout = layerLayout;
		this.contentPane = contentPane;
		SpinnerNumberModel spinnerModelDate=new SpinnerNumberModel(2015, 2010, 2020, 1);
		JSpinner spinnerDate=new JSpinner(spinnerModelDate);
		
		String[] dayNames=new String[WeekDayNames.values().length];
		for(int i=0; i<dayNames.length; i++) dayNames[i]=WeekDayNames.values()[i].toString();
		JComboBox<String> comboDays=new JComboBox<>(dayNames);
		
		String[] monthNamesModel={ "january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};
		for(int i=0; i<monthNamesModel.length; i++) monthNamesModel[i]=ApplicationSession.instance().getString(monthNamesModel[i]);
		JComboBox<String> comboMonth=new JComboBox<>(monthNamesModel);

		GregorianCalendar cal=new GregorianCalendar();
		cal.setTime(new Date());
		int todayDay=cal.get(Calendar.DAY_OF_WEEK);
		if(todayDay==1) comboDays.setSelectedIndex(7);
		else comboDays.setSelectedIndex(todayDay-1);
		
		comboMonth.setSelectedIndex(cal.get(Calendar.MONTH));
			
		this.add(comboDays);
		this.add(comboMonth);
		this.add(spinnerDate);

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

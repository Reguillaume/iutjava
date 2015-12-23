package edu.iut.gui.widget.agenda;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.iut.gui.modele.ListeExamEvent;
import edu.iut.gui.modele.ModeleExamEvent;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;
import edu.iut.gui.widget.agenda.WeekPanel.WeekDayNames;
import edu.iut.gui.widget.vue.VueTabNavigation;

public class DayPanel extends EventPanel {

	public DayPanel(ActiveView activeView,WeekDayNames weekDayNames, JTabbedPane onglets) {
		super(activeView);
		switch (activeView) {
		case DAY_VIEW:
		case WEEK_VIEW:
			GridLayout daysLayout;
			switch(weekDayNames) {
			case EMPTYDAY:
				daysLayout = new GridLayout(24,1);
				this.setLayout(daysLayout);
				break;
			default:
				daysLayout = new GridLayout(25,1);
				this.setLayout(daysLayout);
				this.add(new JLabel(weekDayNames.toString()));
			}
			/*
			VueTabNavigation comp=(VueTabNavigation) onglets.getComponentAt(0);
			int an=(Integer) comp.getDateSpinner().getValue();
			int mois=comp.getMonthCombo().getSelectedIndex();
			int jour=comp.getNumDaysCombo().getSelectedIndex();
			int heure=comp.getHeureCombo().getSelectedIndex();	*/
			for (int hi = 0;hi<24;hi++) {
				JPanel hour = new JPanel();
				hour.add(new JLabel(new Integer(hi).toString()));
				/*for(ModeleExamEvent e : ListeExamEvent.instance()) {
					System.out.println("loo");
					if(e.getExamDate().getYear()==an && e.getExamDate().getMonth()==mois && e.getExamDate().getDate()==jour && heure==hi) {
						hour.add(new JLabel(e.getStudent().getLastname()));
					}
				}*/
				this.add(hour);
			}
			break;
		case MONTH_VIEW:
			JPanel hour = new JPanel();
			hour.add(new JLabel("H"));
			this.add(hour);
		}
	}
	
	protected void setupUIDayView() {
		
	}
	protected void setupUIWeekView() {
		
	}
	protected void setupUIMonthView() {
		
	}


}

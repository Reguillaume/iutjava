package edu.iut.tools;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.iut.gui.frames.SchedulerFrame;
import edu.iut.gui.widget.agenda.AgendaPanelFactory;
import edu.iut.gui.widget.agenda.DayPanel;
import edu.iut.gui.widget.agenda.MonthPanel;
import edu.iut.gui.widget.agenda.WeekPanel;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;
import edu.iut.gui.widget.agenda.ControlAgendaViewPanel;
import edu.iut.gui.widget.agenda.WeekPanel.WeekDayNames;


public class IUTScheduler {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        JFrame mainFrame = new edu.iut.gui.frames.SchedulerFrame("IUT Scheduler");
		        mainFrame.setVisible(true);
		    }
		});
	}
	
}

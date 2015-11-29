package edu.iut.gui.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ControlSchedulerFrame implements ActionListener {
	private SchedulerFrame vue;
	
	public ControlSchedulerFrame(SchedulerFrame vue) {
		this.vue=vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Load" : JOptionPane.showMessageDialog(vue, "L'option 'Load' n'existe pas encore.", "Erreur", JOptionPane.ERROR_MESSAGE);break;
		case "Save" : JOptionPane.showMessageDialog(vue, "L'option 'Save' n'existe pas encore.", "Erreur", JOptionPane.ERROR_MESSAGE);break;
		case "Quit" : JOptionPane.showMessageDialog(vue, "L'option 'Quit' n'existe pas encore.", "Erreur", JOptionPane.ERROR_MESSAGE);break;
		case "About" : JOptionPane.showMessageDialog(vue, "L'option 'About' n'existe pas encore.", "Erreur", JOptionPane.ERROR_MESSAGE);break;
		case "Display" : JOptionPane.showMessageDialog(vue, "L'option 'Display' n'existe pas encore.", "Erreur", JOptionPane.ERROR_MESSAGE);break;
		case "Month" : vue.afficherMonth();break;
		case "Day" : vue.afficherDay();break;
		case "Week" : vue.afficherWeek();break;
		}
	}

}

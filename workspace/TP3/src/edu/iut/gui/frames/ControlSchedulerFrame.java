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
		}
	}

}

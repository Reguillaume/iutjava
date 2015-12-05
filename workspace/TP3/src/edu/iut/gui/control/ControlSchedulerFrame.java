package edu.iut.gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import edu.iut.gui.frames.SchedulerFrame;

/**
 * Contrôleur pour gérer le panel pour choisir la date du planning.
 * @see SchedulerFrame
 * @author Guizmo
 *
 */
public class ControlSchedulerFrame implements ActionListener {
	private SchedulerFrame vue;
	
	public ControlSchedulerFrame(SchedulerFrame vue) {
		this.vue=vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Month" : vue.afficherMonth();break;
		case "Day" : vue.afficherDay();break;
		case "Week" : vue.afficherWeek();break;
		default : JOptionPane.showMessageDialog(vue, "L'option '"+e.getActionCommand()+"' n'existe pas encore.", "Erreur", JOptionPane.INFORMATION_MESSAGE);break;
		}
	}
}

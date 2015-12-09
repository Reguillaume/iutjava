package edu.iut.gui.widget.agenda;

import java.awt.CardLayout;

import javax.swing.JPanel;

import edu.iut.gui.widget.vue.VueTabbed;

public class ControlAgendaViewPanel extends JPanel {

	CardLayout agendaViewLayout;
	JPanel contentPane;
	
	public ControlAgendaViewPanel(CardLayout layerLayout, final JPanel contentPane) {
		//Spinner+Combo
		this.agendaViewLayout = layerLayout;
		this.contentPane = contentPane;
		
		VueTabbed onglets=new VueTabbed();
		add(onglets);
	}
	
}

package edu.iut.gui.widget.agenda;

import java.awt.CardLayout;

import javax.swing.JPanel;

import edu.iut.gui.widget.vue.VueTabbed;

public class ControlAgendaViewPanel extends JPanel {

	CardLayout agendaViewLayout;
	JPanel contentPane;
	VueTabbed onglets;
	
	public ControlAgendaViewPanel(CardLayout layerLayout, final JPanel contentPane) {
		this.agendaViewLayout = layerLayout;
		this.contentPane = contentPane;
		
		onglets=new VueTabbed();
		add(onglets);
	}

	public VueTabbed getOnglets() {
		return onglets;
	}
	
}

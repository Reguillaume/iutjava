package edu.iut.gui.control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import edu.iut.gui.modele.ModeleExam;
import edu.iut.io.XMLProjectWriter;

public class ControleurFrame extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		XMLProjectWriter xml=new XMLProjectWriter();
		xml.save(ModeleExam.instance(), new File("save.xml"));
		System.exit(0);
	}
}

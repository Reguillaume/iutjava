package edu.iut.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	private JMenu fileMenu;
	private JMenuItem loadItem;
	private JMenuItem saveItem;
	private JMenuItem quitItem;
	
	private JMenu editMenu;
	private JMenu viewMenu;
	private JMenuItem monthItem;
	private JMenuItem weekItem;
	private JMenuItem dayItem;
	
	private JMenu helpMenu;
	private JMenuItem diplayItem;
	private JMenuItem aboutItem;
	
	public MenuBar() {
		fileMenu=new JMenu("File");
		
		loadItem=new JMenuItem("Load");
		saveItem=new JMenuItem("Save");
		quitItem=new JMenuItem("Quit");
		
		fileMenu.add(loadItem);
		fileMenu.add(saveItem);
		fileMenu.add(quitItem);
		
		this.add(fileMenu);
		
		editMenu=new JMenu("Edit");
		
		viewMenu=new JMenu("View");
		monthItem=new JMenuItem("Month");
		weekItem=new JMenuItem("Week");
		dayItem=new JMenuItem("Day");
		
		viewMenu.add(monthItem);
		viewMenu.add(weekItem);
		viewMenu.add(dayItem);
		editMenu.add(viewMenu);
		
		this.add(editMenu);
		
		helpMenu=new JMenu("Help");
		
		diplayItem=new JMenuItem("Display");
		aboutItem=new JMenuItem("About");
		
		helpMenu.add(diplayItem);
		helpMenu.add(aboutItem);
		
		this.add(helpMenu);
		
	}
}

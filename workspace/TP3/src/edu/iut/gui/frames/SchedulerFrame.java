package edu.iut.gui.frames;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import edu.iut.gui.widget.agenda.AgendaPanelFactory;
import edu.iut.gui.widget.agenda.ControlAgendaViewPanel;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;


public class SchedulerFrame extends JFrame {
	JPanel contentPane;
	CardLayout layerLayout;
	AgendaPanelFactory agendaPanelFactory;	
	JPanel dayView;
	JPanel weekView;
	JPanel monthView;
	
	protected void setupUI() {
		
		contentPane = new JPanel();
		layerLayout = new CardLayout();
		contentPane.setLayout(layerLayout);
		ControlAgendaViewPanel agendaViewPanel = new ControlAgendaViewPanel(layerLayout,contentPane);
		agendaPanelFactory = new AgendaPanelFactory();
		dayView = agendaPanelFactory.getAgendaView(ActiveView.DAY_VIEW);
		weekView = agendaPanelFactory.getAgendaView(ActiveView.WEEK_VIEW);
		monthView = agendaPanelFactory.getAgendaView(ActiveView.MONTH_VIEW);
		
		contentPane.add(dayView,ActiveView.DAY_VIEW.name());
		contentPane.add(weekView,ActiveView.WEEK_VIEW.name());
		contentPane.add(monthView,ActiveView.MONTH_VIEW.name());
	
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,agendaViewPanel, contentPane);
		this.setContentPane(splitPane);
		this.pack();
		layerLayout.next(contentPane);
		
		JMenuBar menuBar=new JMenuBar();
		
		JMenu fileMenu=new JMenu("File");
		
		JMenuItem loadItem=new JMenuItem("Load");
		JMenuItem saveItem=new JMenuItem("Save");
		JMenuItem quitItem=new JMenuItem("Quit");
		
		fileMenu.add(loadItem);
		fileMenu.add(saveItem);
		fileMenu.add(quitItem);
		
		menuBar.add(fileMenu);
		
		JMenu editMenu=new JMenu("Edit");
		
		JMenu viewMenu=new JMenu("View");
		JMenuItem monthItem=new JMenuItem("Month");
		JMenuItem weekItem=new JMenuItem("Week");
		JMenuItem dayItem=new JMenuItem("Day");
		
		viewMenu.add(monthItem);
		viewMenu.add(weekItem);
		viewMenu.add(dayItem);
		editMenu.add(viewMenu);
		
		menuBar.add(editMenu);
		
		JMenu helpMenu=new JMenu("Help");
		
		JMenuItem diplayItem=new JMenuItem("Display");
		JMenuItem aboutItem=new JMenuItem("About");
		
		helpMenu.add(diplayItem);
		helpMenu.add(aboutItem);
		
		menuBar.add(helpMenu);
		
		setJMenuBar(menuBar);
	}
	
	public SchedulerFrame() {
		super();
		
		addWindowListener (new WindowAdapter(){
			public void windowClosing (WindowEvent e){
				System.exit(0);
			}
		});
		contentPane = null;
		dayView = null;
		weekView = null;
		monthView = null;
		agendaPanelFactory = null;
		setupUI();
	}
	
	public SchedulerFrame(String title) {
		super(title);
		addWindowListener (new WindowAdapter(){
			public void windowClosing (WindowEvent e){
				System.exit(0);
			}
		});
		setupUI();
	}
	
}

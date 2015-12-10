package edu.iut.gui.frames;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Savepoint;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import edu.iut.gui.control.ControlSchedulerFrame;
import edu.iut.gui.modele.ModeleExam;
import edu.iut.gui.widget.agenda.AgendaPanelFactory;
import edu.iut.gui.widget.agenda.ControlAgendaViewPanel;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;
import edu.iut.io.XMLProjectReader;
import edu.iut.io.XMLProjectWriter;

/**
 * Classe représentant la fenêtre principal de l'application.
 * @author Guizmo
 *
 */
public class SchedulerFrame extends JFrame {
	JPanel contentPane;
	CardLayout layerLayout;
	AgendaPanelFactory agendaPanelFactory;	
	JPanel dayView;
	JPanel weekView;
	JPanel monthView;
	
	XMLProjectWriter xmlQuit=new XMLProjectWriter();
	XMLProjectReader xmlBegin=new XMLProjectReader();
	
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
		
		JMenuItem displayItem=new JMenuItem("Display");
		JMenuItem aboutItem=new JMenuItem("About");
		
		helpMenu.add(displayItem);
		helpMenu.add(aboutItem);
		
		menuBar.add(helpMenu);
		
		setJMenuBar(menuBar);
		
		ControlSchedulerFrame control=new ControlSchedulerFrame(this);
		loadItem.addActionListener(control);
		saveItem.addActionListener(control);
		quitItem.addActionListener(control);
		monthItem.addActionListener(control);
		dayItem.addActionListener(control);
		weekItem.addActionListener(control);
		displayItem.addActionListener(control);
		aboutItem.addActionListener(control);
	}
	
	public SchedulerFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				xmlQuit.save(ModeleExam.instance(), new File("save.xml"));
				System.exit(0);
			}
			
			public void windowOpened(WindowEvent e) {
				try {
					xmlBegin.load(new File("save.xml"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		setupUI();
	}
	
	public void afficherMonth() { layerLayout.show(contentPane, ActiveView.MONTH_VIEW.name());}
	public void afficherDay() { layerLayout.show(contentPane, ActiveView.DAY_VIEW.name());}
	public void afficherWeek() { layerLayout.show(contentPane, ActiveView.WEEK_VIEW.name());}
}

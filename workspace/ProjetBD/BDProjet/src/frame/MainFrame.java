package frame;

import javax.swing.JFrame;

import vue.VueMain;

/**
 * Classe représentant la fenetre de l'application.
 * @author guillaumemartinez
 *
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		
		add(new VueMain());
		setVisible(true);
	}
}

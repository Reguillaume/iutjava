package frame;

import javax.swing.JFrame;

import vue.VueMain;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setVisible(true);
		
		add(new VueMain());
	}
}

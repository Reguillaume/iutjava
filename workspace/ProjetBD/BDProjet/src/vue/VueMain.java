package vue;

import java.sql.Connection;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import BD.BD;

/**
 * Classe regroupant toutes les vues dans des onglets sous forme de JPanel.
 * @author guillaumemartinez
 *
 */
public class VueMain extends JPanel {
	private static final long serialVersionUID = 1L;

	public VueMain() {
		JTabbedPane onglets=new JTabbedPane();
		Connection co=BD.connexion("jdbc:oracle:thin:gmarti3/coucouboss@oracle.iut-orsay.fr:1521:etudom");

		onglets.addTab("Etudiants", new VueNbStage(co));
		onglets.addTab("Zone", new VueNbStageParZone(co));
		onglets.addTab("Entreprise", new VueNbStageEntreprise(co));
		
		add(onglets);
	}
}

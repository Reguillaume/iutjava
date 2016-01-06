package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import control.ControlVueStage;

/**
 * Classe permettant d'afficher les informations sur les étudiants.
 * @author guillaumemartinez
 *
 */
public class VueNbStage extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel resLabel=new JLabel();
	private JSpinner anneeSpinner;
	
	private Connection co;
	
	public VueNbStage(Connection co) {
		this.co=co;
		//AFFICHAGE
		JPanel nbEtudiantPanel=new JPanel(new GridLayout(2, 1));
		int nbEtudiantAvecStage=0, nbEtudiantSansStage=0;
		CallableStatement cst;
		
		try {
			cst=co.prepareCall(" { ? = call nbEtudiantAvecStage } ");
			cst.registerOutParameter(1, Types.INTEGER);
			cst.execute();
			nbEtudiantAvecStage=cst.getInt(1);
			cst=co.prepareCall(" { ? = call nbEtudiantSansStage } ");
			cst.registerOutParameter(1, Types.INTEGER);
			cst.execute();
			nbEtudiantSansStage=cst.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		nbEtudiantPanel.add(new JLabel("Il y a "+nbEtudiantSansStage+" étudiants sans stage cette année."));
		nbEtudiantPanel.add(new JLabel("Il y a "+nbEtudiantAvecStage+" étudiants avec stage cette année."));
		
		//AFFICHAGE + INTERACTION
		JPanel nbSansStagePanel=new JPanel(new GridLayout(2, 1));
		JPanel spinnerPanel=new JPanel(new BorderLayout());
		JButton chercherButton=new JButton("Chercher");
		chercherButton.addActionListener(new ControlVueStage(this));
		
		SpinnerNumberModel anneeModel=new SpinnerNumberModel(2016, 1995, 2016, 1);
		anneeSpinner=new JSpinner(anneeModel);
		
		spinnerPanel.add(chercherButton, BorderLayout.EAST);
		spinnerPanel.add(anneeSpinner, BorderLayout.CENTER);
		nbSansStagePanel.add(spinnerPanel);
		nbSansStagePanel.add(resLabel);
		
		setLayout(new GridLayout(2, 1));
		add(nbEtudiantPanel);
		add(nbSansStagePanel);
	}
	
	public int getNbEtudiantSansStage() {
		CallableStatement cst=null;
		int res=0;
		
		try {
			cst=co.prepareCall(" { ? = call nbEtudiantSansStageAnnee(?) } ");
			cst.registerOutParameter(1, Types.INTEGER);
			cst.setInt(2, (Integer) anneeSpinner.getValue());
			cst.executeQuery();
			res=cst.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public void setTextResLabel(String text) {
		resLabel.setText(text);
	}

	public JSpinner getAnneeSpinner() {
		return anneeSpinner;
	}
}

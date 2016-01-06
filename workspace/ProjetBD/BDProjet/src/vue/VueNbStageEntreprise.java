package vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import control.ControlVueStageEntreprise;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

/**
 * Classe permettant d'afficher les informations des entreprises.
 * @author guillaumemartinez
 *
 */
public class VueNbStageEntreprise extends JPanel {
	private static final long serialVersionUID = 1L;

	private Connection co;
	
	private JSpinner anneeSpinner;
	private JLabel descLabel=new JLabel();
	private JLabel resNbStagiaire=new JLabel();
	private JLabel resNbMoyStagiaire=new JLabel();
	private JLabel resStagiaire=new JLabel();
	
	public VueNbStageEntreprise(Connection co) {
		this.co=co;
		
		//SPINNER PANEL
		JPanel spinnerPanel=new JPanel(new BorderLayout());
		JButton chercherButton=new JButton("Chercher");
		chercherButton.addActionListener(new ControlVueStageEntreprise(this));
		
		SpinnerNumberModel anneeModel=new SpinnerNumberModel(0, 0, 20, 1);
		anneeSpinner=new JSpinner(anneeModel);
		spinnerPanel.add(anneeSpinner, BorderLayout.CENTER);
		spinnerPanel.add(chercherButton, BorderLayout.EAST);
		
		//AFFICHAGE RESULTAT
		JPanel resPanel=new JPanel(new GridLayout(1, 3));
		resPanel.add(resNbStagiaire);
		resPanel.add(resNbMoyStagiaire);
		resPanel.add(resStagiaire);
		
		setLayout(new GridLayout(3, 1));
		add(spinnerPanel);
		add(descLabel);
		add(resPanel);
	}
	
	public void setNbStagiaire() {
		CallableStatement cst=null;
		ResultSet resSet=null;
		String res="<html>Nombre de stagiaires par entreprise<br>";
		
		try {
			cst=co.prepareCall(" { call nbStagiaireEntreprise(?, ?) } ");
			cst.setInt(1, (Integer) anneeSpinner.getValue());
			cst.registerOutParameter(2, OracleTypes.CURSOR);
			cst.execute();
			resSet=((OracleCallableStatement)cst).getCursor(2);
			while(resSet.next()) {
				res+=resSet.getString(1)+" : "+resSet.getInt(2)+"<br>";
			}
			res+="</html>";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resNbStagiaire.setText(res);
	}
	
	public void setNbMoyStagiaire() {
		CallableStatement cst=null;
		int res=0;
		
		try {
			cst=co.prepareCall(" { ? = call nbMoyenStagiaireEntreprise(?) } ");
			cst.setInt(2, (Integer) anneeSpinner.getValue());
			cst.registerOutParameter(1, Types.INTEGER);
			cst.executeQuery();
			res=cst.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resNbMoyStagiaire.setText("Nombre moyen de stagiaires encadrés : "+res);
	}
	
	public void setStagiaire() {
		CallableStatement cst=null;
		ResultSet resSet=null;
		String res="<html>Stagiaires d'entreprises<br>";
		
		try {
			cst=co.prepareCall(" { call contactEntrepriseAnnee(?, ?) } ");
			cst.setInt(1, (Integer) anneeSpinner.getValue());
			cst.registerOutParameter(2, OracleTypes.CURSOR);
			cst.execute();
			resSet=((OracleCallableStatement)cst).getCursor(2);
			while(resSet.next()) {
				res+=resSet.getString(1)+" : "+resSet.getString(2)+" "+resSet.getString(3)+"<br>";
			}
			res+="</html>";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resStagiaire.setText(res);
	}
	
	public void setTextDesc(String text) {
		descLabel.setText(text);
	}

	public JSpinner getAnneeSpinner() {
		return anneeSpinner;
	}
}

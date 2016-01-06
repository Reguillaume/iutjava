package vue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.ControlVueStageZone;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

/**
 * Classe permettant de voir le nombre de stages par zone géographique sous un JPanel.
 * @author guillaumemartinez
 *
 */
public class VueNbStageParZone extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTextField villeField=new JTextField();
	private JTextField departementField=new JTextField();
	private JLabel resLabel=new JLabel();
	
	private Connection co;
	
	public VueNbStageParZone(Connection co) {
		CallableStatement cst=null;
		ResultSet res;
		JPanel nbStageParZone;
		this.co=co;
		
		//LISTE ZONES
		//Récupération des infos de la bd.
		try {
			cst=co.prepareCall(" { call nbStageParZone(?) } ");
			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Affichage des infos.
		nbStageParZone=new JPanel(new GridLayout(0,3));
		try {
			res=((OracleCallableStatement)cst).getCursor(1);
			nbStageParZone.add(new JLabel("Ville"));
			nbStageParZone.add(new JLabel("Département"));
			nbStageParZone.add(new JLabel("Stages"));
			while(res.next()) {
				nbStageParZone.add(new JLabel(res.getString(1)));
				nbStageParZone.add(new JLabel(res.getString(2)));
				nbStageParZone.add(new JLabel(""+res.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//INTERACTION ZONE
		JPanel nbStageZoneField=new JPanel(new GridLayout(4, 1));
		JButton chercherButton=new JButton("Chercher");
		chercherButton.addActionListener(new ControlVueStageZone(this));
		//Affichage
		JPanel villePanel=new JPanel(new BorderLayout());
		villePanel.add(new JLabel("Ville : "), BorderLayout.WEST);
		villePanel.add(villeField, BorderLayout.CENTER);
		JPanel depPanel=new JPanel(new BorderLayout());
		depPanel.add(new JLabel("Département : "), BorderLayout.WEST);
		depPanel.add(departementField, BorderLayout.CENTER);
		nbStageZoneField.add(depPanel);
		nbStageZoneField.add(villePanel);
		nbStageZoneField.add(chercherButton);
		nbStageZoneField.add(resLabel);
		
		setLayout(new GridLayout(2, 1));
		add(nbStageParZone);
		add(nbStageZoneField);
	}

	public void setTextResLabel(String text) {
		this.resLabel.setText(text);
	}
	
	public int getNbStage() {
		CallableStatement cst=null;
		int res=0;
		try {
			cst=co.prepareCall(" { ? = call nbStageZone(?, ?) } ");
			cst.registerOutParameter(1, Types.NUMERIC);
			cst.setString(2, villeField.getText());
			cst.setString(3, departementField.getText());
			cst.executeQuery();
			res=cst.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
}

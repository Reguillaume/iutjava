package edu.iut.app;

/**
 * Classe représentant une salle de classe.
 * @author Guizmo
 *
 */
public class Classroom {
	
	/**
	 * Initialise une salle de classe qui ne possède pas de nom : 'non affecté'.
	 */
	public Classroom() {
		classRoomNumber="not affected";
	}
	
	/**
	 * Initialise une salle de classe à partir du nom en paramètre.
	 * @param classRoomNumber
	 * 	Nom de la salle de classe sous forme de chaîne de caractères.
	 */
	public Classroom(String classRoomNumber) {
	this.classRoomNumber=classRoomNumber;
	}
	
	/**
	 * Remplace le nom de la salle de classe par le nom en paramètre.
	 * @param number
	 * 	Nom de la salle de classe sous forme de chaîne de caractères.
	 */
	public void setClassroomNumber(String number) {
		classRoomNumber=number;
	}
	
	/**
	 * Retourne le nom de la salle de classe.
	 * @return Nom de la salle de classe sous forme de chaîne de caractères.
	 */
	public String getClassRoomNumber() {
		return classRoomNumber;
	}
	
	/**
	 * Nom de la salle de classe sous forme de chaîne de caractères.
	 */
	protected String classRoomNumber;
}
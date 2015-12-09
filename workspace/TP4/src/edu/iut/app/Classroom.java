package edu.iut.app;

/**
 * Classe repr�sentant une salle de classe.
 * @author Guizmo
 *
 */
public class Classroom {
	
	/**
	 * Initialise une salle de classe qui ne poss�de pas de nom : 'non affect�'.
	 */
	public Classroom() {
		classRoomNumber="not affected";
	}
	
	/**
	 * Initialise une salle de classe � partir du nom en param�tre.
	 * @param classRoomNumber
	 * 	Nom de la salle de classe sous forme de cha�ne de caract�res.
	 */
	public Classroom(String classRoomNumber) {
	this.classRoomNumber=classRoomNumber;
	}
	
	/**
	 * Remplace le nom de la salle de classe par le nom en param�tre.
	 * @param number
	 * 	Nom de la salle de classe sous forme de cha�ne de caract�res.
	 */
	public void setClassroomNumber(String number) {
		classRoomNumber=number;
	}
	
	/**
	 * Retourne le nom de la salle de classe.
	 * @return Nom de la salle de classe sous forme de cha�ne de caract�res.
	 */
	public String getClassRoomNumber() {
		return classRoomNumber;
	}
	
	/**
	 * Nom de la salle de classe sous forme de cha�ne de caract�res.
	 */
	protected String classRoomNumber;
}
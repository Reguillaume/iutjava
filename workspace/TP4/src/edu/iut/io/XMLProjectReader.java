package edu.iut.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import edu.iut.app.Classroom;
import edu.iut.app.ExamEvent;
import edu.iut.app.Person;
import edu.iut.app.Person.PersonFunction;
import edu.iut.gui.modele.ModeleClassroom;
import edu.iut.gui.modele.ModeleDocument;
import edu.iut.gui.modele.ModeleExam;
import edu.iut.gui.modele.ModelePerson;
import edu.iut.gui.widget.vue.VueCreerExam;
import edu.iut.gui.widget.vue.VueTabNavigation;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// EX 1 Completer la classe

public class XMLProjectReader {
	public XMLProjectReader() {
		
	}
	
	public void load(java.io.File xmlfile) throws IOException {
		ArrayList<ExamEvent> data = new ArrayList<ExamEvent>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document= builder.parse(xmlfile);
			Element root=document.getDocumentElement();
			
			//DOCUMENT
			NodeList documentRoot=root.getElementsByTagName("documentClass");
			NodeList d=((Element)documentRoot.item(0)).getElementsByTagName("d");
			for(int i=0; i<d.getLength(); i++) {
				edu.iut.app.Document doc=new edu.iut.app.Document(((Element)d.item(i)).getAttribute("uri"));
				ModeleDocument.instance().add(doc);
			}
			
			//CLASSROOM
			NodeList classroomRoot=document.getElementsByTagName("classroomClass");
			NodeList c=((Element)classroomRoot.item(0)).getElementsByTagName("c");
			for(int i=0; i<c.getLength(); i++) {
				Classroom classroom=new Classroom(((Element)c.item(i)).getAttribute("numero"));
				ModeleClassroom.instance().add(classroom);
			}
			
			//PERSON
			NodeList personRoot=document.getElementsByTagName("personClass");
			NodeList p=((Element)personRoot.item(0)).getElementsByTagName("p");
			for(int i=0; i<p.getLength(); i++) {
				Element pElement=(Element) p.item(i);
				String nom=pElement.getAttribute("nom");
				String prenom=pElement.getAttribute("prenom");
				String phone=pElement.getAttribute("phone");
				String email=pElement.getAttribute("email");
				PersonFunction fonction=null;
				if(pElement.getAttribute("fonction").equals(PersonFunction.STUDENT.name())) fonction=PersonFunction.STUDENT;
				else fonction=PersonFunction.JURY;
				Person person=new Person(fonction, nom, prenom, email, phone);
				ModelePerson.instance().add(person);		
			}
			
			//EXAMEVENT
			NodeList examEventRoot=root.getElementsByTagName("exameventClass");
			NodeList rootChild=((Element)examEventRoot.item(0)).getElementsByTagName("exam");
			for(int i=0; i<rootChild.getLength(); i++) {
				if(rootChild.item(i).getNodeType()==Node.ELEMENT_NODE) {
					Node child=(Element) rootChild.item(i);
					
					//Date
					Element date=(Element) ((Element)child).getElementsByTagName("date").item(0);
					Date dateExam=new Date(Integer.valueOf(date.getAttribute("annee")), Integer.valueOf(date.getAttribute("mois")), Integer.valueOf(date.getAttribute("jour")), Integer.valueOf(date.getAttribute("heure")), 0);
				
					//Etudiant
					Element etudiant=(Element) ((Element)child).getElementsByTagName("etudiant").item(0);
					int indexEtudiant=Integer.valueOf(etudiant.getAttribute("id"));
					
					//Jury
					NodeList jury= ((Element)child).getElementsByTagName("jury");
					NodeList juryChild = ((Element)jury.item(0)).getElementsByTagName("person");
					ArrayList<Integer> indexJury=new ArrayList<>();
					for(int j=0; j<juryChild.getLength(); j++) {
						Element personChild=(Element) juryChild.item(j);
						indexJury.add(Integer.valueOf(personChild.getAttribute("id")));
					}
					
					//Classroom
					Element classroom=(Element) ((Element)child).getElementsByTagName("classroom").item(0);
					int indexClassroom=Integer.valueOf(classroom.getAttribute("id"));
					
					//Document
					NodeList doc=((Element)child).getElementsByTagName("document");
					NodeList docChild=((Element)doc.item(0)).getElementsByTagName("doc");
					ArrayList<Integer> indexDoc=new ArrayList<>();
					for(int j=0; j<docChild.getLength(); j++) {
						Element leDoc=(Element) docChild.item(j);
						indexDoc.add(Integer.valueOf(leDoc.getAttribute("id")));
					}
					
					//creation de l'exam et insertion dans le modele
					Person e=ModelePerson.instance().get(indexEtudiant);
					ArrayList<Person> j=new ArrayList<>();
					for(int indexj : indexJury) j.add(ModelePerson.instance().get(indexj));
					Classroom classr=ModeleClassroom.instance().get(indexClassroom);
					ArrayList<edu.iut.app.Document> docu=new ArrayList<>();
					for(int indexdocu : indexDoc) docu.add(ModeleDocument.instance().get(indexdocu));
					ModeleExam.instance().add(new ExamEvent(dateExam, e, j, classr, docu));
				}
			}
		} catch (ParserConfigurationException e) {
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		//On rafraichit les listes
	}
}

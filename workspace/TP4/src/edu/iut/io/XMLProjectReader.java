package edu.iut.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import edu.iut.gui.modele.ModeleClassroom;
import edu.iut.gui.modele.ModeleExamEvent;
import edu.iut.gui.modele.ListeClassroom;
import edu.iut.gui.modele.ListeDocument;
import edu.iut.gui.modele.ListeExamEvent;
import edu.iut.gui.modele.ModelePerson;
import edu.iut.gui.modele.ModelePerson.PersonFunction;
import edu.iut.gui.modele.ListePerson;
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
		ArrayList<ModeleExamEvent> data = new ArrayList<ModeleExamEvent>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		ListeClassroom.instance().clear();
		ListeDocument.instance().clear();
		ListeExamEvent.instance().clear();
		ListePerson.instance().clear();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document= builder.parse(xmlfile);
			Element root=document.getDocumentElement();
			
			//DOCUMENT
			NodeList documentRoot=root.getElementsByTagName("documentClass");
			NodeList d=((Element)documentRoot.item(0)).getElementsByTagName("d");
			for(int i=0; i<d.getLength(); i++) {
				edu.iut.gui.modele.ModeleDocument doc=new edu.iut.gui.modele.ModeleDocument(((Element)d.item(i)).getAttribute("uri"));
				ListeDocument.instance().add(doc);
			}
			
			//CLASSROOM
			NodeList classroomRoot=document.getElementsByTagName("classroomClass");
			NodeList c=((Element)classroomRoot.item(0)).getElementsByTagName("c");
			for(int i=0; i<c.getLength(); i++) {
				ModeleClassroom classroom=new ModeleClassroom(((Element)c.item(i)).getAttribute("numero"));
				ListeClassroom.instance().add(classroom);
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
				ModelePerson person=new ModelePerson(fonction, nom, prenom, email, phone);
				ListePerson.instance().add(person);		
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
					ModelePerson e=ListePerson.instance().get(indexEtudiant);
					ArrayList<ModelePerson> j=new ArrayList<>();
					for(int indexj : indexJury) j.add(ListePerson.instance().get(indexj));
					ModeleClassroom classr=ListeClassroom.instance().get(indexClassroom);
					ArrayList<edu.iut.gui.modele.ModeleDocument> docu=new ArrayList<>();
					for(int indexdocu : indexDoc) docu.add(ListeDocument.instance().get(indexdocu));
					ListeExamEvent.instance().add(new ModeleExamEvent(dateExam, e, j, classr, docu));
				}
			}
		} catch (ParserConfigurationException e) {
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		//On rafraichit les listes
	}
}

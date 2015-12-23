package edu.iut.io;

import java.awt.Event;
import java.util.ArrayList;

import javax.management.modelmbean.ModelMBean;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;

import edu.iut.gui.modele.ModeleClassroom;
import edu.iut.gui.modele.ModeleExamEvent;
import edu.iut.gui.modele.ListeClassroom;
import edu.iut.gui.modele.ListeDocument;
import edu.iut.gui.modele.ListeExamEvent;
import edu.iut.gui.modele.ModelePerson;
import edu.iut.gui.modele.ListePerson;



//EX 1 Completer la classe 

public class XMLProjectWriter {
	
	public XMLProjectWriter() {		
	}
	
	public void save(ArrayList<ModeleExamEvent> data, java.io.File xmlfile) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		
		
		try {
			builder = factory.newDocumentBuilder();
		
			Document document = builder.newDocument();
			Element root=document.createElement("root");

			//EXAMEVENT
			Element rootExamEvent = document.createElement("exameventClass");
			for(int i=0; i<ListeExamEvent.instance().size(); i++){
				ModeleExamEvent e=ListeExamEvent.instance().get(i);
				Element exam = document.createElement("exam");
				exam.setAttribute("id", String.valueOf(i));
				
				Element date=document.createElement("date");
				date.setAttribute("heure", String.valueOf(e.getExamDate().getHours()));
				date.setAttribute("jour", String.valueOf(e.getExamDate().getDate()));
				date.setAttribute("mois", String.valueOf(e.getExamDate().getMonth()));
				date.setAttribute("annee", String.valueOf(e.getExamDate().getYear()));

				Element etudiant = document.createElement("etudiant");
				etudiant.setAttribute("id", String.valueOf(ListePerson.instance().indexOf(e.getStudent())));
				
				Element jury = document.createElement("jury");
				for(ModelePerson p: e.getJury()){
					Element person = document.createElement("person");
					person.setAttribute("id", String.valueOf(ListePerson.instance().indexOf(p)));
					jury.appendChild(person);
				}
				
				Element classroom = document.createElement("classroom");
				classroom.setAttribute("id", String.valueOf(ListeClassroom.instance().indexOf(e.getClassroom())));
				
				Element doc = document.createElement("document");
				for(edu.iut.gui.modele.ModeleDocument d: e.getDocuments()){
					Element doc2 = document.createElement("doc");
					doc2.setAttribute("id", String.valueOf(ListeDocument.instance().indexOf(d)));
					doc.appendChild(doc2);
				}
				
				exam.appendChild(date);
				exam.appendChild(etudiant);
				exam.appendChild(jury);
				exam.appendChild(classroom);
				exam.appendChild(doc);
				rootExamEvent.appendChild(exam);
			}
			root.appendChild(rootExamEvent);
			
			//PERSON
			Element rootPerson=document.createElement("personClass");
			for(int i=0; i<ListePerson.instance().size(); i++) {
				ModelePerson person=ListePerson.instance().get(i);
				Element personChild=document.createElement("p");
				personChild.setAttribute("id", String.valueOf(i));
				personChild.setAttribute("fonction", person.getFunction().name());
				personChild.setAttribute("nom", person.getLastname());
				personChild.setAttribute("prenom", person.getFirstname());
				personChild.setAttribute("phone", person.getPhone());
				personChild.setAttribute("email", person.getEmail());
				rootPerson.appendChild(personChild);
			}
			root.appendChild(rootPerson);
			
			//CLASSROOM
			Element rootClassroom=document.createElement("classroomClass");
			for(int i=0; i<ListeClassroom.instance().size(); i++) {
				Element classroomChild=document.createElement("c");
				ModeleClassroom classroom=ListeClassroom.instance().get(i);
				classroomChild.setAttribute("id", String.valueOf(i));
				classroomChild.setAttribute("numero", classroom.getClassRoomNumber());
				rootClassroom.appendChild(classroomChild);
			}
			root.appendChild(rootClassroom);
			
			//DOCUMENT
			Element rootDocument=document.createElement("documentClass");
			for(int i=0; i<ListeDocument.instance().size(); i++) {
				edu.iut.gui.modele.ModeleDocument d=ListeDocument.instance().get(i);
				Element documentChild=document.createElement("d");
				documentChild.setAttribute("id", String.valueOf(i));
				documentChild.setAttribute("uri", d.getDocumentURI());
				rootDocument.appendChild(documentChild);
			}
			root.appendChild(rootDocument);
			
			document.appendChild(root);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(xmlfile);
			
			transformer.transform(source, result);			
		}
		catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}

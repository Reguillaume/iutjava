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

import edu.iut.app.ExamEvent;
import edu.iut.app.Person;
import edu.iut.gui.modele.ModeleClassroom;
import edu.iut.gui.modele.ModeleDocument;
import edu.iut.gui.modele.ModeleExam;
import edu.iut.gui.modele.ModelePerson;



//EX 1 Completer la classe 

public class XMLProjectWriter {
	
	public XMLProjectWriter() {		
	}
	
	public void save(ArrayList<ExamEvent> data, java.io.File xmlfile) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		
		
		try {
			builder = factory.newDocumentBuilder();
		
			Document document = builder.newDocument();
			Element root = document.createElement("examevent");
			for(int i=0; i<ModeleExam.instance().size(); i++){
				ExamEvent e=ModeleExam.instance().get(i);
				Element exam = document.createElement("exam");
				exam.setAttribute("id", String.valueOf(i));
				exam.setAttribute("date", e.getExamDate().toString());
				
				Element etudiant = document.createElement("etudiant");
				etudiant.setAttribute("id", String.valueOf(ModelePerson.instance().indexOf(e.getStudent())));
				
				Element jury = document.createElement("jury");
				for(Person p: e.getJury()){
					Element person = document.createElement("person");
					person.setAttribute("id", String.valueOf(ModelePerson.instance().indexOf(p)));
					jury.appendChild(person);
				}
				
				Element classroom = document.createElement("classroom");
				classroom.setAttribute("id", String.valueOf(ModeleClassroom.instance().indexOf(e.getClassroom())));
				
				Element doc = document.createElement("document");
				for(edu.iut.app.Document d: e.getDocuments()){
					Element doc2 = document.createElement("doc");
					doc2.setAttribute("id", String.valueOf(ModeleDocument.instance().indexOf(d)));
					doc.appendChild(doc2);
				}
				
				exam.appendChild(etudiant);
				exam.appendChild(jury);
				exam.appendChild(classroom);
				exam.appendChild(doc);
				root.appendChild(exam);
			}
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

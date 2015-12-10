package edu.iut.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import edu.iut.app.ExamEvent;
import edu.iut.app.Person;

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
	
	public ArrayList<ExamEvent> load(java.io.File xmlfile) throws IOException {
		ArrayList<ExamEvent> data = new ArrayList<ExamEvent>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document= builder.parse(xmlfile);
			Element root=document.getDocumentElement();
			
			NodeList rootChild=root.getElementsByTagName("exam");
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
				}
			}
		} catch (ParserConfigurationException e) {
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return data;
	}
}

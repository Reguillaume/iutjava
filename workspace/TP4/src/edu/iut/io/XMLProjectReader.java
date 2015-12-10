package edu.iut.io;

import java.io.IOException;
import java.util.ArrayList;

import edu.iut.app.ExamEvent;

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
					System.out.println(((Element) rootChild.item(i)).getAttribute("date"));
				}
			}
		} catch (ParserConfigurationException e) {
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return data;
	}
}

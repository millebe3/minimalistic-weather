// XMLParser.java	Dorothy Carter
// this class uses SAX to parse some XML

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.ArrayList;

public class XMLParser extends DefaultHandler {
	protected ArrayList<Weather> list;
	public void startDocument() throws SAXException {
		list = new ArrayList<Weather>();
	}
	
	public void endDocument() throws SAXException {
	}
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes attr) throws SAXException {}
	public void endElement(String namespaceURI, String localName,
			String qName) throws SAXException {}
	public void characters(char[] ch, int start, int length) throws SAXException {}
}

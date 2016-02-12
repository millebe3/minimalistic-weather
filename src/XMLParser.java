// XMLParser.java	Dorothy Carter
// this class uses SAX to parse some XML

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class XMLParser extends DefaultHandler {
	public void startDocument() throws SAXException {}
	public void endDocument() throws SAXException {}
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes attr) throws SAXException {}
	public void endElement(String namespaceURI, String localName,
			String qName) throws SAXException {}
	public void characters(char[] ch, int start, int length) throws SAXException {}
}

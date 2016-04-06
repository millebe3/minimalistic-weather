// XMLParser.java	Dorothy Carter
// this class uses SAX to parse some XML

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.ArrayList;

public class XMLParser extends DefaultHandler {
	protected ArrayList<Weather> list;
	boolean grab;
	String what, layout;
	int times;
	
	public ArrayList<Weather> getList() {
		return list;
	}
	
	@Override
	public void startDocument() throws SAXException {
		list = new ArrayList<Weather>();
		grab = false;
		
		for (int num=0; num<14; num++) {
			list.add(new Weather());
		}
	}

	@Override	
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes attr) throws SAXException {
		if (qName.equals("layout-key")) {
			grab = true;
			what = "layout-key";
			times = -1;
		} else if (qName.equals("start-valid-time")) {
			grab = true;
			what = "layout";
			times++;
		} else if (qName.equals("temperature")) {
			what = "temperature ";
			what += attr.getValue(0);
			times = -1;
			layout = attr.getValue(2);
		} else if (qName.equals("probability-of-precipitation") || qName.equals("wind-speed") || qName.equals("direction")
				|| qName.equals("cloud-amount") || qName.equals("humidity")) {
			what = qName;
			times = -1;
			layout = attr.getValue(2);
		} else if (qName.equals("value")) {
			grab = true;
			times++;
		}
	}

	@Override	
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (grab) {
			String temp = "";
			for (int i=start; i<start+length; i++) {
				temp += ch[i];
			}
			
			if (what.equals("layout-key")) {
				layout = temp;
			} else if (what.equals("layout")) {
				// put the 12-hour timestamps into the weather objects
				if (layout.equals("k-p12h-n14-3"))
					list.get(times).setTimestamp(temp);
			
			} else {
				// the value given will be a numeric value
				int number = 0;
				try {
					number = Integer.parseInt(temp);
				} catch (NumberFormatException e) {}
				
				if (what.equals("temperature maximum")) {
					list.get(times*2).setHigh(number);
					list.get((times*2)+1).setHigh(number);
					
				} else if (what.equals("temperature minimum")) {
					list.get(times*2).setLow(number);
					list.get((times*2)+1).setLow(number);
					
				} else if (what.equals("temperature apparent")) {
					if ((times+1) % 4 == 0 || times==0)
						list.get((times+1)/4).setCurrent(number);
					
				} else if (what.equals("probability-of-precipitation")) {
					list.get(times).setPrecipitation(number);
					
				} else if (what.equals("wind-speed")) {
					if ((times+1) % 4 == 0 || times==0)
						list.get((times+1)/4).setSpeed(number);
				
				} else if (what.equals("direction")) {
					if ((times+1) % 4 == 0 || times==0)
						list.get((times+1)/4).setDirection(number);
					
				} else if (what.equals("cloud-amount")) {
					if ((times+1) % 4 == 0 || times==0)
						list.get((times+1)/4).setCloud(number);
					
				} else if (what.equals("humidity")) {
					if ((times+1) % 4 == 0 || times==0)
						list.get((times+1)/4).setHumidity(number);
					
				}
			}				
			grab = false;
		}
	}
}

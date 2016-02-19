// GetWeather.java	Dorothy Carter
// This class instantiates an object that stores an URL and the content located at that URL
// The URL always points to the REST service of the NDFD

import java.net.*;
import javax.xml.parsers.*;
import org.xml.sax.SAXException;
import java.util.ArrayList;
import java.io.*;

public class GetWeather {
	protected URL here;
	protected boolean unit;
	protected InputStream in;
	protected ArrayList<Weather> parsed;
	
	public GetWeather(String ZIP, String begin, String end, String units) {
		if (units.equals("m"))
			unit = true;
		else
			unit = false;
		
		
		try {
			buildURL(ZIP, begin, end, units);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		grabData();
		try {
			parseData();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void grabData() {
		try {
			HttpURLConnection cnx = (HttpURLConnection) here.openConnection();
			cnx.connect();
			
			// if the connection is ok, read the content from it
			if (cnx.getResponseCode() == 200)
				in = cnx.getInputStream();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void parseData() throws SAXException, ParserConfigurationException, IOException {
		SAXParserFactory spf = SAXParserFactory.newInstance();
	    spf.setNamespaceAware(true);
	    SAXParser saxParser = spf.newSAXParser();
	    XMLParser handler = new XMLParser();
	    
	    saxParser.parse(in, handler);
	    
	    for (Weather w : handler.getList()) {
	    	w.setMetric(unit);
	    }
	    
	    parsed = handler.getList();
	}
	
	protected void buildURL(String ZIP, String begin, String end, String unit) throws MalformedURLException {
		String base = "http://graphical.weather.gov/xml/sample_products/browser_interface/ndfdXMLclient.php?";
		base += "zipCodeList=" + ZIP;
		base += "&product=time-series";
		base += "&begin=" + begin;
		base += "&end=" + end;
		base += "&Unit=" + unit;
		
		base += "&maxt=maxt"; // max temp
		base += "&mint=mint"; // minimum temp
		base += "&appt=appt"; // apparent temperature
		base += "&pop12=pop12"; // 12-hr probablility of rain
		base += "&sky=sky"; // cloud cover
		base += "&wspd=wspd"; // wind speed
		base += "&wdir=wdir"; // wind direction
		base += "&rh=rh"; // relative humidity
		
		here = new URL(base);
	}
	
	public URL getURL() {
		return here;
	}
	
	public InputStream getContent() {
		return in;
	}
	
	public void setURL(String ZIP, String begin, String end, String unit) throws MalformedURLException {
		buildURL(ZIP, begin, end, unit);
	}
	
	public void setContent() {
		grabData();
	}
	
	public String toString() {
		return here + "\n" + in;
	}
	
	// this is a method to build a timestamp that follows the doc's specifications
	// it may or may not be useful
	public static String buildTimestamp(int year, int month, int day, int hour) {
		String temp = year + "-";
		if (month < 10)
			temp += "0" + month;
		else
			temp += month;
		
		if (day < 10)
			temp += "0" + day;
		else
			temp += day;
		
		temp += "T";
		
		if (hour < 10)
			temp += "0" + hour;
		else
			temp += hour;
		
		temp += ":00";
		
		return temp;
	}
}

/**
 * GetWeather is used to get weather data from the NWS and parse it into an ArrayList of
 * Weather objects. It requires a network connection.
 * 
 * @author Dorothy Carter
 * @version 1.0.1
 */

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
	
	/**
	 * Constructs a GetWeather object, first building a URL, then grabbing the data, then parsing it. Requires some overhead, obviously.
	 * 
	 * @param ZIP a ZIP code
	 * @param begin an ISO 8601 compliant datetime stamp representing the beginning of the time sequence of weather data wanted
	 * @param end an ISO 8601 compliant datetime stamp representing the end of the time sequence of weather data wanted
	 * @param units either "m" or "e", "m" standing for metric and "e" standing for imperial units
	 */
	public GetWeather(String ZIP, String begin, String end, String units) {
		unit = units.equals("m");
		
		try {
			buildURL(ZIP, begin, end, units);
		} catch (MalformedURLException e) {
			// this won't happen, as the buildURL method
			// uses string concatenation to build valid URLs
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
	
	/**
	 * Grabs the weather data from the URL stored in here. Doesn't parse it, just stores the input stream in in.
	 * If the response code is not 200, in might still be null.
	 */
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
	
	/**
	 * Parses the input stream in in using an SAX parser and a handler defined in the XMLParser class.
	 * Handles the possible NullPointerException if in is null by trying to grab the data again and throwing an IOException
	 * if it isn't possible.
	 * 
	 * @throws SAXException if a method call throws it
	 * @throws ParserConfigurationException if a method call throws it
	 * @throws IOException if there's no network connection, or if a method call throws it
	 */
	protected void parseData() throws SAXException, ParserConfigurationException, IOException {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		SAXParser saxParser = spf.newSAXParser();
		XMLParser handler = new XMLParser();
	    	
	    	try {
			saxParser.parse(in, handler);
			
			for (Weather w : handler.getList()) {
				w.setMetric(unit);
			}
			
			parsed = handler.getList();
		} catch (NullPointerException e) {
			// this could happen if the connection was bad
			// try to grab the data again
			grabData();
			
			// try to parse the data again. if in is still null, throw an exception
			if (in != null) {
				saxParser.parse(in, handler);
				
				for (Weather w : handler.getList()) {
					w.setMetric(unit);
				}
				parsed = handler.getList();
			} else
				throw new IOException("Can't get input stream");
		}
	}
	
	/**
	 * Using the parameters provided by the method call, builds a URL to get the weather data wanted and stores it in here
	 * 
	 * @param ZIP the ZIP code you want weather data for
	 * @param begin the ISO 8601 compliant datetime stamp for the beginning of the weather data series
	 * @param end the ISO 8601 compliant datetime stamp for the end of the weather data series
	 * @param unit "m" for metric units, "e" for imperial units
	 * 
	 * @throws MalformedURLException if the resulting URL is malformed
	 */
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
	
	/**
	 * @return the URL the class is getting its weather data from
	 */
	public URL getURL() {
		return here;
	}
	
	/**
	 * @return the input stream containing the XML weather data
	 */
	public InputStream getContent() {
		return in;
	}
	
	/**
	 * @return the parsed ArrayList of Weather objects
	 */
	public ArrayList<Weather> getParsed() {
		return parsed;
	}
	
	/**
	 * uses the buildURL method to build a URL using the given arguments
	 * 
	 * @param ZIP a ZIP code
	 * @param begin the ISO 8601 compliant datetime stamp for the beginning of the weather data series
	 * @param end the ISO 8601 compliant datetime stamp for the end of the weather data series
	 * @param unit "m" for metric units, "e" for imperial units
	 * 
	 * @throws MalformedURLException if the resulting URL is malformed
	 */
	public void setURL(String ZIP, String begin, String end, String unit) throws MalformedURLException {
		buildURL(ZIP, begin, end, unit);
	}
	
	/**
	 * grabs the data again using the grabData method
	 */
	public void setContent() {
		grabData();
	}
	
	/**
	 * tries to parse the data in in again
	 */
	public void setParsed() {
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
	
	/**
	 * returns the URL this object points to
	 */
	public String toString() {
		return here.toString();
	}
	
	/**
	 * Builds an ISO 8601 compliant datetime stamp 
	 * 
	 * @param year the year
	 * @param month the month
	 * @param day the day
	 * @param hour the hour
	 * 
	 * @return an ISO 8601 compliant datetime stamp
	 */
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

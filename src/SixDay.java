import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.net.*;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Locale;
import java.io.IOException;

public class SixDay extends JFrame {
	private  JPanel forecastPanel;
	private  JLabel[] fore, dayLbs;
	private JButton refresh;
	private String ZIP;
	private boolean isMetric;
	
	public SixDay(String z, boolean m) {
		ZIP = z;
		isMetric = m;
		
		forecastPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(25,25,25,25);
		
		dayLbs = new JLabel[6];
		
		dayLbs[0] = new JLabel("Today");
		gbc.gridx = 0;
		gbc.gridy = 0;
		forecastPanel.add(dayLbs[0], gbc);
		
		dayLbs[1] = new JLabel("Tomorrow");
		gbc.gridx = 0;
		gbc.gridy = 2;
		forecastPanel.add(dayLbs[1], gbc);
		
		dayLbs[2] = new JLabel("Day 3");
		gbc.gridx = 0;
		gbc.gridy = 4;
		forecastPanel.add(dayLbs[2], gbc);
		
		dayLbs[3] = new JLabel("Day 4");
		gbc.gridx = 0;
		gbc.gridy = 6;
		forecastPanel.add(dayLbs[3], gbc);
		
		dayLbs[4] = new JLabel("Day 5");
		gbc.gridx = 0;
		gbc.gridy = 8;
		forecastPanel.add(dayLbs[4], gbc);
		
		dayLbs[5] = new JLabel("Day 6");
		gbc.gridx = 0;
		gbc.gridy = 10;
		forecastPanel.add(dayLbs[5], gbc);
		
		
		fore = new JLabel[6];
							
		fore[0] = new JLabel("rain");
		gbc.gridx = 0;
		gbc.gridy = 1;
		forecastPanel.add(fore[0], gbc);
							
							
		fore[1] = new JLabel("sunny");
		gbc.gridx = 0;
		gbc.gridy = 3;
		forecastPanel.add(fore[1], gbc);
							
							
		fore[2] = new JLabel("hot");
		gbc.gridx = 0;
		gbc.gridy = 5;
		forecastPanel.add(fore[2], gbc);
							
							
		fore[3] = new JLabel("snow");
		gbc.gridx = 0;
		gbc.gridy = 7;
		forecastPanel.add(fore[3], gbc);
							
							
		fore[4] = new JLabel("cloudy");
		gbc.gridx = 0;
		gbc.gridy = 9;
		forecastPanel.add(fore[4], gbc);
							
							
		fore[5] = new JLabel("tornado");
		gbc.gridx = 0;
		gbc.gridy = 11;
		forecastPanel.add(fore[5], gbc);
		
		refresh = new JButton("refresh");
		gbc.gridx = 1;
		gbc.gridy = 3;
		forecastPanel.add(refresh, gbc);
		
		refresh.addMouseListener(new RefreshListener());
							
												
		forecastPanel.setPreferredSize(new Dimension(300, 500));
		forecastPanel.setVisible(true);
		JFrame frame2 = new JFrame ("Six Day Forecast");
		frame2.add( forecastPanel);
		frame2.setResizable(false);
		frame2.pack();
		frame2.setVisible(true);
	}
	
	private void placeLabel(JPanel pane, GridBagConstraints gbc, String id, int y) {
		JLabel lb = new JLabel(id + ": ");
		gbc.gridx = 0;
		gbc.gridy = y;
		pane.add(lb, gbc);
	}
	
	private boolean isNetworkUp()
	{
		// google's faq should always be up, and it's 1/10 the size of google.com
		// also, it doesn't redirect
		try
		{
			URL url = new URL("https://www.google.com/intl/en/policies/faq/");
			HttpURLConnection c = (HttpURLConnection) url.openConnection();
			if (c.getResponseCode() == 200)
				return true;
			else
				return false; // something needs to be done!! by the user, of course
			
		}
		catch (MalformedURLException e)
		{
			return false;
		}
		catch (IOException e)
		{
			return false;
		}
	}
	
	
	// TODO: pops up an error dialog with the specified message
	private void errorDialog(String msg)
	{
		JOptionPane.showMessageDialog(forecastPanel, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private class RefreshListener implements MouseListener
	{
		public void mouseClicked(MouseEvent event)
		{
			// first, check for network connection
			if (isNetworkUp())
			{
				try
				{
					Calendar tstamp = Calendar.getInstance(); // gets now in the current locale
					String now = GetWeather.buildTimestamp(tstamp.get(Calendar.YEAR),
													tstamp.get(Calendar.MONTH)+1, // 0-indexed months
													tstamp.get(Calendar.DAY_OF_MONTH),
													tstamp.get(Calendar.HOUR_OF_DAY));
					
					tstamp.add(Calendar.DAY_OF_YEAR, 1);
					String tm = GetWeather.buildTimestamp(tstamp.get(Calendar.YEAR),
													tstamp.get(Calendar.MONTH)+1,
													tstamp.get(Calendar.DAY_OF_MONTH),
													tstamp.get(Calendar.HOUR_OF_DAY));

					String units = isMetric? "m" : "e";
					
					GetWeather w = new GetWeather(ZIP, now, tm, units);
					refreshLabels(w);
				}
				catch (InputMismatchException e)
				{
					errorDialog("Enter a 5 numeric digit US ZIP code");
				}
				
			}
			else
			{
				errorDialog("Check your network connection");
			}
		}
		
		public void mouseEntered(MouseEvent event) {}
		public void mouseExited(MouseEvent event) {}
		public void mouseReleased(MouseEvent event) {}
		public void mousePressed(MouseEvent event) {}
		
		private void refreshLabels(GetWeather w) {
			String base = "high: %d    low: %d    precip: %d";
			Calendar date = Calendar.getInstance();
			for (int i=0; i<6; i++) {
				Weather day = w.getParsed().get(i);
				fore[i].setText(String.format(base, day.getHigh(), day.getLow(), day.getPrecipitation()));
				dayLbs[i].setText(date.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " +
								date.get(Calendar.DAY_OF_MONTH));
				date.add(Calendar.DAY_OF_MONTH, 1);
			}
			
		}
	}
}

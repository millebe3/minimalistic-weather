//**************************************************************************************************

// 211 Final Project				Ben Miller, Jason, Dorothy

//

// Gui for SimpleWeather

//**************************************************************************************************



import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;





import javax.swing.*;

import javax.swing.border.Border;



public class SimpleWeather extends JFrame

{

	

	private static final long serialVersionUID = 1L;

	private JFrame frame;

	private JPanel contents;

	private JPanel panelCenter;

	

	private JPanel panelNorth;

	private JPanel panelSouth;

	

	private Border borderContents = BorderFactory.createEmptyBorder(10, 10, 10, 10);

	private Border borderRegion = BorderFactory.createLineBorder(Color.BLACK, 1);

	private Color colorContents = Color.BLUE;

	private Font fontLabels = new Font(Font.DIALOG, Font.BOLD, 16);

	

	private JTextField enterZip;

	

	private JButton getWeather, usMetric;

	





	private JLabel highTemp, lowTemp, humid, chanceRain;

	private JLabel high, low, humidity, howMuchRain;

	private JLabel temperature, cloudCover, windSpeed;

	private JLabel temp, cloud, wind, zip, space;

	public SimpleWeather()

	{

		

		

		// create main panel

		setFonts();

		contents = new JPanel(new GridBagLayout());

		contents.setLayout(new BorderLayout());

		contents.setBorder(borderContents);

		contents.setBackground(colorContents);

		setContentPane(contents);

		

	

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(10,10,10,10);

		

		

		

		

		

		// create center panel and add labels

		//*******************************************************************

		panelCenter = new JPanel(new GridBagLayout());

		panelCenter.setPreferredSize(new Dimension(800, 300));

		contents.add(panelCenter, BorderLayout.CENTER);

		

		

		

		temperature = new JLabel("Temperature: ");

		gbc.gridx = 0;

		gbc.gridy = 0;

		panelCenter.add(temperature,gbc);

		

		

		 high = new JLabel("High: ");

		gbc.gridx = 0;

		gbc.gridy = 1;

		panelCenter.add(high,gbc);

		

		 low = new JLabel("Low: ");

		gbc.gridx = 0;

		gbc.gridy = 2;

		panelCenter.add(low,gbc);

		

		 humidity = new JLabel("Humidity: ");

		gbc.gridx = 0;

		gbc.gridy = 3;

		panelCenter.add(humidity,gbc);

		

		 chanceRain = new JLabel("Chance of Rain: ");

		gbc.gridx = 0;

		gbc.gridy = 4;

		panelCenter.add(chanceRain, gbc);

		

		 cloudCover = new JLabel("Cloud Cover: ");

		gbc.gridx = 0;

		gbc.gridy = 5;

		panelCenter.add(cloudCover, gbc);

		

		 windSpeed = new JLabel("Wind Speed: ");

		gbc.gridx = 0;

		gbc.gridy = 6;

		panelCenter.add(windSpeed, gbc);

		

		 temp = new JLabel("Hot");

		gbc.gridx = 1;

		gbc.gridy = 0;

		panelCenter.add(temp,gbc);

		

		 highTemp = new JLabel("90");

		gbc.gridx = 1;

		gbc.gridy = 1;

		panelCenter.add(highTemp,gbc);

		

		 lowTemp = new JLabel("Zero");

		gbc.gridx = 1;

		gbc.gridy = 2;

		panelCenter.add(lowTemp,gbc);

		

		 humid = new JLabel("Super humid");

		gbc.gridx = 1;

		gbc.gridy = 3;

		panelCenter.add(humid,gbc);

		

		 howMuchRain = new JLabel("Lots of Rain");

		gbc.gridx = 1;

		gbc.gridy = 4;

		panelCenter.add(howMuchRain,gbc);

		

		 cloud = new JLabel("super cloudy ");

		gbc.gridx = 1;

		gbc.gridy = 5;

		panelCenter.add(cloud, gbc);

		

		 wind = new JLabel("no wind ");

		gbc.gridx = 1;

		gbc.gridy = 6;

		panelCenter.add(wind, gbc);

		//------------------------------------------------------------------------

		



		

		

		

		

		

	

		

		// create top panel and add title label to it

		//**************************************************************************

		panelNorth = new JPanel();

		panelNorth.setBorder(borderRegion);

		panelNorth.setPreferredSize(new Dimension(500, 50));

		JLabel space2 = new JLabel("               ");

		panelNorth.add(space2);

		JLabel space3 = new JLabel("                         ");

		panelNorth.add(space3);

		JLabel lblNorth = new JLabel("SimpleWeather");

		panelNorth.add(lblNorth);

		JLabel space4 = new JLabel("                         ");

		panelNorth.add(space4);

		JLabel date = new JLabel("March 2nd");

		panelNorth.add(date);

		

		contents.add(panelNorth, BorderLayout.NORTH);

		//**************************************************************************

		

		

		

		

		

		// create bottom panel and add labels 

		//**************************************************************************

		panelSouth = new JPanel();

		panelSouth.setBorder(borderRegion);

		panelSouth.setPreferredSize(new Dimension(500, 100));

		contents.add(panelSouth, BorderLayout.SOUTH);

		

		

		

		zip = new JLabel("Enter your zip code: ");

		gbc.gridx = 0;

		gbc.gridy = 0;

		panelSouth.add(zip, gbc);

		

		enterZip = new JTextField(5);

		gbc.gridx = 1;

		gbc.gridy = 0;

		panelSouth.add(enterZip, gbc);

		

		

		getWeather = new JButton("Get Weather Info");

		gbc.gridx = 2;

		gbc.gridy = 0;

		panelSouth.add(getWeather, gbc);

		

		

		 space = new JLabel("                             ");

		gbc.gridx = 3;

		gbc.gridy = 0;

		panelSouth.add(space, gbc);

		

		usMetric = new JButton("US/Metric");

		gbc.gridx = 4;

		gbc.gridy = 0;

		panelSouth.add(usMetric, gbc);

	

		

		GetWeatherListener listener1 = new GetWeatherListener();

		getWeather.addActionListener(listener1);

		UsMetricListener listener2 = new UsMetricListener();

		usMetric.addActionListener(listener2);

		

		//***************************************************************************

		

		

		

		

		// create new jframe and put the entire contents panel inside.  

		// this is done to prevent GUI resizing

		frame = new JFrame("SimpleWeather");

		frame.add(contents);

		frame.setResizable(false);

		frame.pack();

		frame.setVisible(true);

		

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		

		

		

	}

	

	// needed in order to set fonts

	private void setFonts()

	{

		UIManager.put("Label.font", fontLabels);

		

	}

	

	

	

	

	

			

		

			

			// listener class for the getWeather button

			private class GetWeatherListener implements ActionListener

			{

				public void actionPerformed (ActionEvent event)

				{

					

					

					



					

				}	

				

			}

			

			

			

			// listener class for the usMetric button

						private class UsMetricListener implements ActionListener

						{

							public void actionPerformed (ActionEvent event)

							{

								

								

								



								

							}	

							

						}

			

			

						

			// main method

			public static void main(String[] args)

			{

				SimpleWeather gui = new SimpleWeather();

				gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				

			}

			

}






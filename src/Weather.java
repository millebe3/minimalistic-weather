/**
 * Weather stores parsed weather data in various instance variables, and provides some methods
 * to make it easy to display that data
 * 
 * @author Dorothy Carter
 * @version 1.0.1
 */
class Weather {
    protected String timestamp;
    protected boolean metric;
    protected int hi, lo, current;
    protected int precip, wDirection, wSpeed;
    protected int cloud, relHum;
    
    /**
     * initializes the various variables
     */
    public Weather() {
    	timestamp = "";
    	metric = false;
    	hi = 0;
    	lo = 0;
    	current = 0;
    	precip = 0;
    	wDirection = 0;
    	wSpeed = 0;
    	cloud = 0;
    	relHum = 0;
    }
    
    /**
     * @return the high and the low stored within
     */
    @Override
    public String toString() {
    	return hi + " " + lo;
    }
    
    /**
     * @return a string representation of the cloud cover percentage
     */
    public String cloudiness() {
    	if (cloud <= 10)
    		return "clear";
    	else if (cloud <= 50)
    		return "scattered";
    	else if (cloud <= 90)
    		return "broken";
    	else
    		return "overcast";
    }
    
    /**
     * @return a string representation of the wind speed and direction
     */
    public String windiness() {
    	String wind = "" + wSpeed;
    	if (metric)
    		wind += "M/S";
    	else
    		wind += "KN";
    	
    	wind += " ";
    	
    	if (wDirection <= 45)
    		wind += "N";
    	else if (wDirection <= 90)
    		wind += "NE";
    	else if (wDirection <= 135)
    		wind += "E";
    	else if (wDirection <= 180)
    		wind += "SE";
    	else if (wDirection <= 225)
    		wind += "S";
    	else if (wDirection <= 270)
    		wind += "SW";
    	else if (wDirection <= 315)
    		wind += "W";
    	else
    		wind += "NW";
    	
    	return wind;
    }
    
    /**
     * @return the timestamp for the data
     */
    public String getTimestamp() {
    	return timestamp;
    }
    
    /**
     * @return the unit as a boolean. True if metric, false if imperial
     */
    public boolean isMetric() {
    	return metric;
    }
    
    /**
     * @return the high
     */
    public int getHigh() {
    	return hi;
    }
    
    /**
     * @return the low
     */
    public int getLow() {
    	return lo;
    }
    
    /**
     * @return the current temperature
     */
    public int getCurrent() {
    	return current;
    }
    
    /**
     * @return the chance of precipitation
     */
    public int getPrecipitation() {
    	return precip;
    }
    
    /**
     * @return the wind direction in degrees
     */
    public int getDirection() {
    	return wDirection;
    }
    
    /**
     * @return the wind speed
     */
    public int getSpeed() {
    	return wSpeed;
    }
    
    /**
     * @return cloud cover as a percent
     */
    public int getCloud() {
    	return cloud;
    }
    
    /**
     * @return humidity percentage
     */
    public int getHumidity() {
    	return relHum;
    }
    
    /**
     * @param the new time
     */
    public void setTimestamp(String time) {
    	timestamp = time;
    }
    
    /**
     * @param the new unit. True if metric, false if imperial
     */    
    public void setMetric(boolean unit) {
    	metric = unit;
    }
    
    /**
     * @param the new high for the day
     */   
    public void setHigh(int newHi) {
    	hi = newHi;
    }

    /**
     * @param the new low for the day
     */
    public void setLow(int newLo) {
    	lo = newLo;
    }
  
    /**
     * @param the new current temperature
     */
    public void setCurrent(int newCurrent) {
    	current = newCurrent;
    }
    
    /**
     * @param the new chance of precipitation
     */
    public void setPrecipitation(int newPrecip) {
    	precip = newPrecip;
    }
    
    /**
     * @param the new wind direction
     */
    public void setDirection(int newDirection) {
    	wDirection = newDirection;
    }
    
    /**
     * @param the new wind speed
     */
    public void setSpeed(int newSpeed) {
    	wSpeed = newSpeed;
    }
    
    /**
     * @param the new cloud cover
     */
    public void setCloud(int newCloud) {
    	cloud = newCloud;
    }
    
    /**
     * @param the new humidity
     */
    public void setHumidity(int newHm) {
    	relHum = newHm;
    }
}

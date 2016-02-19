// Weather.java     Dorothy Carter
// this class is for storing weather data

class Weather {
    protected String timestamp;
    protected boolean metric;
    protected int hi, lo, current;
    protected int precip, wDirection, wSpeed;
    protected int cloud, relHum;
    
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
    
    public String toString() {
    	return hi + " " + lo;
    }
    
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
    
    public String getTimestamp() {
    	return timestamp;
    }
    
    public boolean getMetric() {
    	return metric;
    }
    
    public int getHigh() {
    	return hi;
    }
    
    public int getLow() {
    	return lo;
    }
    
    public int getCurrent() {
    	return current;
    }
    
    public int getPrecipitation() {
    	return precip;
    }
    
    public int getDirection() {
    	return wDirection;
    }
    
    public int getSpeed() {
    	return wSpeed;
    }
    
    public int getCloud() {
    	return cloud;
    }
    
    public int getHumidity() {
    	return relHum;
    }
    
    public void setTimestamp(String time) {
    	timestamp = time;
    }
    
    public void setMetric(boolean unit) {
    	metric = unit;
    }
    
    public void setHigh(int newHi) {
    	hi = newHi;
    }
    
    public void setLow(int newLo) {
    	lo = newLo;
    }
    
    public void setCurrent(int newCurrent) {
    	current = newCurrent;
    }
    
    public void setPrecipitation(int newPrecip) {
    	precip = newPrecip;
    }
    
    public void setDirection(int newDirection) {
    	wDirection = newDirection;
    }
    
    public void setSpeed(int newSpeed) {
    	wSpeed = newSpeed;
    }
    
    public void setCloud(int newCloud) {
    	cloud = newCloud;
    }
    
    public void setHumidity(int newHm) {
    	relHum = newHm;
    }
}

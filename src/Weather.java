// Weather.java     Dorothy Carter
// This class represents a leaf of that tree we call weather

class Weather {
    protected String timestamp;
    protected boolean metric;
    protected int hi, lo, current;
    protected int precip, wDirection, wSpeed;
    protected int cloud, relHum;
    
    public Weather(String date, boolean isMetric, int high, int low, int apparent, int chancePrecip, int windD,
                   int windS, int cloudCover, int relativeHumidity) {
                   
        timestamp = date;
        metric = isMetric;
        hi = high;
        lo = low;
        current = apparent;
        precip = chancePrecip;
        wDirection = windD;
        wSpeed = windS;
        cloud = cloudCover;
        relHum = relativeHumidity;
    }
    
    // finish out getters and setters
}

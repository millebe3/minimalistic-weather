#XML parsing using SAX
The objective is for this class to take some XML and return a collection of Weather objects, each of which stand for a day

##Weather objects
A Weather object will stand for a day
* String timestamp (in timestamp format YYYY-MM-DDTHH:MM)
* boolean metric (true if it is metric, false if it's imperial)
* int hi (in Fahrenheit/Celcius)
* int lo (in Fahrenheit/Celcius)
* int current (in Fahrenheit/Celcius)
* int precip (precipitation; as percent)
* int wDirection (wind direction; in degrees true)
* int wSpeed (wind speed; in knots/meters per second)
* int cloud (as percent)
* int relHum (relative humidity; as a percent)
* getters & setters
* include methods that convert between cloud cover, wind direction to more useful words ("NE", "partly cloudy", etc) ?

Now, for elements that are in "k-p12h-n14-3" or "k-p3h-n40-4" time formats, the value stored in the Weather object will be the value closest to the current time. All others will be discarded.


##Actual parsing
[java SAX docs](https://docs.oracle.com/javase/7/docs/api/org/xml/sax/package-summary.html)


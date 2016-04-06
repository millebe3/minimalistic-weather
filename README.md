# minimalistic-weather
A minimalistic weather app in Java. Made for a class.

If downloading the file, make sure that the JavaSE in .classpath is the version on your computer (currently, it's 1.8).
##Todo:
+ ~~[NDFD REST service](http://graphical.weather.gov/xml/rest.php)~~
  * ~~Do timestamps (shouldn't be too bad. In the format of YYYY-MM-DDTHH:MM) There's a method in GetWeather that builds them for you.~~
  * ~~Use ZIP codes instead of lat/long because that's way easier~~
  * ~~Units - not hard at all. Provide an option for the user to switch b/w metric and imperial~~
  * ~~Figure out which [elements](http://graphical.weather.gov/xml/docs/elementInputNames.php "element names") to use for the app.~~
+ ~~[Parse the XML](https://docs.oracle.com/cd/B28359_01/appdev.111/b28394/adx_j_parser.htm "java xml parsing"). I know at least two of those libraries (DOM & SAX) are built in.~~
+ Do the GUI. Since it's minimalistic, it should use as little memory, processing power, and time as possible.

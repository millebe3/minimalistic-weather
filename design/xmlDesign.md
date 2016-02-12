#XML parsing using SAX
The objective is for this class to take some XML and return a linked-list tree.

##anatomy of a tree
thre tree returneds
           root node
       /   /    |  \   \
     day1 day2 day3 ... dayN
    /   \
  12h   12h
 /   \ /   \
3h  ...... 3h

a 3h period node (Node class):
* timestamp
* temperature
  * high (int; Fahrenheit/Celcius)
  * low (int; Fahrenheit/Celcius)
  * apparent (int; Fahrenheit/Celcius)
* precipitation (int; percent)
* wind
  * speed (int; knots/meters per second)
  * direction (int; degrees true)
* cloud cover (int; percent)
* relative humidity (int; percent)
* any other elements


##Actual parsing
[java SAX docs](https://docs.oracle.com/javase/7/docs/api/org/xml/sax/package-summary.html)


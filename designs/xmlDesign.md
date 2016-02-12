#XML parsing using SAX
The objective is for this class to take some XML and return a tree.

##anatomy of a tree
the tree returned is used for traversing time periods. It's subdivided into 24h periods, then 12h, then 6h, then 3h, making it a binary tree.

a node (Node class):
* timestamp
* right child
* left child

a 3h period leaf (Weather class):
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
[java SAX docs](https://docs.oracle.com/javase/7/docs/api/org/xml/sax/packagesummary.html)

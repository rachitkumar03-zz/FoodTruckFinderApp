# FoodTruckFinderApp (Uber Challenge)
-------
A geolocation based web service designed to display nearby Food Trucks within San Francisco

### Problem Statement  
-------
Create a service that tells the user what types of food trucks might be found near a specific location on a map.

### Development Track
-------
Backend Development: Include a minimal front-end (e.g. a static view or API docs). Write, document and test your API as if it will be used by other services

### Hosting URL
-------
[Link to the host](http://foodtruckfinderapp-1066.appspot.com/)

[Link to the web service](http://foodtruckfinderapp-1066.appspot.com/find/37.7833:-122.4167)


## Non technical details (How to run and what to expect)
-------

* Go to the [link](http://foodtruckfinderapp-1066.appspot.com/) for an instance to the App. (Hosted on Google App Engine)
* The webpage loads and requests the client to use their location. If accepted, it Geolocates the location and presents a map view. If geolocation not supported or disabled, defaults to center of San francisco.
* Click anywhere on the map to drop a pin and the service displays all the food trucks in a 0.25 mile radius. 
* Clicking of the food truck pin displays the truck details within a google map info window.
* Select a new location by clicking on the map again to clear off the old markers and get new trucks in the requested vicinity. 
* An alert is shown incase there are no food trucks in a 0.25 mile radius.


## Technical Details
-------

###Backend
-------
    - Java
    - Maven
    - JUnit
    - Key Features: Restful Service, Cache Service, Geohash Cache, Factory Pattern WebService Impl, JUnit Tests

#### Impelmentation Details:

* In the interest of time, I used the technologies that I already had an experience working with (~ 2 yrs) . Nevertheless I do have strong CS fundamental background and can definitely pick up a new technology quickly, if needed. 
* Backend is designed as a restful webservice consuming raw data from another api [Food Trucks](https://data.sfgov.org/Economy-and-Community/Mobile-Food-Facility-Permit/rqzj-sfat?)
* The Json data is formatted and stored as Java objects.
* Since pulling data from the external API is an expensive process, implemented a Caching service to hold all the data to avoid an API call for every request made from the client and and improve search efficiency. The Cache is implemented using a Singleton design pattern to aviod more than once instance of it to exist.
* Currently set the cache update time to 180 minutes (configurable value) and update the cache with a timestamp every time it is reloaded.
* Along with caching the food truck data, the cache also contains a mapping of the FoodTruck Id to a calcuated geoHash of the location of the trucks. Created this for efficient lookups to search food trucks based on location.
* Designed the web service consumer classes using a factory pattern incase other APIs need to be consumed in the future. Hence Future Proof.
* Used the SimpleLatLng package to get basic spatial storage and computational capabilities.
* Implemented a restFul GET webservice call to produce a json for the requested coordinates sent forth from the client side.
* The web service is generic and can be plugged into any client requesting data.
* What good is a service that fails to render required functionality. Units Tests to the resque. Wrote JUnit tests for the backend that are configured to run everytime a deploy script is executed. Any broken functionalities can be addressed if tests fail.
* The project design arranges the Model, view and controller based classes in different packages. All the configurable constants and utility functions are placed separately from the MVC classes. Test classes are placed in a separate package.
* As a part of development best practices, documented each class and method using java docs for the ease of understanding.


###Frontend
-------
    - javascript
    - jquery
    - Ajax
    - HTML
    - CSS
    - GoogleMap API
    - Key Features: Geolocation, Map Listeners

#### Impelmentation Details:

* The display and operations on a map view were implemented using the Google Maps API v3 for javascript.
* Loading the web page geolocates the client and displays the google map centered at the location. Used the HTML5 methods to perform geolocation.
* Implemented map listeners using javascript to observe any click events on the map recall the web service and rerender the results.
* Implemented display of marker and marker infowindows to show food truck location and details using javascript and formatted the displays using css.
* The client sends out a GET webservice request along with the selected location to the server to fetch nearby trucks from the api or the cache.
* An ajax call is in place to update markers in case the client requests a new location.


###Deployment
-------

* Used Google App Engine to deploy/run/test code on both a dev server and a production instance 

## Room for Improvements

* Adding more data to increase coverage. Multiple webservice clients can be plugged in to the factory pattern.
* Implementation of a Spatial data structure to hold data and make query search more efficient, like an R-tree implementation or maybe an indexing server like Solr.
* Configuring a noSQL database with spatial capabilities, like MongoDB, to index and query data. Left this part out due to small volume of current data. Developed a Cache in place instead.

## Code Coverage

* The Web service and the frontend have been designed and developed completely by me.
* Used a few external libraries for Spatial operation. SimpleLatLng v1.3

## Checklist For production deploy

* Architectural Design
* Web Service Development
* Front End development
* Automated Unit test development
* Testing frontend (fuctionality)
* End to End Testing
* Documentation
* Code commit (to Github)
* Deployment

## Where I spent my time

* Invested half a day learning the google Map Api and frontend technologies.
* Spent a few hours on setting up the project with Google app engine for dev and production deployments.
* Worked for a day and a half on writing the web service, frontend and automated unit tests.
* Deployed the service to production and committed it to GitHub.

## My Profile

* [LinkedIn Profile](https://www.linkedin.com/in/rachitkumar03)

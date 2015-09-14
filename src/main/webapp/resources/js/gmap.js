/**
 * Javascript written to load / manage googlr map events
 * @author Rachit Kumar
 */

var markerArray = [], marker;
var map;
var lat, lng;

window.onload = initMap; 

function initMap() {
	var mapOptions = {
			center : new google.maps.LatLng(37.7833, -122.4167),
			zoom : 16,
			mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	var infoWindow = new google.maps.InfoWindow();

	geolocate(infoWindow);
	//This will load your map with default location co-ordinates.
	map = new google.maps.Map(document.getElementById("map"), mapOptions);

	//To capture click event on the map
	google.maps.event.addListener(map, 'click', function(event) {
		sendRequest(event.latLng.lat() + ":" + event.latLng.lng());
		deleteMarkers();
		placeMarker(event.latLng, map);
	});
}

function geolocate(infoWindow) {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var pos = {
					lat : position.coords.latitude,
					lng : position.coords.longitude
			};

			infoWindow.setPosition(pos);
			infoWindow.setContent('Location found.');
			map.setCenter(pos);
		}, function() {
			handleLocationError(true, infoWindow, map.getCenter());
		});
	} else {
		// Browser doesn't support Geolocation
		handleLocationError(false, infoWindow, map.getCenter());
	}
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
	infoWindow.setPosition(pos);
	infoWindow
	.setContent(browserHasGeolocation ? 'Error: The Geolocation service failed.'
			: 'Error: Your browser doesn\'t support geolocation.');
}

var getInfoWindow = (function() {
	var _instance = null;
	return function() {
		if (_instance == null) {
			_instance = new google.maps.InfoWindow({
				maxWidth : 300,
				maxHeight : 500
			});
		}
		return _instance;
	};
})();

function popitup(url) {
	newwindow = window.open(url, 'name', 'height=800,width=800');
	if (window.focus) {
		newwindow.focus();
	}
	return false;
}

function placeMarker(location, map) {
	if (marker) {
		marker.setPosition(location);
	} else {
		marker = new google.maps.Marker({
			position : location,
			map : map,
			icon : 'resources/img/pin.png'
		});
	}
}

function sendRequest(param) {
	var servletURL = "/find/" + param;
	if (window.XMLHttpRequest) {
		try {
			req = new XMLHttpRequest();
		} catch (e) {
			req = false;
		}
		if (req) {
			req.onreadystatechange = callAjax;
			req.open("GET", servletURL, true);
			req.send();
		}
	}
}

function callAjax() {
	if (req.readyState == 4 && req.status == 200) {
		var jsonResponse = jQuery.parseJSON(req.responseText);
		processJson(jsonResponse);
	}
}

function processJson(jsonResponse) {
	var location, locMarker, openWindow=null;
	if (jsonResponse.length == 0)
		alert("No Food Trucks in the vicinity!");
	$.each(jsonResponse, function(key, value) {
		location = new google.maps.LatLng(value.latitude, value.longitude);
		locMarker = new google.maps.Marker({
			map : map,
			draggable : false,
			position : location,
			icon : 'resources/img/foodtruck.png'
		});
		var markerContent = "<div id=\"markerwindow\"><h1>" + value.applicant + "</H1><H4>"
		+ value.address
		+ "</H4><table><tbody><tr><td><ul><li><a href=\""
		+ value.schedule + "\" onclick=\"return popitup('"
		+ value.schedule + "')\"><b>Schedule</b></a></li><li><b>Hours:</b> "
		+ value.dayshours + "</li></td><td><ul><li><b>Lot No:</b> "
		+ value.lot + "</li><li><b>Permit: </b> " + value.permit
		+ "</li></td></tr></tbody></table><div class=\"loc\"><b>Location Desc: </b>"
		+ value.locationdescription + "</div><div class=\"loc\"><b>MENU: </b>"
		+ value.fooditems + "</div><div>";

		locMarker.contentString = markerContent;

		locMarker['infowindow'] = new google.maps.InfoWindow({
			content : markerContent
		});

		google.maps.event.addListener(locMarker, 'click', function() {
			if (openWindow != null)
				openWindow.close();
			this['infowindow'].setContent(this.contentString);
			this['infowindow'].open(map, this);
			openWindow = this['infowindow'];
		});
		markerArray.push(locMarker);
	});
}

function deleteMarkers() {
	for (var i = 0; i < markerArray.length; i++) {
		markerArray[i].setMap(null);
	}
	markerArray = [];
}
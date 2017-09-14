package com.boaheninc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.boaheninc.domain.Location;
import com.boaheninc.service.LocationService;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	@Autowired
	private LocationService locationService;
	
	@GetMapping("v1/location/{locationId}")
	public Location getStudent(@PathVariable("locationId") Integer locationId){
		Location location=locationService.getLocation(locationId);
		return location;
	}
	
	@PostMapping("v1/location/")
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	public Location saveLocation(@RequestBody Location location){
		return locationService.saveLocation(location);
	}
	
	@GetMapping("v1/location/allLocations")
	public ResponseEntity<Iterable<Location>> getAllSacedLocations(){
		Iterable<Location> allLocations=locationService.getAllSavedLocations();
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(allLocations);
	}
	
}

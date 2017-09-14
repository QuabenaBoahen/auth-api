package com.boaheninc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boaheninc.domain.Location;
import com.boaheninc.repository.LocationRepository;

@Service
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;
	
	public Iterable<Location> getAllSavedLocations(){
		return locationRepository.findAll();
	}
	
	public Location getLocation(int locationId){
		return locationRepository.findOne(locationId);
	}
	
	public Location saveLocation(Location location){
		return locationRepository.saveAndFlush(location);
	}

}

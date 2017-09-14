package com.boaheninc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.boaheninc.domain.Location;
import com.boaheninc.service.LocationService;

@Controller
public class IndexController {
	@Autowired
	private LocationService locationService;
	
	@GetMapping("/allLocations")
	public String allSavedLocations(Model model){
		Iterable<Location> allLocations=locationService.getAllSavedLocations();
		model.addAttribute("allLocations", allLocations);
		return "locations";
	}

}

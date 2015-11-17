package be.faros.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vaadin.data.util.BeanItemContainer;

import be.faros.entities.Location;
import be.faros.services.ClimateWatchEventService;

@Controller
@RequestMapping("/climatewatch")
class ClimateWatchEventController {
	private final ClimateWatchEventService eventService; 

	@Autowired
	ClimateWatchEventController(ClimateWatchEventService eventService){
		this.eventService = eventService;
	}
	
//	@RequestMapping(method = RequestMethod.GET)
//	ModelAndView findAll() {
//		return new ModelAndView(CLIMATEWATCH_VIEW, "climatewatchevent", eventService.findAll());
//
//	}
	BeanItemContainer<Location> beans = new BeanItemContainer<Location>(Location.class);
//	List<Location> locations = eventService.findAllLocations();
//	for (Location location : locations){
//		beans.addItem(location);
//	}

}
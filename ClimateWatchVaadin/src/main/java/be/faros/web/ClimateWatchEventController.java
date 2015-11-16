package be.faros.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.faros.services.ClimateWatchEventService;

@Controller
@RequestMapping("/climatewatch")
class ClimateWatchEventController {
	private static final String CLIMATEWATCH_VIEW = "climatewatch";
	private final ClimateWatchEventService eventService; 

	@Autowired
	ClimateWatchEventController(ClimateWatchEventService eventService){
		this.eventService = eventService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView findAll() {
		return new ModelAndView(CLIMATEWATCH_VIEW, "climatewatchevent", eventService.findAll());

	}

}
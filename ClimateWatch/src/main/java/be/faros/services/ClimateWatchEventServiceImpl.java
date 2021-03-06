package be.faros.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.faros.dao.ClimateWatchEventDAO;
import be.faros.entities.ClimateWatchEvent;
import be.faros.entities.Location;

@Service("eventService")
public class ClimateWatchEventServiceImpl implements ClimateWatchEventService, Serializable{
	private static final long serialVersionUID = 1L;
	private ClimateWatchEventDAO eventDAO;
	
	@Autowired
	public void setEventDAO(ClimateWatchEventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}
	
	@Override
	public List<ClimateWatchEvent> findAll(){
		return eventDAO.findAll();
	}
	
	@Override
	public List<ClimateWatchEvent> findByDateAndLocation(Date utilDate, long location){
		return eventDAO.findByDateAndLocation(utilDate, location);
	}
	
	@Override
	public
	List<Location> findAllLocations(){
		return eventDAO.findAllLocations();
	}
}
package be.faros.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.faros.dao.ClimateWatchEventDAO;
import be.faros.entities.ClimateWatchEvent;
import be.faros.entities.Location;

@Service("eventService")
public class ClimateWatchEventServiceImpl implements ClimateWatchEventService{
	private final ClimateWatchEventDAO eventDAO;
	@Autowired
	public ClimateWatchEventServiceImpl(ClimateWatchEventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}
	@Override
	public List<ClimateWatchEvent> findAll(){
		return eventDAO.findAll();
	}
	@Override
	public List<ClimateWatchEvent> findByDate(Date date){
		return eventDAO.findByDate(date);
	};

}
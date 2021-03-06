package be.faros.services;

import java.util.Date;
import java.util.List;

import be.faros.entities.ClimateWatchEvent;
import be.faros.entities.Location;

public interface ClimateWatchEventService {
	List<ClimateWatchEvent> findAll();
	List<ClimateWatchEvent> findByDateAndLocation(Date utilDate, long location);
	List<Location> findAllLocations();
}
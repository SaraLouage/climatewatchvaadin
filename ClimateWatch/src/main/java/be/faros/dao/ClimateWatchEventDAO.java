package be.faros.dao;

import java.util.Date;
import java.util.List;

import be.faros.entities.ClimateWatchEvent;
import be.faros.entities.Location;

public interface ClimateWatchEventDAO {
	List<ClimateWatchEvent> findAll();
	List<ClimateWatchEvent> findByDateAndLocation(Date utilDate, long location);
	List<Location> findAllLocations();
}

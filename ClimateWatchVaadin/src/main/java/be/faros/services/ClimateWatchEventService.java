package be.faros.services;

import java.util.List;

import be.faros.entities.ClimateWatchEvent;
import be.faros.entities.Location;

public interface ClimateWatchEventService {
	// ClimateWatchEvent read(long id);
	List<ClimateWatchEvent> findAll();
	List<Location> findAllLocations();

}

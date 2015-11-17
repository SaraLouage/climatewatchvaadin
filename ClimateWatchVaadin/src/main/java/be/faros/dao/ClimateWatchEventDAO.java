package be.faros.dao;

import java.util.List;

import be.faros.entities.ClimateWatchEvent;
import be.faros.entities.Location;

public interface ClimateWatchEventDAO {
//	public ClimateWatchEvent read(long id);
	List<ClimateWatchEvent> findAll();

	List<Location> findAllLocations();
}

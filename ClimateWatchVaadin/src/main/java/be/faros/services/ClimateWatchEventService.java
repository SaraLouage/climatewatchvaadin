package be.faros.services;

import java.util.List;

import be.faros.entities.ClimateWatchEvent;

public interface ClimateWatchEventService {
	// ClimateWatchEvent read(long id);
	List<ClimateWatchEvent> findAll();
}

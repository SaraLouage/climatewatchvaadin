package be.faros.dao;

import java.util.Date;
import java.util.List;

import be.faros.entities.ClimateWatchEvent;

public interface ClimateWatchEventDAO {
//	public ClimateWatchEvent read(long id);
	List<ClimateWatchEvent> findAll();
	List<ClimateWatchEvent> findByDate(Date utilDate);
}

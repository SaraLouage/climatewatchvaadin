package be.faros.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import be.faros.entities.ClimateWatchEvent;
import be.faros.entities.Location;

@Repository
public class ClimateWatchEventDAOImpl implements ClimateWatchEventDAO, Serializable {
	private static final long serialVersionUID = 1L;
	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// @Override
	// public ClimateWatchEvent read(long id) {
	//// return entityManager.find(ClimateWatchEvent.class, id);
	// return null;
	// }

	@Override
	public List<ClimateWatchEvent> findAll() {
		return entityManager.createQuery("select c from ClimateWatchEvent c", ClimateWatchEvent.class).getResultList();
	}

	@Override
	public List<ClimateWatchEvent> findByDateAndLocation(Date utilDate, long location) {
		
		Calendar beginCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();

		beginCal.setTime(utilDate);
		
		beginCal.set(beginCal.get(Calendar.YEAR), beginCal.get(Calendar.MONTH),
				beginCal.get(Calendar.DAY_OF_MONTH),
				0,
				0,
				0);
		
		endCal.set(beginCal.get(Calendar.YEAR), beginCal.get(Calendar.MONTH),
				beginCal.get(Calendar.DAY_OF_MONTH),
				23,
				59,
				59);
		
		
		return entityManager.createQuery("select c from ClimateWatchEvent c where (c.time between "
				+ ":startDate and :endDate) "
				+ "and (c.location_id =:location);",
				ClimateWatchEvent.class)
				.setParameter("startDate", beginCal)
				.setParameter("endDate", endCal)
				.setParameter("location", location)
				.getResultList();
		
	}
	@Override
	public List<Location> findAllLocations() {
		
		
		return entityManager.createQuery("select c from Locations c ;",
				Location.class)
				.getResultList();
	}
	

}

package be.faros.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import be.faros.entities.ClimateWatchEvent;

@Repository
public class ClimateWatchEventDAOImpl implements ClimateWatchEventDAO, Serializable {

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
	public List<ClimateWatchEvent> findByDate(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);		
		Calendar calendarStart = Calendar.getInstance();
		Calendar calendarEnd = Calendar.getInstance();
		
		calendarStart.set(calendar.YEAR, calendar.MONTH, calendar.DAY_OF_MONTH, 0,0,0);
		calendarEnd.set(calendar.YEAR, calendar.MONTH, calendar.DAY_OF_MONTH, 23,59,59);
		
	
		return entityManager.createQuery("select c from ClimateWatchEvent c where "
				+ "between ':calendarStart' and ':calendarEnd'",
				ClimateWatchEvent.class)
				.setParameter("calendarStart", calendarStart)
				.setParameter("calendarEnd", calendarEnd)
				.getResultList();
	}

}

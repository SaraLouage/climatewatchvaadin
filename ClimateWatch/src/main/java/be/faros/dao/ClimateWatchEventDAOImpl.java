package be.faros.dao;

import java.io.Serializable;
import java.util.Calendar;
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
	public List<ClimateWatchEvent> findByDate(java.util.Date utilDate) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(utilDate);

		
		String calendarString = ""+ cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR) + "%";

		
		return entityManager.createQuery("select c from ClimateWatchEvent c where c.time like "
				+ " :date",
				ClimateWatchEvent.class)
				.setParameter("date",calendarString)
				.getResultList();
		
	}

}

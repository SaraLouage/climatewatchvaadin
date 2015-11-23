package be.faros.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import be.faros.entities.ClimateWatchEvent;

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
	public List<ClimateWatchEvent> findByDate(Date utilDate) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(utilDate);
		@SuppressWarnings("deprecation")
		Date startDate = new Date(cal.get(Calendar.YEAR)-1900, cal.get(Calendar.MONTH)
				, cal.get(Calendar.DAY_OF_MONTH), 0,0,0);
		System.out.println("SSSSSSSSSSSTTTTTTTTTAAAAAAAARRRRRRRTTTTTTTTDDDDDDDAAAAAAAt");
		System.out.println(startDate);
		@SuppressWarnings("deprecation")
		Date endDate = new Date(cal.get(Calendar.YEAR)-1900, cal.get(Calendar.MONTH)
				, cal.get(Calendar.DAY_OF_MONTH), 23,59,59);
		
//		String calendarString = ""+ cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR)+"%";
		
		return entityManager.createQuery("select c from ClimateWatchEvent c where c.time between "
				+ ":startDate and :endDate",
				ClimateWatchEvent.class)
				.setParameter("startDate", startDate)
				.setParameter("endDate", endDate)
				.getResultList();
		
	}

}

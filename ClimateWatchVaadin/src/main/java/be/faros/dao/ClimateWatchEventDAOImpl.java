package be.faros.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import be.faros.entities.ClimateWatchEvent;
import be.faros.entities.Location;

@Repository
public class ClimateWatchEventDAOImpl implements ClimateWatchEventDAO {

	private EntityManager entityManager;
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

//	@Override
//	public ClimateWatchEvent read(long id) {
////		return entityManager.find(ClimateWatchEvent.class, id);
//		return null;
//	}

	@Override
	public List<ClimateWatchEvent> findAll() {
	return entityManager.createQuery("select c from ClimateWatchEvent c", ClimateWatchEvent.class)
	.getResultList();
	}
	@Override
	public List<Location> findAllLocations() {
	return entityManager.createQuery("select l from Locations l", Location.class)
	.getResultList();
	}
}

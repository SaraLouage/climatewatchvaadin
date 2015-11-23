package be.faros.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LOCATIONS")
public class Location implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long location_id;
//	@OneToMany
//	@JoinColumn(name="LOCATION_ID")
//	@OrderBy("time")
//	private Set<ClimateWatchEvent> events;
	private String location;
	public String getLocation() {
		return location;
	}


	public long getLocation_id() {
		return location_id;
	}


	//	public Set<ClimateWatchEvent> getEvents(){
//		return Collections.unmodifiableSet(events);
//	}
	public Location(long id, String location){
		this.location_id = id;
		this.location = location;
	}
	public Location(){}
	public String toString(){
		return location;
	}
}

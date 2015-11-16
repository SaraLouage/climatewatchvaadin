package be.faros.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="locations")
public class Location implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long LOCATION_ID;
	@OneToMany
	@JoinColumn(name="LOCATION_ID")
	@OrderBy("time")
	private Set<ClimateWatchEvent> events;
	private String location;
	public String getLocation() {
		return location;
	}
	public Set<ClimateWatchEvent> getEvents(){
		return Collections.unmodifiableSet(events);
	}
	public Location(long id, String location){
		this.LOCATION_ID = id;
		this.location = location;
	}
	public Location(){}
	public String toString(){
		return location;
	}
}

package be.faros.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="climatewatchevent")
public class ClimateWatchEvent implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	private Date time;
	private float degrees;
	@ManyToOne
	@JoinColumn(name="LOCATION_ID")
	private Location location;
	
	public Date getTime() {
		return time; 
	}
	public float getDegrees() {
		return degrees;
	}
	public Location getLocation(){
		return location;
	}
	public ClimateWatchEvent(){}
	public ClimateWatchEvent(long id, Date time, float degrees, Location location){
		this.id = id;
		this.time = time;
		this.degrees = degrees;
		this.location = location;
	}
}

package rs.opendata.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nezgode")
public class Accident {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "lat")
	private double latidute;

	@Column(name = "long")
	private double longitude;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getLatidute() {
		return latidute;
	}

	public void setLatidute(double latidute) {
		this.latidute = latidute;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}

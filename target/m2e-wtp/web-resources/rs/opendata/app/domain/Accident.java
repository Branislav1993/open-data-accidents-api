package rs.opendata.app.domain;

import java.util.Date;

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
	private double latitude;

	@Column(name = "lng")
	private double longitude;

	@Column(name = "date")
	private Date date;

	@Column(name = "type")
	private int type;

	@Column(name = "summary")
	private String summary;

	@Column(name = "precipitation")
	private String precipitation;

	@Column(name = "temperature")
	private Double temperature;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		return "Accident [id=" + id + ", latidute=" + latitude + ", longitude=" + longitude + ", date=" + date
				+ ", type=" + type + ", summary=" + summary + ", precipitation=" + precipitation + ", temperature="
				+ temperature + "]";
	}

	public boolean equals(Accident a) {
		if (this.getId() == a.getId())
			return true;
		else
			return false;
	}

}

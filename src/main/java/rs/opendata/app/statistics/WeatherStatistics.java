package rs.opendata.app.statistics;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WeatherStatistics {

	@Id
	private String weather;

	@Basic
	private Integer numberOfAccidents;

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public Integer getNumberOfAccidents() {
		return numberOfAccidents;
	}

	public void setNumberOfAccidents(Integer numberOfAccidents) {
		this.numberOfAccidents = numberOfAccidents;
	}

	@Override
	public String toString() {
		return "WeatherStatistics [weather=" + weather + ", numberOfAccidents=" + numberOfAccidents + "]";
	}

}

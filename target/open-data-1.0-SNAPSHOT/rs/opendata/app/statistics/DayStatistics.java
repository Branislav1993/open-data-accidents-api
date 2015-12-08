package rs.opendata.app.statistics;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DayStatistics {

	@Id
	private Integer dayOfWeek;

	@Basic
	private Integer numberOfAccidents;

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Integer getNumberOfAccidents() {
		return numberOfAccidents;
	}

	public void setNumberOfAccidents(Integer numberOfAccidents) {
		this.numberOfAccidents = numberOfAccidents;
	}

	@Override
	public String toString() {
		return "DayStatistics [dayOfWeek=" + dayOfWeek + ", numberOfAccidents=" + numberOfAccidents + "]";
	}

}

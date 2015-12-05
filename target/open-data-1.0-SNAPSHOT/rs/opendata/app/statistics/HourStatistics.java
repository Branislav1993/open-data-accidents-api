package rs.opendata.app.statistics;

public class HourStatistics {

	private Integer hour;
	private Integer numberOfAccidents;

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getNumberOfAccidents() {
		return numberOfAccidents;
	}

	public void setNumberOfAccidents(Integer numberOfAccidents) {
		this.numberOfAccidents = numberOfAccidents;
	}

	@Override
	public String toString() {
		return "HourStatistics [hour=" + hour + ", numberOfAccidents=" + numberOfAccidents + "]";
	}

}

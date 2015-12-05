package rs.opendata.app.statistics;

public class DayStatistics {

	private String month;
	private String dayOfWeek;
	private Integer dayOfMonth;
	private Integer numberOfAccidents;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Integer getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public Integer getNumberOfAccidents() {
		return numberOfAccidents;
	}

	public void setNumberOfAccidents(Integer numberOfAccidents) {
		this.numberOfAccidents = numberOfAccidents;
	}

	@Override
	public String toString() {
		return "DayStatistics [month=" + month + ", dayOfWeek=" + dayOfWeek + ", dayOfMonth=" + dayOfMonth
				+ ", numberOfAccidents=" + numberOfAccidents + "]";
	}
	
	

}

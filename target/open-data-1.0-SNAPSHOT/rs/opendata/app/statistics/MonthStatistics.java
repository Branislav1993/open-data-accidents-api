package rs.opendata.app.statistics;

public class MonthStatistics {

	private String month;
	private Integer numberOfAccidents;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getNumberOfAccidents() {
		return numberOfAccidents;
	}

	public void setNumberOfAccidents(Integer numberOfAccidents) {
		this.numberOfAccidents = numberOfAccidents;
	}

	@Override
	public String toString() {
		return "MonthStatistics [month=" + month + ", numberOfAccidents=" + numberOfAccidents + "]";
	}

}

package rs.opendata.app.statistics;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MonthStatistics {

	@Id
	private Integer month;

	@Basic
	private Integer numberOfAccidents;

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
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

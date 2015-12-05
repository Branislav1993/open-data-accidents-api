package rs.opendata.app.statistics;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QuarterStatistics {

	@Id
	private Integer quarter;

	@Basic
	private Integer numberOfAccidents;

	public Integer getQuarter() {
		return quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	public Integer getNumberOfAccidents() {
		return numberOfAccidents;
	}

	public void setNumberOfAccidents(Integer numberOfAccidents) {
		this.numberOfAccidents = numberOfAccidents;
	}

	@Override
	public String toString() {
		return "QuarterStatistics [quarter=" + quarter + ", numberOfAccidents=" + numberOfAccidents + "]";
	}

}

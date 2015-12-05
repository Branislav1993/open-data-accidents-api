package rs.opendata.app.statistics;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SummaryTypeStatistics {

	@Id
	private String summary;

	@Basic
	private Integer numberOfAccidents;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getNumberOfAccidents() {
		return numberOfAccidents;
	}

	public void setNumberOfAccidents(Integer numberOfAccidents) {
		this.numberOfAccidents = numberOfAccidents;
	}

	@Override
	public String toString() {
		return "SummaryTypeStatistics [summary=" + summary + ", numberOfAccidents=" + numberOfAccidents + "]";
	}

}

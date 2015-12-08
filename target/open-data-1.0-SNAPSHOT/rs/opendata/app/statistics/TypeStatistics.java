package rs.opendata.app.statistics;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TypeStatistics {

	@Id
	private Integer type;

	@Basic
	private Integer numberOfAccidents;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getNumberOfAccidents() {
		return numberOfAccidents;
	}

	public void setNumberOfAccidents(Integer numberOfAccidents) {
		this.numberOfAccidents = numberOfAccidents;
	}

	@Override
	public String toString() {
		return "TypeStatistics [type=" + type + ", numberOfAccidents=" + numberOfAccidents + "]";
	}

}

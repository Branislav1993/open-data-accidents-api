package rs.opendata.app.statistics;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportedAccidentStatistics {

	@Id
	private Integer sum;
	
	@Basic
	private Integer sumSolved;
	
	@Basic
	private Integer sumUnsolved;

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Integer getSumSolved() {
		return sumSolved;
	}

	public void setSumSolved(Integer sumSolved) {
		this.sumSolved = sumSolved;
	}

	public Integer getSumUnsolved() {
		return sumUnsolved;
	}

	public void setSumUnsolved(Integer sumUnsolved) {
		this.sumUnsolved = sumUnsolved;
	}

	@Override
	public String toString() {
		return "ReportedAccidentStatistics [sum=" + sum + ", sumSolved=" + sumSolved + ", sumUnsolved=" + sumUnsolved
				+ "]";
	}

}

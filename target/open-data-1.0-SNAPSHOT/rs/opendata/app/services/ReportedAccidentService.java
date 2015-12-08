package rs.opendata.app.services;

import java.util.List;

import rs.opendata.app.domain.ReportedAccident;
import rs.opendata.app.statistics.ReportedAccidentStatistics;

public interface ReportedAccidentService {
	
	public List<ReportedAccident> getReportedAccidents();

	public ReportedAccidentStatistics getGlobalStatus();

	public void saveReportedAccident(ReportedAccident reportedAccident);

}

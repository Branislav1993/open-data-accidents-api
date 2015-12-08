package rs.opendata.app.services;

import java.util.List;

import rs.opendata.app.dao.ReportedAccidentDao;
import rs.opendata.app.domain.ReportedAccident;
import rs.opendata.app.statistics.ReportedAccidentStatistics;

public class ReportedAccidentServiceImp implements ReportedAccidentService {
	
	protected ReportedAccidentDao rad = new ReportedAccidentDao();

	@Override
	public List<ReportedAccident> getReportedAccidents() {
		return rad.getReportedAccidents();
	}

	@Override
	public ReportedAccidentStatistics getGlobalStatus() {
		return rad.getGlobalStatus();
	}

	@Override
	public void saveReportedAccident(ReportedAccident reportedAccident) {
		rad.saveReportedAccident(reportedAccident);
	}

}

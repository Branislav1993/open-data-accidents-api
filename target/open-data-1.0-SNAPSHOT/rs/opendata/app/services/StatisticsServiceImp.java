package rs.opendata.app.services;

import java.util.List;

import rs.opendata.app.dao.StatisticsDao;
import rs.opendata.app.statistics.DayStatistics;
import rs.opendata.app.statistics.HourStatistics;
import rs.opendata.app.statistics.MonthStatistics;
import rs.opendata.app.statistics.YearStatistics;

public class StatisticsServiceImp implements StatisticsService {
	
	protected StatisticsDao sd = new StatisticsDao();

	@Override
	public List<YearStatistics> getYearStatistics() {
		return sd.getYearStatistics();
	}

	@Override
	public MonthStatistics getMonthStatistics() {
		return sd.getMonthStatistics();
	}

	@Override
	public DayStatistics getDayStatistics() {
		return sd.getDayStatistics();
	}

	@Override
	public HourStatistics getHourStatistics() {
		return sd.getHourStatistics();
	}

}

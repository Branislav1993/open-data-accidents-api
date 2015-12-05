package rs.opendata.app.services;

import java.util.List;

import rs.opendata.app.statistics.DayStatistics;
import rs.opendata.app.statistics.HourStatistics;
import rs.opendata.app.statistics.MonthStatistics;
import rs.opendata.app.statistics.YearStatistics;

public interface StatisticsService {

	public List<YearStatistics> getYearStatistics();

	public MonthStatistics getMonthStatistics();

	public DayStatistics getDayStatistics();

	public HourStatistics getHourStatistics();

}

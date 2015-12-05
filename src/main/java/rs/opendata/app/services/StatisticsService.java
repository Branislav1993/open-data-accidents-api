package rs.opendata.app.services;

import java.util.List;

import rs.opendata.app.statistics.DayStatistics;
import rs.opendata.app.statistics.HourStatistics;
import rs.opendata.app.statistics.MonthStatistics;
import rs.opendata.app.statistics.QuarterStatistics;
import rs.opendata.app.statistics.SummaryTypeStatistics;
import rs.opendata.app.statistics.TemperatureStatistics;
import rs.opendata.app.statistics.TypeStatistics;
import rs.opendata.app.statistics.WeatherStatistics;
import rs.opendata.app.statistics.YearStatistics;

public interface StatisticsService {

	public List<YearStatistics> getYearStatistics();

	public List<MonthStatistics> getMonthStatistics();

	public List<DayStatistics> getDayStatistics();

	public List<HourStatistics> getHourStatistics();

	public List<QuarterStatistics> getQuarterStatistics();

	public List<TypeStatistics> getTypeStatistics();

	public List<WeatherStatistics> getWeatherStatistics();

	public List<SummaryTypeStatistics> getSummaryTypeStatistics();

	public List<TemperatureStatistics> getTemperatureStatistics(Integer step, Double min, Double max);

}
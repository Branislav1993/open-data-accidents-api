package rs.opendata.app.services;

import java.util.List;

import rs.opendata.app.dao.StatisticsDao;
import rs.opendata.app.statistics.DayStatistics;
import rs.opendata.app.statistics.HourStatistics;
import rs.opendata.app.statistics.MonthStatistics;
import rs.opendata.app.statistics.QuarterStatistics;
import rs.opendata.app.statistics.SummaryTypeStatistics;
import rs.opendata.app.statistics.TemperatureStatistics;
import rs.opendata.app.statistics.TypeStatistics;
import rs.opendata.app.statistics.WeatherStatistics;
import rs.opendata.app.statistics.YearStatistics;

public class StatisticsServiceImp implements StatisticsService {
	
	protected StatisticsDao sd = new StatisticsDao();

	@Override
	public List<YearStatistics> getYearStatistics() {
		return sd.getYearStatistics();
	}

	@Override
	public List<MonthStatistics> getMonthStatistics() {
		return sd.getMonthStatistics();
	}

	@Override
	public List<DayStatistics> getDayStatistics() {
		return sd.getDayStatistics();
	}

	@Override
	public List<HourStatistics> getHourStatistics() {
		return sd.getHourStatistics();
	}

	@Override
	public List<QuarterStatistics> getQuarterStatistics() {
		return sd.getQuarterStatistics();
	}

	@Override
	public List<TypeStatistics> getTypeStatistics() {
		return sd.getTypeStatistics();
	}

	@Override
	public List<WeatherStatistics> getWeatherStatistics() {
		return sd.getWeatherStatistics();
	}

	@Override
	public List<SummaryTypeStatistics> getSummaryTypeStatistics() {
		return sd.getSummaryTypeStatistics();
	}

	@Override
	public List<TemperatureStatistics> getTemperatureStatistics(Integer step, Double min, Double max) {
		return sd.getTemperatureStatistics(step, min, max);
	}

}

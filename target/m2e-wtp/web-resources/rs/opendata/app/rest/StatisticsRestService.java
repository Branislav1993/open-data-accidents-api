package rs.opendata.app.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rs.opendata.app.exceptions.AppException;
import rs.opendata.app.rest.parsers.StatisticsJsonParser;
import rs.opendata.app.services.StatisticsService;
import rs.opendata.app.services.StatisticsServiceImp;
import rs.opendata.app.statistics.DayStatistics;
import rs.opendata.app.statistics.HourStatistics;
import rs.opendata.app.statistics.MonthStatistics;
import rs.opendata.app.statistics.QuarterStatistics;
import rs.opendata.app.statistics.SummaryTypeStatistics;
import rs.opendata.app.statistics.TemperatureStatistics;
import rs.opendata.app.statistics.TypeStatistics;
import rs.opendata.app.statistics.WeatherStatistics;
import rs.opendata.app.statistics.YearStatistics;
import rs.opendata.app.util.KeyNotFoundInBundleException;
import rs.opendata.app.util.ResourceBundleUtil;

@Path("/statistics")
public class StatisticsRestService {

	private final Logger logger = LogManager.getLogger(StatisticsRestService.class);
	protected StatisticsService statisticsService;

	public StatisticsRestService() {
		statisticsService = new StatisticsServiceImp();
	}

	@GET
	@Path("/year")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getYearStatistics() {

		List<YearStatistics> yearStats = statisticsService.getYearStatistics();

		if (yearStats.isEmpty()) {
			try {
				throw new AppException(Status.NOT_FOUND, ResourceBundleUtil.getMessage("statistics.not_found.year"));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}
		}

		String json = StatisticsJsonParser.serializeYearStatistics(yearStats).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/quarter")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getQuarterStatistics() {

		List<QuarterStatistics> quarterStats = statisticsService.getQuarterStatistics();

		if (quarterStats == null) {
			try {
				throw new AppException(Status.NOT_FOUND, ResourceBundleUtil.getMessage("statistics.not_found.quarter"));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}
		}

		String json = StatisticsJsonParser.serializeQuarterStatistics(quarterStats).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/month")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getMonthStatistics() {

		List<MonthStatistics> monthStats = statisticsService.getMonthStatistics();

		if (monthStats == null) {
			try {
				throw new AppException(Status.NOT_FOUND, ResourceBundleUtil.getMessage("statistics.not_found.month"));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}
		}

		String json = StatisticsJsonParser.serializeMonthStatistics(monthStats).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/day")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getDayStatistics() {

		List<DayStatistics> dayStats = statisticsService.getDayStatistics();

		if (dayStats == null) {
			try {
				throw new AppException(Status.NOT_FOUND, ResourceBundleUtil.getMessage("statistics.not_found.day"));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}
		}

		String json = StatisticsJsonParser.serializeDayStatistics(dayStats).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/hour")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getHourStatistics() {

		List<HourStatistics> hourStats = statisticsService.getHourStatistics();

		if (hourStats == null) {
			try {
				throw new AppException(Status.NOT_FOUND, ResourceBundleUtil.getMessage("statistics.not_found.hour"));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}
		}

		String json = StatisticsJsonParser.serializeHourStatistics(hourStats).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/type")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getTypeStatistics() {

		List<TypeStatistics> typeStats = statisticsService.getTypeStatistics();

		String json = StatisticsJsonParser.serializeTypeStatistics(typeStats).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/weather")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getWeatherStatistics() {

		List<WeatherStatistics> weatherStats = statisticsService.getWeatherStatistics();

		String json = StatisticsJsonParser.serializeWeatherStatistics(weatherStats).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/summary")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getSummaryTypeStatistics() {

		List<SummaryTypeStatistics> summaryTypeStats = statisticsService.getSummaryTypeStatistics();

		String json = StatisticsJsonParser.serializeSummaryTypeStatistics(summaryTypeStats).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/temperature")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getTemperatureStatistics(@QueryParam("tempStep") Integer step, @QueryParam("min") Double min,
			@QueryParam("max") Double max) {

		if (step == null)
			step = 5;
		if (min == null)
			min = -20.0;
		if (max == null)
			max = 45.0;

		List<TemperatureStatistics> temperatureStats = statisticsService.getTemperatureStatistics(step, min, max);

		String json = StatisticsJsonParser.serializeTemperatureStatistics(temperatureStats).toString();

		return Response.status(Status.OK).entity(json).build();
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getAllStatistics() {

		List<YearStatistics> yearStats = statisticsService.getYearStatistics();
		List<MonthStatistics> monthStats = statisticsService.getMonthStatistics();
		List<DayStatistics> dayStats = statisticsService.getDayStatistics();
		List<HourStatistics> hourStats = statisticsService.getHourStatistics();
		List<QuarterStatistics> quarterStats = statisticsService.getQuarterStatistics();
		List<TypeStatistics> typeStats = statisticsService.getTypeStatistics();
		List<WeatherStatistics> weatherStats = statisticsService.getWeatherStatistics();
		List<SummaryTypeStatistics> summaryTypeStats = statisticsService.getSummaryTypeStatistics();

		String json = StatisticsJsonParser.serializeAllStatistics(yearStats, monthStats, dayStats, hourStats,
				quarterStats, typeStats, weatherStats, summaryTypeStats).toString();

		return Response.status(Status.OK).entity(json).build();
	}

}

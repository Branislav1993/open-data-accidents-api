package rs.opendata.app.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
import rs.opendata.app.statistics.YearStatistics;
import rs.opendata.app.util.KeyNotFoundInBundleException;
import rs.opendata.app.util.ResourceBundleUtil;

@Path("/api/statistics")
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
	@Path("/month")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getMonthStatistics() {

		MonthStatistics monthStats = statisticsService.getMonthStatistics();

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

		DayStatistics dayStats = statisticsService.getDayStatistics();

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
	@Path("/day")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getHourStatistics() {

		HourStatistics hourStats = statisticsService.getHourStatistics();

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
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getAllStatistics() {

		List<YearStatistics> yearStats = statisticsService.getYearStatistics();
		MonthStatistics monthStats = statisticsService.getMonthStatistics();
		DayStatistics dayStats = statisticsService.getDayStatistics();
		HourStatistics hourStats = statisticsService.getHourStatistics();

		String json = StatisticsJsonParser.serializeAllStatistics(yearStats, monthStats, dayStats, hourStats)
				.toString();

		return Response.status(Status.OK).entity(json).build();
	}

}

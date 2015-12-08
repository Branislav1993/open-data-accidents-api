package rs.opendata.app.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rs.opendata.app.config.Settings;
import rs.opendata.app.domain.Accident;
import rs.opendata.app.domain.LatLngWrapper;
import rs.opendata.app.exceptions.AppException;
import rs.opendata.app.rest.parsers.AccidentJsonParser;
import rs.opendata.app.services.AccidentService;
import rs.opendata.app.services.AccidentServiceImp;
import rs.opendata.app.util.KeyNotFoundInBundleException;
import rs.opendata.app.util.ResourceBundleUtil;

@Path("/accidents")
public class AccidentRestService {

	protected AccidentService accidentService;

	public AccidentRestService() {
		accidentService = new AccidentServiceImp();
	}

	private final Logger logger = LogManager.getLogger(AccidentRestService.class);

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getAccident(@PathParam("id") int id) {
		Accident a = accidentService.getAccident(id);

		if (a == null) {
			try {
				throw new AppException(Status.NOT_FOUND,
						ResourceBundleUtil.getMessage("accidents.not_found.noAccidentId", String.valueOf(id)));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}
		}

		String json = AccidentJsonParser.serializeAccident(a).toString();

		return Response.status(Status.OK).entity(json).build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getAccidents(@QueryParam("page") int page, @QueryParam("limit") int limit,
			@QueryParam("from") String from, @QueryParam("to") String to) {

		if (limit == 0) {
			limit = Settings.getInstance().config.query.limit;
		}

		if (page == 0) {
			page = 1;
		}

		if (from == null) {
			from = "";
		}

		if (to == null) {
			to = "";
		}

		List<Accident> accidents = accidentService.getAccidents(page, limit, from, to);

		if (accidents.isEmpty()) {
			try {
				throw new AppException(Status.NOT_FOUND,
						ResourceBundleUtil.getMessage("accidents.not_found.noAccidents"));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}
		}

		String json = AccidentJsonParser.serializeAccidents(accidents).toString();

		CacheControl cc = new CacheControl();
		cc.setMaxAge(86400);

		return Response.status(Status.OK).entity(json).cacheControl(cc).build();
	}

	@GET
	@Path("/circle")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getAccidentsInRadius(@QueryParam("lat") double latitude, @QueryParam("lng") double longitude,
			@QueryParam("r") int radius, @QueryParam("page") int page, @QueryParam("limit") int limit,
			@QueryParam("from") String from, @QueryParam("to") String to, @QueryParam("d") Integer dayOfWeek,
			@QueryParam("summary") String summary, @QueryParam("analyze") boolean analyze,
			@QueryParam("fromh") Integer fromH, @QueryParam("toh") Integer toH) {

		if (limit == 0)
			limit = Settings.getInstance().config.query.limit;

		if (page == 0)
			page = 1;

		if (radius == 0)
			radius = 1000;

		if (from == null)
			from = "";

		if (to == null)
			to = "";

		if (!analyze) {
			dayOfWeek = 1;
			summary = null;
			fromH = null;
			toH = null;
		}

		List<Accident> accidents = accidentService.getAccidentsInRadius(latitude, longitude, radius, page, limit, from,
				to, analyze, dayOfWeek, summary, fromH, toH);

		// if (accidents.isEmpty()) {
		// try {
		// throw new AppException(Status.NOT_FOUND,
		// ResourceBundleUtil.getMessage("accidents.not_found.noAccidents"));
		// } catch (KeyNotFoundInBundleException e) {
		// logger.error(e);
		// }
		// }

		String json = AccidentJsonParser.serializeAccidents(accidents).toString();

		CacheControl cc = new CacheControl();
		cc.setMaxAge(86400);

		return Response.status(Status.OK).entity(json).cacheControl(cc).build();

	}

	@POST
	@Path("/path")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getPathAccidents(List<LatLngWrapper> points) {

		List<Accident> accidents = accidentService.getPathAccidents(points);

		String json = AccidentJsonParser.serializeAccidents(accidents).toString();

		return Response.status(Status.OK).entity(json).build();

	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getAllAccident() {
		List<Accident> all = accidentService.getAccidents();

		String json = AccidentJsonParser.serializePointsAccident(all).toString();
		
		CacheControl cc = new CacheControl();
		cc.setMaxAge(86400);

		return Response.status(Status.OK).entity(json).cacheControl(cc).build();

	}
}

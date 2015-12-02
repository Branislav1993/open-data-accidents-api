package rs.opendata.app.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rs.opendata.app.domain.Accident;
import rs.opendata.app.exceptions.AppException;
import rs.opendata.app.rest.parsers.AccidentJsonParser;
import rs.opendata.app.services.AccidentService;
import rs.opendata.app.services.AccidentServiceImp;
import rs.opendata.app.util.KeyNotFoundInBundleException;
import rs.opendata.app.util.ResourceBundleUtil;

@Path("/api")
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
	public Response getAccidents(int page, int limit) {

		if (limit == 0) {
			limit = 10;
		}

		if (page == 0) {
			page = 1;
		}

		List<Accident> accidents = accidentService.getAccidents(page, limit);

		if (accidents == null) {
			try {
				throw new AppException(Status.NOT_FOUND,
						ResourceBundleUtil.getMessage("accidents.not_found.noAccidents"));
			} catch (KeyNotFoundInBundleException e) {
				logger.error(e);
			}
		}

		String json = AccidentJsonParser.serializeAccidents(accidents).toString();

		return Response.status(Status.OK).entity(json).build();
	}
}

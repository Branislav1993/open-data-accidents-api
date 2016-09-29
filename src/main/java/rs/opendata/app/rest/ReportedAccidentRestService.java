package rs.opendata.app.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import rs.opendata.app.domain.ReportedAccident;
import rs.opendata.app.rest.parsers.ReportedAccidentJsonParser;
import rs.opendata.app.services.ReportedAccidentService;
import rs.opendata.app.services.ReportedAccidentServiceImp;
import rs.opendata.app.statistics.ReportedAccidentStatistics;

@Path("/reports")
public class ReportedAccidentRestService {
	protected ReportedAccidentService raService;

	public ReportedAccidentRestService() {
		raService = new ReportedAccidentServiceImp();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getReportedAccident() {

		List<ReportedAccident> all = raService.getReportedAccidents();

		String json = ReportedAccidentJsonParser.serializeReportedAccidents(all).toString();

		CacheControl cc = new CacheControl();
		cc.setMaxAge(86400);

		return Response.status(Status.OK).entity(json).cacheControl(cc).build();
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response getPathAccidents(ReportedAccident ra) {

		raService.saveReportedAccident(ra);

		ReportedAccidentStatistics ras = raService.getGlobalStatus();

		String json = ReportedAccidentJsonParser.serializeGlobalStatus(ras).toString();

		return Response.status(Status.OK).entity(json).build();

	}
}

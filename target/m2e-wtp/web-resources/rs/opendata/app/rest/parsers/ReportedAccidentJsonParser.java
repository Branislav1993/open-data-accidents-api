package rs.opendata.app.rest.parsers;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rs.opendata.app.domain.ReportedAccident;
import rs.opendata.app.statistics.ReportedAccidentStatistics;

public class ReportedAccidentJsonParser {

	public static Object serializeReportedAccidents(List<ReportedAccident> all) {
		JsonArray accidentsJson = new JsonArray();

		if (all != null && !all.isEmpty()) {
			for (ReportedAccident a : all) {
				JsonObject json = new JsonObject();
				json.addProperty("id", a.getId());
				json.addProperty("email", a.getEmail());
				json.addProperty("lat", a.getLatitude());
				json.addProperty("lng", a.getLongitude());
				json.addProperty("problemDesc", a.getProblem());
				json.addProperty("solution", a.getSolution());
				json.addProperty("status", a.getStatus());
				accidentsJson.add(json);
			}
		}

		return accidentsJson;
	}

	public static Object serializeGlobalStatus(ReportedAccidentStatistics ras) {
		JsonObject json = new JsonObject();
		json.addProperty("sum", ras.getSum());
		json.addProperty("solved", ras.getSumSolved());
		json.addProperty("unsolved", ras.getSumUnsolved());

		return json;

	}

}

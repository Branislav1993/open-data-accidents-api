package rs.opendata.app.rest.parsers;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rs.opendata.app.domain.Accident;

public class AccidentJsonParser {

	public static JsonObject serializeAccident(Accident a) {

		JsonObject accidentJson = new JsonObject();

		accidentJson.addProperty("id", a.getId());
		accidentJson.addProperty("x", a.getLatidute());
		accidentJson.addProperty("y", a.getLongitude());

		return accidentJson;
	}

	public static JsonArray serializeAccidents(List<Accident> accidents) {
		JsonArray accidentsJson = new JsonArray();

		if (accidents != null && !accidents.isEmpty()) {
			for (Accident a : accidents) {
				accidentsJson.add(serializeAccident(a));
			}
		}

		return accidentsJson;
	}
}

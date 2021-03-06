package rs.opendata.app.rest.parsers;

import java.util.Calendar;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rs.opendata.app.domain.Accident;
import rs.opendata.app.formatters.DayOfWeekParser;

public class AccidentJsonParser {

	private static Calendar c = Calendar.getInstance();

	public static JsonObject serializeAccident(Accident a) {

		JsonObject accidentJson = new JsonObject();

		c.setTime(a.getDate());
		int dow = c.get(Calendar.DAY_OF_WEEK) - 1;

		accidentJson.addProperty("id", a.getId());
		accidentJson.addProperty("x", a.getLatitude());
		accidentJson.addProperty("y", a.getLongitude());
		accidentJson.addProperty("date", a.getDate().toString());
		accidentJson.addProperty("dayOfWeek", DayOfWeekParser.parseDayOfWeek(dow));
		accidentJson.addProperty("temperature", a.getTemperature());
		accidentJson.addProperty("precipitation", a.getPrecipitation());
		accidentJson.addProperty("summary", a.getSummary());
		accidentJson.addProperty("type", a.getType());

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

	public static Object serializePointsAccident(List<Accident> all) {
		JsonArray accidentsJson = new JsonArray();

		if (all != null && !all.isEmpty()) {
			for (Accident a : all) {
				JsonObject accidentJson = new JsonObject();
				accidentJson.addProperty("id", a.getId());
				accidentJson.addProperty("x", a.getLatitude());
				accidentJson.addProperty("y", a.getLongitude());
				accidentJson.addProperty("type", a.getType());
				accidentsJson.add(accidentJson);
			}
		}

		return accidentsJson;
	}
}

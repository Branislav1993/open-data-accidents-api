package rs.opendata.app.rest.parsers;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rs.opendata.app.statistics.DayStatistics;
import rs.opendata.app.statistics.HourStatistics;
import rs.opendata.app.statistics.MonthStatistics;
import rs.opendata.app.statistics.YearStatistics;

public class StatisticsJsonParser {

	public static JsonArray serializeYearStatistics(List<YearStatistics> yearStatistics) {

		JsonArray yJsonArray = new JsonArray();
		
		for (YearStatistics y : yearStatistics) {
			JsonObject yJson = new JsonObject();
			yJson.addProperty("year", y.getYear());
			yJson.addProperty("numberOfAccidents", y.getNumberOfAccidents());
			yJsonArray.add(yJson);
		}


		return yJsonArray;
	}

	public static JsonObject serializeMonthStatistics(MonthStatistics m) {

		JsonObject mJson = new JsonObject();

		mJson.addProperty("month", m.getMonth());
		mJson.addProperty("numberOfAccidents", m.getNumberOfAccidents());

		return mJson;
	}

	public static JsonObject serializeDayStatistics(DayStatistics d) {

		JsonObject dJson = new JsonObject();

		dJson.addProperty("month", d.getMonth());
		dJson.addProperty("dayOfWeek", d.getDayOfWeek());
		dJson.addProperty("dayOfMonth", d.getDayOfMonth());
		dJson.addProperty("numberOfAccidents", d.getNumberOfAccidents());

		return dJson;
	}

	public static JsonObject serializeHourStatistics(HourStatistics h) {

		JsonObject hJson = new JsonObject();

		hJson.addProperty("hour", h.getHour());
		hJson.addProperty("numberOfAccidents", h.getNumberOfAccidents());

		return hJson;
	}

	public static Object serializeAllStatistics(List<YearStatistics> yearStats, MonthStatistics monthStats,
			DayStatistics dayStats, HourStatistics hourStats) {
		JsonObject allJson = new JsonObject();
		
		JsonArray yJson = serializeYearStatistics(yearStats);
		JsonObject mJson = serializeMonthStatistics(monthStats);
		JsonObject dJson = serializeDayStatistics(dayStats);
		JsonObject hJson = serializeHourStatistics(hourStats);
		
		allJson.add("yearStatistics", yJson);
		allJson.add("monthStatistics", mJson);
		allJson.add("dayStatistics", dJson);
		allJson.add("hourStatistics", hJson);
		
		return allJson;
	}

}

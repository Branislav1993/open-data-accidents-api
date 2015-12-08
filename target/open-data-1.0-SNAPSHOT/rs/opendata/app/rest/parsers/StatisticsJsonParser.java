package rs.opendata.app.rest.parsers;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import rs.opendata.app.formatters.AccidentTypeParser;
import rs.opendata.app.formatters.DayOfWeekParser;
import rs.opendata.app.formatters.MonthParser;
import rs.opendata.app.statistics.DayStatistics;
import rs.opendata.app.statistics.HourStatistics;
import rs.opendata.app.statistics.MonthStatistics;
import rs.opendata.app.statistics.QuarterStatistics;
import rs.opendata.app.statistics.SummaryTypeStatistics;
import rs.opendata.app.statistics.TemperatureStatistics;
import rs.opendata.app.statistics.TypeStatistics;
import rs.opendata.app.statistics.WeatherStatistics;
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

	public static JsonArray serializeMonthStatistics(List<MonthStatistics> monthStatistics) {

		JsonArray mJsonArray = new JsonArray();

		for (MonthStatistics m : monthStatistics) {
			JsonObject mJson = new JsonObject();
			mJson.addProperty("monthNumber", m.getMonth());
			mJson.addProperty("monthName", MonthParser.parseMonth(m.getMonth()));
			mJson.addProperty("numberOfAccidents", m.getNumberOfAccidents());
			mJsonArray.add(mJson);
		}

		return mJsonArray;
	}

	public static JsonArray serializeDayStatistics(List<DayStatistics> dayStatistics) {

		JsonArray dJsonArray = new JsonArray();

		for (DayStatistics d : dayStatistics) {
			JsonObject dJson = new JsonObject();
			dJson.addProperty("dayOfWeekNumber", d.getDayOfWeek() + 1);
			dJson.addProperty("dayOfWeek", DayOfWeekParser.parseDayOfWeek(d.getDayOfWeek()));
			dJson.addProperty("numberOfAccidents", d.getNumberOfAccidents());
			dJsonArray.add(dJson);
		}

		return dJsonArray;
	}

	public static JsonArray serializeHourStatistics(List<HourStatistics> hourStatistics) {

		JsonArray hJsonArray = new JsonArray();

		for (HourStatistics h : hourStatistics) {
			JsonObject hJson = new JsonObject();
			hJson.addProperty("hour", h.getHour());
			hJson.addProperty("numberOfAccidents", h.getNumberOfAccidents());
			hJsonArray.add(hJson);
		}

		return hJsonArray;
	}

	public static JsonArray serializeQuarterStatistics(List<QuarterStatistics> quarterStatistics) {

		JsonArray hJsonArray = new JsonArray();

		for (QuarterStatistics q : quarterStatistics) {
			JsonObject qJson = new JsonObject();
			qJson.addProperty("quarter", q.getQuarter());
			qJson.addProperty("numberOfAccidents", q.getNumberOfAccidents());
			hJsonArray.add(qJson);
		}

		return hJsonArray;
	}

	public static JsonArray serializeTypeStatistics(List<TypeStatistics> typeStats) {
		JsonArray tJsonArray = new JsonArray();

		for (TypeStatistics t : typeStats) {
			JsonObject tJson = new JsonObject();
			tJson.addProperty("type", AccidentTypeParser.parseAccidentType(t.getType()));
			tJson.addProperty("numberOfAccidents", t.getNumberOfAccidents());
			tJsonArray.add(tJson);
		}

		return tJsonArray;
	}

	public static JsonArray serializeWeatherStatistics(List<WeatherStatistics> weatherStats) {
		JsonArray wJsonArray = new JsonArray();

		for (WeatherStatistics w : weatherStats) {
			JsonObject wJson = new JsonObject();
			wJson.addProperty("precipitation", w.getWeather());
			wJson.addProperty("numberOfAccidents", w.getNumberOfAccidents());
			wJsonArray.add(wJson);
		}

		return wJsonArray;
	}

	public static JsonArray serializeSummaryTypeStatistics(List<SummaryTypeStatistics> summaryTypeStats) {
		JsonArray sJsonArray = new JsonArray();

		for (SummaryTypeStatistics s : summaryTypeStats) {
			JsonObject sJson = new JsonObject();
			sJson.addProperty("summary", s.getSummary());
			sJson.addProperty("numberOfAccidents", s.getNumberOfAccidents());
			sJsonArray.add(sJson);
		}

		return sJsonArray;
	}
	
	public static JsonArray serializeTemperatureStatistics(List<TemperatureStatistics> temperatureStats) {
		JsonArray tsJsonArray = new JsonArray();

		for (TemperatureStatistics ts : temperatureStats) {
			JsonObject tsJson = new JsonObject();
			tsJson.addProperty("from", ts.getFrom());
			tsJson.addProperty("to", ts.getTo());
			tsJson.addProperty("count", ts.getCount());
			tsJsonArray.add(tsJson);
		}

		return tsJsonArray;
	}

	public static JsonObject serializeAllStatistics(List<YearStatistics> yearStats, List<MonthStatistics> monthStats,
			List<DayStatistics> dayStats, List<HourStatistics> hourStats, List<QuarterStatistics> quarterStats,
			List<TypeStatistics> typeStats, List<WeatherStatistics> weatherStats, List<SummaryTypeStatistics> summaryTypeStats) {
		JsonObject allJson = new JsonObject();

		JsonArray yJson = serializeYearStatistics(yearStats);
		JsonArray qJson = serializeQuarterStatistics(quarterStats);
		JsonArray mJson = serializeMonthStatistics(monthStats);
		JsonArray dJson = serializeDayStatistics(dayStats);
		JsonArray hJson = serializeHourStatistics(hourStats);
		JsonArray tJson = serializeTypeStatistics(typeStats);
		JsonArray wJson = serializeWeatherStatistics(weatherStats);
		JsonArray sJson = serializeSummaryTypeStatistics(summaryTypeStats);

		allJson.add("yearStatistics", yJson);
		allJson.add("quarterStatistics", qJson);
		allJson.add("monthStatistics", mJson);
		allJson.add("dayStatistics", dJson);
		allJson.add("hourStatistics", hJson);
		allJson.add("typeStatistics", tJson);
		allJson.add("weatherStatistics", wJson);
		allJson.add("summaryTypeStatistics", sJson);

		return allJson;
	}

}

package rs.opendata.app.formatters;

public class AccidentTypeParser {

	public static String parseAccidentType(int i) {
		switch (i) {
		case 1:
			return "Material accident";
		case 2:
			return "Injury accident";
		case 3:
			return "Fatal accident";
		}
		return null;
	}
}

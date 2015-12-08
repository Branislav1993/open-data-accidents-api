package rs.opendata.app.services;

import java.util.List;

import rs.opendata.app.domain.Accident;
import rs.opendata.app.domain.LatLngWrapper;

public interface AccidentService {
	
	public Accident getAccident(int id);
	
	public List<Accident> getAccidents(int page, int limit, String from, String to);

	public List<Accident> getAccidentsInRadius(double latitude, double longitude, int radius, int page, int limit, String from, String to, boolean analyze, Integer dayOfWeek, String summary, Integer fromH, Integer toH);

	public List<Accident> getPathAccidents(List<LatLngWrapper> points);

	public List<Accident> getAccidents();

}

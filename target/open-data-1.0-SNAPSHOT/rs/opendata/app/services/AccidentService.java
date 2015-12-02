package rs.opendata.app.services;

import java.util.List;

import rs.opendata.app.domain.Accident;

public interface AccidentService {
	
	public Accident getAccident(int id);
	
	public List<Accident> getAccidents(int page, int limit);

}
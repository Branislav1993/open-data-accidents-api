package rs.opendata.app.services;

import java.util.List;

import rs.opendata.app.dao.AccidentDao;
import rs.opendata.app.domain.Accident;

public class AccidentServiceImp implements AccidentService {

	protected AccidentDao ad = new AccidentDao();

	@Override
	public Accident getAccident(int id) {
		return ad.getAccident(id);
	}

	@Override
	public List<Accident> getAccidents(int page, int limit, String from, String to) {
		return ad.getAccidents(page, limit, from, to);
	}

	@Override
	public List<Accident> getAccidentsInRadius(double latitude, double longitude, int radius, int page, int limit, String from, String to) {
		return ad.getAccidentsInRadius(latitude, longitude, radius, page, limit, from, to);
	}

}

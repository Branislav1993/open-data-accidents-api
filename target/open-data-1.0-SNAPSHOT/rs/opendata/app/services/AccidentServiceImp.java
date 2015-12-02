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
	public List<Accident> getAccidents(int page, int limit) {
		return ad.getAccidents(page, limit);
	}

}

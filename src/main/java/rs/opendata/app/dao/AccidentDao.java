package rs.opendata.app.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import rs.opendata.app.database.HibernateUtil;
import rs.opendata.app.domain.Accident;
import rs.opendata.app.domain.LatLngWrapper;

public class AccidentDao {

	private final Logger logger = LogManager.getLogger(AccidentDao.class);
	private DateFormat df = new SimpleDateFormat("yyyy-dd-MM-hh:mm");

	public Accident getAccident(int id) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "SELECT a " + "FROM Accident a " + "WHERE a.id = :id";

		Query query = session.createQuery(queryString);

		query.setInteger("id", id);

		Accident a = (Accident) query.uniqueResult();

		session.close();

		return a;
	}

	@SuppressWarnings("unchecked")
	public List<Accident> getAccidents(int page, int limit, String from, String to) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		java.util.Date fromDate = null;
		java.util.Date toDate = null;

		try {
			if (!from.isEmpty())
				fromDate = df.parse(from);
			if (!to.isEmpty())
				toDate = df.parse(to);
		} catch (ParseException e) {
			logger.warn(e);
		}

		String queryString = "SELECT a " + "FROM Accident a";

		if (fromDate != null && toDate == null) {
			queryString += " WHERE a.date >= :fromDate";
		} else if (fromDate == null && toDate != null) {
			queryString += " WHERE a.date <= :toDate";
		} else if (fromDate != null && toDate != null) {
			queryString += " WHERE a.date BETWEEN :fromDate and :toDate";
		}

		Query query = session.createQuery(queryString);
		if (fromDate != null && toDate == null) {
			query.setDate("fromDate", fromDate);
			System.out.println("from added to query");
		} else if (fromDate == null && toDate != null) {
			query.setDate("toDate", toDate);
		} else if (fromDate != null && toDate != null) {
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}

		List<Accident> accidents = query.setFirstResult((page - 1) * limit).setMaxResults(limit).list();

		session.close();

		return accidents;

	}

	@SuppressWarnings("unchecked")
	public List<Accident> getAccidentsInRadius(double latitude, double longitude, int radius, int page, int limit,
			String from, String to, boolean analyze, Integer dayOfWeek, String summary, Integer fromH, Integer toH) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		java.util.Date fromDate = null;
		java.util.Date toDate = null;

		try {
			if (!from.isEmpty())
				fromDate = df.parse(from);
			if (!to.isEmpty())
				toDate = df.parse(to);
		} catch (ParseException e) {
			logger.warn(e);
		}

		String queryString = "SELECT * " + "FROM nezgode as a "
				+ "WHERE earth_box(ll_to_earth(:lat, :lng), :radius) @> ll_to_earth(a.lat, a.lng)";

		if (fromDate != null && toDate == null) {
			queryString += " AND a.date >= :fromDate";
		} else if (fromDate == null && toDate != null) {
			queryString += " AND a.date <= :toDate";
		} else if (fromDate != null && toDate != null) {
			queryString += " AND a.date BETWEEN :fromDate and :toDate";
		}

		if (analyze) {
			queryString += " AND EXTRACT(DOW FROM date) = :dayOfWeek AND summary = :summary AND EXTRACT(hour FROM date) BETWEEN :fromH AND :toH";
		}

		Query query = session.createSQLQuery(queryString).addEntity(Accident.class);

		if (fromDate != null && toDate == null) {
			query.setDate("fromDate", fromDate);
			System.out.println("from added to query");
		} else if (fromDate == null && toDate != null) {
			query.setDate("toDate", toDate);
		} else if (fromDate != null && toDate != null) {
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}

		if (analyze) {
			query.setInteger("dayOfWeek", dayOfWeek);
			query.setString("summary", summary);
			query.setInteger("fromH", fromH);
			query.setInteger("toH", toH);
		}

		query.setDouble("lat", latitude);
		query.setDouble("lng", longitude);
		query.setInteger("radius", radius);

		List<Accident> accidents = query.setFirstResult((page - 1) * limit).setMaxResults(limit).list();

		session.close();

		return accidents;

	}

	@SuppressWarnings("unchecked")
	public List<Accident> getPathAccidents(List<LatLngWrapper> points) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		List<Accident> all = new LinkedList<>();

		for (int i = 0; i < points.size(); i++) {

			String queryString = "SELECT * " + "FROM nezgode_updated as a "
					+ "WHERE earth_box(ll_to_earth(:lat, :lng), :radius) @> ll_to_earth(a.lat, a.lng)";

			Query query = session.createSQLQuery(queryString).addEntity(Accident.class);

			query.setDouble("lat", points.get(i).getLat());
			query.setDouble("lng", points.get(i).getLng());
			query.setInteger("radius", 20);
			List<Accident> l = query.list();
			for (Accident a : l) {
				if (!all.contains(a))
					all.add(a);
			}
		}

		session.close();

		return all;
	}

	@SuppressWarnings("unchecked")
	public List<Accident> getAllAccidents() {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "SELECT a " + "FROM Accident a";
		Query query = session.createQuery(queryString);

		List<Accident> all = query.list();
		
		session.close();

		return all;
	}
}

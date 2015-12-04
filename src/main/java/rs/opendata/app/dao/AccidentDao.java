package rs.opendata.app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import rs.opendata.app.database.HibernateUtil;
import rs.opendata.app.domain.Accident;

public class AccidentDao {

	public Accident getAccident(int id) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "SELECT a " + 
					 		 "FROM Accident a " + 
							 "WHERE a.id = :id";

		Query query = session.createQuery(queryString);

		query.setInteger("id", id);

		Accident a = (Accident) query.uniqueResult();

		session.close();

		return a;
	}

	@SuppressWarnings("unchecked")
	public List<Accident> getAccidents(int page, int limit) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "SELECT a " + 
							 "FROM Accident a";

		List<Accident> accidents = session.createQuery(queryString)
				.setFirstResult((page - 1) * limit)
				.setMaxResults(limit)						  
				.list();

		session.close();

		return accidents;

	}

	@SuppressWarnings("unchecked")
	public List<Accident> getAccidentsInRadius(double latitude, double longitude, int radius, int page, int limit) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "SELECT * " + 
							 "FROM nezgode as a " +
						     "WHERE earth_box(ll_to_earth(:lat, :lng), :radius) @> ll_to_earth(a.lat, a.lng)";

		Query query = session.createSQLQuery(queryString).addEntity(Accident.class);
		query.setDouble("lat", latitude);
		query.setDouble("lng", longitude);
		query.setInteger("radius", radius);

		List<Accident> accidents = query.setFirstResult((page - 1) * limit)
				.setMaxResults(limit)						  
				.list();

		session.close();

		return accidents;

	}
}

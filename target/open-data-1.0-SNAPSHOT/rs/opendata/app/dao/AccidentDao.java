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

		List<Accident> accidents = session.createQuery(queryString).list();

		session.close();

		return accidents;

	}
}

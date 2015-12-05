package rs.opendata.app;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import rs.opendata.app.database.HibernateUtil;
import rs.opendata.app.domain.Accident;

public class Test {

	public static void main(String[] args) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		// String queryString = "SELECT id from nezgode";
		//
		// Query query = session.createSQLQuery(queryString);
		//
		// @SuppressWarnings("unchecked")
		// List<Integer> all = query.list();
		//
		// for (int i = 0; i < all.size(); i++) {
		// String s = "select a from Accident a where a.id=" + all.get(i);
		// Accident a = (Accident) session.createQuery(s).uniqueResult();
		// if(a.getSummary().equals("Clear") &&
		// a.getPrecipitation().equals("unknown")){
		// a.setPrecipitation("sunny");
		// session.update(a);
		// }
		// }
		// session.getTransaction().commit();

		double t1 = -20.0;
		double t2 = -15.0;

		for (int i = 0; i < 13; i++) {
			Criteria c = session.createCriteria(Accident.class);
			c.setProjection(Projections.rowCount());
			c.add(Restrictions.gt("temperature", t1));
			c.add(Restrictions.lt("temperature", t2));
			Long r = Long.valueOf(c.uniqueResult().toString());
			System.out.println("Interval: " + t1 + " - " + t2 + " Count: " + r);
			t1 += 5.0;
			t2 += 5.0;
		}

		session.close();
		HibernateUtil.getInstance().shutdown();

	}

}

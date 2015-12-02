package rs.opendata.app;

import java.util.List;

import org.hibernate.Session;

import rs.opendata.app.database.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		// String queryString = "SELECT a FROM Accident a";
		//
		// Accident a =
		// (Accident)session.createQuery(queryString).uniqueResult();
		//
		// System.out.println(a.getId() + " ovo je ID bre"); 44.797425,
		// 20.461053
		
		String queryString = "SELECT a.id FROM Accident a "
				+ "WHERE earth_box(ll_to_earth(44.797425,20.461053), 1000) @> ll_to_earth(a.latitude, a.longitude)";
		
		List<Integer> res = session.createQuery(queryString).list();
		
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}

//		double x = 44.797425;
//		double y = 20.461053;
//		double inc = 0.15;
//
//		for (int i = 0; i < 10; i++) {
//			System.out.println("Radim " + i+1);
//			Accident a = new Accident();
//			a.setId(i + 1);
//			a.setLatidute(x + inc);
//			a.setLongitude(y + inc);
//			inc += 0.1;
//			session.save(a);
//		}
//		
//		session.getTransaction().commit();

		session.close();
		HibernateUtil.getInstance().shutdown();		
		

	}
}

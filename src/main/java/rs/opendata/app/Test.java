package rs.opendata.app;

import java.util.List;

import org.hibernate.Session;

import rs.opendata.app.database.HibernateUtil;
import rs.opendata.app.domain.Accident;

public class Test {
	

	public static void main(String[] args) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		String query = "from Accident a where a.id=";
		String s = "select id from nezgode";
		@SuppressWarnings("unchecked")
		List<Integer> all = session.createSQLQuery(s).list();
		int b = 0;
		for (Integer id : all) {
			Accident a = (Accident) session.createQuery(query + id).uniqueResult();
			if(a.getSummary().equals("unknown")) b++;
		}
		System.out.println("Missing: " + b);
		session.close();
		HibernateUtil.getInstance().shutdown();

	}

}

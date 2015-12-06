package rs.opendata.app;

import org.hibernate.Session;

import rs.opendata.app.database.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		
		session.close();
		HibernateUtil.getInstance().shutdown();

	}

}

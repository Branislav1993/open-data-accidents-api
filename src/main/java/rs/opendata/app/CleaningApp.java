package rs.opendata.app;

import org.hibernate.Session;

import rs.opendata.app.database.HibernateUtil;

public class CleaningApp {

	public static void main(String[] args) {

		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		
		session.createSQLQuery("delete from nezgode where type = -1").executeUpdate();
		System.out.println("Done");

		session.getTransaction().commit();
		session.close();
		HibernateUtil.getInstance().shutdown();
	}

}

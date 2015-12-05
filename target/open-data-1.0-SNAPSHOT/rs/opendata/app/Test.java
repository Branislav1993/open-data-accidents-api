package rs.opendata.app;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import rs.opendata.app.database.HibernateUtil;
import rs.opendata.app.statistics.YearStatistics;

public class Test {

	public static void main(String[] args) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "SELECT cast(date_part('year', date) as integer) as year, count(*) as numberOfAccidents "
				+ "FROM nezgode " + "GROUP by year";

		Query query = session.createSQLQuery(queryString).addEntity(YearStatistics.class);

		@SuppressWarnings("unchecked")
		List<YearStatistics> all = query.list();

		session.close();

		for (YearStatistics y : all) {
			System.out.println(y);
		}
		HibernateUtil.getInstance().shutdown();

	}

}

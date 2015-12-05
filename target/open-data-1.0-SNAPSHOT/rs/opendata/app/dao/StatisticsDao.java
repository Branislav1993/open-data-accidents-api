package rs.opendata.app.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import rs.opendata.app.database.HibernateUtil;
import rs.opendata.app.statistics.DayStatistics;
import rs.opendata.app.statistics.HourStatistics;
import rs.opendata.app.statistics.MonthStatistics;
import rs.opendata.app.statistics.YearStatistics;

public class StatisticsDao {


	public List<YearStatistics> getYearStatistics() {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "SELECT cast(date_part('year', date) as integer) as year, count(*) as numberOfAccidents "
				+ "FROM nezgode " + "GROUP by year";

		Query query = session.createSQLQuery(queryString).addEntity(YearStatistics.class);

		@SuppressWarnings("unchecked")
		List<YearStatistics> all = query.list();

		session.close();

		return all;
	}

	public MonthStatistics getMonthStatistics() {
		// TODO Auto-generated method stub
		return null;
	}

	public DayStatistics getDayStatistics() {
		// TODO Auto-generated method stub
		return null;
	}

	public HourStatistics getHourStatistics() {
		// TODO Auto-generated method stub
		return null;
	}

}

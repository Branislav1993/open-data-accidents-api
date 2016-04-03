package rs.opendata.app.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import rs.opendata.app.database.HibernateUtil;
import rs.opendata.app.domain.Accident;
import rs.opendata.app.statistics.DayStatistics;
import rs.opendata.app.statistics.HourStatistics;
import rs.opendata.app.statistics.MonthStatistics;
import rs.opendata.app.statistics.QuarterStatistics;
import rs.opendata.app.statistics.SummaryTypeStatistics;
import rs.opendata.app.statistics.TemperatureStatistics;
import rs.opendata.app.statistics.TypeStatistics;
import rs.opendata.app.statistics.WeatherStatistics;
import rs.opendata.app.statistics.YearStatistics;

public class StatisticsDao {

	public List<YearStatistics> getYearStatistics() {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "SELECT cast(date_part('year', date) as integer) as year, count(*) as numberOfAccidents "
				+ "FROM nezgode_updated " + "GROUP by year";

		Query query = session.createSQLQuery(queryString).addEntity(YearStatistics.class);

		@SuppressWarnings("unchecked")
		List<YearStatistics> all = query.list();

		session.close();

		return all;
	}

	public List<MonthStatistics> getMonthStatistics() {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "SELECT cast(date_part('month', date) as integer) as month, count(*) as numberOfAccidents "
				+ "FROM nezgode_updated " + "GROUP by month " + "ORDER BY month";

		Query query = session.createSQLQuery(queryString).addEntity(MonthStatistics.class);

		@SuppressWarnings("unchecked")
		List<MonthStatistics> all = query.list();

		session.close();

		return all;
	}

	public List<DayStatistics> getDayStatistics() {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "SELECT EXTRACT(DOW FROM date) as dayOfWeek, count(*) as numberOfAccidents FROM nezgode_updated GROUP by dayOfWeek order by dayOfWeek";

		Query query = session.createSQLQuery(queryString).addEntity(DayStatistics.class);

		@SuppressWarnings("unchecked")
		List<DayStatistics> all = query.list();

		session.close();

		return all;
	}

	public List<HourStatistics> getHourStatistics() {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "SELECT EXTRACT(HOUR FROM date) as hour, count(*) as numberOfAccidents FROM nezgode_updated GROUP by hour order by hour";

		Query query = session.createSQLQuery(queryString).addEntity(HourStatistics.class);

		@SuppressWarnings("unchecked")
		List<HourStatistics> all = query.list();

		session.close();

		return all;
	}

	public List<QuarterStatistics> getQuarterStatistics() {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "SELECT EXTRACT(QUARTER FROM date) as quarter, count(*) as numberOfAccidents FROM nezgode_updated GROUP by quarter order by quarter";

		Query query = session.createSQLQuery(queryString).addEntity(QuarterStatistics.class);

		@SuppressWarnings("unchecked")
		List<QuarterStatistics> all = query.list();

		session.close();

		return all;
	}

	public List<TypeStatistics> getTypeStatistics() {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "SELECT type, count(*) as numberOfAccidents FROM nezgode_updated GROUP by type order by type";

		Query query = session.createSQLQuery(queryString).addEntity(TypeStatistics.class);

		@SuppressWarnings("unchecked")
		List<TypeStatistics> all = query.list();

		session.close();

		return all;
	}

	public List<WeatherStatistics> getWeatherStatistics() {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "SELECT precipitation as weather, count(*) as numberOfAccidents FROM nezgode_updated GROUP by weather order by weather";

		Query query = session.createSQLQuery(queryString).addEntity(WeatherStatistics.class);

		@SuppressWarnings("unchecked")
		List<WeatherStatistics> all = query.list();

		session.close();

		return all;
	}

	public List<SummaryTypeStatistics> getSummaryTypeStatistics() {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		String queryString = "SELECT summary, count(*) as numberOfAccidents FROM nezgode_updated GROUP by summary order by numberOfAccidents DESC";

		Query query = session.createSQLQuery(queryString).addEntity(SummaryTypeStatistics.class);

		@SuppressWarnings("unchecked")
		List<SummaryTypeStatistics> all = query.list();

		session.close();

		return all;
	}

	public List<TemperatureStatistics> getTemperatureStatistics(Integer step, Double min, Double max) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		Double t1 = min;
		Double t2 = min + step;

		List<TemperatureStatistics> all = new LinkedList<>();

		for (; t1 < max;) {
			Criteria c = session.createCriteria(Accident.class);
			c.setProjection(Projections.rowCount());
			c.add(Restrictions.gt("temperature", t1));
			c.add(Restrictions.lt("temperature", t2));
			TemperatureStatistics t = new TemperatureStatistics();
			Integer count = Integer.parseInt(c.uniqueResult().toString());
			t.setCount(count);
			t.setFrom(t1);
			t.setTo(t2);
			all.add(t);
			t1 += step;
			t2 += step;
		}

		session.close();

		return all;
	}

}

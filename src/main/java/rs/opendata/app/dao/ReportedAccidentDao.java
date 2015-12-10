package rs.opendata.app.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import rs.opendata.app.database.HibernateUtil;
import rs.opendata.app.domain.ReportedAccident;
import rs.opendata.app.statistics.ReportedAccidentStatistics;

public class ReportedAccidentDao {

	@SuppressWarnings("unchecked")
	public List<ReportedAccident> getReportedAccidents() {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		List<ReportedAccident> all = session.createCriteria(ReportedAccident.class).list();

		session.close();

		return all;
	}

	public ReportedAccidentStatistics getGlobalStatus() {

		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		ReportedAccidentStatistics ras = new ReportedAccidentStatistics();

		Long sum = (Long) session.createCriteria(ReportedAccident.class).setProjection(Projections.rowCount())
				.uniqueResult();
		BigInteger solved = (BigInteger) session
				.createSQLQuery("Select sum(case when status then 1 else 0 end) from prijavljenje_nezgode")
				.uniqueResult();

		int a = Integer.parseInt(Long.valueOf(sum).toString());
		int b = solved.intValue();
		ras.setSum(a);
		ras.setSumSolved(b);
		ras.setSumUnsolved(a - b);

		return ras;
	}

	public void saveReportedAccident(ReportedAccident reportedAccident) {
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();

		session.save(reportedAccident);

		session.getTransaction().commit();
		session.close();
	}

}

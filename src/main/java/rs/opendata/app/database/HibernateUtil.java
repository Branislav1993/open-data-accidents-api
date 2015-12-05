package rs.opendata.app.database;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	private static ServiceRegistry serviceRegistry;

	private static HibernateUtil INSTANCE;

	private HibernateUtil() {
		try {

			Configuration configuration = new Configuration();
			configuration.configure("hibernate/hibernate.cfg.xml");
			configuration.setProperties(getProperties());

			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static HibernateUtil getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HibernateUtil();
		}
		return INSTANCE;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void shutdown() {
		getSessionFactory().close();
	}

	private Properties getProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.connection.driver_class", "org.postgresql.Driver");
		prop.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/opendata");
		prop.put("hibernate.connection.user", "postgres");
		prop.put("hibernate.connection.password", "povratakkralja0");
		prop.put("hibernate.connection.pool_size", 10);
		prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		prop.put("hibernate.show_sql", true);

		return prop;
	}

}

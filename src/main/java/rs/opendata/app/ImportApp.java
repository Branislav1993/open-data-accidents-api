package rs.opendata.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import org.hibernate.Session;

import rs.opendata.app.database.HibernateUtil;
import rs.opendata.app.domain.Accident;

/**
 * Hello world!
 *
 */
public class ImportApp {
	public static void main(String[] args) {

		DateFormat format = new SimpleDateFormat("dd.MM.yyyy,HH:mm");
		format.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));

		Accident[] array = new Accident[18360];
		for (int i = 0; i < array.length; i++) {
			array[i] = new Accident();
		}

		String c1 = "C:/Users/Baki/Desktop/1.csv";
		String c2 = "C:/Users/Baki/Desktop/2.csv";
		String c3 = "C:/Users/Baki/Desktop/3.csv";
		String c4 = "C:/Users/Baki/Desktop/4.csv";
		String c5 = "C:/Users/Baki/Desktop/5.csv";

		BufferedReader br1 = null;
		BufferedReader br2 = null;
		BufferedReader br3 = null;
		BufferedReader br4 = null;
		BufferedReader br5 = null;

		int b1 = 0;
		int b2 = 0;
		int b3 = 0;
		int b4 = 0;
		int b5 = 0;

		String line1 = "";
		String line2 = "";
		String line3 = "";
		String line4 = "";
		String line5 = "";

		try {
			br1 = new BufferedReader(new FileReader(c1));
			br2 = new BufferedReader(new FileReader(c2));
			br3 = new BufferedReader(new FileReader(c3));
			br4 = new BufferedReader(new FileReader(c4));
			br5 = new BufferedReader(new FileReader(c5));

			while ((line1 = br1.readLine()) != null) {
				Integer id = Integer.parseInt(line1);
				array[b1].setId(id);
				b1++;
			}

			while ((line2 = br2.readLine()) != null) {
				Date date = format.parse(line2);
				array[b2].setDate(new Timestamp(date.getTime()));
				b2++;

			}
			while ((line3 = br3.readLine()) != null) {
				Double d = Double.parseDouble(line3);
				array[b3].setLatitude(d);
				b3++;
			}
			while ((line4 = br4.readLine()) != null) {
				Double d = Double.parseDouble(line4);
				array[b4].setLongitude(d);
				b4++;
			}
			while ((line5 = br5.readLine()) != null) {
				int broj = -1;
				switch (line5) {
				case "Sa mat.stetom":
					broj = 1;
					break;
				case "Sa povredjenim":
					broj = 2;
					break;
				case "Sa poginulim":
					broj = 3;
					break;
				}
				array[b5].setType(broj);
				b5++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// for (int i = 0; i < array.length; i++) {
		// if (array[i].getId() == 1103411) {
		// array[i].setId(1);
		// break;
		// }
		// }

		List<Accident> lista = new LinkedList<>();

		for (int i = 0; i < array.length; i++) {
			if (lista.contains(array[i])) continue;
				lista.add(array[i]);
		}

		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		// for (int i = 0; i < array.length; i++) {
		// Accident a = (Accident) session.createQuery("select a from Accident a
		// where a.id=" + array[i].getId())
		// .uniqueResult();
		//
		// if (a != null) {
		// a.setDate(array[i].getDate());
		// session.save(a);
		// }
		// }
		// session.getTransaction().commit();

		for (int i = 0; i < lista.size(); i++) {
			session.save(lista.get(i));
		}
		session.getTransaction().commit();
		session.close();
		HibernateUtil.getInstance().shutdown();

	}
}

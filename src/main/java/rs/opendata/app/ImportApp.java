package rs.opendata.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

		Accident[] array = new Accident[13177];
		for (int i = 0; i < array.length; i++) {
			array[i] = new Accident();
		}

		String c1 = "C:/Users/Branislav Vidojevic/Desktop/c1.csv";
		String c2 = "C:/Users/Branislav Vidojevic/Desktop/c2.csv";
		String c3 = "C:/Users/Branislav Vidojevic/Desktop/c3.csv";
		String c4 = "C:/Users/Branislav Vidojevic/Desktop/c4.csv";
		String c5 = "C:/Users/Branislav Vidojevic/Desktop/c5.csv";

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
				String string = line2.substring(1, line2.length() - 1);
				DateFormat format = new SimpleDateFormat("dd.MM.yyyy,hh:mm");
				format.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
				Date date = format.parse(string);
				array[b2].setDate(date);
				b2++;

			}
			while ((line3 = br3.readLine()) != null) {
				String s = "44." + line3;
				Double d = Double.parseDouble(s);
				array[b3].setLatitude(d);
				b3++;
			}
			while ((line4 = br4.readLine()) != null) {
				String s = "20." + line4;
				Double d = Double.parseDouble(s);
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
		
		for (int i = 0; i < array.length; i++) {
			if(array[i].getId() == 1103411) {
				array[i].setId(1);
				break;
			}
		}

		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
		session.beginTransaction();
		for (int i = 0; i < array.length; i++) {
			session.save(array[i]);
		}

		session.getTransaction().commit();
		session.close();
		HibernateUtil.getInstance().shutdown();

	}
}

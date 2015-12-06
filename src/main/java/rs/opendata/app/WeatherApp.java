package rs.opendata.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import rs.opendata.app.database.HibernateUtil;
import rs.opendata.app.domain.Accident;

public class WeatherApp {
	private final static String USER_AGENT = "Mozilla/5.0";
	static String api1 = "213a104a6d1894883954446b83239d12";
	static String api2 = "936a205a1d8ffc2bf8a534df6536435b";

	public static void main(String[] args) throws IOException, ParseException {

		Session session1 = HibernateUtil.getInstance().getSessionFactory().openSession();
		Session session = HibernateUtil.getInstance().getSessionFactory().openSession();

		String s = "select id from nezgode";
		String query = "from Accident a where a.id=";
		DateFormat df = new SimpleDateFormat("yyyy-dd-MMhh:mm:00");
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.rcub.bg.ac.rs", 8080));

		@SuppressWarnings("unchecked")
		List<Integer> all = session1.createSQLQuery(s).list();
		session1.close();

		for (int i = all.size() - 1; i >= 0; i--) {
			session.beginTransaction();
			Integer id = all.get(i);
			
			Accident a = (Accident) session.createQuery(query + id).uniqueResult();
			
			String d = df.format(a.getDate());
			d = d.substring(0, 10) + "T" + d.substring(10, d.length());
			System.out.println(d);

			double lat = a.getLatitude();
			double lng = a.getLongitude();

			String url = "https://api.forecast.io/forecast/" + api1 + "/" + lat + "," + lng + "," + d + "?units=si";

			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

//			int responseCode = con.getResponseCode();
//			System.out.println("\nSending 'GET' request to URL : " + url);
//			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			String res = response.toString();

			JsonObject weatherJson = (new JsonParser()).parse(res).getAsJsonObject();
			JsonObject currentlyJson = weatherJson.getAsJsonObject("currently");

			String summary = "unknown";
			String prep = "unknown";
			Double temp = -99.0;
			
			if (currentlyJson.get("summary") != null) {
				summary = currentlyJson.get("summary").getAsString();
			}
			if (currentlyJson.get("precipType") != null) {
				prep = currentlyJson.get("precipType").getAsString();
			}
			if (currentlyJson.get("temperature") != null) {
				temp = currentlyJson.get("temperature").getAsDouble();

			}

			System.out.println(summary);
			System.out.println(prep);
			System.out.println(temp);

			a.setSummary(summary);
			a.setTemperature(temp);
			a.setPrecipitation(prep);

			session.saveOrUpdate(a);
			System.out.println((i+1) + " " + a.getDate().toString());
			session.getTransaction().commit();

		}
		session.close();
		HibernateUtil.getInstance().shutdown();
	}

}

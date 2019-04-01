import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;

/**
 * The program implements an application that simply displays website ode
 * responses.
 *
 * @author Christos Pavlatos
 * @version 2.0
 * @since 2019-03-31
 */

public class GETCodeResponse {

	public static void main(String[] args) throws Exception {

		String fileContainingUrl = "sites.txt";
		String siteLine = null;

		FileReader urlToTest = new FileReader(fileContainingUrl);
		BufferedReader bufferedReader = new BufferedReader(urlToTest);

		while ((siteLine = bufferedReader.readLine()) != null) {
			System.out.println(siteLine);
			URL uri = new URL(siteLine);
			HttpURLConnection urlConnection = (HttpURLConnection) uri.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.connect();

			int code = urlConnection.getResponseCode();
			System.out.println("Response  : " + code);
			if (code == 200) {
				System.out.println("200 OK");
			} else if (code == 301) {
				System.out.println("301 Moved Permanently");
			} else if (code == 404) {
				System.out.println("404 Not Found");
			} else {
				System.out.println(code);
			}
		}
		bufferedReader.close();
	}

}

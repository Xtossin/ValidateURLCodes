import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import java.util.Objects;

public class GETCodeResponse {

	public static void main(String[] args) throws Exception {

		String fileContainingUrl = "associations.csv";

		String siteLine = null;
		String domain = "https://pcth6fm1ud.execute-api.us-west-1.amazonaws.com/dev/";

		FileReader urlToTest = new FileReader(fileContainingUrl);
		BufferedReader bufferedReader = new BufferedReader(urlToTest);

		while (bufferedReader.readLine() != null) {

			String splitBy = ",";
			siteLine = domain + bufferedReader.readLine();
			String[] b = siteLine.split(splitBy);
			String expectedUrl = b[1];
			System.out.println(b[0]);
			URL uri = new URL(b[0]);
			HttpURLConnection urlConnection = (HttpURLConnection) uri.openConnection();

			urlConnection.setRequestMethod("GET");
			urlConnection.setInstanceFollowRedirects(false);
			HttpURLConnection.setFollowRedirects(false);

			String uriInString = uri.toString();

			String newUrl = urlConnection.getHeaderField("Location");
			System.out.println("Location : " + newUrl);

			int code = urlConnection.getResponseCode();

			System.out.println("Response Code   : " + code);

			boolean testResult = passOrFail(newUrl, expectedUrl, code);
			System.out.println(testResult + "\n");
			urlConnection.disconnect();

		}
		bufferedReader.close();
	}

	public static boolean passOrFail(String newUrl, String expectedUrl, int code) {
		return (Objects.equals(newUrl, expectedUrl) && code == 301);
	}
}

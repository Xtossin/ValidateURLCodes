import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import java.util.Objects;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

public class GETCodeResponse {

	public static void main(String[] args) throws Exception {

		String fileContainingUrl = "sites.txt";
		String siteLine = "";

		FileReader urlToTest = new FileReader(fileContainingUrl);
		BufferedReader bufferedReader = new BufferedReader(urlToTest);

		while ((siteLine = bufferedReader.readLine()) != null) {
			URL uri = new URL(siteLine);
			HttpURLConnection urlConnection = (HttpURLConnection) uri.openConnection();
			urlConnection.setRequestMethod("GET");
			String newUrl = urlConnection.getHeaderField("Location");
			

			String httpsAdd = "https://";
			String sampleString = siteLine;
			String[] items = sampleString.split("(?<=http://)");
			String itemLista = httpsAdd + items[1];

			int code = urlConnection.getResponseCode();

			PassedORfailed.passOrFail(itemLista, itemLista, code);
				System.out.println(PassedORfailed.passOrFail(newUrl, itemLista, code));
				System.out.println("URL Tested      : " + siteLine);
				System.out.println("Location of URL : " + newUrl);
				System.out.println("Response Code   : " + code + "\n");
				
			urlConnection.connect();
		}
		bufferedReader.close();
	}
}

package nordakademie.server.logik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Ermittelt die eigene IP mittels der Amazon Web Services.
 *
 *
 */
public class IpChecker {

	public static String getIp() throws Exception {
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			String ip = in.readLine();
			return ip;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
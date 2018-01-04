package nordakademie.server.logik;

import java.util.ArrayList;

import nordakademie.server.sql.SQLConnectUser;

/**
 * Überprüft, ob die Logindaten eines Users korrekt sind.
 *
 *
 */
public class CheckLogin {
	public static String login(String user, String password) {
		String loginStatus = "2";
		ArrayList<String> data = SQLConnectUser.getServerData();
		for (int i = 0; i < data.size(); ++i) {
			if (data.get(i + 1).equals(user)) {
				if (data.get(i).equals(password)) {
					loginStatus = "3";
				}
			}
			i++;
		}
		return loginStatus;
	}
}

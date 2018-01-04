package nordakademie.server.logik;

import java.util.ArrayList;

import nordakademie.server.sql.SQLConnectUser;

/**
 * Fügt einen User der Userdatenbank hinzu, sofern dieser nicht schon existiert.
 *
 *
 */
public class AddUser {
	public static String userhinzufuegen(String user, String password) {
		String status = "6";
		ArrayList<String> data = SQLConnectUser.getServerData();
		for (int n = 0; n < data.size(); n++) {
			if (data.get(n + 1).equals(user)) {
				status = "7";
			}
			n++;
		}
		if (status.equals("6")) {
			SQLConnectUser.insertName(password, user);
		}

		return status;
	}
}

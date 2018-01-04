package nordakademie.server.logik;

import java.util.ArrayList;

import nordakademie.server.sql.SQLConnectServer;

/**
 * Fügt den eigenen Server der Serverdatenbank hinzu oder aktualisiert einen
 * schon vorhandenen Eintrag mit der aktuellen IP-Adresse.
 *
 */
public class AddServer {
	public static void checkVerwaltungsserverInDataBase(String servername, String ip) {
		String serverbereitsvorhanden = "nein";
		ArrayList<String> data = SQLConnectServer.getServerData();
		for (int i = 0; i < data.size(); ++i) {
			if (data.get(i).equals(servername)) {
				serverbereitsvorhanden = "ja";
			}
		}
		if (serverbereitsvorhanden.equals("ja")) {
			SQLConnectServer.updateServerName(servername, ip);
		} else {
			SQLConnectServer.addServerName(servername, ip);
		}
	}
}

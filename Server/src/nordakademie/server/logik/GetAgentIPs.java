package nordakademie.server.logik;

import java.util.ArrayList;

import nordakademie.server.sql.SQLConnectAgents;

/**
 * Fragt aus der Agentdatenbank alle IPs von Agents ab, die einem bestimmten
 * User gehören.
 *
 */
public class GetAgentIPs {

	public static String agentIPs(String name, String password) {
		ArrayList<String> rohDaten = SQLConnectAgents.getServerData();
		final StringBuilder ipListe = new StringBuilder();
		ipListe.append("3;");
		for (int n = 0; n < rohDaten.size(); n++) {
			if (password.equals(rohDaten.get(n))) {
				System.out.println(rohDaten.get(n));
				n++;
				if (name.equals(rohDaten.get(n))) {
					System.out.println(rohDaten.get(n));
					n++;
					ipListe.append(rohDaten.get(n) + ";");
					System.out.println(rohDaten.get(n));
				} else {
					n++;
				}
			} else {
				n++;
				n++;
			}

		}

		;

		return (ipListe.toString());

	}

}

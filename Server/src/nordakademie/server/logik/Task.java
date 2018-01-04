package nordakademie.server.logik;

import java.util.*;

/**
 * Ist die Task-Klasse f�r die zu erstellenden Tasks zum regelm��igen
 * Aktualisieren der eigenen IP in der Serverdatenbank.
 *
 */
class Task extends TimerTask

{
	public void run()

	{
		try {
			AddServer.checkVerwaltungsserverInDataBase("server01", IpChecker.getIp());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
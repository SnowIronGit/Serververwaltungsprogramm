package nordakademie.server.logik;

import java.util.Timer;

/**
 * Erzeugt die Timer-Tasks für das regelmäßige Aktualisieren der eigenen IP in
 * der Serverdatenbank.
 *
 */
public class TimerTaskServer {
	public static void taskAddVerwaltungssserver() {
		Timer timer = new Timer();
		timer.schedule(new Task(), 2000);
		timer.schedule(new Task(), 1000, 50000);
	}
}
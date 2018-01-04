package nordakademie.server.logik;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Schreibt in eine Logdatei Einträge, welche ihm als String übergeben werden.
 *
 *
 */
public class WriteLog {
	public static void schreibeLog(String kindOfAction, String user, String ip, String befehl, String rueckmeldung) {
		PrintWriter pWriter = null;
		try {
			pWriter = new PrintWriter(new FileWriter("log.txt", true), true);
			if (ip.equals("leer")) {

			} else {
				pWriter.println("Action: " + kindOfAction + "| User: " + user + " Agent-IP: " + ip + " Befehl: "
						+ befehl + " Folgende Rückmledung: " + rueckmeldung);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (pWriter != null) {
				pWriter.flush();
				pWriter.close();
			}
		}
	}
}

package nordakademie.server.logik;

/**
 * Wandelt Befehle vom Client in agentkonforme Syntax um.
 *
 *
 */
public class OrderRefactoring {
	public static String BefehlsnameToBefehl(String befehlsname) {
		String befehl = "";
		switch (befehlsname) {
		case "Systemdateien reparieren":
			befehl = "sfc /scannow";
			break;
		case "Neustart":
			befehl = "shutdown /r";
			break;
		case "Herunterfahren":
			befehl = "shutdown /s";
			break;
		case "Freien Speicher löschen":
			befehl = "cipher /w:C";
			break;
		case "Taschenrechner":
			befehl = "calc";
			break;
		default:
			befehl = befehlsname;
		}
		return befehl;

	}
}

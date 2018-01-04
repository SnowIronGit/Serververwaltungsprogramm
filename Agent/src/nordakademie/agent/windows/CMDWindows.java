package nordakademie.agent.windows;

import java.io.IOException;

/**
 * Führt Befehle auf der Windows-Kommandozeile aus.
 *
 *
 */
public class CMDWindows {

	public static void befehlausfuehren(String befehl) throws IOException {
		Runtime.getRuntime().exec("cmd /c " + befehl);
	}

}

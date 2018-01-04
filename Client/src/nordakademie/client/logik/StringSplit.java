package nordakademie.client.logik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Dient dem Umwandeln von Strings.
 *
 *
 */
public class StringSplit {
	public static String[] stringToArray(String zeichenkette) {
		return zeichenkette.split(Pattern.quote(";"));

	}

	public static ArrayList<String> stringToArraylist(String zeichenkette) {
		return new ArrayList<String>(Arrays.asList(zeichenkette.split(";")));
	}

}

package nordakademie.server.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import nordakademie.server.logik.AES;
import nordakademie.server.logik.WriteLog;

public class SocketServerToAgent {
	public static String connectToAgent(String ip, String befehl) throws IOException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

		try {
			Socket server = new Socket(ip, 3141);
			Scanner in = new Scanner(server.getInputStream());
			PrintWriter out = new PrintWriter(server.getOutputStream(), true);
			out.println(AES.Encrypt(befehl));
			String ergebnis = (AES.Decrypt(in.nextLine()));
			server.close();

			return ergebnis;
		} catch (ConnectException e) {

			return "1";
		}
	}
}
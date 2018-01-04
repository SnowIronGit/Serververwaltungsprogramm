package nordakademie.client.socket;

import java.net.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import nordakademie.client.logik.AES;

import java.io.*;

/**
 * Baut eine Verbindung vom Client zum Server auf und übermittelt Daten.
 * 
 *
 */
public class SocketClientToServer {
	private static String t;

	public static String connectClientToServer(String host, int port, String inhalt) throws InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		Socket s = null;
		try {
			s = new Socket(host, port);
		} catch (UnknownHostException uhe) {
			System.out.println("Unknown Host :" + host);
			// s = null;
			return "0";
		} catch (IOException ioe) {
			// s = null;
			return "0";
		}
		if (s == null)
			System.exit(-1);
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			out.println(AES.Encrypt(inhalt));
			out.flush();
			t = (AES.Decrypt(in.readLine()));
			out.println(AES.Encrypt("quit"));
			out.flush();
			return t;
		} catch (IOException ioe) {
			return "0";
		} finally {
			try {
				out.close();
				in.close();
				s.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

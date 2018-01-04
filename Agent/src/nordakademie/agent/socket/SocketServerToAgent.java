package nordakademie.agent.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import nordakademie.agent.windows.AES;
import nordakademie.agent.windows.CMDWindows;

/**
 * 
 * Öffnet einen Socket auf Port 3141, um Anfragen vom Server zu erhalten.
 *
 */

public class SocketServerToAgent {
	private static void handleConnection(Socket client) throws IOException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		Scanner in = new Scanner(client.getInputStream());
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);

		String befehl = AES.Decrypt(in.nextLine());
		CMDWindows.befehlausfuehren(befehl);

		out.println(AES.Encrypt("4"));
	}

	public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		ServerSocket server = new ServerSocket(3141);

		while (true) {
			Socket client = null;

			try {
				client = server.accept();
				handleConnection(client);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (client != null)
					try {
						client.close();
					} catch (IOException e) {
					}
			}
		}
	}
}

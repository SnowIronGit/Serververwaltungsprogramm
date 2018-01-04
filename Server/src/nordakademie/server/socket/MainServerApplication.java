package nordakademie.server.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import nordakademie.server.logik.AES;
import nordakademie.server.logik.AddUser;

import nordakademie.server.logik.OrderRefactoring;
import nordakademie.server.logik.CheckLogin;
import nordakademie.server.logik.GetAgentIPs;

import nordakademie.server.logik.StringSplit;
import nordakademie.server.logik.TimerTaskServer;
import nordakademie.server.logik.WriteLog;
import nordakademie.server.sql.SQLConnectAgents;

/**
 * Ist die Mainklasse des Servers. Startet die Threads zur Annahme eines Clients
 * und übernimmt die weitere Verarbeitung der Clientanfrage mittels eigener
 * Logik.
 *
 */
public class MainServerApplication {
	ServerSocket server;
	boolean ServerOn = true;

	public MainServerApplication() throws Exception {
		TimerTaskServer.taskAddVerwaltungssserver();
		try {
			server = new ServerSocket(80);
		} catch (IOException ioe) {
			System.exit(-1);
		}
		while (ServerOn) {
			try {
				Socket clientSocket = server.accept();
				ClientServiceThread cliThread = new ClientServiceThread(clientSocket);
				cliThread.start();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		try {
			server.close();
		} catch (Exception ioe) {
			System.exit(-1);
		}
	}

	public static void main(String[] args) throws Exception {
		new MainServerApplication();
	}

	class ClientServiceThread extends Thread {
		Socket client;
		boolean m_bRunThread = true;

		public ClientServiceThread() {
			super();
		}

		ClientServiceThread(Socket clientsocket) {
			client = clientsocket;
		}

		public void run() {
			BufferedReader in = null;
			PrintWriter out = null;
			try {
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
				while (m_bRunThread) {
					String[] werte = null;
					werte = StringSplit.stringToArray(AES.Decrypt(in.readLine()));
					String kindOfAction = werte[0];
					if (kindOfAction.equalsIgnoreCase("quit")) {
						m_bRunThread = false;
					} else {
						String user = werte[1];
						String password = werte[2];
						String ip = werte[3];
						String befehl = OrderRefactoring.BefehlsnameToBefehl(werte[4]);
						if ((CheckLogin.login(user, password)).equals("3")
								|| (kindOfAction.equals("userhinzufuegen"))) {
							if (!ServerOn) {
								out.println(AES.Encrypt("Serverstop"));
								out.flush();
								m_bRunThread = false;
							}
							if (kindOfAction.equalsIgnoreCase("quit")) {
								m_bRunThread = false;
								ServerOn = false;
							} else {
								switch (kindOfAction) {
								case "login":
									String ipliste = GetAgentIPs.agentIPs(user, password);
									out.println(AES.Encrypt(ipliste));
									out.flush();
									break;
								case "befehl":
									String rueckmeldung = SocketServerToAgent.connectToAgent(ip, befehl);
									WriteLog.schreibeLog(kindOfAction, user, ip, befehl, rueckmeldung);
									out.println(AES.Encrypt(rueckmeldung));
									out.flush();
									break;
								case "serverhinzufuegen":
									SQLConnectAgents.insertName(password, user, ip);
									out.println(AES.Encrypt("5"));
									out.flush();
									break;
								case "userhinzufuegen":
									String status = AddUser.userhinzufuegen(user, password);
									out.println(AES.Encrypt(status));
									out.flush();
									break;
								}
							}
						} else {
							out.println(AES.Encrypt("2"));
							out.flush();
						}
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					in.close();
					out.close();
					client.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
}
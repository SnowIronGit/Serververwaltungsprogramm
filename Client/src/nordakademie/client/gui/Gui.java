package nordakademie.client.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import nordakademie.client.logik.StringSplit;
import nordakademie.client.socket.SocketClientToServer;
import nordakademie.client.sql.SQLConnectServer;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JComboBox;

/**
 * 
 * Erstellt eine GUI zur bedienung des Programmes.
 *
 */
public class Gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_benutzerEingabe;
	private JTextField txt_passwortEingabe;
	private JTextField txt_kommandoEingabe;
	private JTextField txt_serverauswahlStatus;
	private JTextField txt_befehlStatus;
	private JTextField txt_benutzerStatus;
	private JTextField txt_passwortStatus;
	private JTextField txt_serverEingabe;
	private JTextField txt_kommandoStatus;
	private JTextField txt_serverStatus;
	private JTextField txt_serverPort;
	String comboBoxListe[] = { "Systemdateien reparieren", "Neustart", "Herunterfahren", "Freien Speicher löschen",
			"Taschenrechner" };
	String comboBoxVerwaltungsServer[] = SQLConnectServer.getServerData().toArray(new String[] {});
	ComboBoxModel<String> cbxModel2 = new DefaultComboBoxModel<String>(comboBoxVerwaltungsServer);
	JComboBox<String> cBoxServer = new JComboBox<String>();
	JComboBox<String> cBoxBefehl = new JComboBox<String>();
	JComboBox<String> cBoxVerwaltungsserver = new JComboBox<String>();
	private JTextField txt_newUser;
	private JTextField txt_newPassword;
	private JTextField txt_newUserStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		setMinimumSize(new Dimension(1000, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		txt_benutzerEingabe = new JTextField();
		txt_benutzerEingabe.setColumns(10);

		txt_passwortEingabe = new JTextField();
		txt_passwortEingabe.setColumns(10);

		txt_kommandoEingabe = new JTextField();
		txt_kommandoEingabe.setColumns(10);

		JLabel lblBenutzerId = new JLabel("Benutzer ID");

		JLabel lblPasswort = new JLabel("Passwort");

		JLabel lblBefehl = new JLabel("Serverauswahl");

		txt_serverauswahlStatus = new JTextField();
		txt_serverauswahlStatus.setColumns(10);

		JLabel lblStatus = new JLabel("Befehlsauswahl");

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});

		JButton btnBefehl = new JButton("Befehl ausf\u00FChren");
		btnBefehl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				befehlausfuehren();

			}
		});

		txt_befehlStatus = new JTextField();
		txt_befehlStatus.setColumns(10);

		JLabel lblVerbindungsstatus = new JLabel("Kommandozeile");

		txt_benutzerStatus = new JTextField();
		txt_benutzerStatus.setColumns(10);

		txt_passwortStatus = new JTextField();
		txt_passwortStatus.setColumns(10);

		JLabel lblEingabe = new JLabel("Eingabe");

		JLabel lblStatus_1 = new JLabel("Status");

		JButton btnKommando = new JButton("Kommandozeile ausfuehren");
		btnKommando.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kommandoausfuehren();
			}
		});

		JButton btnServerHinzufuegen = new JButton("Server hinzufuegen");
		btnServerHinzufuegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				serverhinzufuegen();
			}

		});

		JLabel lblNeuerServer = new JLabel("Neuer Server");

		txt_serverEingabe = new JTextField();
		txt_serverEingabe.setColumns(10);

		txt_kommandoStatus = new JTextField();
		txt_kommandoStatus.setColumns(10);

		txt_serverStatus = new JTextField();
		txt_serverStatus.setColumns(10);

		JLabel lblIpport = new JLabel("IP/Port");

		txt_serverPort = new JTextField();
		txt_serverPort.setText("80");
		txt_serverPort.setColumns(10);

		JLabel lblUserRegistrieren = new JLabel("User registrieren");

		txt_newUser = new JTextField();
		txt_newUser.setColumns(10);

		txt_newPassword = new JTextField();
		txt_newPassword.setColumns(10);

		JLabel lblUsername = new JLabel("Username");

		JLabel lblPasswort_1 = new JLabel("Passwort");

		JButton btnUserRegistrieren = new JButton("Neuen User registrieren");
		btnUserRegistrieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userhinzufuegen();
			}
		});

		txt_newUserStatus = new JTextField();
		txt_newUserStatus.setColumns(10);
		cBoxVerwaltungsserver.setModel(cbxModel2);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(286).addComponent(lblEingabe).addGap(225)
						.addComponent(lblStatus_1).addContainerGap(394, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(33).addGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblIpport).addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(
										lblPasswort).addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
										.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
												.createParallelGroup(Alignment.LEADING).addComponent(lblNeuerServer)
												.addComponent(lblVerbindungsstatus).addComponent(lblBefehl)
												.addComponent(lblStatus).addComponent(lblBenutzerId))
												.addGap(31)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(txt_serverEingabe, GroupLayout.DEFAULT_SIZE, 316,
																Short.MAX_VALUE)
														.addComponent(cBoxBefehl, Alignment.TRAILING, 0, 316,
																Short.MAX_VALUE)
														.addComponent(cBoxServer, 0, 316, Short.MAX_VALUE)
														.addComponent(txt_benutzerEingabe, GroupLayout.DEFAULT_SIZE,
																316, Short.MAX_VALUE)
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(txt_newUser, GroupLayout.PREFERRED_SIZE,
																		131, GroupLayout.PREFERRED_SIZE)
																.addGap(18).addComponent(txt_newPassword,
																		GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
														.addGroup(Alignment.TRAILING,
																gl_contentPane.createSequentialGroup()
																		.addComponent(cBoxVerwaltungsserver, 0, 145,
																				Short.MAX_VALUE)
																		.addGap(18).addComponent(txt_serverPort,
																				GroupLayout.PREFERRED_SIZE, 153,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(txt_passwortEingabe, GroupLayout.DEFAULT_SIZE,
																316, Short.MAX_VALUE)
														.addComponent(txt_kommandoEingabe, GroupLayout.DEFAULT_SIZE,
																316, Short.MAX_VALUE)))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblUserRegistrieren).addGap(341)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(txt_newUserStatus, GroupLayout.DEFAULT_SIZE, 263,
														Short.MAX_VALUE)
												.addComponent(txt_benutzerStatus, GroupLayout.DEFAULT_SIZE, 263,
														Short.MAX_VALUE)
												.addComponent(txt_passwortStatus, GroupLayout.DEFAULT_SIZE, 263,
														Short.MAX_VALUE)
												.addComponent(txt_serverauswahlStatus, GroupLayout.DEFAULT_SIZE, 263,
														Short.MAX_VALUE)
												.addComponent(txt_befehlStatus, GroupLayout.DEFAULT_SIZE, 263,
														Short.MAX_VALUE)
												.addComponent(txt_serverStatus, Alignment.TRAILING,
														GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
												.addComponent(txt_kommandoStatus, 259, 263, Short.MAX_VALUE))
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(btnUserRegistrieren, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnBefehl, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnKommando, GroupLayout.PREFERRED_SIZE, 183,
														Short.MAX_VALUE)
												.addComponent(btnServerHinzufuegen, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(50)))))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(179).addComponent(lblUsername).addGap(110)
						.addComponent(lblPasswort_1).addContainerGap(593, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblEingabe).addComponent(lblStatus_1))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblIpport).addComponent(cBoxVerwaltungsserver,
														GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(txt_serverPort, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(txt_benutzerEingabe, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txt_benutzerStatus, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblBenutzerId))
								.addGap(18)
								.addGroup(
										gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblPasswort)
												.addComponent(txt_passwortEingabe, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txt_passwortStatus, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(cBoxServer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblBefehl).addComponent(txt_serverauswahlStatus,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(84).addComponent(btnLogin)))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cBoxBefehl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_befehlStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBefehl).addComponent(lblStatus))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblVerbindungsstatus)
						.addComponent(txt_kommandoEingabe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_kommandoStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnKommando))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNeuerServer)
						.addComponent(txt_serverEingabe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_serverStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnServerHinzufuegen))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblUsername)
						.addComponent(lblPasswort_1))
				.addGap(7)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblUserRegistrieren)
						.addComponent(txt_newUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_newPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUserRegistrieren).addComponent(txt_newUserStatus, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(30)));
		contentPane.setLayout(gl_contentPane);
	}

	private void login() {
		if ("".equals(txt_benutzerEingabe.getText())) {
			txt_benutzerStatus.setText("User angeben");
		} else {
			if ("".equals(txt_passwortEingabe.getText())) {
				txt_passwortStatus.setText("Passwort angeben");
			} else {
				String[] werte = null;
				String kindOfAction = "login";
				StringBuilder str = new StringBuilder();
				try {
					str.append(kindOfAction + ";" + txt_benutzerEingabe.getText() + ";" + txt_passwortEingabe.getText()
							+ ";leer;leer");
					werte = (StringSplit.stringToArray(
							SocketClientToServer.connectClientToServer((String) cBoxVerwaltungsserver.getSelectedItem(),
									Integer.parseInt(txt_serverPort.getText()), str.toString())));
				} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
						| IllegalBlockSizeException | BadPaddingException e) {
					e.printStackTrace();
				}
				switch (werte[0]) {
				case "0":
					serverFehler();
					break;
				case "1":
					agentFehler();
					break;
				case "2":
					wrongLogin();
					break;
				case "3":
					werte[0] = "127.0.0.1";
					ComboBoxModel<String> cbxModel = new DefaultComboBoxModel<String>(werte);
					ComboBoxModel<String> cbxModel1 = new DefaultComboBoxModel<String>(comboBoxListe);
					cBoxServer.setModel(cbxModel);
					cBoxBefehl.setModel(cbxModel1);
					txt_benutzerStatus.setText("Login erfolgreich");
					txt_passwortStatus.setText("Login erfolgreich");
					txt_serverauswahlStatus.setText("Aktuelle Serverliste");
					break;
				}
			}
		}
	}

	private void befehlausfuehren() {
		String rueckmeldung = null;
		String kindOfAction = "befehl";
		StringBuilder str = new StringBuilder();
		try {
			str.append(kindOfAction + ";" + txt_benutzerEingabe.getText() + ";" + txt_passwortEingabe.getText() + ";"
					+ (String) cBoxServer.getSelectedItem() + ";" + (String) cBoxBefehl.getSelectedItem());
			rueckmeldung = (SocketClientToServer.connectClientToServer((String) cBoxVerwaltungsserver.getSelectedItem(),
					Integer.parseInt(txt_serverPort.getText()), str.toString()));
		} catch (InvalidKeyException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (NoSuchPaddingException e1) {
			e1.printStackTrace();
		} catch (IllegalBlockSizeException e1) {
			e1.printStackTrace();
		} catch (BadPaddingException e1) {
			e1.printStackTrace();
		}
		switch (rueckmeldung) {
		case "0":
			serverFehler();
			break;
		case "1":
			agentFehler();
			break;
		case "2":
			wrongLogin();
			break;
		case "4":
			txt_benutzerStatus.setText("Login erfolgreich");
			txt_passwortStatus.setText("Login erfolgreich");
			txt_serverauswahlStatus.setText((String) cBoxServer.getSelectedItem());
			txt_befehlStatus.setText("Befehl ausgefuehrt");
			txt_kommandoStatus.setText("");
			break;
		}
	}

	private void kommandoausfuehren() {
		if ("".equals(txt_kommandoEingabe.getText())) {
			txt_kommandoStatus.setText("Befehl eingeben");
		} else {
			if ("".equals(txt_benutzerEingabe.getText())) {
				txt_benutzerStatus.setText("User angeben");
			} else {
				if ("".equals(txt_passwortEingabe.getText())) {
					txt_passwortStatus.setText("Passwort angeben");
				} else {
					String rueckmeldung = null;
					String kindOfAction = "befehl";
					StringBuilder str = new StringBuilder();
					try {
						str.append(kindOfAction + ";" + txt_benutzerEingabe.getText() + ";"
								+ txt_passwortEingabe.getText() + ";" + (String) cBoxServer.getSelectedItem() + ";"
								+ txt_kommandoEingabe.getText());
						rueckmeldung = (SocketClientToServer.connectClientToServer(
								(String) cBoxVerwaltungsserver.getSelectedItem(),
								Integer.parseInt(txt_serverPort.getText()), str.toString()));
					} catch (InvalidKeyException e) {
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					} catch (NoSuchPaddingException e) {
						e.printStackTrace();
					} catch (IllegalBlockSizeException e) {
						e.printStackTrace();
					} catch (BadPaddingException e) {
						e.printStackTrace();
					}
					switch (rueckmeldung) {
					case "0":
						serverFehler();
						break;
					case "1":
						agentFehler();
						break;
					case "2":
						wrongLogin();
						break;
					case "4":
						txt_benutzerStatus.setText("Login erfolgreich");
						txt_passwortStatus.setText("Login erfolgreich");
						txt_serverauswahlStatus.setText((String) cBoxServer.getSelectedItem());
						txt_kommandoStatus.setText("Befehl ausgefuehrt");
						txt_befehlStatus.setText("");
						break;
					}
				}
			}
		}
	}

	private void serverhinzufuegen() {
		if ("".equals(txt_serverEingabe.getText())) {
			txt_serverStatus.setText("IP angeben");
		} else {
			if ("".equals(txt_benutzerEingabe.getText())) {
				txt_benutzerStatus.setText("User angeben");
			} else {
				if ("".equals(txt_passwortEingabe.getText())) {
					txt_passwortStatus.setText("Passwort angeben");
				} else {
					String rueckmeldung = null;
					String kindOfAction = "serverhinzufuegen";
					StringBuilder str = new StringBuilder();
					try {
						str.append(kindOfAction + ";" + txt_benutzerEingabe.getText() + ";"
								+ txt_passwortEingabe.getText() + ";" + txt_serverEingabe.getText() + ";leer");
						rueckmeldung = (SocketClientToServer.connectClientToServer(
								(String) cBoxVerwaltungsserver.getSelectedItem(),
								Integer.parseInt(txt_serverPort.getText()), str.toString()));
					} catch (InvalidKeyException e) {
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					} catch (NoSuchPaddingException e) {
						e.printStackTrace();
					} catch (IllegalBlockSizeException e) {
						e.printStackTrace();
					} catch (BadPaddingException e) {
						e.printStackTrace();
					}
					switch (rueckmeldung) {
					case "0":
						serverFehler();
						break;
					case "1":
						agentFehler();
						break;
					case "2":
						wrongLogin();
						break;
					case "5":
						txt_benutzerStatus.setText("Login erfolgreich");
						txt_passwortStatus.setText("Login erfoglreich");
						login();
						txt_serverStatus.setText("Server hinzugefuegt");
						break;
					}
				}
			}
		}
	}

	private void userhinzufuegen() {
		if ("".equals(txt_newUser.getText())) {
			txt_newUserStatus.setText("Username angeben");
		} else {
			if ("".equals(txt_newPassword.getText())) {
				txt_newUserStatus.setText("Passwort angeben");
			} else {
				String rueckmeldung = null;
				String kindOfAction = "userhinzufuegen";
				StringBuilder str = new StringBuilder();
				try {
					str.append(kindOfAction + ";" + txt_newUser.getText() + ";" + txt_newPassword.getText() + ";"
							+ "leer" + ";leer");
					rueckmeldung = (SocketClientToServer.connectClientToServer(
							(String) cBoxVerwaltungsserver.getSelectedItem(),
							Integer.parseInt(txt_serverPort.getText()), str.toString()));
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (NoSuchPaddingException e) {
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					e.printStackTrace();
				} catch (BadPaddingException e) {
					e.printStackTrace();
				}
				switch (rueckmeldung) {
				case "0":
					serverFehler();
					break;
				case "1":
					agentFehler();
					break;
				case "2":
					wrongLogin();
					break;
				case "6":
					txt_newUserStatus.setText("User hinzugefuegt");
					break;
				case "7":
					txt_newUserStatus.setText("User bereits vorhanden");
					break;
				}
			}

		}
	}

	private void wrongLogin() {
		txt_benutzerStatus.setText("Falscher Benutzername");
		txt_passwortStatus.setText("Falsches Passwort");
		txt_serverauswahlStatus.setText("");
		txt_kommandoStatus.setText("Ungülter Login");
		txt_befehlStatus.setText("Ungülter Login");
		txt_serverStatus.setText("Ungültiger Login");
	}

	private void serverFehler() {
		txt_benutzerStatus.setText("Server offline");
		txt_passwortStatus.setText("Server offline");
		txt_serverauswahlStatus.setText("");
		txt_kommandoStatus.setText("Server offline");
		txt_befehlStatus.setText("Server offline");
		txt_serverStatus.setText("Server offline");
	}

	private void agentFehler() {
		txt_benutzerStatus.setText("Agent offline");
		txt_passwortStatus.setText("Agent offline");
		txt_serverauswahlStatus.setText("");
		txt_kommandoStatus.setText("Agent offline");
		txt_befehlStatus.setText("Agent offline");
		txt_serverStatus.setText("Agent offline");
	}
}

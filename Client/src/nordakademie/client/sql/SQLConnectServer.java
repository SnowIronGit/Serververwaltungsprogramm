package nordakademie.client.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Fragt aus der Serverdatenbank eine Liste mit allen IPs aller eingetragenden
 * Server ab. *
 */
public class SQLConnectServer {

	private static Connection conn = null;
	private static String dbHost = "sql367.your-server.de";
	private static String dbPort = "3306";
	private static String database = "meyero_db4";
	private static String dbUser = "meyero_4";
	static ArrayList<String> liste = new ArrayList<String>();
	private static String dbPassword = "ABcxsxHSUk4Ky6Yq";

	private SQLConnectServer() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + database + "?" + "user="
					+ dbUser + "&" + "password=" + dbPassword);
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
		}
	}

	private static Connection getInstance() {
		if (conn == null)
			new SQLConnectServer();
		return conn;
	}

	public static ArrayList<String> getServerData() {

		conn = getInstance();

		if (conn != null) {
			Statement query;
			try {
				query = conn.createStatement();

				String sql = "SELECT ip FROM agents";
				ResultSet result = query.executeQuery(sql);

				liste.clear();
				while (result.next()) {
					liste.add(result.getString("ip"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return liste;
	}
}
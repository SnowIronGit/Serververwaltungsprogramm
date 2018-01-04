package nordakademie.server.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Ist zuständig für den Zugriff auf die Agentdatenbank, um die IPs von Agents
 * zuhinterlegen und auch alle Agents eines Users abzufragen.
 *
 */
public class SQLConnectAgents {

	private static Connection conn = null;
	private static String dbHost = "sql367.your-server.de";
	private static String dbPort = "3306";
	private static String database = "meyero_db2";
	private static String dbUser = "meyero_2";
	static ArrayList<String> liste = new ArrayList<String>();
	private static String dbPassword = "bR6WwhAb1RpiwBrb";

	private SQLConnectAgents() {
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
			new SQLConnectAgents();
		return conn;
	}

	public static ArrayList<String> getServerData() {

		conn = getInstance();

		if (conn != null) {
			Statement query;
			try {
				query = conn.createStatement();
				String sql = "SELECT password, username, ip FROM agents " + "ORDER BY username";
				ResultSet result = query.executeQuery(sql);
				liste.clear();
				while (result.next()) {
					liste.add(result.getString("password"));
					liste.add(result.getString("username"));
					liste.add(result.getString("ip"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return liste;
	}

	public static void insertName(String password, String username, String ip) {
		conn = getInstance();

		if (conn != null) {
			try {
				String sql = "INSERT INTO agents(username, password, ip ) " + "VALUES(?, ?, ?)";
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, ip);
				preparedStatement.executeUpdate();
				String lastagents = "SELECT agents_id, password, username, ip " + "FROM agents "
						+ "ORDER BY agents_id DESC LIMIT 1";
				ResultSet result = preparedStatement.executeQuery(lastagents);

				if (result.next()) {
					System.out.println("(" + result.getInt(1) + ")" + result.getString(2) + " " + result.getString(3)
							+ " " + result.getString(4));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
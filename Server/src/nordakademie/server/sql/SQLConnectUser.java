package nordakademie.server.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Ist zuständig für den Zugriff des Servers auf die User-Datenbank. Kann User
 * hinzufügen und User abfragen.
 *
 */
public class SQLConnectUser {

	private static Connection conn = null;
	private static String dbHost = "sql367.your-server.de";
	private static String dbPort = "3306";
	private static String database = "meyero_db3";
	private static String dbUser = "meyero_3";
	static ArrayList<String> liste = new ArrayList<String>();
	private static String dbPassword = "tWYj6KUF616yQUfK";

	private SQLConnectUser() {
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
			new SQLConnectUser();
		return conn;
	}

	public static ArrayList<String> getServerData() {

		conn = getInstance();

		if (conn != null) {
			Statement query;
			try {
				query = conn.createStatement();

				String sql = "SELECT password, username FROM agents " + "ORDER BY username";
				ResultSet result = query.executeQuery(sql);

				liste.clear();
				while (result.next()) {
					liste.add(result.getString("password"));
					liste.add(result.getString("username"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return liste;
	}

	public static void insertName(String password, String username) {
		conn = getInstance();

		if (conn != null) {
			try {
				String sql = "INSERT INTO agents(username, password) " + "VALUES(?, ?)";
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
				preparedStatement.executeUpdate();

				String lastagents = "SELECT agents_id, password, username " + "FROM agents "
						+ "ORDER BY agents_id DESC LIMIT 1";
				ResultSet result = preparedStatement.executeQuery(lastagents);
				if (result.next()) {
					System.out.println("(" + result.getInt(1) + ")" + result.getString(2) + " " + result.getString(3));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void updateName(String firstName, String lastName, int agentsId) {
		conn = getInstance();

		if (conn != null) {
			try {

				String querySql = "SELECT agents_id, password, username " + "FROM agents " + "WHERE agents_id = ?";

				PreparedStatement preparedQueryStatement = conn.prepareStatement(querySql);
				preparedQueryStatement.setInt(1, agentsId);
				ResultSet result = preparedQueryStatement.executeQuery();

				if (result.next()) {
					System.out.println(
							"VORHER: (" + result.getInt(1) + ")" + result.getString(2) + " " + result.getString(3));
				}

				String updateSql = "UPDATE agents " + "SET password = ?, username = ? " + "WHERE agents_id = ?";
				PreparedStatement preparedUpdateStatement = conn.prepareStatement(updateSql);
				preparedUpdateStatement.setString(1, firstName);
				preparedUpdateStatement.setString(2, lastName);
				preparedUpdateStatement.setInt(3, agentsId);
				preparedUpdateStatement.executeUpdate();
				result = preparedQueryStatement.executeQuery();
				if (result.next()) {
					System.out.println(
							"NACHHER: (" + result.getInt(1) + ")" + result.getString(2) + " " + result.getString(3));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
package nordakademie.server.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Ist zuständig für den Zugriff des Servers auf die Serverdatenbank, die eigene
 * IP-Adresse inkl. Rechnernamen zu hinterlegen.
 *
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

				String sql = "SELECT servername FROM agents";
				ResultSet result = query.executeQuery(sql);

				liste.clear();
				while (result.next()) {
					liste.add(result.getString("servername"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return liste;
	}

	public static void addServerName(String servername, String ip) {
		conn = getInstance();

		if (conn != null) {
			try {
				String sql = "INSERT INTO agents(servername, ip) " + "VALUES(?, ?)";
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, servername);
				preparedStatement.setString(2, ip);
				preparedStatement.executeUpdate();

				String lastagents = "SELECT agents_id, ip, servername " + "FROM agents "
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

	public static void updateServerName(String servername, String ip) {
		conn = getInstance();

		if (conn != null) {
			try {

				String querySql = "SELECT ip, servername " + "FROM agents " + "WHERE servername = ?";

				PreparedStatement preparedQueryStatement = conn.prepareStatement(querySql);
				preparedQueryStatement.setString(1, servername);
				ResultSet result = preparedQueryStatement.executeQuery();

				String updateSql = "UPDATE agents " + "SET ip = ?, servername = ? " + "WHERE servername = ?";
				PreparedStatement preparedUpdateStatement = conn.prepareStatement(updateSql);

				preparedUpdateStatement.setString(1, ip);

				preparedUpdateStatement.setString(2, servername);
				preparedUpdateStatement.setString(3, ("'" + servername + "'"));

				preparedUpdateStatement.executeUpdate();
				result = preparedQueryStatement.executeQuery();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
package jdbc.mssql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.mssql.config.DBConnectConfig;
import jdbc.mssql.lib.JsonHelper;

public class DBControl {

	private static JsonHelper jsonHelper = new JsonHelper();

	public static void init() {
		
		String filePath = String.format("%s\\%s", System.getProperty("user.dir"), "config.json");
		String json = jsonHelper.fnReadJson(filePath);
		DBConnectConfig config = jsonHelper.getDBConnectConfig(json);
		
		String host          = config.getHost();
		String port          = config.getPort();
		String database      = config.getDatabase();
		String connectOption = config.getConnectOption();
		String user          = config.getUser();
		String password      = config.getPassword();

		String url = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;%s", host, port, database, connectOption);
		DBConnector.init(url, user, password);
	}

	
	public static ResultSet fnQuery(String sql) {
		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = DBConnector.getConnection();
			if (conn == null) return null;

			Statement statement = conn.createStatement();
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.fnReleaseConnection(conn);
		}
		return rs;
	}
}
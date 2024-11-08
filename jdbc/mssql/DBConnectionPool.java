package jdbc.mssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class DBConnectionPool {
	
	private String url;
	private String user;
	private String password;
	private List<Connection> connectionPool;
	private int poolSize = 10;

	public DBConnectionPool(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.connectionPool = new ArrayList<>(poolSize);
		init();
	}

	private void init() {
		try {
			for (int i = 0; i < poolSize; i++) {
				Connection conn = DriverManager.getConnection(url, user, password);
				connectionPool.add(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		if (!connectionPool.isEmpty()) {
			return connectionPool.remove(connectionPool.size() - 1);
		}
		return null;
	}

	public void fnReleaseConnection(Connection connection) {
		if (connection != null) {
			connectionPool.add(connection);
		}
		// System.out.println(connectionPool.size());
	}
}

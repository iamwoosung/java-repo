package jdbc.mssql;

import java.sql.Connection;

public class DBConnector {
	
	private static DBConnectionPool connectionPool;

	public static void init(String url, String user, String password) {
		if (connectionPool == null) {
			connectionPool = new DBConnectionPool(url, user, password);
			// System.out.println("커넥션풀 생성");
		}
	}

	public static Connection getConnection() {
		return connectionPool.getConnection();
	}
	
	public static void fnReleaseConnection(Connection connection) {
		connectionPool.fnReleaseConnection(connection);
	}
	
}
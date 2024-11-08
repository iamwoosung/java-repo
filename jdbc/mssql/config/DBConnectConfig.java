package jdbc.mssql.config;

public class DBConnectConfig {

	private String host;
	private String port;
	private String database;
	private String user;
	private String password;
	private String connectOption;
	
	public DBConnectConfig(String host, String port, String database, String user, String password, String connectOption) {
		this.host          = host;
		this.port          = port;
		this.database      = database;
		this.user          = user;
		this.password      = password;
		this.connectOption = connectOption;
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getDatabase() {
		return database;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getConnectOption() {
		return connectOption;
	}

}

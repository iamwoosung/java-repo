package json.Models;

public class DBConnectDTO {

	private String host;
	private String port;
	private String database;
	private String user;
	private String password;
	private String connectOption;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConnectOption() {
		return connectOption;
	}

	public void setConnectOption(String connOption) {
		this.connectOption = connOption;
	}
}

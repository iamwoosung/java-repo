package json;

import json.Models.DBConnectDTO;

public class Main {

	private JsonHelper jsonHelper = new JsonHelper();
	private DBConnectDTO conn = new DBConnectDTO();

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		String filePath = String.format("%s\\%s", System.getProperty("user.dir"), "config.json");
		String json = jsonHelper.fnReadJson(filePath);
		
		conn = jsonHelper.getDBConnectInfo(json);
		
		System.out.println(conn.getHost());
		System.out.println(conn.getPort());
		System.out.println(conn.getDatabase());
		System.out.println(conn.getUser());
		System.out.println(conn.getPassword());
		System.out.println(conn.getConnectOption());
	}

}

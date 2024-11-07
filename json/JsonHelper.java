package json;

import java.io.BufferedReader;
import java.io.FileReader;

import org.json.JSONObject;

import json.Models.DBConnectDTO;

public class JsonHelper {

	public String fnReadJson(String path) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String jsonData = null;
			while ((jsonData = br.readLine()) != null) {
				sb.append(jsonData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public DBConnectDTO getDBConnectInfo(String json) {
		DBConnectDTO conn = new DBConnectDTO();
		
		JSONObject jsonObject = new JSONObject(json);
		
		// 검색할 key도 인자로 받을 수 있으나 
		// 가독성 떨어지고 env 사용이 많지 않아 
		// 하드코딩 처리함
		JSONObject mssqlObject = jsonObject.getJSONObject("MSSQL");
		
		conn.setHost         (mssqlObject.getString("Host"));
		conn.setPort         (String.valueOf(mssqlObject.get("Port")));
		conn.setDatabase     (mssqlObject.getString("Database"));
		conn.setUser         (mssqlObject.getString("User"));
		conn.setPassword     (mssqlObject.getString("Password"));
		conn.setConnectOption(mssqlObject.getString("ConnectOption"));
		
		return conn;
	}
	
}

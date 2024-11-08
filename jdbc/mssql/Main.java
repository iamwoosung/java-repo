package jdbc.mssql;

import java.sql.ResultSet;

public class Main {


	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			DBControl.init();

			
			// 호출 시험(Query)
			ResultSet rs = DBControl.fnQuery("SELECT * FROM CAR");
			while (rs.next()) {
				System.out.println(rs.getInt("UID"));
			}
			rs.close();
			
			// 호출 시험(Procedure)
			rs = DBControl.fnQuery("EXEC dbo.WEB_TEST_DO 1");
			while (rs.next()) {
				System.out.println(rs.getString("Ver"));
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

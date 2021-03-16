package kr.caravan.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class carsDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	private void getConnect() {
		String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user = "hr";
		String password = "hr";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean login(String car_name, String pw) {
		getConnect();
		boolean check = false;
		String sql = "select * from cars where car_name = ? and pw = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, car_name);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			if (rs.next()) {
				check = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return check;
	}

	public int pwChange(String car_name, String pw) {
		int cnt = -1;
		getConnect();
		String SQL = "update cars set pw = ? where car_name = ?";
		try {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, pw);
			ps.setString(2, car_name);
			cnt = ps.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
			return cnt;
	}
}

package kr.caravan.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.gson.Gson;

public class consultDAO {
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
	
	public int consultInsert(int num, String cmt) {
		int cnt = -1;
		getConnect();
		try {
			String SQL = "insert into consult(con_num,num, cmt) " + "values (con_num_seq.nextval,?,?)";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, num);
			ps.setString(2, cmt);
			cnt = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}

	public String qnASelect() {
		getConnect();
		Gson gson = new Gson();
		ArrayList<consultVO> arr = new ArrayList<consultVO>();
		String sql = "select c.num, s.time, c.car_name, s.cmt, c.tel " + 
				"from customer c, consult s " + 
				"where c.num = s.num and complete = '0' order by s.time";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("num");
				String time = rs.getString("time");
				String car_name = rs.getString("car_name");
				String cmt = rs.getString("cmt");
				String tel = rs.getString("tel");
				arr.add(new consultVO(num,time,car_name,cmt,tel));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		String json = gson.toJson(arr);
		return json;
	}

	public int consultUpdate(int num, String time) {
		int cnt = -1;
		getConnect();
		String SQL = "update consult set complete = '1' where num = ? and time = ?";
		try {
		ps = conn.prepareStatement(SQL);
		ps.setInt(1, num);
		ps.setString(2, time);
		cnt = ps.executeUpdate(); 
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		dbClose();
	}
		return cnt;
	}

	public int led_livUpdate(String car_name, String led_liv) {
		int cnt = -1;
		getConnect();
		String SQL = "update cars_func set led_liv = ? where car_name = ?";
		try {
		ps = conn.prepareStatement(SQL);
		ps.setString(1, led_liv);
		ps.setString(2, car_name);
		cnt = ps.executeUpdate(); 
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		dbClose();
	}
		return cnt;
	}

	public int consultSelect(int num) {
		getConnect();
		int cnt = 0;
		String sql = "select num " + 
				"from consult " + 
				"where complete = '0' ";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				cnt++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}

	public int qnAPreSelect() {
		getConnect();
		int cnt = 0;
		String sql = "select num " + 
				"from consult " + 
				"where complete = '0'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				cnt++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}

	
}

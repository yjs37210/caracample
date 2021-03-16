package kr.caravan.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class customerDAO {
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

	public int numSelect(String car_name) {
		getConnect();
		int num = 0;
		String sql = "select num from customer where car_name = ? order by num desc";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, car_name);
			rs = ps.executeQuery();
			if(rs.next()) {
				num = rs.getInt("num");
			}
		} catch (Exception e) {   
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return num;
	}

	public int customerInsert(customerVO vo) {
		int cnt = -1;
		getConnect();
		String SQL = "insert into customer(num, name, tel, car_name, male, female) values (customer_num_seq.nextval,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getTel());
			ps.setString(3, vo.getCar_name());
			ps.setInt(4, vo.getMale());
			ps.setInt(5, vo.getFemale());
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}

	public String customerSelect() {
		getConnect();
		Gson gson = new Gson();
		ArrayList<customerVO> arr = new ArrayList<customerVO>();
		String sql = "select * " + 
				"from customer where able = '1'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String tel = rs.getString("tel");
				String car_name = rs.getString("car_name");
				int male = rs.getInt("male");
				int female = rs.getInt("female");
				String check_in = rs.getString("check_in");
				String check_out = rs.getString("check_out");
				arr.add(new customerVO(num, name, tel, car_name, male, female, check_in, check_out));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		String json = gson.toJson(arr);
		return json;
	}

	public String ableselect() {
		getConnect();
		Gson gson = new Gson();
		ArrayList<String> all = new ArrayList<String>();
		String sql = "select car_name from customer where able = '1'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				String car_name = rs.getString(1);
				all.add(car_name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		String json = gson.toJson(all);
		return json;
	}

	//db다 갈고있어서 조용ㅇ조용
	public int ableUpdate(int num) {
		int cnt = -1;
		getConnect();
		String SQL = "update customer set able = '0' where num = ?";
		try {
		ps = conn.prepareStatement(SQL);
		ps.setInt(1, num);
		cnt = ps.executeUpdate(); 
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		dbClose();
	}
		return cnt;
	}

	public boolean car_name_ableselect(String car_name) {
		getConnect();
		boolean check = false;
		String sql = "select num from customer where car_name = ? and able = '1'";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, car_name);
			rs = ps.executeQuery();
			while(rs.next()) {
				check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return check;

		
	}
	
}
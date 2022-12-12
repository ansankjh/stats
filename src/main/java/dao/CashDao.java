package dao;

import java.util.*;
import java.sql.*;

public class CashDao {
	// 페이징
	public HashMap<String, Object> selectMaxMinYear(Connection conn) throws Exception {
		HashMap<String, Object> map = null;
		// 쿼리문 작성
		String sql = "SELECT"
				+ " (SELECT Min(YEAR(cash_date)) FROM cash) minYear"
				+ " , (SELECT Max(YEAR(cash_date)) FROM cash) maxYear"
				+ "	FROM DUAL";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			map = new HashMap<String, Object>();
			map.put("minYear", rs.getInt("minYear"));
			map.put("maxYear", rs.getInt("maxYear"));
			
		}
		// 자원반납
		stmt.close();
		rs.close();
		return map;
	}
	
	// 월별 수입&지출 합
	public ArrayList<HashMap<String, Object>> selectCashSumByMonth(Connection conn, int year, String category) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		// 쿼리문 작성
		String sql = "SELECT MONTH(cash_date) month, SUM(cash_price) price"
				+ " FROM cash"
				+ " WHERE YEAR(cash_date) = ? AND category = ?"
				+ " GROUP BY MONTH(cash_date)"
				+ " ORDER BY MONTH(cash_date) ASC";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, year);
		stmt.setString(2, category);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("month", rs.getInt("month"));
			m.put("price", rs.getInt("price"));
			list.add(m);
		}
		// 자원반납
		stmt.close();
		rs.close();
		return list;
	}
	// 년도별 수입&지출 합
	public ArrayList<HashMap<String, Object>> selectCashListByCategory(Connection conn, String category) throws Exception {
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		// 쿼리문 작성
		String sql = "SELECT YEAR(cash_date) y, SUM(cash_price) price"
				+ " FROM cash WHERE category = ?"
				+ " GROUP BY YEAR(cash_date)"
				+ "	ORDER BY YEAR(cash_date) ASC";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setString(1, category);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("year", rs.getInt("y"));
			m.put("price", rs.getInt("price"));
			list.add(m);
		}
		// 자원반납
		stmt.close();
		rs.close();
		return list;
	}
}

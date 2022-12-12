package service;

import dao.*;
import java.util.*;
import java.sql.*;
import util.DBUtil;

public class CashService { // get(SELECT), add(INSERT), modify(UPDATE), remove(DELETE)
	private CashDao cashDao;
	
	public HashMap<String, Object> getMaxMinYear() {
		// 객체초기화
		HashMap<String, Object> map = null;
		DBUtil dbUtil = null;
		Connection conn = null;
		try {
			// 드라이버 로딩, 연결
			dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			// AutoCommit off
			conn.setAutoCommit(false);
			// 메서도 호출
			this.cashDao = new CashDao();
			map = cashDao.selectMaxMinYear(conn);
			// conn.commit();
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	public ArrayList<HashMap<String, Object>> getCashSumByMonth(int year, String category) {
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		// 연결 초기화
		Connection conn =null;
		
		try {
			/*
			 DBUtil dbUtil = new DBUtil();
			 dbUtil = conn.getConnection();
			 */
			// 드라이버 로딩, 연결
			conn = new DBUtil().getConnection();
			// AutoCommit off
			conn.setAutoCommit(false);
			// 메서도 호출
			this.cashDao = new CashDao();
			list = cashDao.selectCashSumByMonth(conn, year, category);
			// conn.commit();
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	// 년도별 수입, 치출
	public ArrayList<HashMap<String, Object>> getCashListByCategory(String category) {
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		// 연결 초기화
		Connection conn =null;
		
		try {
			// 드라이버 로딩, 연결
			conn = new DBUtil().getConnection();
			// AutoCommit off
			conn.setAutoCommit(false);
			// 메서도 호출
			this.cashDao = new CashDao();
			list = cashDao.selectCashListByCategory(conn, category);
			// conn.commit();
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}

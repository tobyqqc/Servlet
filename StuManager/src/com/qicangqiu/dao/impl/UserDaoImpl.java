package com.qicangqiu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qicangqiu.dao.UserDao;
import com.qicangqiu.util.JDBCUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean login(String userName, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConn();
			String sql = "select * from t_user where username=? and password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			return rs.next();
//			System.out.println(conn.isClosed());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.release(rs, ps, conn);
		}
		
		return false;
	}

}

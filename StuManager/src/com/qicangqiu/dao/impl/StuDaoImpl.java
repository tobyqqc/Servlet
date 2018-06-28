package com.qicangqiu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qicangqiu.dao.StuDao;
import com.qicangqiu.domain.Student;
import com.qicangqiu.util.JDBCUtil;

public class StuDaoImpl implements StuDao {

	@Override
	public List<Student> findAll() {
		List<Student> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConn();
			String sql = "select * from t_stu";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Student stu = new Student();
				
				stu.setId(rs.getInt("id"));
				stu.setAge(rs.getInt("age"));
				stu.setName(rs.getString("name"));
				stu.setAddress(rs.getString("address"));
				stu.setGender(rs.getString("gender"));
				
				list.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(rs, ps, conn);
		}
		return list;
	}

}

package com.springkadai.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import jdbc.DBConnect;

@Repository
public class LoginTest {
	
	@Test
	public void login() {
		
		String id = "1";
		String pass = "1";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user1 where id=?";
		
		try {
			con = DBConnect.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Assert.assertEquals(pass, rs.getString("pass"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally {
			DBConnect.close(con, pstmt, rs);
		}
	}
	
	public String idCheck(String id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user1 where id=?";
		
		try {
			con = DBConnect.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return "fail";
			}else {
				return "success";
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}finally {
			DBConnect.close(con, pstmt, rs);
		}
	}
	
}

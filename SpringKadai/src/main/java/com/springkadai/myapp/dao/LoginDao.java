package com.springkadai.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import jdbc.DBConnect;

@Repository
public class LoginDao {

	public String login(String id, String pass) {
		
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
				if(pass.equals(rs.getString("pass"))) {
					return "true";
				}else {
					return "パスワードが間違っています。";
				}
			}else {
				return "ユーザーIDが間違っています。";
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
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
				return "このユーザーIDは既に使用されています。";
			}else {
				return "使用できます。";
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

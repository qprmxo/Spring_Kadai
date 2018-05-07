package com.springkadai.myapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.springkadai.myapp.vo.UserDetailVo;

import jdbc.DBConnect;

@Repository
public class UserDetailDao {

	public int insert(String id, Date birth, String club) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into userdetail values(seq_no.nextval,?,?,?)";

		try {
			con = DBConnect.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setDate(2, birth);
			pstmt.setString(3, club);
			return pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return 0;
		}finally {
			DBConnect.close(con, pstmt, null);
		}
	}
	
	public int delete(String id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from userdetail where id=?";
		
		try {
			con = DBConnect.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			return pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return 0;
		}finally {
			DBConnect.close(con, pstmt, null);
		}
	}
	
	public int update(String id, Date birth, String club) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update userdetail set birth=?, club=? where id=?";
		
		try {
			con = DBConnect.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, birth);
			pstmt.setString(2, club);
			pstmt.setString(3, id);
			return pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return 0;
		}finally {
			DBConnect.close(con, pstmt, null);
		}
	}
	
	public UserDetailVo find(String id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from userdetail where id=?";
		
		try {
			con = DBConnect.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				UserDetailVo userdetail = new UserDetailVo(rs.getInt("no"), rs.getString("id"), rs.getDate("birth"), rs.getString("club"));
				return userdetail;
			}
			return null;		
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}finally {
			DBConnect.close(con, pstmt, rs);
		}
	}
}

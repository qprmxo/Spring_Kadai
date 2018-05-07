package com.springkadai.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.springkadai.myapp.vo.UserVo;

import jdbc.DBConnect;

@Repository
public class UserDao {

	public int insert(String id, String pass, String name, String kana) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into user1 values(?,?,?,?)";
		
		try {
			con = DBConnect.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, kana);
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
		String sql = "delete from user1 where id=?";
		
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
	
	public int update(String id, String name, String kana) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update user1 set name=?, kana=? where id=?";
		
		try {
			con = DBConnect.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, kana);
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
	
	public ArrayList<UserVo> select(String id, String name, String kana) {
		
		ArrayList<UserVo> list = new ArrayList<UserVo>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select u.*,ud.* from user1 u, userdetail ud where u.id = ud.id and u.id like ? and u.name like ? and u.kana like ? order by ud.no asc";
		
		try {
			con = DBConnect.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id + "%");
			pstmt.setString(2, name + "%");
			pstmt.setString(3, kana + "%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UserVo user = new UserVo(rs.getString("id"), rs.getString("name"), rs.getString("kana"), rs.getDate("birth"), rs.getString("club"));
				list.add(user);
			}			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}finally {
			DBConnect.close(con, pstmt, rs);
		}
	}
	
	public UserVo find(String id) {
		
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
				UserVo userVo = new UserVo(rs.getString("id"), rs.getString("pass"), rs.getString("name"), rs.getString("kana"));
				return userVo;
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

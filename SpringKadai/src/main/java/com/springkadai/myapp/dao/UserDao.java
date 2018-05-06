package com.springkadai.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import jdbc.DBConnect;

@Repository
public class UserDao {

	public int insert(String id, String pass, String name, String kana) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into user1 values(?,?,?,?)";
		
		System.out.println(sql);
		
		try {
			con = DBConnect.getConn();
			pstmt = con.prepareStatement(sql);
			System.out.println("daoId : " + id);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, kana);
			System.out.println("pstmt.exeup : " + pstmt.executeUpdate());
			return pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return 0;
		}finally {
			DBConnect.close(con, pstmt, null);
		}
	}
}

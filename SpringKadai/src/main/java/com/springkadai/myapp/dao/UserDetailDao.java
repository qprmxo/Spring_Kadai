package com.springkadai.myapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

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
}

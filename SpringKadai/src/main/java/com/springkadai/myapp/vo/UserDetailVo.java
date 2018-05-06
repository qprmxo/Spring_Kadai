package com.springkadai.myapp.vo;

import java.sql.Date;

public class UserDetailVo {

	private int no;
	private String id;
	private Date birth;
	private String club;
	
	public UserDetailVo() {}

	public UserDetailVo(int no, String id, Date birth, String club) {
		super();
		this.no = no;
		this.id = id;
		this.birth = birth;
		this.club = club;
	}
	
	public UserDetailVo(String id, Date birth, String club) {
		super();
		this.id = id;
		this.birth = birth;
		this.club = club;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}
	
	
}

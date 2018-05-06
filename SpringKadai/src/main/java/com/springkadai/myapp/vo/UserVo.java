package com.springkadai.myapp.vo;

import java.sql.Date;

public class UserVo {

	private String id;
	private String pass;
	private String name;
	private String kana;
	
	private Date birth;
	private String club;
	
	public UserVo() {}

	public UserVo(String id, String pass, String name, String kana) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.kana = kana;
	}

	public UserVo(String id, String name, String kana, Date birth, String club) {
		super();
		this.id = id;
		this.name = name;
		this.kana = kana;
		this.birth = birth;
		this.club = club;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKana() {
		return kana;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}
	
	
}

package com.springkadai.myapp.vo;

public class UserVo {

	private String id;
	private String pass;
	private String name;
	private String kana;
	
	public UserVo() {}

	public UserVo(String id, String pass, String name, String kana) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.kana = kana;
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

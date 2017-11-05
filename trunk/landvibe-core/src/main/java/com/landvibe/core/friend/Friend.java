package com.landvibe.core.friend;

import java.util.List;

import com.landvibe.common.model.BaseModel;


public class Friend extends BaseModel {
	private static final long serialVersionUID = -175057078389873341L;

	/**
	 * User Entity
	 */
	private long friend_no;
	private long user_no;
	private String email;
	private String name;
	private String birthday;
	private String birthday_detail;
	private int solarlunar;
	private int gender;
	private String modify_date;
	
	public Friend() {
		super();
	}
	
	
	
	public Friend(long friend_no, long user_no, String email, String name, String birthday,
			String birthday_detail,int solarlunar, int gender, String modify_date) {
		super();
		this.friend_no = friend_no;
		this.user_no = user_no;
		this.email = email;
		this.name = name;
		this.birthday = birthday;
		this.birthday_detail = birthday_detail;
		this.solarlunar = solarlunar;
		this.gender = gender;
		this.modify_date = modify_date;
	}



	public long getFriend_no() {
		return friend_no;
	}
	public void setFriend_no(long friend_no) {
		this.friend_no = friend_no;
	}
	public long getUser_no() {
		return user_no;
	}
	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getBirthday_detail() {
		return birthday_detail;
	}
	public void setBirthday_detail(String birthday_detail) {
		this.birthday_detail = birthday_detail;
	}
	public int getSolarlunar() {
		return solarlunar;
	}
	public void setSolarlunar(int solarlunar) {
		this.solarlunar = solarlunar;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getModify_date() {
		return modify_date;
	}
	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	
	
	
}
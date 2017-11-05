package com.landvibe.core.user;

import java.util.List;

import com.landvibe.common.model.BaseModel;
import com.landvibe.core.userfavorite.UserFavorite;


public class User extends BaseModel {
	private static final long serialVersionUID = -175057078389873349L;

	/**
	 * User Entity
	 */
	private long user_no;
	private String email;
	private String password;
	private String name;
	private String birthday;
	private String birthday_detail;
	private int gender;
	private String modify_date;
	private int solarlunar;
	private String user_token;
	private String user_reg_id;
	private int unalarmed_report_count;
	private String naver_id;
	private String facebook_id;
	
	
	private List<UserFavorite> favoriteList; //즐겨찾기에 사용 구현 유보 
	
	//생성자 추가 필요 
	public User(){}
	public User(long user_no){
		this.user_no = user_no;
	}
	
	public String getFacebook_id() {
		return facebook_id;
	}
	public void setFacebook_id(String facebook_id) {
		this.facebook_id = facebook_id;
	}
	public String getNaver_id() {
		return naver_id;
	}
	public void setNaver_id(String naver_id) {
		this.naver_id = naver_id;
	}
	public int getUnalarmed_report_count() {
		return unalarmed_report_count;
	}
	public void setUnalarmed_report_count(int unalarmed_report_count) {
		this.unalarmed_report_count = unalarmed_report_count;
	}
	public String getUser_reg_id() {
		return user_reg_id;
	}
	public void setUser_reg_id(String user_reg_id) {
		this.user_reg_id = user_reg_id;
	}
	public String getUser_token() {
		return user_token;
	}
	public void setUser_token(String user_token) {
		this.user_token = user_token;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public List<UserFavorite> getFavoriteList() {
		return favoriteList;
	}
	public void setFavoriteList(List<UserFavorite> favoriteList) {
		this.favoriteList = favoriteList;
	}
	
	public int getSolarlunar() {
		return solarlunar;
	}
	public void setSolarlunar(int solarlunar) {
		this.solarlunar = solarlunar;
	}
	

	
}

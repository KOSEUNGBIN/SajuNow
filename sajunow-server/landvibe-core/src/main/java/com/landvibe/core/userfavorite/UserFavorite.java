package com.landvibe.core.userfavorite;

import com.landvibe.common.model.BaseModel;

public class UserFavorite extends BaseModel{

	/**
	 *  UserFavorite Entity
	 */
	private static final long serialVersionUID = -4111140327325229067L;
	
	private long user_no;
	private long company_no;
	
	public UserFavorite() {
		super();
	}
	public UserFavorite(long user_no, long company_no) {
		super();
		this.user_no = user_no;
		this.company_no = company_no;
	}
	public long getUser_no() {
		return user_no;
	}
	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}
	public long getCompany_no() {
		return company_no;
	}
	public void setCompany_no(long company_no) {
		this.company_no = company_no;
	}
	
	

}

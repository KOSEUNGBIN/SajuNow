package com.landvibe.core.companytoreport;

import com.landvibe.common.model.BaseModel;

public class CompanyToReport extends BaseModel{

	/**
	 * Company_Category Entity
	 */
	private static final long serialVersionUID = 5007059448288429620L;
	
	private long company_no;
	private long report_no;
	private float score;
	private String comment;
	private String register_date;
	private String user_nickname;
	private int count;
	private String nick_name;
	
	
	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getNick_name() {
		return nick_name;
	}


	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}


	public CompanyToReport() {
		super();
	}
	
	
	public CompanyToReport(long company_no, long report_no) {
		super();
		this.company_no = company_no;
		this.report_no = report_no;
	}
	
	
	public String getUser_nickname() {
		return user_nickname;
	}


	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}


	public long getCompany_no() {
		return company_no;
	}
	public void setCompany_no(long company_no) {
		this.company_no = company_no;
	}
	public long getReport_no() {
		return report_no;
	}
	public void setReport_no(long report_no) {
		this.report_no = report_no;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getRegister_date() {
		return register_date;
	}
	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}
	
	
	
	



	
	
	
	
}

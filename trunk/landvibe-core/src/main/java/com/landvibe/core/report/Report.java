package com.landvibe.core.report;

import com.landvibe.common.model.BaseModel;

public class Report extends BaseModel{
	
	
	/**
	 *  후기 Entity
	 */
	private static final long serialVersionUID = 8930472457515797152L;
	
	private long report_no;
	private long company_no;
	private float score;
	private String comment;
	private String register_date;
	private String user_nickname;
	
	
	
	
	public Report(long report_no) {
		super();
		this.report_no = report_no;
	}
	
	
	
	
	public Report(float score, String comment, String user_nickname) {
		super();
		this.score = score;
		this.comment = comment;
		this.user_nickname = user_nickname;
	}




	public String getUser_nickname() {
		return user_nickname;
	}
	
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public long getReport_no() {
		return report_no;
	}
	public void setReport_no(long report_no) {
		this.report_no = report_no;
	}
	public long getCompany_no() {
		return company_no;
	}
	public void setCompany_no(long company_no) {
		this.company_no = company_no;
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

package com.landvibe.core.promotion;

import com.landvibe.common.model.BaseModel;

public class Promotion extends BaseModel{
	
	
	/**
	 *  광고 Entity
	 */
	private static final long serialVersionUID = 8930472457515797155L;
	
	private long promotion_no;
	private long company_no;
	private String start_date;
	private String end_date;
	
	
	// 생성자 추가 필요 
	public Promotion(){}
	public Promotion(long promotion_no){
		this.promotion_no = promotion_no;
	}


	public long getPromotion_no() {
		return promotion_no;
	}


	public void setPromotion_no(long promotion_no) {
		this.promotion_no = promotion_no;
	}


	public long getCompany_no() {
		return company_no;
	}


	public void setCompany_no(long company_no) {
		this.company_no = company_no;
	}


	public String getStart_date() {
		return start_date;
	}


	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}


	public String getEnd_date() {
		return end_date;
	}


	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	
	
}

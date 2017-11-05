package com.landvibe.core.companyfaq;

import com.landvibe.common.model.BaseModel;

public class CompanyFaq extends BaseModel{

	/**
	 *  Q&A Entity
	 */
	private static final long serialVersionUID = 6182796256729650970L;
	
	private long faq_no;
	private String title;
	private String content;
	private String register_date;
	
	
	public CompanyFaq() {
		super();
	}
	public CompanyFaq(long faq_no, String title, String content, String register_date) {
		super();
		this.faq_no = faq_no;
		this.title = title;
		this.content = content;
		this.register_date = register_date;
	}
	
	public long getFaq_no() {
		return faq_no;
	}
	public void setFaq_no(long faq_no) {
		this.faq_no = faq_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegister_date() {
		return register_date;
	}
	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}
	
	
	
	
}

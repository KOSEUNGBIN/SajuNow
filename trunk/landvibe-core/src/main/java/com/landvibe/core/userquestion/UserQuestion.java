package com.landvibe.core.userquestion;

import com.landvibe.common.model.BaseModel;

public class UserQuestion extends BaseModel{

	/**
	 *  Q&A Entity
	 */
	private static final long serialVersionUID = 6182796256729650970L;
	
	private long question_no;
	private String title;
	private String content;
	private String register_date;
	
	
	public UserQuestion() {
		super();
	}
	public UserQuestion(long question_no, String title, String content, String register_date) {
		super();
		this.question_no = question_no;
		this.title = title;
		this.content = content;
		this.register_date = register_date;
	}
	public long getQuestion_no() {
		return question_no;
	}
	public void setQuestion_no(long question_no) {
		this.question_no = question_no;
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

package com.landvibe.core.chatmessage;

import com.landvibe.common.model.BaseModel;

public class ChatMessage extends BaseModel {

	/**
	 * Message Entity
	 * 
	 * GCM의 Message와 이름 겹쳐서 ChatMessage로 변경 
	 */
	private static final long serialVersionUID = -9216676794550767226L;
	
	
	private long message_no;
	private long company_no;
	private long history_no;
	private String message;
	private long sender;
	private String send_date;
	
	public ChatMessage() {
		super();
	}

	public ChatMessage(long message_no, long history_no, String message, long sender, String send_date) {
		super();
		this.message_no = message_no;
		this.history_no = history_no;
		this.message = message;
		this.sender = sender;
		this.send_date = send_date;
	}
	
	public ChatMessage( long history_no, String message, long sender, String send_date) {
		super();
		this.history_no = history_no;
		this.message = message;
		this.sender = sender;
		this.send_date = send_date;
	}
	
	public ChatMessage( long history_no, String message, long sender) {
		super();
		this.history_no = history_no;
		this.message = message;
		this.sender = sender;
	}
	
	

	public long getCompany_no() {
		return company_no;
	}

	public void setCompany_no(long company_no) {
		this.company_no = company_no;
	}

	public long getMessage_no() {
		return message_no;
	}

	public void setMessage_no(long message_no) {
		this.message_no = message_no;
	}

	public long getHistory_no() {
		return history_no;
	}

	public void setHistory_no(long history_no) {
		this.history_no = history_no;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getSender() {
		return sender;
	}

	public void setSender(long sender) {
		this.sender = sender;
	}

	public String getSend_date() {
		return send_date;
	}

	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	
}

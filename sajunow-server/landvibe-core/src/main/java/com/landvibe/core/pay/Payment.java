package com.landvibe.core.pay;

import org.springframework.stereotype.Component;

import com.landvibe.common.model.BaseModel;

@Component
public class Payment extends BaseModel {

	private static final long serialVersionUID = -5173263175385935051L;
	
	private String mid;
	private int unitprice;
	private long payment_no;
	private long user_no;
	private long company_no;
	private long history_no;
	private long margin;
	private String replycode;
	private String replyMsg;
	private String tid;
	private String mb_serial_no;
	private String goodcurrency;
	private String cardtype;
	private String hashresult;
	private String payment_date;
	private String receipttoemail;
	
	private String payment_date_month;
	private long payment_count_month;
	private long payment_sum_month;
	
	
	public Payment() {
		super();
	}
	

	public Payment(int unitprice, String replycode ,String tid, String cardtype, String receipttoemail) {
		super();
		this.replycode = replycode;
		this.unitprice = unitprice;
		this.tid = tid;
		this.cardtype = cardtype;
		this.receipttoemail = receipttoemail;
	}
	
	
	
	public Payment(long user_no, long company_no, long history_no, String receipttoemail, long margin) {
		super();
		this.user_no = user_no;
		this.company_no = company_no;
		this.history_no = history_no;
		this.receipttoemail = receipttoemail;
		this.margin = margin;
	}


	public long getCompany_no() {
		return company_no;
	}


	public void setCompany_no(long company_no) {
		this.company_no = company_no;
	}


	public long getMargin() {
		return margin;
	}


	public void setMargin(long margin) {
		this.margin = margin;
	}


	public String getMid() {
		return mid;
	}


	public void setMid(String mid) {
		this.mid = mid;
	}


	public int getUnitprice() {
		return unitprice;
	}


	public void setUnitprice(int unitprice) {
		this.unitprice = unitprice;
	}


	public long getPayment_no() {
		return payment_no;
	}


	public void setPayment_no(long payment_no) {
		this.payment_no = payment_no;
	}


	public long getUser_no() {
		return user_no;
	}


	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}


	public long getHistory_no() {
		return history_no;
	}


	public void setHistory_no(long history_no) {
		this.history_no = history_no;
	}


	public String getReplycode() {
		return replycode;
	}


	public void setReplycode(String replycode) {
		this.replycode = replycode;
	}


	public String getReplyMsg() {
		return replyMsg;
	}


	public void setReplyMsg(String replyMsg) {
		this.replyMsg = replyMsg;
	}


	public String getTid() {
		return tid;
	}


	public void setTid(String tid) {
		this.tid = tid;
	}


	public String getMb_serial_no() {
		return mb_serial_no;
	}


	public void setMb_serial_no(String mb_serial_no) {
		this.mb_serial_no = mb_serial_no;
	}


	public String getGoodcurrency() {
		return goodcurrency;
	}


	public void setGoodcurrency(String goodcurrency) {
		this.goodcurrency = goodcurrency;
	}


	public String getCardtype() {
		return cardtype;
	}


	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}


	public String getHashresult() {
		return hashresult;
	}


	public void setHashresult(String hashresult) {
		this.hashresult = hashresult;
	}


	public String getPayment_date() {
		return payment_date;
	}


	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}


	public String getReceipttoemail() {
		return receipttoemail;
	}


	public void setReceipttoemail(String receipttoemail) {
		this.receipttoemail = receipttoemail;
	}


	public String getPayment_date_month() {
		return payment_date_month;
	}


	public void setPayment_date_month(String payment_date_month) {
		this.payment_date_month = payment_date_month;
	}


	public long getPayment_count_month() {
		return payment_count_month;
	}


	public void setPayment_count_month(long payment_count_month) {
		this.payment_count_month = payment_count_month;
	}


	public long getPayment_sum_month() {
		return payment_sum_month;
	}


	public void setPayment_sum_month(long payment_sum_month) {
		this.payment_sum_month = payment_sum_month;
	}


	
	
}

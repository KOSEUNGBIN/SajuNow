
package com.landvibe.core.history;

import java.util.List;

import com.landvibe.common.model.BaseModel;
import com.landvibe.core.chatmessage.ChatMessage;
import com.landvibe.core.companytocategory.CompanyToCategory;

public class History extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -666200902510592495L;
	/**
	 * 후기 Entity
	 */

	private long history_no;
	private boolean offline_yn;
	private boolean end_yn;
	private boolean simple_history_end_yn;
	private int select_history;
	private String start_date;
	private String end_date;
	private long user_no;
	private long company_no;
	private List<ChatMessage> msgList;
	private ChatMessage msg;
	private String nick_name;
	private List<CompanyToCategory> HistoryList;
	private String name;
	private String user_reg_id;
	private String company_reg;
	private long user_not_read;
	private long company_not_read;
	private long is_report_alarmed;
	private boolean isdeleted_user;
	private boolean isdeleted_company;
	private int count;

	public History() {
		super();
	}

	/**
	 * User app에서 접근
	 * 
	 * @param ft_history,
	 *            ft_user Table의 name 추가
	 * 
	 */
	public History(long history_no, boolean offline_yn, boolean end_yn, boolean simple_history_end_yn, int select_history, String start_date, String end_date,
			long user_no, long company_no, String name) {
		super();
		this.history_no = history_no;
		this.company_no = company_no;
		this.user_no = user_no;
		this.offline_yn = offline_yn;
		this.end_yn = end_yn;
		this.select_history = select_history;
		this.start_date = start_date;
		this.end_date = end_date;
		this.name = name;
	}

	/**
	 * Company app에서 접근
	 * 
	 * @param ft_history,
	 *            ft_company, ft_CompanyToCategory Table의 nick_name, HistoryList
	 *            추가
	 * 
	 */
	public History(long history_no, boolean offline_yn, boolean end_yn, boolean simple_history_end_yn, int select_history, String start_date, String end_date,
			long user_no, long company_no, String nick_name, List<CompanyToCategory> HistoryList) {
		super();
		this.history_no = history_no;
		this.company_no = company_no;
		this.user_no = user_no;
		this.offline_yn = offline_yn;
		this.end_yn = end_yn;
		this.simple_history_end_yn = simple_history_end_yn;
		this.select_history = select_history;
		this.start_date = start_date;
		this.end_date = end_date;
		this.nick_name = nick_name;
		this.HistoryList = HistoryList;
	}

	/**
	 * 양쪽 app에서 접근
	 * 
	 * @param ft_history,
	 *            ft_message Table의 msgList 추가
	 * 
	 */
	public History(long history_no, boolean offline_yn, boolean end_yn, boolean simple_history_end_yn, int select_history, String start_date, String end_date,
			long user_no, long company_no, List<ChatMessage> msgList) {
		super();
		this.history_no = history_no;
		this.company_no = company_no;
		this.user_no = user_no;
		this.offline_yn = offline_yn;
		this.end_yn = end_yn;
		this.simple_history_end_yn = simple_history_end_yn;
		this.select_history = select_history;
		this.start_date = start_date;
		this.end_date = end_date;
		this.msgList = msgList;
	}

	public History(long history_no, boolean offline_yn, boolean end_yn, boolean simple_history_end_yn, int select_history, String start_date, String end_date,
			long user_no, long company_no, List<ChatMessage> msgList,ChatMessage msg, String nick_name,
			List<CompanyToCategory> historyList, String name, String user_reg_id, String company_reg,
			long user_not_read, long company_not_read, long is_report_alarmed, boolean isdeleted_user, boolean isdeleted_company) {
		super();
		this.history_no = history_no;
		this.offline_yn = offline_yn;
		this.end_yn = end_yn;
		this.simple_history_end_yn = simple_history_end_yn;
		this.select_history = select_history;
		this.start_date = start_date;
		this.end_date = end_date;
		this.user_no = user_no;
		this.company_no = company_no;
		this.msgList = msgList;
		this.msg = msg;
		this.nick_name = nick_name;
		HistoryList = historyList;
		this.name = name;
		this.user_reg_id = user_reg_id;
		this.company_reg = company_reg;
		this.user_not_read = user_not_read;
		this.company_not_read = company_not_read;
		this.is_report_alarmed = is_report_alarmed;
		this.isdeleted_user = isdeleted_user;
		this.isdeleted_company = isdeleted_company;
	}
	
	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getIs_report_alarmed() {
		return is_report_alarmed;
	}

	public void setIs_report_alarmed(long is_report_alarmed) {
		this.is_report_alarmed = is_report_alarmed;
	}

	public long getHistory_no() {
		return history_no;
	}

	public void setHistory_no(long history_no) {
		this.history_no = history_no;
	}

	public long getCompany_no() {
		return company_no;
	}

	public void setCompany_no(long company_no) {
		this.company_no = company_no;
	}

	public long getUser_no() {
		return user_no;
	}

	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}

	public boolean isOffline_yn() {
		return offline_yn;
	}

	public void setOffline_yn(boolean offline_yn) {
		this.offline_yn = offline_yn;
	}

	public boolean isEnd_yn() {
		return end_yn;
	}

	public void setEnd_yn(boolean end_yn) {
		this.end_yn = end_yn;
	}
	
	public boolean isSimple_history_end_yn() {
		return simple_history_end_yn;
	}

	public void setSimple_history_end_yn(boolean simple_history_end_yn) {
		this.simple_history_end_yn = simple_history_end_yn;
	}
	
	public int getSelect_history() {
		return select_history;
	}

	public void setSelect_history(int select_history) {
		this.select_history = select_history;
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

	public List<ChatMessage> getMsgList() {
		return msgList;
	}

	public void setMsgList(List<ChatMessage> msgList) {
		this.msgList = msgList;
	}
	public ChatMessage getMsg() {
		return msg;
	}

	public void setMsg(ChatMessage msg) {
		this.msg = msg;
	}
	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public List<CompanyToCategory> getHistoryList() {
		return HistoryList;
	}

	public void setHistoryList(List<CompanyToCategory> detail_category) {
		this.HistoryList = HistoryList;
	}

	public String getName() {
		return name;
	}

	public void SetName(String name) {
		this.name = name;
	}

	public String getUser_reg_id() {
		return user_reg_id;
	}

	public void setUser_reg_id(String user_reg_id) {
		this.user_reg_id = user_reg_id;
	}

	public String getCompany_reg_id() {
		return company_reg;
	}

	public void setCompany_reg_id(String company_reg_id) {
		this.company_reg = company_reg_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany_reg() {
		return company_reg;
	}

	public void setCompany_reg(String company_reg) {
		this.company_reg = company_reg;
	}

	public long getUser_not_read() {
		return user_not_read;
	}

	public void setUser_not_read(long user_not_read) {
		this.user_not_read = user_not_read;
	}

	public long getCompany_not_read() {
		return company_not_read;
	}

	public void setCompany_not_read(long company_not_read) {
		this.company_not_read = company_not_read;
	}

	public boolean isIsdeleted_user() {
		return isdeleted_user;
	}

	public void setIsdeleted_user(boolean isdeleted_user) {
		this.isdeleted_user = isdeleted_user;
	}

	public boolean isIsdeleted_company() {
		return isdeleted_company;
	}

	public void setIsdeleted_company(boolean isdeleted_company) {
		this.isdeleted_company = isdeleted_company;
	}

}

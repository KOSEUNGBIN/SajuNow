package com.landvibe.core.company;

import java.util.List;

import com.landvibe.common.model.BaseModel;
import com.landvibe.core.companytocategory.CompanyToCategory;
import com.landvibe.core.companytoreport.CompanyToReport;

public class Company extends BaseModel{

	/**
	 * Company Entity 
	 */
	private static final long serialVersionUID = -2270896007662573614L;
	
	private long company_no;
	private String email;
	private String password;
	private String nick_name;
	private double score_average;
	private String experience;
	private String address;
	private boolean only_online;
	private boolean history_simple;
	private long history_count;
	private boolean simple_chat_possibility_result;
	private long simple_history_possibility_count;
	private int simple_chat_count;
	private boolean simple_chat_possibility;
	private long history_possibility_count;
	private int chat_count;
	private boolean chat_possibility;
	private boolean chat_possibility_result;
	private boolean chat_switch;
	private String history_introduce;	
	private String greeting;	
	private String introduce;
	private boolean online_pay;
	private boolean offline_pay;
	private long premium;
	private long margin;
	private String modify_date;
	private String company_token;
	private String company_reg_id;
	private String possible_time;
	private String category_list;
	private String category_detail;
	private int report_count;
	private long favorite_count;
	
	
	
	private String category_code;
	private String category_name;
	List<CompanyToReport> companyToReport;
	List<CompanyToCategory> companyToCategory;
	
	private long user_no;
	
	
	
	//생성자 추가 필요 
	public Company(){}
	public Company(long company_no){
		this.company_no = company_no;
	}
	
	
	
	
	
	public String getCategory_detail() {
		return category_detail;
	}
	public void setCategory_detail(String category_detail) {
		this.category_detail = category_detail;
	}
	public long getFavorite_count() {
		return favorite_count;
	}
	public void setFavorite_count(long favorite_count) {
		this.favorite_count = favorite_count;
	}
	public boolean isChat_possibility_result() {
		return chat_possibility_result;
	}
	public void setChat_possibility_result(boolean chat_possibility_result) {
		this.chat_possibility_result = chat_possibility_result;
	}
	public boolean isChat_switch() {
		return chat_switch;
	}
	public void setChat_switch(boolean chat_switch) {
		this.chat_switch = chat_switch;
	}
	public int getReport_count() {
		return report_count;
	}
	public void setReport_count(int report_count) {
		this.report_count = report_count;
	}
	public String getCategory_list() {
		return category_list;
	}
	public void setCategory_list(String category_list) {
		this.category_list = category_list;
	}
	public String getCompany_reg_id() {
		return company_reg_id;
	}
	public void setCompany_reg_id(String company_reg_id) {
		this.company_reg_id = company_reg_id;
	}
	public String getCompany_token() {
		return company_token;
	}
	public void setCompany_token(String company_token) {
		this.company_token = company_token;
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
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public double getScore_average() {
		return score_average;
	}
	public void setScore_average(double score_average) {
		this.score_average = score_average;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isOnly_online() {
		return only_online;
	}
	public void setOnly_online(boolean only_online) {
		this.only_online = only_online;
	}
	public boolean isHistory_simple() {
		return history_simple;
	}
	public void setHistory_simple(boolean history_simple) {
		this.history_simple = history_simple;
	}
	public long getHistory_count() {
		return history_count;
	}
	public void setHistory_count(long history_count) {
		this.history_count = history_count;
	}
	public boolean isSimple_chat_possibility_result() {
		return simple_chat_possibility_result;
	}
	public void setSimple_chat_possibility_result(boolean simple_chat_possibility_result) {
		this.simple_chat_possibility_result = simple_chat_possibility_result;
	}
	
	public long getSimple_history_possibility_count() {
		return simple_history_possibility_count;
	}
	public void setSimple_history_possibility_count(long simple_history_possibility_count) {
		this.simple_history_possibility_count = simple_history_possibility_count;
	}
	public int getSimple_chat_count() {
		return simple_chat_count;
	}
	public void setSimple_chat_count(int simple_chat_count) {
		this.simple_chat_count = simple_chat_count;
	}
	public boolean isSimple_chat_possibility() {
		return simple_chat_possibility;
	}
	public void setSimple_chat_possibility(boolean simple_chat_possibility) {
		this.simple_chat_possibility = simple_chat_possibility;
	}
	public long getHistory_possibility_count() {
		return history_possibility_count;
	}
	public void setHistory_possibility_count(long history_possibility_count) {
		this.history_possibility_count = history_possibility_count;
	}
	public int getChat_count() {
		return chat_count;
	}
	public void setChat_count(int chat_count) {
		this.chat_count = chat_count;
	}
	public boolean getChat_possibility() {
		return chat_possibility;
	}
	public void setChat_possibility(boolean chat_possibility) {
		this.chat_possibility = chat_possibility;
	}
	public String getHistory_introduce() {
		return history_introduce;
	}
	public void setHistory_introduce(String history_introduce) {
		this.history_introduce = history_introduce;
	}
	public String getGreeting() {
		return greeting;
	}
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public boolean isOnline_pay() {
		return online_pay;
	}
	public void setOnline_pay(boolean online_pay) {
		this.online_pay = online_pay;
	}
	public boolean isOffline_pay() {
		return offline_pay;
	}
	public void setOffline_pay(boolean offline_pay) {
		this.offline_pay = offline_pay;
	}
	public long getPremium() {
		return premium;
	}
	public void setPremium(long premium) {
		this.premium = premium;
	}
	
	public long getmargin() {
		return margin;
	}
	public void setmargin(long margin) {
		this.margin = margin;
	}
	public String getModify_date() {
		return modify_date;
	}
	public void setModify_date(String modify_date) {
		this.modify_date = modify_date;
	}
	
	public String getPossible_time() {
		return possible_time;
	}
	public void setPossible_time(String possible_time) {
		this.possible_time = possible_time;
	}
	
	public List<CompanyToReport> getCompanyToReport() {
		return companyToReport;
	}
	public void setCompanyToReport(List<CompanyToReport> companyToReport) {
		this.companyToReport = companyToReport;
	}
	public String getCategory_code() {
		return category_code;
	}
	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public List<CompanyToCategory> getCompanyToCategory() {
		return companyToCategory;
	}
	public void setCompanyToCategory(List<CompanyToCategory> companyToCategory) {
		this.companyToCategory = companyToCategory;
	}
	
	
}

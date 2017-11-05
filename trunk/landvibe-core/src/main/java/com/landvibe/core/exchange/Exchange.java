package com.landvibe.core.exchange;

import org.springframework.stereotype.Component;

import com.landvibe.common.model.BaseModel;

@Component
public class Exchange extends BaseModel {

	private static final long serialVersionUID = -5173263175385435051L;
	
	 	private long exchange_no;
	 	private long company_no;
	    private long exchange_amount;
	    private String status;
	    private String exchange_date;
	    
	    private String nick_name;
	    private String exchange_date_month;
	    private long exchange_count_month;
	    private long exchange_sum_month;
	    
	    public Exchange(){
	    	super();
	    }

	   

	    public Exchange(long exchange_no, long company_no, long exchange_amount, String status,
				String exchange_date) {
			super();
			this.exchange_no = exchange_no;
			this.company_no = company_no;
			this.exchange_amount = exchange_amount;
			this.status = status;
			this.exchange_date = exchange_date;
		}



		public String getNick_name() {
			return nick_name;
		}



		public void setNick_name(String nick_name) {
			this.nick_name = nick_name;
		}



		public long getCompany_no() {
			return company_no;
		}



		public void setCompany_no(long company_no) {
			this.company_no = company_no;
		}



		public long getExchange_no() {
	        return exchange_no;
	    }

	    public void setExchange_no(long exchange_no) {
	        this.exchange_no = exchange_no;
	    }

	    public long getExchange_amount() {
	        return exchange_amount;
	    }

	    public void setExchange_amount(long exchange_amount) {
	        this.exchange_amount = exchange_amount;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public String getExchange_date() {
	        return exchange_date;
	    }

	    public void setExchange_date(String exchange_date) {
	        this.exchange_date = exchange_date;
	    }



		public String getExchange_date_month() {
			return exchange_date_month;
		}



		public void setExchange_date_month(String exchange_date_month) {
			this.exchange_date_month = exchange_date_month;
		}



		public long getExchange_count_month() {
			return exchange_count_month;
		}



		public void setExchange_count_month(long exchange_count_month) {
			this.exchange_count_month = exchange_count_month;
		}



		public long getExchange_sum_month() {
			return exchange_sum_month;
		}



		public void setExchange_sum_month(long exchange_sum_month) {
			this.exchange_sum_month = exchange_sum_month;
		}

	    
	
}

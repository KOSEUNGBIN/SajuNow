package com.landvibe.core.companytocategory;

import com.landvibe.common.model.BaseModel;

public class CompanyToCategory extends BaseModel{

	/**
	 * Company_Category Entity
	 */
	private static final long serialVersionUID = 5007059448288459740L;
	
	private long idx;
	private long company_no;
	private long category_code;
	
	private String category_name; //company - category join할 때 사용 
	private long unitprice; //company - category join할 때 사용 

	public CompanyToCategory() {
		super();
	}

	public CompanyToCategory(long idx, long company_no, long category_code, String category_name,long unitprice) {
		super();
		this.idx = idx;
		this.company_no = company_no;
		this.category_code = category_code;
		this.category_name = category_name;
		this.unitprice = unitprice;
	}

	public long getIdx() {
		return idx;
	}

	public void setIdx(long idx) {
		this.idx = idx;
	}

	public long getCompany_no() {
		return company_no;
	}

	public void setCompany_no(long company_no) {
		this.company_no = company_no;
	}

	public long getCategory_code() {
		return category_code;
	}

	public void setCategory_code(long category_code) {
		this.category_code = category_code;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public long getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(long unitprice) {
		this.unitprice = unitprice;
	}
	
	
	
	
}

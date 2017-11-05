package com.landvibe.core.category;

import com.landvibe.common.model.BaseModel;

public class Category extends BaseModel{

	/**
	 * Category Entity
	 */
	private static final long serialVersionUID = -5173263875485935051L;
	
	private long category_code;
	private String category_name;
	
	public Category(){}
	public Category(long category_code){
		this.category_code = category_code;
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
	
}

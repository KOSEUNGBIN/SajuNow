package com.landvibe.core.companytocategory;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyToCategoryBo {
	
	/**
	 * 안쓸꺼 같음 
	 */
	
	@Autowired
	private CompanyToCategoryDao companyToCategoryDao;
	
	public CompanyToCategory getByNo(long company_no){
		return companyToCategoryDao.select(company_no);
	}
	
	public List<CompanyToCategory> getAll(){
		return companyToCategoryDao.selectAll();
	}
	
	public void insert(long category_code, long company_no){
		companyToCategoryDao.insert(category_code, company_no);
	}

}

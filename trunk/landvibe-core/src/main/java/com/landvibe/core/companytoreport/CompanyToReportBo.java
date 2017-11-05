package com.landvibe.core.companytoreport;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyToReportBo {
	
	/**
	 * 안쓸꺼 같음 
	 */
	
	@Autowired
	private CompanyToReportDao companyToReportDao;
	
	
	public void insert(CompanyToReport companyToReport){
		companyToReportDao.insert(companyToReport);
	}
	
	public List<Object> getCompanyReportRank(){
		return companyToReportDao.selectReportRank();
	}
	
	public void deleteList(List<Long> report_no_list){
		companyToReportDao.deleteList(report_no_list);
	}

}

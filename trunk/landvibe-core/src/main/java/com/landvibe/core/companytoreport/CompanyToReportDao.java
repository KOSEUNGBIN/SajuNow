package com.landvibe.core.companytoreport;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyToReportDao {
	
	
	void insert(CompanyToReport companyToReport);
	
	List<Object> selectReportRank();
	
	void deleteList(List<Long> report_no_list);

}

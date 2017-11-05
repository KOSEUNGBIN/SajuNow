package com.landvibe.core.report;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDao {
	
	int insert(Report report);
	
	long selectKey();
	
}

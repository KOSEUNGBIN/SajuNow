package com.landvibe.core.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReportBo {
	
	@Autowired
	private ReportDao reportDao;
	
	/**
	 * Promotion 생성  
	 * @param promotion
	 */
	public long create(Report report) {
		reportDao.insert(report);
		return reportDao.selectKey();
	}
	
	
	

}

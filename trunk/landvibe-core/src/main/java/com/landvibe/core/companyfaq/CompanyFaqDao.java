package com.landvibe.core.companyfaq;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyFaqDao {
	
	int insert(CompanyFaq faq);

	CompanyFaq selectByNo(@Param("faq_no") long faq_no);
	
	List<CompanyFaq> selectList();
	
	List<CompanyFaq> selectDateList();
	
	int deleteByNo(@Param("faq_no") long faq_no);
	
	int update(CompanyFaq faq);
	

}

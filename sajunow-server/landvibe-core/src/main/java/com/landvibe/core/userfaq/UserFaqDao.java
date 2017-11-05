package com.landvibe.core.userfaq;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFaqDao {
	
	int insert(UserFaq faq);

	UserFaq selectByNo(@Param("faq_no") long faq_no);
	
	List<UserFaq> selectList();
	
	List<UserFaq> selectDateList();
	
	int deleteByNo(@Param("faq_no") long faq_no);
	
	int update(UserFaq faq);
	

}

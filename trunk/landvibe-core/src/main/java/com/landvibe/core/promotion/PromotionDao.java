package com.landvibe.core.promotion;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionDao {
	
	int insert(Promotion promtion);

	Promotion selectByNo(@Param("promotion_no") long promotion_no);

	int deleteByPomotionNo(@Param("promotion_no") long promotion_no);
	
	int deleteByCompanyNo(@Param("company_no") long company_no);
	
	int update(Promotion promtion);

}

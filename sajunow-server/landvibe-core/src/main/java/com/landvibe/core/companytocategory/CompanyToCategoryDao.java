package com.landvibe.core.companytocategory;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyToCategoryDao {
	
	CompanyToCategory select(@Param("company_no") long company_no );
	
	List<CompanyToCategory> selectAll();
	
	void insert(@Param("category_code") long category_code, @Param("company_no") long company_no);

}

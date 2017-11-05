package com.landvibe.core.userfavorite;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserFavoriteDao {
	
	void insert(@Param("user_no") long user_no, @Param("company_no") long company_no);

	void delete(@Param("user_no") long user_no, @Param("company_no") long company_no);
	
	List<UserFavorite> select(@Param("user_no") long user_no, @Param("company_no") long company_no);
	
	long selectFavoriteCount(@Param("company_no") long company_no);
}

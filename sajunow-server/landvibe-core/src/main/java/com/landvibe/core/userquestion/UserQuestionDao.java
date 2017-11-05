package com.landvibe.core.userquestion;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuestionDao {
	
	int insert(UserQuestion question);

	UserQuestion selectByNo(@Param("question_no") long question_no);
	
	List<UserQuestion> selectList();
	
	List<UserQuestion> selectDateList();
	
	int deleteByNo(@Param("question_no") long question_no);
	
	int update(UserQuestion question);
	

}

package com.landvibe.core.companyquestion;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.landvibe.core.userquestion.UserQuestion;

@Repository
public interface CompanyQuestionDao {

	int insert(CompanyQuestion question);

	CompanyQuestion selectByNo(@Param("question_no") long question_no);

	List<CompanyQuestion> selectList();
	
	List<UserQuestion> selectDateList();

	int deleteByNo(@Param("question_no") long question_no);

	int update(CompanyQuestion question);
}

package com.landvibe.core.companyquestion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.landvibe.core.userquestion.UserQuestion;

@Service
public class CompanyQuestionBo {


	@Autowired
	CompanyQuestionDao companyQuestionDao;
	
	
	/**
	 * Q&A 게시글 생성  
	 * @param question
	 * @return
	 */
	public int create(CompanyQuestion question){
		return companyQuestionDao.insert(question);
	}
	
	/**
	 * 게시글 불러오기 
	 * @param question_no
	 * @return
	 */
	public CompanyQuestion getByNo(long question_no){
		return companyQuestionDao.selectByNo(question_no);
	}
	
	/**
	 * 게시글 모든 리스트 불러오기 
	 * @return
	 */
	public List<CompanyQuestion> getList(){
		return companyQuestionDao.selectList();
	}
	
	/**
	 * 게시글의 등록 날짜 리스트 불러오기
	 * @return
	 */
	public List<UserQuestion> getRegisterDateList(){
		return companyQuestionDao.selectDateList();
	}
	
	
	/**
	 * 게시글 삭제 
	 * @param question_no
	 * @return
	 */
	public int deleteByNo(long question_no){
		return companyQuestionDao.deleteByNo(question_no);
	}
	
	/**
	 * 게시글 수정 
	 * @param question
	 * @return
	 */
	public int update(CompanyQuestion question){
		return companyQuestionDao.update(question);
	}

}

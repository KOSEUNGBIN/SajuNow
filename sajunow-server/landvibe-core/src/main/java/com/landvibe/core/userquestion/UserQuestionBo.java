package com.landvibe.core.userquestion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserQuestionBo {
	
	@Autowired
	UserQuestionDao userQuestionDao;
	
	
	/**
	 * Q&A 게시글 생성  
	 * @param question
	 * @return
	 */
	public int create(UserQuestion question){
		return userQuestionDao.insert(question);
	}
	
	/**
	 * 게시글 불러오기 
	 * @param question_no
	 * @return
	 */
	public UserQuestion getByNo(long question_no){
		return userQuestionDao.selectByNo(question_no);
	}
	
	/**
	 * 게시글 모든 리스트 불러오기 
	 * @return
	 */
	public List<UserQuestion> getList(){
		return userQuestionDao.selectList();
	}
	
	/**
	 * 게시글의 등록 날짜 리스트 불러오기
	 * @return
	 */
	public List<UserQuestion> getRegisterDateList(){
		return userQuestionDao.selectDateList();
	}
	
	
	/**
	 * 게시글 삭제 
	 * @param question_no
	 * @return
	 */
	public int deleteByNo(long question_no){
		return userQuestionDao.deleteByNo(question_no);
	}
	
	/**
	 * 게시글 수정 
	 * @param question
	 * @return
	 */
	public int update(UserQuestion question){
		return userQuestionDao.update(question);
	}

}

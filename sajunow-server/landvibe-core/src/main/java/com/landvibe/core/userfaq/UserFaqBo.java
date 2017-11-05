package com.landvibe.core.userfaq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFaqBo {
	
	@Autowired
	UserFaqDao userFaqDao;
	
	
	/**
	 * Q&A 게시글 생성  
	 * @param question
	 * @return
	 */
	public int create(UserFaq faq){
		return userFaqDao.insert(faq);
	}
	
	/**
	 * 게시글 불러오기 
	 * @param question_no
	 * @return
	 */
	public UserFaq getByNo(long faq_no){
		return userFaqDao.selectByNo(faq_no);
	}
	
	/**
	 * 게시글 모든 리스트 불러오기 
	 * @return
	 */
	public List<UserFaq> getList(){
		return userFaqDao.selectList();
	}
	
	/**
	 * 게시글의 등록 날짜 리스트 불러오기
	 * @return
	 */
	public List<UserFaq> getRegisterDateList(){
		return userFaqDao.selectDateList();
	}
	
	
	/**
	 * 게시글 삭제 
	 * @param question_no
	 * @return
	 */
	public int deleteByNo(long faq_no){
		return userFaqDao.deleteByNo(faq_no);
	}
	
	/**
	 * 게시글 수정 
	 * @param question
	 * @return
	 */
	public int update(UserFaq faq){
		return userFaqDao.update(faq);
	}

}

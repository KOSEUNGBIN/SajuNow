package com.landvibe.core.companyfaq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyFaqBo {
	
	@Autowired
	CompanyFaqDao companyFaqDao;
	
	
	/**
	 * Q&A 게시글 생성  
	 * @param question
	 * @return
	 */
	public int create(CompanyFaq faq){
		return companyFaqDao.insert(faq);
	}
	
	/**
	 * 게시글 불러오기 
	 * @param question_no
	 * @return
	 */
	public CompanyFaq getByNo(long faq_no){
		return companyFaqDao.selectByNo(faq_no);
	}
	
	/**
	 * 게시글 모든 리스트 불러오기 
	 * @return
	 */
	public List<CompanyFaq> getList(){
		return companyFaqDao.selectList();
	}
	
	/**
	 * 게시글의 등록 날짜 리스트 불러오기
	 * @return
	 */
	public List<CompanyFaq> getRegisterDateList(){
		return companyFaqDao.selectDateList();
	}
	
	
	/**
	 * 게시글 삭제 
	 * @param question_no
	 * @return
	 */
	public int deleteByNo(long faq_no){
		return companyFaqDao.deleteByNo(faq_no);
	}
	
	/**
	 * 게시글 수정 
	 * @param question
	 * @return
	 */
	public int update(CompanyFaq faq){
		return companyFaqDao.update(faq);
	}

}

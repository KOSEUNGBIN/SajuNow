package com.landvibe.core.promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PromotionBo {
	
	@Autowired
	private PromotionDao promotionDao;
	
	/**
	 * Promotion 생성  
	 * @param promotion
	 */
	public void create(Promotion promotion) {
		promotionDao.insert(promotion);
	}
	
	/**
	 * promotionNo 로 불러오기 
	 * @param promotionNo
	 * @return Promotion
	 */
	public Promotion getByNo(long promotionNo) {
		return promotionDao.selectByNo(promotionNo);
	}
	
	/**
	 * promotionNo 로 삭제 
	 * @param promotionNo
	 * @return 삭제된 개수 
	 */
	public int deleteByPomotionNo(long promotionNo){
		return promotionDao.deleteByPomotionNo(promotionNo);
	}
	
	/**
	 * CompanyNo 로 삭제 
	 * @param companyNo
	 * @return 삭제된 개수 
	 */
	public int deleteByCompanyNo(long companyNo){
		return promotionDao.deleteByCompanyNo(companyNo);
	}
	
	/**
	 * PromotionNo 변경 
	 * @param promtion
	 * @return update된 개수 
	 */
	public int update(Promotion promtion){
		return promotionDao.update(promtion);
	}

}

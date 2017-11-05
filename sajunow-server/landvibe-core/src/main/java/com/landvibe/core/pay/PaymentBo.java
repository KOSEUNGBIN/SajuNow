package com.landvibe.core.pay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentBo {
	@Autowired
	private PaymentDao paymentDao;

	public void create(Payment payment) {
		paymentDao.insert(payment);
	}	
	public void updateBase(Payment payment) {
		paymentDao.updateBase(payment);
	}
	public long selectSumByCompanyNo(long company_no){
		return paymentDao.selectSumByCompanyNo(company_no);
	}
	public List<Payment> selectCountAndSumByCompanyNoOfMonth(long company_no){
		return paymentDao.selectCountAndSumByCompanyNoOfMonth(company_no);
	}
}

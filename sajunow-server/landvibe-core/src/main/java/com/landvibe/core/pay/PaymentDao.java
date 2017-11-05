package com.landvibe.core.pay;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.landvibe.core.history.History;

@Repository
public interface PaymentDao {
	

	void insert(Payment payment);
	
	void updateBase(Payment payment);
	
	long selectSumByCompanyNo(@Param("company_no") long company_no);
	
	List<Payment> selectCountAndSumByCompanyNoOfMonth(@Param("company_no") long company_no);
}
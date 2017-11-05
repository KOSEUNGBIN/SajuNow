package com.landvibe.core.exchange;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.landvibe.core.company.Company;
import com.landvibe.core.history.History;

@Repository
public interface ExchangeDao {
	
		long insert(Exchange exchange);
	
		List<Exchange> selectByCompanyNo(@Param("company_no") long company_no);
		
		List<Exchange> selectAllWaiting();
		
		long selectDoneSumByCompanyNo(@Param("company_no") long company_no);
		
		long selectSumByCompanyNo(@Param("company_no") long company_no);
	
		List<Exchange> selectCountAndSumByCompanyNoOfMonth(@Param("company_no") long company_no);
	
		void update(@Param("exchange_no") long exchange_no);
	
		void delete(@Param("exchange_no") long exchange_no);
}
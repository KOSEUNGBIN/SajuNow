package com.landvibe.core.exchange;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeBo {
	@Autowired
	private ExchangeDao exchangeDao;

	public long create(Exchange exchange) {
		return exchangeDao.insert(exchange);
	}
	
	public List<Exchange> selectByCompanyNo(long company_no) {
		return exchangeDao.selectByCompanyNo(company_no);
	}
	
	public List<Exchange> selectAllWaiting(){
		return exchangeDao.selectAllWaiting();
	}
	
	public long selectSumByCompanyNo(long company_no){
		return exchangeDao.selectSumByCompanyNo(company_no);
	}
	
	public long selectDoneSumByCompanyNo(long company_no){
		return exchangeDao.selectDoneSumByCompanyNo(company_no);
	}
	
	
	public List<Exchange> selectCountAndSumByCompanyNoOfMonth(long company_no){
		return exchangeDao.selectCountAndSumByCompanyNoOfMonth(company_no);
	}
	public void update(long exchange_no) {
		exchangeDao.update(exchange_no);
	}
	
	public void delete(long exchange_no) {
		exchangeDao.delete(exchange_no);
	}
	
	
}
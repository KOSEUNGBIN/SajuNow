package com.landvibe.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.landvibe.api.response.ErrorResponse;
import com.landvibe.api.response.SuccessResponse;
import com.landvibe.core.company.CompanyBo;
import com.landvibe.core.exchange.Exchange;
import com.landvibe.core.exchange.ExchangeBo;
import com.landvibe.core.pay.PaymentBo;

/**
 * 친구 api
 * 
 * 사용 안할듯 논의 후 삭제 예정
 * 
 * @author 건희
 *
 */
@RestController
@RequestMapping("/profit")
public class ProfitController {
	
	@Autowired
	private ExchangeBo exchangeBo;
	
	@Autowired
	private PaymentBo paymentBo;
	
	@Autowired
	private CompanyBo companyBo;

	@RequestMapping("/exchange/company/{company_no:[0-9]+}")
	@ResponseBody
	public Object getByCompanyNo(@PathVariable long company_no) {

		List<Exchange> exchangeList = exchangeBo.selectByCompanyNo(company_no);
		if (exchangeList == null) {
			return new ErrorResponse("사용자의 출금 요청 정보가 없습니다.");
		} else {
			return new SuccessResponse(exchangeList);
		}

	}
	
	@RequestMapping("/month/company/{company_no:[0-9]+}")
	@ResponseBody
	public Object getByCompanyNoOfMonth(@PathVariable long company_no) {

		Map<String,Object> map =  new HashMap<String,Object>();
		map.put("exchange_all", exchangeBo.selectDoneSumByCompanyNo(company_no));
		map.put("waiting_amount", exchangeBo.selectSumByCompanyNo(company_no));
		map.put("payment_all", paymentBo.selectSumByCompanyNo(company_no));
		map.put("exchange_month_list", exchangeBo.selectCountAndSumByCompanyNoOfMonth(company_no));
		map.put("payment_month_list", paymentBo.selectCountAndSumByCompanyNoOfMonth(company_no));
		map.put("company", companyBo.getByNo(company_no));
		return new SuccessResponse(map);
		
	}
	
	@RequestMapping(value = "/exchange/insert", method = RequestMethod.POST)
	@ResponseBody
	public void insertExchange(@ModelAttribute Exchange exchange) {
		exchangeBo.create(exchange);
	}

//	@RequestMapping(value = "/exchange/update", method = RequestMethod.POST)
//	@ResponseBody
//	public void updateExchange(@ModelAttribute Exchange exchange) {
//
//		exchangeBo.update(exchange);
//
//	}

	@RequestMapping("/exchange/delete/{exchange_no:[0-9]+}")
	@ResponseBody
	public void deleteExchange(@PathVariable long exchange_no) {
		exchangeBo.delete(exchange_no);

	}

}

package com.landvibe.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.landvibe.core.exchange.Exchange;
import com.landvibe.core.exchange.ExchangeBo;
import com.landvibe.core.history.History;
import com.landvibe.core.history.HistoryBo;
import com.landvibe.core.user.User;
import com.landvibe.core.user.UserBo;

@Controller
@RequestMapping("pay")
public class PayController {

	@Autowired
	private ExchangeBo exchangeBo;
	
	//출금요청내역 ( exchange_no ,역술인 이름 , 출금 가능액 ,  출금 요청액 , 출금 요청)
	@RequestMapping("/exchange/list")
	public ModelAndView ExchangeWaitingList() {
		
		ModelAndView mav = new ModelAndView("pay/list");
		mav.addObject("exchangeList",exchangeBo.selectAllWaiting());
		return mav;
	}
	
	@RequestMapping("/exchange/status/update/done/{exchangeNo:[0-9]+}")
	public String ExchangeStatusUpdateToDone(@PathVariable long exchangeNo) {
		exchangeBo.update(exchangeNo);
		return "redirect:/pay/exchange/list";
	}
	
}

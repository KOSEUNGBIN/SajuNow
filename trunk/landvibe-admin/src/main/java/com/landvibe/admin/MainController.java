package com.landvibe.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.landvibe.core.company.CompanyBo;
import com.landvibe.core.companytoreport.CompanyToReportBo;
import com.landvibe.core.history.HistoryBo;
import com.landvibe.core.user.UserBo;



@Controller
@RequestMapping("main")
public class MainController {
	
	@Autowired 
	private UserBo userBo;
	
	@Autowired 
	private CompanyBo companyBo;
	
	@Autowired 
	private HistoryBo historyBo;
	
	@Autowired 
	private CompanyToReportBo companytoreportBo;;
	

	@RequestMapping("")
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView("main");
		mav.addObject("user_count",userBo.getCount());
		mav.addObject("company_count",companyBo.getCount());
		mav.addObject("live_history_count",historyBo.getLiveCount());
		mav.addObject("history_count",historyBo.getCount());
		mav.addObject("company_history_rank",historyBo.getCompanyRank());
		mav.addObject("company_report_rank",companytoreportBo.getCompanyReportRank());
		
		
		return mav;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout() {
		ModelAndView mav = new ModelAndView("logout");
		SecurityContextHolder.clearContext();
		return mav;
	}
}
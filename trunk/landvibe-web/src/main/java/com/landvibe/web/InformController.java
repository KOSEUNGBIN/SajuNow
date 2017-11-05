package com.landvibe.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.landvibe.core.companyfaq.CompanyFaq;
import com.landvibe.core.companyfaq.CompanyFaqBo;
import com.landvibe.core.companyquestion.CompanyQuestion;
import com.landvibe.core.companyquestion.CompanyQuestionBo;
import com.landvibe.core.userfaq.UserFaq;
import com.landvibe.core.userfaq.UserFaqBo;
import com.landvibe.core.userquestion.UserQuestion;
import com.landvibe.core.userquestion.UserQuestionBo;

@Controller
@RequestMapping("/webview")
@SessionAttributes("user")
public class InformController {
	@Autowired
	private CompanyQuestionBo companyQuestionBo;
	
	@Autowired
	private UserQuestionBo userQuestionBo;
	
	@Autowired
	private CompanyFaqBo companyFaqBo;
	
	@Autowired
	private UserFaqBo userFaqBo;

	@RequestMapping("/faq/company")
	@ResponseBody
	public Object informCompany() {
		ModelAndView mav = new ModelAndView("faq/company");
		List<CompanyFaq> faqList = companyFaqBo.getList();
		mav.addObject("faqList", faqList);
		return mav;
	}
	
	@RequestMapping("/faq/user")
	@ResponseBody
	public Object faqUser() {
		ModelAndView mav = new ModelAndView("faq/user");
		List<UserFaq> faqList = userFaqBo.getList();
		mav.addObject("faqList", faqList);
		return mav;
	}
	
	@RequestMapping("/inform/company")
	@ResponseBody
	public Object faqCompany() {
		ModelAndView mav = new ModelAndView("inform/company");
		List<CompanyQuestion> questionList = companyQuestionBo.getList();
		mav.addObject("questionList", questionList);
		return mav;
	}
	
	@RequestMapping("/inform/user")
	@ResponseBody
	public Object informUser() {
		ModelAndView mav = new ModelAndView("inform/user");
		List<UserQuestion> questionList = userQuestionBo.getList();
		mav.addObject("questionList", questionList);
		return mav;
	}
	
	
	@RequestMapping("/agreement")
	@ResponseBody
	public Object agreement() {
		ModelAndView mav = new ModelAndView("sign/agreementOfApp");
		return mav;
	}
	
	@RequestMapping("/agreement/private")
	@ResponseBody
	public Object agreement1() {
		ModelAndView mav = new ModelAndView("sign/agreementOfApp1");
		return mav;
	}
	
}
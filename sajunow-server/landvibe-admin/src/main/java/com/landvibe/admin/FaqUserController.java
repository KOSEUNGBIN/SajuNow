package com.landvibe.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.landvibe.core.userfaq.UserFaq;
import com.landvibe.core.userfaq.UserFaqBo;

@Controller
@RequestMapping("/faq/user")
public class FaqUserController {

	@Autowired
	UserFaqBo userFaqBo;

	@RequestMapping("")
	public ModelAndView faq() {

		ModelAndView mav = new ModelAndView("faq/user/information");

		List<UserFaq> faqList = userFaqBo.getList();
		mav.addObject("faqList", faqList);

		return mav;
	}

	@RequestMapping("/add")
	public ModelAndView faqAddtion() {
		ModelAndView mav = new ModelAndView("faq/user/add");
		return mav;
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String faqSubmit(UserFaq faq) {

		userFaqBo.create(faq);
		return "redirect:/faq/user";
	}

	@RequestMapping(value = "/modify/{faq_no:[0-9]+}")
	public ModelAndView faqModify(@PathVariable long faq_no) {

		ModelAndView mav = new ModelAndView("faq/user/modify");

		mav.addObject("faq", userFaqBo.getByNo(faq_no));
		return mav;
	}

	@RequestMapping(value = "/modify/submit/{faq_no:[0-9]+}")
	public String faqModifySubmit(UserFaq faq, @PathVariable long faq_no) {

		userFaqBo.update(faq);

		return "redirect:/faq/user";
	}
	
	@RequestMapping(value = "/delete/{faq_no:[0-9]+}" )
	public String faqDelete(@PathVariable long faq_no) {

		userFaqBo.deleteByNo(faq_no);
		
		return "redirect:/faq/user";
	}

}

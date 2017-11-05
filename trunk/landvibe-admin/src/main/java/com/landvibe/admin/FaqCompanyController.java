package com.landvibe.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.landvibe.core.companyfaq.CompanyFaq;
import com.landvibe.core.companyfaq.CompanyFaqBo;

@Controller
@RequestMapping("/faq/company")
public class FaqCompanyController {

	@Autowired
	CompanyFaqBo companyFaqBo;

	@RequestMapping("")
	public ModelAndView faq() {

		ModelAndView mav = new ModelAndView("faq/company/information");

		List<CompanyFaq> faqList = companyFaqBo.getList();
		mav.addObject("faqList", faqList);

		return mav;
	}

	@RequestMapping("/add")
	public ModelAndView faqAddtion() {
		ModelAndView mav = new ModelAndView("faq/company/add");
		return mav;
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String faqSubmit(CompanyFaq faq) {

		companyFaqBo.create(faq);

		return "redirect:/faq/company";
	}

	@RequestMapping(value = "/modify/{faq_no:[0-9]+}")
	public ModelAndView faqModify(@PathVariable long faq_no) {

		ModelAndView mav = new ModelAndView("faq/company/modify");

		mav.addObject("faq", companyFaqBo.getByNo(faq_no));
		return mav;
	}

	@RequestMapping(value = "/modify/submit/{faq_no:[0-9]+}")
	public String faqModifySubmit(CompanyFaq faq, @PathVariable long faq_no) {

		companyFaqBo.update(faq);

		return "redirect:/faq/company";
	}

	@RequestMapping(value = "/delete/{faq_no:[0-9]+}")
	public String faqDelete(@PathVariable long faq_no) {

		companyFaqBo.deleteByNo(faq_no);

		return "redirect:/faq/company";
	}

}

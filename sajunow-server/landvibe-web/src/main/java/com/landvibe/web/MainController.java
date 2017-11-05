package com.landvibe.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.landvibe.core.company.Company;
import com.landvibe.core.company.CompanyBo;

@Controller
@RequestMapping("")
@SessionAttributes("user")
public class MainController {
	@Autowired
	private CompanyBo companyBo;

	
	@RequestMapping("")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView("redirect:/main/all/0");
		return mav;
	}

	@RequestMapping("/main/all/{start:[0-9]+}")
	public ModelAndView CompanyJoinCategoryPaging(@PathVariable long start) {
		ModelAndView mav = new ModelAndView("main/list");
		List<Company> companyList = companyBo.getJoinCompany(start);
		mav.addObject("companys", companyList);
		return mav;
	}
	
	@RequestMapping("/main/{category_code:[0-9]+}/{start:[0-9]+}")
	@ResponseBody
	public Object CompanyJoinCategoryByCategoryPaging(@PathVariable long category_code, @PathVariable long start) {
		ModelAndView mav = new ModelAndView("main/list");
		List<Company> companyList = companyBo.getJoinCompanyByCategory(start, category_code);
		mav.addObject("companys", companyList);
		
		return mav;
	}
	
	@RequestMapping("/main/about")
	@ResponseBody
	public Object About() {
		ModelAndView mav = new ModelAndView("main/about");
		return mav;
	}
	
	
}
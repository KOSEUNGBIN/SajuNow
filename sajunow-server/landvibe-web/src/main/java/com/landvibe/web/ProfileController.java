package com.landvibe.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.landvibe.core.company.Company;
import com.landvibe.core.company.CompanyBo;
import com.landvibe.core.user.User;

@Controller
@RequestMapping("/profile")
@SessionAttributes("user")
public class ProfileController {
	@Autowired
	private CompanyBo companyBo;

	@RequestMapping("/{companyNo}")
	public ModelAndView detail(@PathVariable long companyNo, HttpSession session) {
		ModelAndView mav = new ModelAndView("profile/detail");
		
		
		User user = (User) session.getAttribute("user");
		
		Company company = companyBo.getJoinCompanyToReport(companyNo);
		company.setReport_count(companyBo.getCompanyReportCount(companyNo));
		
		double score_average = company.getScore_average();
		double score_average_temp = Math.floor(score_average);
		
		if(score_average_temp <= score_average && score_average < score_average_temp+0.5 )
			company.setScore_average(score_average_temp);
		else if(score_average_temp+0.5 <= score_average && score_average < score_average_temp+1)
			company.setScore_average(score_average_temp+0.5);
		else
			;
		mav.addObject("company",company);
		if(user != null)
		{
			System.out.println("user not null");
			mav.addObject("user",user);
		}
		return mav;
	}
}
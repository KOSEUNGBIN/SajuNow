package com.landvibe.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.landvibe.core.companyquestion.CompanyQuestion;
import com.landvibe.core.companyquestion.CompanyQuestionBo;

@Controller
@RequestMapping("/inform/company")
public class InformCompanyController {
	

	@Autowired
	CompanyQuestionBo companyQuestionBo;

	@RequestMapping("")
	public ModelAndView inform() {

		ModelAndView mav = new ModelAndView("inform/company/information");

		List<CompanyQuestion> questionList = companyQuestionBo.getList();
		mav.addObject("questionList", questionList);

		return mav;
	}

	@RequestMapping("/add")
	public ModelAndView informAddtion() {
		ModelAndView mav = new ModelAndView("inform/company/add");
		return mav;
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String informSubmit(CompanyQuestion question) {

		companyQuestionBo.create(question);

		return "redirect:/inform/company";
	}

	@RequestMapping(value = "/modify/{question_no:[0-9]+}")
	public ModelAndView informModify(@PathVariable long question_no) {

		ModelAndView mav = new ModelAndView("inform/company/modify");

		mav.addObject("question", companyQuestionBo.getByNo(question_no));
		return mav;
	}

	@RequestMapping(value = "/modify/submit/{question_no:[0-9]+}")
	public String informModifySubmit(CompanyQuestion question, @PathVariable long question_no) {

		companyQuestionBo.update(question);

		return "redirect:/inform/company";
	}

	@RequestMapping(value = "/delete/{question_no:[0-9]+}")
	public String informDelete(@PathVariable long question_no) {

		companyQuestionBo.deleteByNo(question_no);

		return "redirect:/inform/company";
	}

}

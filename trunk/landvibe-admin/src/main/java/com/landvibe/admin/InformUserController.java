package com.landvibe.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.landvibe.core.userquestion.UserQuestion;
import com.landvibe.core.userquestion.UserQuestionBo;

@Controller
@RequestMapping("/inform/user")
public class InformUserController {

	@Autowired
	UserQuestionBo userQuestionBo;

	@RequestMapping("")
	public ModelAndView inform() {

		ModelAndView mav = new ModelAndView("inform/user/information");

		List<UserQuestion> questionList = userQuestionBo.getList();
		mav.addObject("questionList", questionList);

		return mav;
	}

	@RequestMapping("/add")
	public ModelAndView informAddtion() {
		ModelAndView mav = new ModelAndView("inform/user/add");
		return mav;
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String informSubmit(UserQuestion question) {

		userQuestionBo.create(question);
		return "redirect:/inform/user";
	}

	@RequestMapping(value = "/modify/{question_no:[0-9]+}")
	public ModelAndView informModify(@PathVariable long question_no) {

		ModelAndView mav = new ModelAndView("inform/user/modify");

		mav.addObject("question", userQuestionBo.getByNo(question_no));
		return mav;
	}

	@RequestMapping(value = "/modify/submit/{question_no:[0-9]+}")
	public String informModifySubmit(UserQuestion question, @PathVariable long question_no) {

		userQuestionBo.update(question);

		return "redirect:/inform/user";
	}
	
	@RequestMapping(value = "/delete/{question_no:[0-9]+}" )
	public String informDelete(@PathVariable long question_no) {

		userQuestionBo.deleteByNo(question_no);
		
		return "redirect:/inform/user";
	}

}

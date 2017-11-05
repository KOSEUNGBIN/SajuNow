package com.landvibe.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.landvibe.common.utils.AuthUtils;
import com.landvibe.core.company.CompanyBo;
import com.landvibe.core.user.User;
import com.landvibe.core.user.UserBo;

@Controller
@RequestMapping("/sign")
@SessionAttributes("user")
public class SignController {
	@Autowired
	private CompanyBo companyBo;

	@Autowired
	private UserBo userBo;

	@Autowired
	private AuthUtils authUtils;

	@RequestMapping(value = "/in/submit", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView Login(@RequestParam String email, @RequestParam String password, HttpSession session) {

		ModelAndView mv = null;

		User user = userBo.selectForLogin(email, password);

		if (user != null) {
			mv = new ModelAndView();
			mv.addObject("user", user);
			mv.setViewName("redirect:/main/all/0");
		} else {
			mv = new ModelAndView("/sign/in", "error_message", "이메일 혹은 비밀번호가 올바르지 않습니다.");
		}
		return mv;

	}

	@RequestMapping(value = "/in")
	public ModelAndView signIn() {
		ModelAndView mv = new ModelAndView("sign/in");
		return mv;
	}

	@RequestMapping(value = "/up/submit", method = RequestMethod.POST)
	public ModelAndView signUpsubmit(User users , HttpSession session) {

		ModelAndView mv;
		if (userBo.selectByEmail(users.getEmail()) == null) {

			users.setBirthday_detail(users.getBirthday_detail().replaceAll("\\s", ""));
			userBo.create(users);
			
			mv = new ModelAndView("sign/in");
			return mv;
		} else {
			mv = new ModelAndView("sign/up");
			return mv;
		}

	}

	@RequestMapping(value = "/up")
	public ModelAndView signUp() {
		ModelAndView mv = new ModelAndView("sign/up");
		return mv;
	}

	@RequestMapping(value = "/forget")
	public ModelAndView signForget() {
		ModelAndView mv = new ModelAndView("sign/forget");
		return mv;
	}

	@RequestMapping(value = "/out")
	public ModelAndView signOut(SessionStatus session) {
		session.setComplete();
		ModelAndView mv = new ModelAndView("redirect:/main/all/0");
		return mv;
	}
	
	@RequestMapping(value = "/agreement")
	public ModelAndView signAgreement() {
		ModelAndView mv = new ModelAndView("sign/agreement");
		return mv;
	}
	
	@RequestMapping(value = "/agreement1")
	public ModelAndView signAgreement1() {
		ModelAndView mv = new ModelAndView("sign/agreement1");
		return mv;
	}

}
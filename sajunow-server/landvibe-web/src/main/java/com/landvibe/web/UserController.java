package com.landvibe.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.landvibe.core.company.Company;
import com.landvibe.core.company.CompanyBo;
import com.landvibe.core.user.User;
import com.landvibe.core.user.UserBo;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {
	@Autowired
	private CompanyBo companyBo;

	@Autowired
	private UserBo userBo;

	@RequestMapping(value = "/profile")
	public ModelAndView userShowDetail(HttpSession session) {

		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView("user/profile");
		mv.addObject("user", userBo.getByNo(user.getUser_no()));
		return mv;
	}
	
	@RequestMapping(value = "/modify")
	public ModelAndView userModify(HttpSession session) {

		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView("user/modify");
		mv.addObject("user", userBo.getByNo(user.getUser_no()));
		return mv;
	}
	
	@RequestMapping(value = "/update" , method=RequestMethod.POST)
	public ModelAndView userUpdate(User user) {

		user.setBirthday_detail(user.getBirthday_detail().replaceAll("\\s", ""));
		userBo.update(user);
		
		ModelAndView mv = new ModelAndView("main/list");
		List<Company> companyList = companyBo.getJoinCompany(1);
		mv.addObject("companys", companyList);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/check/psw" , method=RequestMethod.POST)
	public boolean userCheckedPwd(@RequestParam("email") String email,@RequestParam("password") String password) {
		
		User user = userBo.selectByEmail(email);

		return password.equals(user.getPassword());
	}
		
	
	
}
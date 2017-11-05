package com.landvibe.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.landvibe.core.history.History;
import com.landvibe.core.history.HistoryBo;
import com.landvibe.core.user.User;
import com.landvibe.core.user.UserBo;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserBo userBo;
	
	@Autowired
	private HistoryBo historyBo;

	@RequestMapping("/{page:[0-9]+}")
	public ModelAndView userMain(@PathVariable long page) {

		ModelAndView mav = new ModelAndView("user/list");

		long selectNo = (page - 1) * 15;

		long userListLength = userBo.getCount();
		long userTotalPage = (userListLength - 1) / 15 + 1;

		List<Long> pagegroup_no = new ArrayList<Long>();

		pagegroup_no.add((page - 1) - (page - 1) % 5 + 1);

		if ((page - 1) / 5 != (userTotalPage - 1) / 5) {
			pagegroup_no.add((page - 1) - (page - 1) % 5 + 5);
		} else {
			pagegroup_no.add(userTotalPage);
		}

		if (page > 5) {
			pagegroup_no.add((page - 1) - (page - 1) % 5 - 4);
		} else {
			pagegroup_no.add(1L);
		}

		if (page < (userTotalPage - 1) - (userTotalPage - 1) % 5 + 1) {
			pagegroup_no.add((page - 1) - (page - 1) % 5 + 6);
		} else {
			pagegroup_no.add(userTotalPage);
		}

		System.out.println(selectNo);

		mav.addObject("pagegroup_no", pagegroup_no);
		mav.addObject("userList", userBo.getOfSection(selectNo));

		return mav;
	}
	
	@RequestMapping("/detail/{userNo:[0-9]+}")
	public ModelAndView userDetail(@PathVariable long userNo) {
		
		ModelAndView mav = new ModelAndView("user/detail");
		User user = userBo.getByNo(userNo);
		mav.addObject("user",user);
		
		List<History> historyAllCompanyList = historyBo. getByAllCompanyHistory(userNo);
		mav.addObject("CompanyList",historyAllCompanyList);
		return mav;
	}
	
	@RequestMapping(value = "/pause" , method = RequestMethod.POST)
	@ResponseBody
	public boolean userPause(@RequestParam("pause") boolean pause , @RequestParam("userNo") long userNo ) {
		
		User user = userBo.getByNo(userNo);
		user.setUser_no(userNo);
		if(pause)
			user.setUser_reg_id("BLOCK");
		else
			user.setUser_reg_id("LOGOUT");
		userBo.updateRegId(user);
		
		List<History> companyHistoryList = historyBo.getByAllCompanyHistory(userNo);

		for (int i = 0; i < companyHistoryList.size(); i++) {
			companyHistoryList.get(i).setUser_reg_id(user.getUser_reg_id());
			historyBo.updateUserReg(companyHistoryList.get(i));
		}
		return true;
	}
}

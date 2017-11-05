package com.landvibe.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.landvibe.core.company.Company;
import com.landvibe.core.company.CompanyBo;
import com.landvibe.core.user.User;
import com.landvibe.core.user.UserBo;

public class UserAuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserBo userBo;
	
	@Autowired
	private CompanyBo companyBo;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		boolean isAuthed = false;
		//System.out.println("request.getHeader(Cookie) : " + request.getHeader("Cookie"));
		//System.out.println("request.getParameter : " + request.getAttribute("Cookie"));

		// 로그인 사용자 정보를 쿠키에서 가져옴
		
			String Userkey = request.getHeader("Cookie");
			System.out.println("Cookie Value : " + Userkey);

			int user;
			int company;
			

			user = userBo.selectByToken(Userkey);
			company = companyBo.selectByToken(Userkey);
			if (user > 0 || company > 0 ) {
				isAuthed = true;
				//System.out.println(userBo.selectByToken(Userkey).toString() + "is Authed..");
			} else {
				//System.out.println("is Not Authed..");
			}

		//	System.out.println("Request Attribute : " + request.getCookies().toString());


		//System.out.println("isAuthed : " + isAuthed);
		return isAuthed;

	}

}
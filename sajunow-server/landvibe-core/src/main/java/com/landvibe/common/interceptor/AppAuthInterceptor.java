


package com.landvibe.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.landvibe.common.utils.DeEncrypter;
import com.landvibe.core.company.Company;
import com.landvibe.core.company.CompanyBo;
import com.landvibe.core.user.User;
import com.landvibe.core.user.UserBo;

public class AppAuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserBo userBo;
	
	@Autowired
	private CompanyBo companyBo;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		boolean isAuthed = false;

			String Userkey = request.getHeader("Cookie");
			System.out.println("Cookie Value : " + Userkey);
			/*DeEncrypter deEncrypter = DeEncrypter.getInstance();
			Userkey = deEncrypter.decrypt(Userkey);*/
			if (Userkey.equals("PASSWORD"))
				isAuthed = true;
			
			return isAuthed;

	}

}


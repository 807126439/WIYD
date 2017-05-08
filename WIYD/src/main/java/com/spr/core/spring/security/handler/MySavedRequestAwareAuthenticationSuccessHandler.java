package com.spr.core.spring.security.handler;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.spr.core.utils.IpUtils;
import com.spr.web.system.dao.ILoginLogDao;
import com.spr.web.system.entity.LoginLog;

public class MySavedRequestAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	@Resource
	private ILoginLogDao loginLogDao;
	private static final String LogDetails = "用户成功登录系统";
	
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		
		super.onAuthenticationSuccess(request, response, authentication);
		


		LoginLog log = new LoginLog(authentication.getName(),
				       LogDetails,IpUtils.ipToLong(request.getRemoteAddr()));
		this.loginLogDao.insert(log);
	    
	}

	
	
	
	
	
}

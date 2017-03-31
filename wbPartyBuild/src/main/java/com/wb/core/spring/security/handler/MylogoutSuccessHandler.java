package com.wb.core.spring.security.handler;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;





public class MylogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {


	
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
		    throws IOException, ServletException {
					
		    super.handle(request, response, authentication);
		    
		  }


	
	
	
}
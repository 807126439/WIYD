package com.spr.core.spring.security.filter;





import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spr.core.spring.security.status.ErrorCode;
import com.spr.core.spring.security.user.extend.MyUser;
import com.spr.web.system.service.IAuthorityService;






/**
 * 登录验证过滤器
 * @author wb_java_zjr
 *
 */
public class CaptchaAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	@Resource
	private IAuthorityService authorityService;
	private final String j_captcha = "j_captcha";
	

	
	
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) {
		
	
			//if(checkCaptcha(request)){
				
			    Authentication authentication = super.attemptAuthentication(request, response);
				
				MyUser user = (MyUser) authentication.getPrincipal();
								
				
				if(user.getRoleIds() == null || user.getRoleIds().isEmpty()){
					return authentication;
				}
				
				String menu = this.authorityService.getAuthMenu(user.getRoleIds(),request.getContextPath());
				request.getSession().setAttribute("menu", menu);
			
				List<String> authCodes = this.authorityService.getAuthControlItem(user.getRoleIds());
				user.setAuthCodes(authCodes);
					
				
				return authentication;
					
			/*}else{
				 
				 throw new AuthenticationServiceException(ErrorCode.CAPTCHA_ERROR+",验证码错误！");
			 }*/
		


	}


	
	
	
	
	
	 

	 /**
	  * 校验验证码
	  * @param request
	  * @return
	  */
	 public boolean checkCaptcha(HttpServletRequest request){
		 String reqCaptchaVal = request.getParameter(j_captcha);//用户输入的验证码
		 String curCaptchaVal = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		 if(reqCaptchaVal == null || curCaptchaVal == null){			 
			 return false;
			 
		 }else if(reqCaptchaVal.equals(curCaptchaVal)){
			 
			 return true;
		 }else{
			 
			 return false; 
		 }
		 
		 
	 }
    
	







	
	
	
}


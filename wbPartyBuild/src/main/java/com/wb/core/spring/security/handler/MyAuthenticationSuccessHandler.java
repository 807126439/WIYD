package com.wb.core.spring.security.handler;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.wb.core.utils.IpUtils;
import com.wb.web.portals.service.IAccessRecordService;
import com.wb.web.system.dto.log.LoginLogDTO;
import com.wb.web.system.service.ILoginLogService;



public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    @Resource
	private ILoginLogService loginLogService;
    @Resource
    private IAccessRecordService accessRecordService;
	private static final String LogDetails = "用户成功登录系统";
	
	
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
	
		super.onAuthenticationSuccess(request, response, authentication);
		
		LoginLogDTO dto = new LoginLogDTO(authentication.getName(),
				       IpUtils.ipToLong(request.getRemoteAddr()),new Date(),LogDetails);
		this.loginLogService.saveLog(dto);
		
		this.accessRecordService.accessIncreas();
	
		
	}


	
	

	
	
	
	
	
	
	
	
}

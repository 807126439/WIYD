package com.wb.core.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.wb.core.spring.security.utils.SecurityPrincipalUtil;

/**
 * 权限判断标签
 * @author wb_java_zjr
 *
 */
public class IfOwnAuthTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4322653573038903247L;
	private String authCode;
	
	

	
	public int doStartTag() throws JspException {
				
		
		return EVAL_PAGE;
	}
	

	public int doEndTag() throws JspException {
		
		if(SecurityPrincipalUtil.checkIsHasAuth(authCode)){
			
			return EVAL_BODY_INCLUDE;//执行标签体
		}
		
		
		return SKIP_BODY;//跳过标签体
	}

	
	
	public String getAuthCode() {
		return authCode;
	}


	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	
	
	
	
	
	
	

}

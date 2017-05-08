package com.spr.core.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.spr.core.spring.security.utils.SecurityPrincipalUtil;

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
		
		if(SecurityPrincipalUtil.checkIsHasAuth(authCode)){
			return EVAL_BODY_INCLUDE;//执行标签体
		}
		
		return SKIP_BODY;//跳过标签体
	}
	

	public int doEndTag() throws JspException {
		
		
		
		authCode = null;
		return EVAL_PAGE;
		
	}

	
	
	public String getAuthCode() {
		return authCode;
	}


	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	
	
	
	
	
	
	

}

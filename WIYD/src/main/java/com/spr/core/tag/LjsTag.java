package com.spr.core.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import com.spr.core.spring.security.utils.SecurityPrincipalUtil;


/**
 * 超链接按钮标签，生成js代码（表格内的操作按钮）
 * @author Administrator
 *
 */
public class LjsTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5256292152140255160L;
	private final String CLASS_DEF = "ui-btn ui-btn-primary size-s";
	private String title;		//标题
	private String context;		//文本
	private String funMethod; 	//点击方法名
	private String className;	//class名
	private String operCode;	//操作权限码
	private String varName;     //js定义的变量名
	
	
	
	

	public int doStartTag() throws JspException {
		
		className = className == null ? CLASS_DEF:className;
		title = title == null ? context:title;
		
		return EVAL_PAGE;
	}
	
	
	

	public int doEndTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		
		try {
			out.print(end());
			out.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}

		className = null;
		title = null;
		return EVAL_PAGE;
	}

	public String end(){
				
		if(!SecurityPrincipalUtil.checkIsHasAuth(operCode)){
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">");
		sb.append("var "+varName+"=");
		sb.append("\"<a title=\\\""+title+"\\\" href=\\\"javascript:;\\\" ");
		sb.append("onclick=\\\""+funMethod+"\\\" ");
		if(!StringUtils.isBlank(className)){
			sb.append("class=\\\""+className+"\\\" ");
		}	
		sb.append(">"+context+"");
		sb.append("</a>\";");
		sb.append("</script>");
		

		return sb.toString();
		
	}
	
	

	
	
	
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFunMethod() {
		return funMethod;
	}
	public void setFunMethod(String funMethod) {
		this.funMethod = funMethod;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getOperCode() {
		return operCode;
	}
	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}
	
	public String getVarName() {
		return varName;
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}
	



	
	
	
	
	
	
	
}

package com.wb.core.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.wb.core.spring.security.utils.SecurityPrincipalUtil;

public class HyperlinkTopIconTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4114473499283188295L;
	private final String default_class = "btn btn-primary radius";
	private String title;		//标题
	private String funMethod; 	//点击方法名
	private String className;	//class名
	private String operCode;	//操作权限码
	private String style = "";  //样式
	private String icon;		//图标
	private String context;		//文本内容
	
	
	
   public int doStartTag() throws JspException {
	   className = className == null ? default_class:className;
	   title = title == null ? className:title;	
		
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
		sb.append("<a href=\"javascript:;\" onclick=\""+funMethod+"\" ");
		sb.append("class=\""+className+"\" > ");
		sb.append("<i class=\"Hui-iconfont\">"+icon+"</i>");
		sb.append(context+"</a>");
		
		return sb.toString();
		
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
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
	
	
	
	
	
	
	
	

}

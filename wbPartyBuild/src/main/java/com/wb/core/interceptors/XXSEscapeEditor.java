package com.wb.core.interceptors;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;


/**
 * 替换html特殊字符为转意思字符
 * @author wb_java_zjr
 *
 */
public class XXSEscapeEditor extends PropertyEditorSupport{


	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			//1.不会对中文进行编码  2.只对符合规范的html标签进行转义		
			text = HtmlUtils.htmlEscape(text);										
			setValue(text);
			
		} else {
			setValue(null);
		}
	}

	
	
	
}

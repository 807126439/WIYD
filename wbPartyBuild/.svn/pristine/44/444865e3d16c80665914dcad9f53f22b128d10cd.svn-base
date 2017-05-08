package com.wb.web.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wb.core.common.controller.BaseController;

/**
 * 2017.02.10
 * 
 * @author Administrator
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/countController")
public class CountController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5382627132466849504L;

	@RequestMapping(value = "/loginFrequency", method = { RequestMethod.GET })
	public String loginFrequency(HttpServletRequest request) {
		this.wrapMenuTitle(request);
		return "system/count/loginFrequency-list.jsp";
	}

	@RequestMapping(value = "/studyFrequency", method = { RequestMethod.GET })
	public String studyFrequency(HttpServletRequest request) {
		this.wrapMenuTitle(request);
		return "system/count/studyFrequency-list.jsp";
	}
}

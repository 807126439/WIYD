package com.spr.web.login;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spr.core.common.controller.BaseController;
import com.spr.core.utils.DateUtil;
import com.spr.web.file.service.IBaseFileService;
import com.spr.web.system.dto.user.WeiUserDTO;
import com.spr.web.system.service.IBaseDictService;
import com.spr.web.system.service.IUserInfoService;
import com.spr.web.system.service.IUserService;

@Controller
@Scope("prototype")
public class IndexController extends BaseController {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2874105942311327743L;

	@Resource
	private IBaseFileService baseFileService;
	@Resource
	private IBaseDictService baseDictService;
	@Resource
	private IUserService userService;
	@Resource
	private IUserInfoService userInfoService;

	@RequestMapping(value = "/index.do")
	public String index(HttpServletRequest request) {
		String today = DateUtil
				.DateToStrByPattern(new Date(), "yyyy年MM月dd日  E");
		request.getSession().setAttribute("today", today);


		return "index/index.jsp";
	}

	@RequestMapping(value = "/homePage.do")
	public String homePage(HttpServletRequest request) {

		// queryMap存放条件,扩展使用.
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userId", this.getNowUser().getId());


		request.setAttribute("date", new Date());

		return "index/homePage.jsp";
	}

}

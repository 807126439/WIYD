package com.spr.web.Investment.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spr.core.common.bean.AjaxJson;
import com.spr.core.common.controller.BaseController;
import com.spr.core.gobal.GobalVal;
import com.spr.core.utils.http.HttpUtils;
import com.spr.web.Investment.dto.UrlDTO;

@Controller
@Scope("prototype")
@RequestMapping("/investmentController")
public class InvestmentController extends BaseController {

	private static final long serialVersionUID = 1L;

	
	// 跳转添加页面
	@RequestMapping(value = "/skipAddInvestment")
	public String skipAddDocumentFile(HttpServletRequest request) {


		return "Investment/Investment/addInvestment.jsp";
	}
	
	// 加载代码内容
	@RequestMapping(value = "/LoadInvestment")
	@ResponseBody
	public AjaxJson LoadInvestment(HttpServletRequest request,String fundId) {
		
		UrlDTO dto=new UrlDTO(fundId, 1, 100, null, null);
		String result="";
		try {
			byte[] messageByte=HttpUtils.sendGETRequest(dto.getUrl(), null, "utf-8");
			result=new String(messageByte,"utf-8");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(result);
		
		
		

		return new AjaxJson(this.ADD_SUCCESS_MESSAGE,AjaxJson.success);
	}
	

	
	
}

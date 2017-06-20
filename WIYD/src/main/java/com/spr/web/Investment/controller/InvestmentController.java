package com.spr.web.Investment.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spr.core.common.bean.AjaxJson;
import com.spr.core.common.controller.BaseController;
import com.spr.core.gobal.GobalVal;
import com.spr.core.utils.http.HttpUtils;
import com.spr.core.utils.json.JSONHelper;
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
	public AjaxJson LoadInvestment(HttpServletRequest request,String fundId) throws Exception {
		
		UrlDTO dto=new UrlDTO(fundId, 1, 100, null, null);
		
		byte[] messageByte=HttpUtils.sendGETRequest(dto.getUrl(), null, "utf-8");
		String result=new String(messageByte,"utf-8");
		//开始解析
		String jsonData=result.substring(result.indexOf("=")+1, result.length()-1);
		System.out.println(jsonData);
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		String content = jsonObject.getString("content");
		
		System.out.println(content);
		
		//使用dom4j解析数据
		Document document=DocumentHelper.parseText(content);
		Element root = document.getRootElement();
		
		System.out.println(root.getName());
		Element tbody = root.element("tbody");
		Iterator it = tbody.elementIterator();
		
		while(it.hasNext()){
			Element tr =(Element) it.next();
			Iterator it2 = tr.elementIterator();
			while(it2.hasNext()){
				Element td =(Element) it2.next();
				System.out.println(td.getText());
			}
		}
		
		

		return new AjaxJson(this.ADD_SUCCESS_MESSAGE,AjaxJson.success);
	}
}

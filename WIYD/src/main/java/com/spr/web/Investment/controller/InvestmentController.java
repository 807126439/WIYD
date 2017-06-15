package com.spr.web.Investment.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

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
	public AjaxJson LoadInvestment(HttpServletRequest request,String fundId) {
		
		UrlDTO dto=new UrlDTO(fundId, 1, 100, null, null);
		String result="";
		try {
			//载入文档
			File file = new File("C:\\Users\\Java2\\Desktop\\新建文件夹\\dadad.txt");
			
			FileInputStream fis=new FileInputStream(file);
			InputStreamReader isr=new InputStreamReader(fis, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String line=null;
			StringBuilder allText = new StringBuilder();
			while((line=br.readLine())!= null){
				allText.append(line);
			}
			System.out.println(allText.toString());
//			byte[] messageByte=HttpUtils.sendGETRequest(dto.getUrl(), null, "utf-8");
//			result=new String(messageByte,"utf-8");
			String jsonData=result.substring(result.indexOf("="), result.length());
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			String content = jsonObject.getString("content");
			
			//使用dom4j解析数据
			Document document=DocumentHelper.parseText(content);
			Element root = document.getRootElement();
			Element tbody = root.element("tbody");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(result);
		
		
		

		return new AjaxJson(this.ADD_SUCCESS_MESSAGE,AjaxJson.success);
	}
	

	
	
}

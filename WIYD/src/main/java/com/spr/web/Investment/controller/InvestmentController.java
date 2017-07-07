package com.spr.web.Investment.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.ShardedJedis;

import com.spr.core.common.bean.AjaxJson;
import com.spr.core.common.controller.BaseController;
import com.spr.core.redis.RedisDataSource;
import com.spr.core.utils.http.HttpUtils;
import com.spr.web.Investment.dto.UrlDTO;
import com.spr.web.Investment.service.IFundService;

@Controller
@Scope("prototype")
@RequestMapping("/investmentController")
public class InvestmentController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private IFundService fundService;
	@Resource
	private RedisDataSource redisDataSource;

	
	// 跳转添加页面
	@RequestMapping(value = "/skipAddInvestment")
	public String skipAddDocumentFile(HttpServletRequest request) {


		return "Investment/Investment/addInvestment.jsp";
	}
	
	// 加载代码内容
	@RequestMapping(value = "/LoadInvestment")
	@ResponseBody
	public AjaxJson LoadInvestment(HttpServletRequest request,String fundId) throws Exception {
		
		//Jedis实例
		ShardedJedis shardedJedis = redisDataSource.getRedisClient();
		
		//创建fund记录
		this.fundService.addFund(fundId);
		
		String result="";
		//载入文档
		File file = new File("C:\\Users\\Java2\\Desktop\\软件\\dadad.txt");
		
		FileInputStream fis=new FileInputStream(file);
		InputStreamReader isr=new InputStreamReader(fis, "gbk");
		BufferedReader br = new BufferedReader(isr);
		String line=null;
		StringBuilder allText = new StringBuilder();
		while((line=br.readLine())!= null){
			allText.append(line);
		}
		System.out.println(allText.toString());
		result=allText.toString();
		
		
//		UrlDTO dto=new UrlDTO(fundId, 1, 100, null, null);
//		
//		byte[] messageByte=HttpUtils.sendGETRequest(dto.getUrl(), null, "utf-8");
//		String result=new String(messageByte,"utf-8");
//		//开始解析
		String jsonData=result.substring(result.indexOf("=")+1, result.length()-1);
		jsonData=jsonData.substring(0,jsonData.lastIndexOf(";"));
		System.out.println("json数据:"+jsonData);
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		String content = jsonObject.getString("content");
		
		System.out.println("xml数据:"+content);
		
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
				
				//调用Jedis保存内容
				//shardedJedis.zadd("fund-"+fundId+"-value", score, member)
			}
		}
		
		

		return new AjaxJson(this.ADD_SUCCESS_MESSAGE,AjaxJson.success);
	}
}

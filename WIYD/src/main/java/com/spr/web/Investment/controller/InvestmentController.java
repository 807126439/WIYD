package com.spr.web.Investment.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.ShardedJedis;

import com.alibaba.dubbo.common.utils.StringUtils;
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
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		String content = jsonObject.getString("content");
		
		
		//使用dom4j解析数据
		Document document=DocumentHelper.parseText(content);
		Element root = document.getRootElement();
		
		System.out.println(root.getName());
		Element tbody = root.element("tbody");
		Iterator it = tbody.elementIterator();
		//记录增长率
		Map<BigDecimal,Integer> incrList = new HashMap<BigDecimal, Integer>();
		//增长率
		BigDecimal i = null;
		//总和
		BigDecimal sum = new BigDecimal(0);
		//样本数量
		int num =0;
		if(!StringUtils.isBlank(fundId)){
			while(it.hasNext()){
				num++;
				Element tr =(Element) it.next();
				Iterator it2 = tr.elementIterator();
				Node valueNode = tr.node(1);
				Node dateNode = tr.node(0);
				Node increNode = tr.node(3);
				//调用Jedis保存内容
				shardedJedis.hset("fund-"+fundId+"-value", dateNode.getText(), valueNode.getText());
				shardedJedis.hset("fund-"+fundId+"-incr", dateNode.getText(), increNode.getText().replace("%", ""));
				//incrList.add((new BigDecimal(increNode.getText().replace("%", ""))).setScale(2, BigDecimal.ROUND_HALF_UP));
				if(incrList.containsKey(i = new BigDecimal(increNode.getText().replace("%", "")).setScale(1, BigDecimal.ROUND_HALF_UP))){
					incrList.put(i, incrList.get(i)+1);
				}else{
					incrList.put(i, 1);
				}
				sum = sum.add(i);
			}
		}
		
		//计算正太分布常量
		//平均数
		BigDecimal average = sum.divide(new BigDecimal(num), 2, BigDecimal.ROUND_HALF_UP);
		//求方差(数学期望)
		BigDecimal varianceSum = new BigDecimal(0);
		Iterator inIter = incrList.entrySet().iterator();
		while (inIter.hasNext()) {
			Map.Entry entry = (Map.Entry) inIter.next();
			BigDecimal key = (BigDecimal)entry.getKey();
			Integer val = (Integer)entry.getValue();
			varianceSum = varianceSum.add(key.subtract(average).pow(2));
		}
		BigDecimal variance = varianceSum.divide(new BigDecimal(num), 2, BigDecimal.ROUND_HALF_UP);
		
		//正态分布对象
		//正态分布曲线数据
		Map<Double,Double> distributionline = new LinkedHashMap<Double, Double>();
		NormalDistribution normalDistributioin = new NormalDistribution(average.doubleValue(),variance.doubleValue());
		for(double aa = -5 ; aa < 5 ; aa+=0.5 ){
			distributionline.put(aa, normalDistributioin.density(aa));
		}
		
		shardedJedis.close();
		Map<String,Object> dataMap=new HashMap<String,Object>();
		dataMap.put("incrList", incrList);
		dataMap.put("distributionline", distributionline);
		
		return new AjaxJson(this.ADD_SUCCESS_MESSAGE,AjaxJson.success,dataMap);
	}
}

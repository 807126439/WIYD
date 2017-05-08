package com.spr.core.common.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.spr.core.common.dto.BaseDTO;
import com.spr.core.common.dto.SortDTO;
import com.spr.core.common.dto.UUIDDTO;
import com.spr.core.utils.ReflectUtil;


/**
 * 
 * @author wb_java_zjr
 *
 */
public  class DataQuery {

	private Integer startQuery = 0;	//查询起始位置
	private Integer pageSize = 10;
	private Integer currentPage = 1;	
	private String sidx = "id";  	 //排序字段
	private String sord = "desc";     //排序方式
	private String sEcho;
	private Map<String,Object> queryMap;

	
	public DataQuery() {
		this(new HashMap<String, Object>());
	}
	
	public DataQuery(Map<String,Object> queryMap) {		 
		 this.queryMap = queryMap;
	}
	

	public Integer getStartQuery() {
		return startQuery;
	}
	public void setStartQuery(Integer startQuery) {
		this.startQuery = startQuery;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(Integer currentPage) {
		//当前页转化为开始查询的位置
		 startQuery = (currentPage-1) * pageSize;
		 
		 this.currentPage = currentPage;
	}
	
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;		
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}	
		
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	

	public Map<String, Object> getQueryMap() {
		return queryMap;
	}
	public void setQueryMap(Map<String, Object> queryMap) {
		this.queryMap = queryMap;
	}
	
	public  DataQuery putToMap(String key,Object val){
		this.queryMap.put(key, val);
		
		return this;
	}
	
	public DataQuery setNotQueryPage(){
		this.startQuery = null;
		this.pageSize = null;
		
		return this;
	}
	
	
	/**
	 * 组装分页信息
	 * @return
	 */
	public DataQuery assemblePageOffset(){
				
		
		this.queryMap.put("startQuery", this.startQuery);				 //分页查询开始位置
		if(startQuery!=null){
			this.queryMap.put("endQuery", this.startQuery + this.pageSize);  //分页查询结束位置
		}
		this.queryMap.put("pageSize", this.pageSize);
		
		return this;
	}
	
	
	/**
	 * 组装排序信息
	 * @param clazz 
	 * @param alias 别名
	 */
	public DataQuery assembleOrderInfo(Class<?> clazz,String alias){
		String sidx_temp = this.sidx;
		String sord_temp = this.sord;
	
		if(StringUtils.isBlank(sidx_temp)){
			sidx_temp = "id";
		}
		if(StringUtils.isBlank(sord_temp)){
			sord_temp= "desc";
		}
		
		String temp = ReflectUtil.getFiledValueByAnnotation(clazz, sidx_temp);
		if(StringUtils.isBlank(temp)){
			temp = "id";
		}
		
		if(!StringUtils.isBlank(alias)){
			sidx_temp = alias+ "." +temp;
		}else{
			sidx_temp = temp;
		}
	  
		
		this.queryMap.put("sidx", sidx_temp);
		this.queryMap.put("sord", sord_temp);
		
		return this;
	}
	
	
	/**
	 * 清除查询条件
	 */
	public void clear(){
		this.getQueryMap().clear();
		
	}
	
	
	
	
	
	/**
	 * 组装排序信息（匹配多个表）
	 * @param oederArray
	 * @param defAlias
	 * @return
	 */
	public DataQuery assembleOrderInfo(OrderPair[] orderArray,String defAlias){
		
		String temp = null;
		if(orderArray!=null && orderArray.length>0){
			for (OrderPair op : orderArray) {
				 temp = ReflectUtil.getFiledValueByAnnotation(op.clazz, this.sidx);
				 				 
				 if(!StringUtils.isBlank(temp)){
					 
					 if(!StringUtils.isBlank(op.alias)){
						 this.sidx = op.alias+ "." +temp;
					}else{
						this.sidx = temp;
					}
				  
					
					this.queryMap.put("sidx", this.sidx);
					this.queryMap.put("sord", this.sord);
					
					return this;
				 }
			}
		}
		
		if(!StringUtils.isBlank(defAlias)){
			 this.sidx = defAlias+ ".id";
		}else{
			this.sidx = temp;
		}	
		this.queryMap.put("sidx",this.sidx);
		this.queryMap.put("sord", this.sord);
		
		return this;
	}
	
	
	/**
	 * 排序组件1封装类
	 * @author wb_java_zjr
	 *
	 */
	public static class OrderPair{
		public Class<?> clazz;
		public String alias;
		
		public OrderPair(Class<?> clazz, String alias) {
			super();
			this.clazz = clazz;
			this.alias = alias;
		}
		
		
	}
	
	
	//组装表格序号	
	public static <T extends BaseDTO> void wrapTableNo(List<T> list,int start){		
		
		for (BaseDTO dto : list) {
			dto.setSort((long) ++start);
		}
		
	}
	
	
	//组装表格序号	
	public static <T extends UUIDDTO> void wrapTableNo2(List<T> list,int start){
		
		for (UUIDDTO dto : list) {
			dto.setSort((long) ++start);
		}
		
	}


	//组装表格序号	
		public static <T extends SortDTO> void wrapTableNo3(List<T> list,int start){
			
			for (SortDTO dto : list) {
				dto.setSort((long) ++start);
			}
			
		}

	
}

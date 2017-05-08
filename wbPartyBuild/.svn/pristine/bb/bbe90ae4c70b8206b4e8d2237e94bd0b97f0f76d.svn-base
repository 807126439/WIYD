package com.wb.core.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.IBaseDao;
import com.wb.core.common.dao.utils.ConditionQuery;
import com.wb.core.common.dao.utils.OrderBy;
import com.wb.core.common.dto.BaseQueryDTO;






@SuppressWarnings({ "rawtypes", "unchecked" })
public  class BaseDaoImpl<PK extends Serializable,T> implements IBaseDao<PK, T> {
	
	
	private SessionFactory sessionFactory;
	private Class<T> inheritClass;
	public Logger logger = Logger.getLogger(getClass());
	
	
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		inheritClass = (Class) type.getActualTypeArguments()[1];
	}
	
	protected Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	


	public Class<T> getInheritClass() {
		return inheritClass;
	}

	public void flush(){
		 this.getSession().flush();
	}

 
   public void clear(){
		this.getSession().clear();	  
	}



	/**
	 * 根据传入的实体持久化对象
	 */
	public <T> Serializable save(T entity) {
		try {
			Serializable id = getSession().save(entity);
			getSession().flush();

			if (logger.isDebugEnabled()) {
				logger.debug("保存实体成功," + entity.getClass().getName());
			}
			return id;
		} catch (RuntimeException e) {
			logger.error("保存实体异常", e);
			throw e;
		}

	}
	
	/**
	 * 批量保存数据
	 * 
	 * @param <T>
	 * @param entitys
	 *            要持久化的临时实体对象集合
	 */
	public <T> void batchSave(List<T> entitys) {
		for (int i = 0; i < entitys.size(); i++) {
			getSession().save(entitys.get(i));
			if (i % 20 == 0) {
				// 20个对象后才清理缓存，写入数据库
				getSession().flush();
				getSession().clear();
			}
		}
		// 最后清理一下----防止大于20小于40的不保存
		getSession().flush();
		getSession().clear();
	}

	
	/**
	 * 根据传入的实体添加或更新对象
	 * 
	 * @param <T>
	 * 
	 * @param entity
	 */
	public <T> void saveOrUpdate(T entity) {
		try {
			getSession().saveOrUpdate(entity);
			getSession().flush();
			if (logger.isDebugEnabled()) {
				logger.debug("添加或更新成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("添加或更新异常", e);
			throw e;
		}
	}
	
	
	
	
	/**
	 * 根据传入的实体更新对象
	 * 
	 * @param <T>
	 * 
	 * @param entity
	 */	
	public <T> void update(T entity) {
		try {
			getSession().update(entity);
			getSession().flush();
			if (logger.isDebugEnabled()) {
				logger.debug("更新成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("更新异常", e);
			throw e;
		}

	}

	
	
	
	/**
	 * 根据传入的实体删除对象
	 */
	public <T> void delete(T entity) {
		try {
			getSession().delete(entity);
			getSession().flush();
			if (logger.isDebugEnabled()) {
				logger.debug("删除成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("删除异常", e);
			throw e;
		}
	}

	

	/**
	 * 删除全部的实体
	 * 
	 * @param <T>
	 * 
	 * @param entitys
	 */
	public void deleteAll(List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			this.getSession().delete(list.get(i));
			this.getSession().flush();
		}
		
	}

	/**
	 * 根据Id获取对象。
	 */
	public T getById(final Serializable id) {	
		return (T) this.getSession().get(this.inheritClass, id);
	}

	
	/**
	 * 查询所有集合
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> loadAll() {
		Criteria criteria = this.getSession().createCriteria(this.inheritClass);

		
		return criteria.list();
	}

	
	
   /**
    * HQL查询集合	
    * @param hql
    * @return
    */
   public List<T> findList(String hql) {
        Query query = getSession().createQuery(hql);
        return (List<T>) query.list();
    }

   
   /**
    * HQL按条件查询集合
    * @param hql
    * @param objects
    * @return
    */
   public List<T> findList(String hql, Object... objects) {
       Query query = getSession().createQuery(hql);
       setParameter(query, objects);
       return (List<T>) query.list();
   }
   
   /**
    * SQL查询集合	
    * @param hql
    * @return
    */
   public List<T> findListBySql(String sql) {
       Query query = getSession().createSQLQuery(sql);
       return (List<T>) query.list();
   }

   /**
    * SQL按条件查询集合
    * @param hql
    * @param objects
    * @return
    */
   public List<T> findListBySql(String sql, Object... objects) {
       Query query = getSession().createSQLQuery(sql).addEntity(getInheritClass());
       setParameter(query, objects);
       return (List<T>) query.list();
   }
   
   
   /**
    * SQL按条件查询对象
    * @param hql
    * @param objects
    * @return
    */
   public T findObjectBySql(String sql, Object... objects) {
       Query query = getSession().createSQLQuery(sql).addEntity(getInheritClass());
       setParameter(query, objects);
       return (T) query.uniqueResult();
   }
   
   

   /**
    * 执行SQL操作语句	
    * @param sql
    * @return
    */
   public int executeSql(String sql) {
       Query query = getSession().createSQLQuery(sql);
       return query.executeUpdate();
   }
   
   /**
    * 执行SQL操作语句	
    * @param sql
    * @return
    */
   public int executeSql(String sql, Object... objects) {
       Query query = getSession().createSQLQuery(sql);
       setParameter(query, objects);
       return query.executeUpdate();
   }
   
   

	public Criterion generateSearchCriterion(String searchField,
			String searchString, String searchOper) {

		//(7)如果searchField、searchString、searchOper均不为null，且searchString不为空字符串时，则创建Criterion
		if (searchField != null && searchString != null
				& searchString.length() > 0 && searchOper != null) {
			if ("eq".equals(searchOper)) {
		  
				return Restrictions.eq(searchField,
						checkSearchFieldType(searchField, searchString));
		    
			} else if ("ne".equals(searchOper)) {
				return Restrictions.ne(searchField,
						checkSearchFieldType(searchField, searchString));
			} else if ("lt".equals(searchOper)) {
				return Restrictions.lt(searchField,
						checkSearchFieldType(searchField, searchString));
			} else if ("le".equals(searchOper)) {
				return Restrictions.le(searchField,
						checkSearchFieldType(searchField, searchString));
			} else if ("gt".equals(searchOper)) {
				return Restrictions.gt(searchField,
						checkSearchFieldType(searchField, searchString));
			} else if ("ge".equals(searchOper)) {
				return Restrictions.ge(searchField,
						checkSearchFieldType(searchField, searchString));
			} else if ("bw".equals(searchOper)) {// 开始于
				return Restrictions.ilike(searchField, searchString,
						MatchMode.START);
			} else if ("bn".equals(searchOper)) {// 不开始于
				return Restrictions.not(Restrictions.ilike(searchField,
						searchString, MatchMode.START));
			} else if ("ew".equals(searchOper)) {// 结尾于
				return Restrictions.ilike(searchField, searchString,
						MatchMode.END);
			} else if ("en".equals(searchOper)) {// 不结尾于
				return Restrictions.not(Restrictions.ilike(searchField,
						searchString, MatchMode.END));
			} else if ("cn".equals(searchOper)) {
				return Restrictions.ilike(searchField, searchString,
						MatchMode.ANYWHERE);
			} else if ("nc".equals(searchOper)) {
				return Restrictions.not(Restrictions.ilike(searchField,
						searchString, MatchMode.ANYWHERE));
			} else if ("nn".equals(searchOper)) {
				return Restrictions.isNotNull(searchField);
			} else if ("nu".equals(searchOper)) {
				return Restrictions.isNull(searchField);
			} else {
				return Restrictions.isNull(searchField);
			}

		}
		return null;
	}
	
	
	// 判断数据类型，并转换
	protected Object checkSearchFieldType(String searchField,
			String searchString) {
		
		if(searchField.equals("lastOperatorTime")){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
			Date date;
			try {
				date = df.parse(searchString);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;	
			} 
		}
		String fieldType = "";
		try {
			
			if(searchField.equals("id")){
				fieldType = "java.lang.Long";
			}else{
				fieldType = inheritClass.getDeclaredField(searchField).toString();
			}
			
		} catch (SecurityException e) {		
			throw new RuntimeException("数据类型转换失败"); // 抛出运行时异常
		
		} catch (NoSuchFieldException e) {
			throw new RuntimeException("数据类型转换失败"); // 抛出运行时异常
		}
		
		if(fieldType.contains("java.lang.Integer")) {
			return Integer.parseInt(searchString);
			
		}else if(fieldType.contains("java.lang.Long")){
			return Long.parseLong(searchString);
			
		}else if (fieldType.contains("java.lang.String")) {
			return searchString;
			
		}else if (fieldType.contains("java.lang.Float")){
			return Float.parseFloat(searchString);
			
		}else if (fieldType.contains("java.lang.Boolean")){
			return Boolean.parseBoolean(searchString);
			
		}else if(fieldType.contains("java.lang.Double")){
			return Double.parseDouble(searchString);

		}else if (fieldType.contains("java.util.Date")){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
			Date date;
			try {
				date = df.parse(searchString);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
				return null;	
			} 	
		}else{
			return null;
		}
	}

	// 排序
	public Order generateOrderCriterion(String sord, String sidx) {
		Order order = null;
		if (sord != null && sidx != null && sord.length() > 0
				&& sidx.length() > 0) {
			if (sord.equals("desc"))
				return Order.desc(sidx);
			else
				return Order.asc(sidx);
		}
		return order;
	}
	
	
	// 排序
	public Order generateOrderCriterion(String sord, String sidx,Criteria cr) {
		
		Order order = null;
		if (sord != null && sidx != null && sord.length() > 0
				&& sidx.length() > 0) {
			if (sord.equals("desc"))
				return Order.desc(sidx);
			else
				return Order.asc(sidx);
		}
		return order;
	}
	
	
	
	/**
	 * 生成排序sql语句
	 * @param sord  排序方式
	 * @param sidx	排序字段
	 * @param prefix_sidx 字段前缀
	 * @return
	 */
	public String generateOrderSql(String sord, String sidx,String prefix_sidx){
		if (sord != null && sidx != null && sord.length() > 0
				&& sidx.length() > 0) {
			String prefix = "";
			if(!StringUtils.isBlank(prefix_sidx)){
				prefix = prefix_sidx+".";
			}
			if (sord.equals("desc"))
				return " ORDER BY "+prefix+sidx+" DESC ";
			else
				return " ORDER BY "+prefix+sidx+" ASC ";
		}else{
			return "";
		}
	}
	
	
	public Order setOrder(String type, String param) {
		if (type.equals("asc")) {
			
			return Order.asc(param);
		} else {
			return Order.desc(param);
		}
	}
	
	

	public String setOrder(String type) {
		if (type.equals("asc")) {
			return "ASC";

		} else {

			return "DESC";

		}
	}
	
	
	
	
	
	/**
	 * 根据Map<查询字段，查询值> 判断是否存在
	 * @param queryMap
	 * @return
	 */
	public boolean checkWheatherExistByCondition(Map<String, Object> queryMap) {
		Criteria cr = this.getSession().createCriteria(this.inheritClass);		
		Iterator<Entry<String, Object>> entryiterator = queryMap.entrySet().iterator(); 		
		while (entryiterator.hasNext()) {  
          Entry<String,Object> entry =  entryiterator.next();
          cr.add(Restrictions.eq(entry.getKey(),entry.getValue()));
    	}		
		
		Long count =(Long) cr.setProjection(Projections.rowCount()).uniqueResult();
		
		if(count<1){
			return false;
		}else{
			 return true;
		}
		
		
	}
	
	
	
	   /**
	    * 按条件查询并返回传入的字段	
	    * @param queryField	  查询字段
	    * @param queryVal	  查询值	
	    * @param returnFields 返回字段集合
	    * @return
	    */
	   public  Object[] findObjectByReturnFields(String queryField,Object queryVal,String... returnFields){
			Criteria cr = this.getSession().createCriteria(this.inheritClass	);	
			
			if(!StringUtils.isBlank(queryField) && queryVal!=null){  
			   cr.add(Restrictions.eq(queryField,queryVal));
			   
			   ProjectionList projectionList = Projections.projectionList();
			   for (int i = 0; i < returnFields.length; i++) {
				   projectionList.add(Projections.property(returnFields[i]));
			   }
			 
			   cr.setProjection(projectionList);
			  
			   return (Object[]) cr.uniqueResult();
			  
			}else{
				throw new NullPointerException("query params is null");
			}
			  
	   }
	   
	
	   public  Object findUniqueByReturnField(String queryField,Object queryVal,String returnField){
			Criteria cr = this.getSession().createCriteria(this.inheritClass	);	
			
			if(!StringUtils.isBlank(queryField) && queryVal!=null){  
			   cr.add(Restrictions.eq(queryField,queryVal));
			   
			  cr.setProjection(Projections.property(returnField));
			 		   		  
			   return (Object) cr.uniqueResult();
			  
			}else{
				throw new NullPointerException("query params is null");
			}
			  
	  }
 
  

   
  
	   public Page<T> searchEntityByPage(BaseQueryDTO queryDto) {
	        Criteria cr = this.getSession().createCriteria(this.inheritClass);
			
	    	if(queryDto.getQueryMap()!=null && !queryDto.getQueryMap().isEmpty()){
				Iterator<Entry<String, Object>>	iterator =  queryDto.getQueryMap().entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, Object> entry = iterator.next();
					cr.add(Restrictions.eq(entry.getKey(), entry.getValue()));
				}
			}
			
			OrderBy orderBy = new OrderBy();
			Order order = generateOrderCriterion(queryDto.getSord(),queryDto.getSidx());
			if (order != null) {
				orderBy.add(order);
				orderBy.build(cr);
			}
			
			Long rectoal = (Long) cr.setProjection(Projections.rowCount()).uniqueResult();
		    cr.setProjection(null);
		    cr.setResultTransformer(Criteria.ROOT_ENTITY);
		    	    
		    cr.setFirstResult(queryDto.getStartQuery());
			cr.setMaxResults(queryDto.getPageSize());
			
			List<T> list =cr.list();		
			
			return new Page<T>(queryDto.getCurrentPage(),queryDto.getPageSize(),list,rectoal);
		
		}
  
  
	   /**
	    * Jqgrid多条件查询
	    * @param queryDto
	    * @return
	    */
	   public Page<T> searchEntityWithConditionForJqgrid(BaseQueryDTO queryDTO) {
			ConditionQuery query = new ConditionQuery();
			Criteria  cr = this.getSession().createCriteria(this.inheritClass);	
			
			if(queryDTO.getQueryMap()!=null && !queryDTO.getQueryMap().isEmpty()){
				Iterator<Entry<String, Object>>	iterator =  queryDTO.getQueryMap().entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, Object> entry = iterator.next();
					cr.add(Restrictions.eq(entry.getKey(), entry.getValue()));
				}
			}
			
			
			for (int i = 0; i < queryDTO.getRules().size(); i++) {
				JSONObject rule = (JSONObject) queryDTO.getRules().get(i);			
				String searchField = rule.getString("field");
				String searchOper=rule.getString("op");
				String searchString=rule.getString("data");			
				
				Criterion criterion = null;													
				criterion = generateSearchCriterion(searchField, searchString, searchOper);					
				
				if (criterion != null) {
					query.add(criterion);
				}
		}		
		
		if (queryDTO.getGroupOp().equals("AND")) {
			query.build(cr);
		} else {
			query.buildOr(cr);
		}
		
		// 排序
		OrderBy orderBy = new OrderBy();
		Order order = generateOrderCriterion(queryDTO.getSord(), queryDTO.getSidx());
		if (order != null) {
			orderBy.add(order);
			orderBy.build(cr);
		}
			
		Long rectoal = (Long) cr.setProjection(Projections.rowCount()).uniqueResult();
		cr.setProjection(null);
		cr.setResultTransformer(Criteria.ROOT_ENTITY);
	    Integer firstResult = (queryDTO.getCurrentPage()-1)*queryDTO.getPageSize();		    
	    cr.setFirstResult(firstResult);
		cr.setMaxResults(queryDTO.getPageSize());
		List<T> resultList = cr.list();//返回结果集
	
		
		
		return new Page<T>(queryDTO.getCurrentPage(),queryDTO.getPageSize(),resultList,rectoal);
	}

	   
    /**
     * 查询唯一的实体
     * @param filedName 查询字段
     * @param value 	查询值	
     */
	public T getUniqueByProperty(String filedName,String value){
		if(filedName == null || value == null){
			throw new NullPointerException("params has null");
		}
		Criteria cr = this.getSession().createCriteria(this.inheritClass);
		cr.add(Restrictions.eq(filedName, value));
		return (T) cr.uniqueResult();
	}
	
	
	/**
	 * 根据条件获取主键
	 * @param filedName
	 * @param value
	 * @return
	 */
	public List<PK> getPrimaryKeyByCondition(String filedName,Object value){
		if(filedName == null || value == null){
			throw new NullPointerException("params has null");
		}
		Criteria cr = this.getSession().createCriteria(this.inheritClass);
		cr.add(Restrictions.eq(filedName, value));
		
		cr.setProjection(Projections.property("id"));
	
		return cr.list();
	}
		
		
		
		
	/**
	 * 按条件检查记录是否存在
	 * @param filedName
	 * @param value
	 * @param exceptId
	 * @return
	 */
	public boolean checkIsExistByProperty(String filedName,Object value,Object exceptId){
		if(filedName == null || value == null){
			throw new NullPointerException("params has null");
		}
		Criteria cr = this.getSession().createCriteria(this.inheritClass);
		cr.add(Restrictions.eq(filedName, value));
		
		if(exceptId!=null){
			cr.add(Restrictions.ne("id", exceptId));
		}
		
		cr.setProjection(Projections.rowCount());
		
		Long result = (Long) cr.uniqueResult();
		if(result>0){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * 分页查询填充查询语句
	 * @param countBuilder  查询数量语句
	 * @param listBuilder	查询集合语句
	 * @param queryStr		查询语句
	 */
	public void bulidQueryStrItem(StringBuilder countBuilder,StringBuilder listBuilder,String queryStr){
		countBuilder.append(queryStr);
		listBuilder.append(queryStr);
	}
	
	/**
	 * 分页查询设置查询语句参数
	 * @param countQuery
	 * @param listQuery
	 * @param nameParameter 占位符
	 * @param val			查询值	
	 */
	public void setQueryParamsVal(Query countQuery,Query listQuery,String nameParameter,Object val){
		countQuery.setParameter(nameParameter, val);
		listQuery.setParameter(nameParameter, val);
	}
	
	
	public void setQueryParamsListVal(Query countQuery,Query listQuery,String nameParameter,Object val){
		countQuery.setParameterList(nameParameter, (Collection) val);
		listQuery.setParameterList(nameParameter, (Collection) val);
	}
	
		
	private  void setParameter(Query query, Object... objects) {
        for (int i = 0; i < objects.length; i++) {
            query.setParameter(i, objects[i]);
        }
	 }


	public Long countTotalNum() {
		Criteria cr = this.getSession().createCriteria(this.getInheritClass());	

		return (Long) cr.setProjection(Projections.rowCount()).uniqueResult();
	}

	  
	public Criteria getCriteria(){
		return this.getSession().createCriteria(getInheritClass());
	}
}

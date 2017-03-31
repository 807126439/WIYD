package com.wb.core.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.BaseQueryDTO;



public interface IBaseDao<PK extends Serializable ,T > {
	
	public void flush();
	
	public void clear();
	
	public <T> Serializable save(T entity); 
	
	public <T> void batchSave(List<T> entitys);
	
	public <T> void saveOrUpdate(T entity);
	
	public <T>  void update(T entity); 
	
	public <T> void delete(T entity);
	
	public void deleteAll(List<T> list);

    public T getById(Serializable id);
    
	public <T> List<T> loadAll(); 
	
	public List<T> findList(String hql, Object... objects); 
	
	public List<T> findList(String hql); 
	
    public List<T> findListBySql(String sql, Object... objects);
    
    public T findObjectBySql(String sql, Object... objects);
    
    public int executeSql(String sql, Object... objects);
	
	public Criterion generateSearchCriterion(String searchField,String searchString, String searchOper);
	
	public List<PK> getPrimaryKeyByCondition(String filedName,Object value);
	
	public boolean checkWheatherExistByCondition(Map<String, Object> queryMap);
	
	public  Object[] findObjectByReturnFields(String queryField,Object queryVal,String... returnFields);
	
	public  Object findUniqueByReturnField(String queryField,Object queryVal,String returnField);
	
	public T getUniqueByProperty(String filedName,String value);
	
	public boolean checkIsExistByProperty(String filedName,Object value,Object exceptId);
	
	public Page<T> searchEntityWithConditionForJqgrid(BaseQueryDTO queryDTO);
	
	public Page<T> searchEntityByPage(BaseQueryDTO queryDto);
	
	public void bulidQueryStrItem(StringBuilder countBuilder,StringBuilder listBuilder,String queryStr);
	
	public void setQueryParamsVal(Query countQuery,Query listQuery,String nameParameter,Object val);
	
	public Long countTotalNum(); 
	
	public Criteria getCriteria();

}


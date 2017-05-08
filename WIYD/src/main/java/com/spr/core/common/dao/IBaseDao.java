package com.spr.core.common.dao;

import java.io.Serializable;

public interface IBaseDao<PK extends Serializable,T> {
	
	public int deleteById(PK id);
	
	public int insert(T entity);
	
	public int insertSelective(T entity);
	
	public int update(T entity);
		
	public T getById(PK id);
	
	public int updateSelective(T entity);
	
}

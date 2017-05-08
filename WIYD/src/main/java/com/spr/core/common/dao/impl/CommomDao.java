package com.spr.core.common.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.spr.core.common.dao.ICommomDao;

/**
 * 
 * @author zjr
 *
 */

@Component("commomDao")
public class CommomDao extends SqlSessionDaoSupport implements ICommomDao{
	
	
	@Autowired
	@Qualifier("sqlSessionFactory")
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	
	public SqlSession getSqlSession() {
		
		return super.getSqlSession();
	}

	
	
	
}

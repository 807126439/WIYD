package com.wb.web.system.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.web.system.dao.IZonePathDao;
import com.wb.web.system.entity.ZonePath;

@Repository("zonePathDao")
public class ZonePathDaoImpl extends BaseDaoImpl<Long, ZonePath> implements IZonePathDao{

	
	@SuppressWarnings("unchecked")
	public List<ZonePath> searchListByTypeWithMarkASC(String type) {
		if(type == null || StringUtils.isBlank(type)){
			throw new IllegalArgumentException("Cannot type  null or empty");
		}
		
		String SQL = "SELECT * FROM c_zone_path WHERE type=? ORDER BY mark ASC";
		Query query = this.getSession().createSQLQuery(SQL).addEntity(getInheritClass());
		query.setString(0, type);
		
		return query.list();
		
		
	}

}

package com.wb.web.base.dao.impl;

import org.hibernate.Query;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.web.base.dao.IBaseFileDao;
import com.wb.web.base.entity.BaseFile;

@Repository("baseFileDao")
public class BaseFileDaoImpl extends BaseDaoImpl<Long, BaseFile> implements IBaseFileDao{

	/**
	 * 查询文件的物理路径
	 * @param id
	 * @return
	 */
	public String getBaseFilePath(Long id){
		String SQL = "SELECT CONCAT(z.path,f.save_path) as path FROM m_base_file f INNER JOIN c_zone_path z ON f.zone_path_id=z.id  WHERE f.id=?";
		
		Query query = this.getSession().createSQLQuery(SQL).addScalar("path", StringType.INSTANCE);
		query.setParameter(0, id);
		
		return (String) query.uniqueResult();
	}
	
	/**
	 * 查询公共文件的预览路径
	 * @param id
	 * @return
	 */
	public String getFileViewPath(Long id){
		String SQL = "SELECT CONCAT(z.virtual_path,f.transfer_path) as path FROM m_base_file f INNER JOIN c_zone_path z ON f.zone_path_id=z.id  WHERE f.id=?";
		
		Query query = this.getSession().createSQLQuery(SQL).addScalar("path", StringType.INSTANCE);
		query.setParameter(0, id);
		
		return (String) query.uniqueResult();
	}
	
	
	
	
	
	
}

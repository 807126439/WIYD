package com.wb.web.portals.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.ShortType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.web.portals.dao.IAdvertisementDao;
import com.wb.web.portals.dto.ads.IndexAdsDTO;
import com.wb.web.portals.entity.Advertisement;

@Repository("advertisementDao")
public class AdvertisementDaoImpl extends BaseDaoImpl<Long, Advertisement> implements IAdvertisementDao{

	
	@SuppressWarnings("unchecked")
	public List<IndexAdsDTO> searchIndexAdsList(){
		String SQL = "SELECT title,pattern,base_file_id as baseFileId,sort_num as sortNum,link_url as linkUrl,type_id as typeId FROM cy_ads ORDER BY sort_num ASC"; 
		
		Query query = this.getSession().createSQLQuery(SQL)
					  .addScalar("title", StringType.INSTANCE).addScalar("pattern", StringType.INSTANCE)
					  .addScalar("baseFileId",LongType.INSTANCE)
					  .addScalar("sortNum", IntegerType.INSTANCE).addScalar("linkUrl", StringType.INSTANCE)
					  .addScalar("typeId", ShortType.INSTANCE)
					  .setResultTransformer(Transformers.aliasToBean(IndexAdsDTO.class));
		
		
		return query.list();
	}
	
	
}

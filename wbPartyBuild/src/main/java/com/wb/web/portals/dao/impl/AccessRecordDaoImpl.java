package com.wb.web.portals.dao.impl;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.web.portals.dao.IAccessRecordDao;
import com.wb.web.portals.dto.accessRecord.AccessRecordDTO;
import com.wb.web.portals.dto.activityRule.ActivityRuleDTO;
import com.wb.web.portals.entity.AccessRecord;
import com.wb.web.portals.entity.ActivityRule;

@Repository("accessRecordDao")
public class AccessRecordDaoImpl extends BaseDaoImpl<Long, AccessRecord> implements IAccessRecordDao{

	@Override
	public void accessIncreas() {
		
	}

	@Override
	public AccessRecordDTO getAccessRecord() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select id,total_access as totalAccess,today_access as todayAccess,last_operator_time as lastOperatorTime from c_access_record where 1=1 order by id DESC limit 1");
		Query query = this.getSession().createSQLQuery(sql.toString())
					.addScalar("id",LongType.INSTANCE)
					.addScalar("totalAccess",LongType.INSTANCE)
					.addScalar("todayAccess",LongType.INSTANCE)
					.addScalar("lastOperatorTime",DateType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(AccessRecordDTO.class));
		return (AccessRecordDTO) query.uniqueResult();
	}

}

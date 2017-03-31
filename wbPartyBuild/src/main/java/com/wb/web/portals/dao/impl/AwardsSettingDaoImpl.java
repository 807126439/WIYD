package com.wb.web.portals.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.utils.Assert;
import com.wb.core.utils.ReflectUtil;
import com.wb.web.portals.dao.IAwardWinningWorksDao;
import com.wb.web.portals.dao.IAwardsSettingDao;
import com.wb.web.portals.dto.awardWinningWorks.AwardDTO;
import com.wb.web.portals.dto.awardsSetting.AwardsSettingDTO;
import com.wb.web.portals.dto.awardsSetting.AwardsSettingQueryDTO;
import com.wb.web.portals.entity.AwardWinningWorks;
import com.wb.web.portals.entity.AwardsSetting;

@Repository("awardsSettingDao")
public class AwardsSettingDaoImpl extends BaseDaoImpl<Long, AwardsSetting>
		implements IAwardsSettingDao {

	@Resource
	private IAwardWinningWorksDao awardWinningWorksDao;

	@Override
	public Page<AwardsSettingDTO> searchEntityByPage2(
			AwardsSettingQueryDTO queryDTO) {

		StringBuilder countBuilder = new StringBuilder(
				"SELECT COUNT(*) as num FROM c_awards_setting a WHERE 1=1 ");
		StringBuilder listBuilder = new StringBuilder(
				"select a.id as asId,a.awards_name as awardsName,a.amount,a.memo,a.prize,c.pattern,a.sort_num as sortNum")
				.append(" from c_awards_setting a INNER JOIN m_base_file c ON a.base_file_id = c.id where 1=1 ");

		if (queryDTO.getActivityId() != null) {
			this.bulidQueryStrItem(countBuilder, listBuilder,
					" AND a.activity_id=:activityId");
		}

		if (!StringUtils.isBlank(queryDTO.getSidx())
				&& !StringUtils.isBlank(queryDTO.getSord())) {
			String filedName = ReflectUtil.getFiledValueByAnnotation(
					getInheritClass(), queryDTO.getSidx());
			listBuilder.append(generateOrderSql(queryDTO.getSord(), filedName,
					null));
		}

		Query queryCount = this.getSession()
				.createSQLQuery(countBuilder.toString())
				.addScalar("num", LongType.INSTANCE);
		Query queryList = this
				.getSession()
				.createSQLQuery(listBuilder.toString())
				.addScalar("asId", LongType.INSTANCE)
				.addScalar("awardsName", StringType.INSTANCE)
				.addScalar("amount", IntegerType.INSTANCE)
				.addScalar("memo", StringType.INSTANCE)
				.addScalar("prize", StringType.INSTANCE)
				.addScalar("pattern", StringType.INSTANCE)
				.addScalar("sortNum", IntegerType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(AwardsSettingDTO.class));

		if (queryDTO.getActivityId() != null) {
			this.setQueryParamsVal(queryCount, queryList, "activityId",
					queryDTO.getActivityId());
		}

		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());

		return new Page<AwardsSettingDTO>(queryDTO.getCurrentPage(),
				queryDTO.getPageSize(), queryList.list(),
				(Long) queryCount.uniqueResult());

	}

	@Override
	public List<AwardsSettingDTO> getAllAwardsSettingByActivityId(
			Long activityId) {
		// 查询该活动下所有奖项
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id as asId,a.awards_name as awardsName,a.amount,a.prize,b.pattern ");
		sql.append("from c_awards_setting a inner join m_base_file b on a.base_file_id = b.id  ");
		sql.append("where a.activity_id ="+activityId);
	
		Query query = this
				.getSession()
				.createSQLQuery(sql.toString())
				.addScalar("asId", LongType.INSTANCE)
				.addScalar("awardsName", StringType.INSTANCE)
				.addScalar("amount", IntegerType.INSTANCE)
				.addScalar("prize", StringType.INSTANCE)
				.addScalar("pattern",StringType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(AwardsSettingDTO.class));
		List<AwardsSettingDTO> aslist = query.list();
		return aslist;

	}

	
	@Override
	public List<AwardDTO> getAllAwardByActivityId(
			Long activityId) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select id,awards_name as awardName,amount from c_awards_setting where activity_id = ? ");
		sql.append("ORDER BY sort_num asc  ");
		
		Query query = this
				.getSession()
				.createSQLQuery(sql.toString())
				.addScalar("id", LongType.INSTANCE)
				.addScalar("awardName", StringType.INSTANCE)
				.addScalar("amount", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(AwardDTO.class))
				.setParameter(0, activityId);
						
		List<AwardDTO> aslist = query.list();
		return aslist;
		
		
	}
	

	/**
	 * 将已占用的获奖名额剔除
	 */
	public List<AwardsSettingDTO> delAssignedAwardsSettingByActivityId(
			List<AwardsSettingDTO> aslist, Long activityId) {
		for (AwardsSettingDTO asdto : aslist) {
			asdto.setAmountleft(asdto.getAmount());
		}
		// 查询该活动下所有获奖稿件
		List<AwardWinningWorks> awwlist = awardWinningWorksDao
				.searchAwardWinningWorksByActivityId(activityId);
		if (awwlist != null && awwlist.size() > 0) {
			// 遍历获奖稿件，将奖项的名额扣减
			
			for (AwardWinningWorks aww : awwlist) {
				for (AwardsSettingDTO asdto : aslist) {
					int left = asdto.getAmountleft();
					if (aww.getAwardsSetting().getId().equals(asdto.getAsId())) {
						left--;
						asdto.setAmountleft(left);
						
					}
				}
			}
			
			
		List<AwardsSettingDTO> copylist = new ArrayList<AwardsSettingDTO>();	
		for (AwardsSettingDTO asdto : aslist) {
			if (asdto.getAmountleft() > 0) {
				copylist.add(asdto);
			}
		}
			return copylist;
		}
		return aslist;
	}

	@Override
	public void delASByActivityId(Long activityId) {	
		Assert.notNull(activityId, "activityId can not be null");
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from c_awards_setting where activity_id = ?");
		Query query = this.getSession().createSQLQuery(sql.toString()).setLong(0, activityId);
		query.executeUpdate();	
		
	}

}

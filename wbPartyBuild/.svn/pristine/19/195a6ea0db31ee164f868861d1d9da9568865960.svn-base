package com.wb.web.portals.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.ShortType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.web.portals.dao.IAwardWinningWorksDao;
import com.wb.web.portals.dao.IManuscriptDao;
import com.wb.web.portals.dto.manuscript.ManuscriptDTO;
import com.wb.web.portals.dto.manuscript.ManuscriptQueryDTO;
import com.wb.web.portals.dto.manuscript.PhotoDTO;
import com.wb.web.portals.entity.AwardWinningWorks;
import com.wb.web.portals.entity.Manuscript;

@Repository("manuscriptyDao")
public class ManuscriptDaoImpl extends BaseDaoImpl<Long, Manuscript> implements
		IManuscriptDao {

	@Resource
	private IAwardWinningWorksDao awardWinningWorksDao;

	@Override
	public Page<ManuscriptDTO> searchManuscriptByPage(ManuscriptQueryDTO queryDTO){
		
		StringBuilder countBuilder = new StringBuilder("SELECT COUNT(*) as num FROM c_manuscript a INNER JOIN c_user b ON a.user_id = b.id INNER JOIN m_base_file c ON a.base_file_id = c.id where 1=1 ");
		StringBuilder listBuilder = new StringBuilder("SELECT a.id as msId,a.status,a.title,a.author,b.user_name AS username,a.description,c.pattern,a.create_time AS createTime,a.ban_chunk_id as banChunkId ")
											  .append("FROM c_manuscript a INNER JOIN c_user b ON a.user_id = b.id INNER JOIN m_base_file c ON a.base_file_id = c.id where 1=1 ");


		if (queryDTO.getActivityId() != null) {
			this.bulidQueryStrItem(countBuilder, listBuilder,
					"AND a.activity_id=:activityId ");
		}
		if (queryDTO.getBanChunkId() != null) {
			this.bulidQueryStrItem(countBuilder, listBuilder,
					"AND a.ban_chunk_id=:banChunkId ");
		}
		
		this.bulidQueryStrItem(countBuilder, listBuilder,
				" order by  a.create_time desc");
	
		

		Query queryCount = this.getSession()
				.createSQLQuery(countBuilder.toString())
				.addScalar("num", LongType.INSTANCE);
		Query queryList = this
				.getSession()
				.createSQLQuery(listBuilder.toString())
				.addScalar("msId", LongType.INSTANCE)
				.addScalar("status", ShortType.INSTANCE)
				.addScalar("title", StringType.INSTANCE)
				.addScalar("author", StringType.INSTANCE)
				.addScalar("username", StringType.INSTANCE)
				.addScalar("description", StringType.INSTANCE)
				.addScalar("pattern", StringType.INSTANCE)
				.addScalar("createTime", TimestampType.INSTANCE)
				.addScalar("banChunkId", LongType.INSTANCE)
				.setResultTransformer(
						Transformers.aliasToBean(ManuscriptDTO.class));

		if (queryDTO.getActivityId() != null) {
			this.setQueryParamsVal(queryCount, queryList, "activityId",
					queryDTO.getActivityId());
		}
		if (queryDTO.getBanChunkId() != null) {
			this.setQueryParamsVal(queryCount, queryList, "banChunkId",
					queryDTO.getBanChunkId());
		}

		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());

		return new Page<ManuscriptDTO>(queryDTO.getCurrentPage(),
				queryDTO.getPageSize(), queryList.list(),
				(Long) queryCount.uniqueResult());

	}


	@Override
	public List<PhotoDTO> getCheckedPhotosByActivityId(Long activityId) {

		StringBuilder listBuilder = new StringBuilder("SELECT a.id,b.pattern,b.zone_path_id as zonePathId ");
		listBuilder.append("from c_manuscript a ");
		listBuilder.append("INNER JOIN m_base_file b on a.base_file_id = b.id ");
		listBuilder.append("where a.status = 1 and a.activity_id=?" );
		
		
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
				.addScalar("id", LongType.INSTANCE).addScalar("pattern", StringType.INSTANCE)
				.addScalar("zonePathId", LongType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(PhotoDTO.class)).setParameter(0, activityId);


		return queryList.list();
	}

	public List<Manuscript> getAllManuscriptByActivityId(long activityId) {
		// 查询该活动下所有稿件
		Criteria cr = this.getSession().createCriteria(getInheritClass());
		cr.add(Restrictions.and(Restrictions.eq("activity.id", activityId), Restrictions.eq("status", (short)1)));
		List<Manuscript> mlist = cr.list();

		return mlist;
	}

	/**
	 * 剔除已获奖稿件
	 * 
	 * @param mlist
	 * @return
	 */
	public List<Manuscript> delAwardWinningManuscriptByActivityId(
			List<Manuscript> mlist, long activityId) {
		// 查询该活动下所有获奖稿件
		List<AwardWinningWorks> alist = awardWinningWorksDao
				.searchAwardWinningWorksByActivityId(activityId);

		if (alist != null && alist.size() > 0) {
			List<Manuscript> copylist = new ArrayList<Manuscript>();
			for (Manuscript ms : mlist) {
				copylist.add(ms);
			}
			
			// 遍历获奖稿件ID删除已获奖稿件

			for (AwardWinningWorks aww : alist) {
				for (Manuscript ms : mlist) {
					if (aww.getManuscript().getId().equals(ms.getId())) {
						copylist.remove(ms);
					}
				}
			}
			return copylist;
		} else {
			return mlist;
		}
	}


	@Override
	public void deleteVote(Long msId, String userId) {
		
		String sql = "delete from c_manuscript_voter where manuscript_id =:msId and user_id =:userId";
        Query query = this.getSession().createSQLQuery(sql).setParameter("msId", msId).setParameter("userId", userId);
        query.executeUpdate();
		
	}

	
	@Override
	public Page<PhotoDTO> queryPhotosByPage(ManuscriptQueryDTO queryDTO) {
				
		StringBuilder countBuilder = new StringBuilder("SELECT count(*) as num  ");				
		StringBuilder listBuilder = new StringBuilder("SELECT a.id,a.status,a.title,a.author,a.description,a.create_time as uploadTime,a.love,b.pattern,b.big_pattern as bigPattern,b.zone_path_id as zonePathId ");				
														
		this.bulidQueryStrItem(countBuilder, listBuilder, "from c_manuscript a INNER JOIN m_base_file b on a.base_file_id = b.id where 1=1 ");			
		this.bulidQueryStrItem(countBuilder, listBuilder, "and a.activity_id=:activityId ");		
		
		
		if (queryDTO.getAuthor() != null && queryDTO.getAuthor() != ""){			
			this.bulidQueryStrItem(countBuilder, listBuilder, " and a.author like:author");		
		}if (queryDTO.getTitle() !=null && queryDTO.getTitle() != ""){
			this.bulidQueryStrItem(countBuilder, listBuilder, " and a.title like:title");
		}if (queryDTO.getStatus() !=null){
			this.bulidQueryStrItem(countBuilder, listBuilder, " and a.status =:status");
		}
		
		
		if(queryDTO.getQueryByTime() != null && queryDTO.getQueryByTime() == true){
			listBuilder.append(" order by a.create_time DESC ");	
		}if(queryDTO.getQueryByLove() != null && queryDTO.getQueryByLove() == true){
			listBuilder.append(" order by a.love DESC ");	
		}else if(queryDTO.getQueryByTime() == null && queryDTO.getQueryByLove() == null){
			listBuilder.append(" order by a.status desc,a.create_time desc ");
		}
		
		
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString())
				.addScalar("num", LongType.INSTANCE);
		
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
				.addScalar("id", LongType.INSTANCE)
				.addScalar("status", ShortType.INSTANCE)
				.addScalar("title", StringType.INSTANCE)
				.addScalar("author", StringType.INSTANCE)
				.addScalar("description", StringType.INSTANCE)
				.addScalar("uploadTime", TimestampType.INSTANCE)
				.addScalar("love", IntegerType.INSTANCE)
				.addScalar("pattern", StringType.INSTANCE)
				.addScalar("bigPattern", StringType.INSTANCE)
				.addScalar("zonePathId", LongType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(PhotoDTO.class))
				.setFirstResult((queryDTO.getCurrentPage() - 1) * queryDTO.getPageSize())
				.setMaxResults(queryDTO.getPageSize());	
		
		    this.setQueryParamsVal(queryCount,queryList,"activityId",queryDTO.getActivityId());
			if(queryDTO.getAuthor() != null && queryDTO.getAuthor() != ""){			
				this.setQueryParamsVal(queryCount, queryList,"author", "%"+queryDTO.getAuthor()+"%");
			}if(queryDTO.getTitle() !=null && queryDTO.getTitle() != ""){
				this.setQueryParamsVal(queryCount, queryList, "title", "%"+queryDTO.getTitle()+"%");
			}if (queryDTO.getStatus() !=null){
				this.setQueryParamsVal(queryCount, queryList, "status", queryDTO.getStatus());
			}
	
		
		return new Page<PhotoDTO>(queryDTO.getCurrentPage(), queryDTO.getPageSize(), queryList.list(),(Long) queryCount.uniqueResult());
	}


	@Override
	public void deleteVote(Long msId) {
		String sql = "delete from c_manuscript_voter where manuscript_id =:msId";
        Query query = this.getSession().createSQLQuery(sql).setParameter("msId", msId);
        query.executeUpdate();
	}
	

}

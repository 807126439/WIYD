package com.wb.web.portals.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dao.impl.BaseDaoImpl;
import com.wb.core.common.utils.Assert;
import com.wb.web.portals.dao.ICommentDao;
import com.wb.web.portals.dto.comment.CommentDTO;
import com.wb.web.portals.dto.comment.CommentQueryDTO;
import com.wb.web.portals.dto.comment.CountCommentDTO;
import com.wb.web.portals.entity.Comment;

@Repository("commentDao")
public class CommentDaoImpl extends BaseDaoImpl<Long, Comment> implements ICommentDao{

	@Override
	public Page<CommentDTO> searchEntityByPage(CommentQueryDTO queryDTO) {
		
		
		StringBuilder countBuilder = new StringBuilder("SELECT COUNT(*) as num ");
		StringBuilder listBuilder = new StringBuilder("SELECT a.id as id,b.user_name as username ,a.comment_time as commentTime,a.last_operator_time as updateTime,a.content as content ");
											
											
		
		
		this.bulidQueryStrItem(countBuilder, listBuilder, "from cy_comment a inner JOIN c_user b on a.user_id = b.id INNER JOIN cy_communication c on a.communication_id = c.id WHERE 1=1 ");
		if(queryDTO.getCommunicationId()!=null){
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND a.communication_id=:communicationId ");
		}if(queryDTO.getUsername()!=null&&queryDTO.getUsername()!=""){
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND b.user_name like:username ");
		}
		
		this.bulidQueryStrItem(countBuilder, listBuilder, "order by a.comment_time desc ");
		
		
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
						.addScalar("id", LongType.INSTANCE).addScalar("username", StringType.INSTANCE)
						.addScalar("commentTime", TimestampType.INSTANCE)
						.addScalar("updateTime", TimestampType.INSTANCE)
						.addScalar("content", StringType.INSTANCE)
						.setResultTransformer(Transformers.aliasToBean(CommentDTO.class));	
		
		if(queryDTO.getCommunicationId()!=null){
			this.setQueryParamsVal(queryCount, queryList, "communicationId", queryDTO.getCommunicationId());
		}if(queryDTO.getUsername()!=null&&queryDTO.getUsername()!=""){
			this.setQueryParamsVal(queryCount, queryList, "username", queryDTO.getUsername());
		}		
			
		
		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());
		
		return new Page<CommentDTO>(queryDTO.getCurrentPage(), queryDTO.getPageSize(),queryList.list(),(Long) queryCount.uniqueResult());		
	}	

	
	
	
	
	@Override
	public Page<CommentDTO> getAllComment(Long comId,Integer curPage,Integer pageSize,Boolean byTime,Boolean byLove ) {
		
		Assert.notNull(comId, "comId must not be null");
		
		StringBuilder countBuilder = new StringBuilder("SELECT count(*) as num from cy_comment a ");				
		countBuilder.append("INNER JOIN c_user b on a.user_id = b.id ");				
		countBuilder.append("where 1=1 ");
		
		
		StringBuilder listBuilder = new StringBuilder("select a.id,a.content,b.user_name as username,a.is_have_child as isHaveChild,a.love,a.comment_time as commentTime,a.last_operator_time as updateTime ");				
		listBuilder.append("from cy_comment a INNER JOIN c_user b on a.user_id = b.id ");
		listBuilder.append("where 1=1 ");				
		
			
		this.bulidQueryStrItem(countBuilder, listBuilder, "and a.communication_id=? ");		
	    this.bulidQueryStrItem(countBuilder, listBuilder, "and a.parent_id is null ");
	    
	    
	    if(byTime == true){
	    	listBuilder.append(" order by a.comment_time desc ");
	    }if(byLove == true){
	    	listBuilder.append(" order by a.love desc ");
	    }
				
		
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString())
				.addScalar("num", LongType.INSTANCE).setParameter(0, comId);
		
		
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
				.addScalar("id", LongType.INSTANCE).addScalar("content", StringType.INSTANCE)
				.addScalar("username", StringType.INSTANCE)
				.addScalar("isHaveChild",BooleanType.INSTANCE)
				.addScalar("love",IntegerType.INSTANCE)
				.addScalar("commentTime", TimestampType.INSTANCE)
				.addScalar("updateTime", TimestampType.INSTANCE)				
				.setResultTransformer(Transformers.aliasToBean(CommentDTO.class)).setParameter(0, comId);
									
	    queryList.setFirstResult((curPage - 1) * pageSize);
	    queryList.setMaxResults(pageSize);
		
		return new Page<CommentDTO>(curPage, pageSize, queryList.list(),(Long) queryCount.uniqueResult());			
	}
	

	@Override
	public Integer conutComment(Long comId,Integer type) {		
		String x="";	
		if(type==1){
			x = x+"DISTINCT(user_id)";
		}else{
			x = x+"*";
		}	
		String sql = "SELECT COUNT("+x+") as num from cy_comment where communication_id ="+comId;
		Query query =  this.getSession().createSQLQuery(sql).addScalar("num", IntegerType.INSTANCE);		
		Integer num = (Integer) query.uniqueResult();
		return num;
	}

	
	@Override
	public Integer countTotlaPage(Long comId) {
		String sql = "SELECT COUNT(*) as num from cy_comment where  parent_id is null and communication_id ="+comId;
		Query query =  this.getSession().createSQLQuery(sql).addScalar("num", IntegerType.INSTANCE);		
		Integer num = (Integer) query.uniqueResult();
		Integer totalPage = null;
		if(num<=6){
			totalPage = 1;
		}else{
			if(num%6 == 0){
				totalPage = num/6;
			}else{
				totalPage = num/6+1;
			}
		}			
		return totalPage;
	}
	
	
	public Integer countTotalSize(Long comId){
		String sql = "SELECT COUNT(*) as num from cy_comment where  parent_id is null and communication_id ="+comId;
		Query query =  this.getSession().createSQLQuery(sql).addScalar("num", IntegerType.INSTANCE);			
		return (Integer) query.uniqueResult();
	}
	
	


	@Override
	public List<CommentDTO> getAllChildComment(Long parentId) {		
		StringBuffer sql = new StringBuffer("select a.id,a.content,a.user_id as userId,a.target_user_id as targetId,b.user_name as username,a.love,a.comment_time as commentTime,a.last_operator_time as updateTime ");				
		sql.append("from cy_comment a INNER JOIN c_user b on a.user_id = b.id ");
		sql.append("where a.parent_id = ?");
		sql.append(" order by a.comment_time desc");
				
		Query queryList = this.getSession().createSQLQuery(sql.toString())
				.addScalar("id", LongType.INSTANCE)
				.addScalar("content", StringType.INSTANCE)
				.addScalar("userId", StringType.INSTANCE)
				.addScalar("targetId", StringType.INSTANCE)
				.addScalar("username", StringType.INSTANCE)
				.addScalar("love",IntegerType.INSTANCE)
				.addScalar("commentTime", TimestampType.INSTANCE)
				.addScalar("updateTime", TimestampType.INSTANCE)				
				.setResultTransformer(Transformers.aliasToBean(CommentDTO.class)).setParameter(0, parentId);
		return queryList.list();
	}





	@Override
	public Integer countChildComment(Long comId) {
		String sql = "SELECT COUNT(*) as num from cy_comment where parent_id ="+comId;
		Query query =  this.getSession().createSQLQuery(sql).addScalar("num", IntegerType.INSTANCE);				
		Integer num = (Integer) query.uniqueResult();
		return num;
	}



	@Override
	public List<CommentDTO> getCommentList(Long comId) {
		String sql = "SELECT id from cy_comment where communication_id = "+comId;
		Query query =  this.getSession().createSQLQuery(sql).addScalar("id", LongType.INSTANCE)
				           .setResultTransformer(Transformers.aliasToBean(CommentDTO.class));
		
		return query.list();
	}





	@Override
	public void deleteVote(Long commentId, String userId) {
		String sql = "delete from cy_comment_voter where comment_id =:commentId and user_id =:userId";
        Query query = this.getSession().createSQLQuery(sql).setParameter("commentId", commentId).setParameter("userId", userId);
        query.executeUpdate();
	}





	@Override
	public Page<CountCommentDTO> searchCountCommentByPage(CommentQueryDTO queryDTO) {
		
		
		String commonSql = "FROM c_user a LEFT JOIN cy_comment b ON a.id = b.user_id LEFT JOIN c_job_depart_relation jdr ON a.id=jdr.user_id where  a.is_del=0 and a.is_ignore_stat=0 ";
		
		
		StringBuilder countBuilder = new StringBuilder("SELECT COUNT(*) as num from ");
		countBuilder.append(" (SELECT b.user_id,count(DISTINCT b.comment_time) AS  num "+commonSql);
	
	
		StringBuilder listBuilder = new StringBuilder("SELECT a.id as userId,a.chinese_name AS username,count(DISTINCT b.comment_time) AS num,MAX(b.comment_time) as lastCommentTime ");
		listBuilder.append(commonSql);
		
		if(queryDTO.getUsername()!=null&&queryDTO.getUsername()!=""){
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND a.chinese_name like:username ");
		}if(queryDTO.getCommentTimeBegin()!=null&&queryDTO.getCommentTimeEnd()==null){
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND b.comment_time >=:commentTimeBegin ");
		}if(queryDTO.getCommentTimeEnd()!=null&&queryDTO.getCommentTimeBegin()==null){
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND b.comment_time <=:commentTimeEnd ");
		}if(queryDTO.getCommentTimeEnd()!=null&&queryDTO.getCommentTimeBegin()!=null){
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND b.comment_time >=:commentTimeBegin and b.comment_time <=:commentTimeEnd ");
		}
		
		
		
		//部门查询条件相关
		//获取ID下自身以及后代部门ID
		if(queryDTO.getDepartmentIds()!=null && !StringUtils.isBlank(queryDTO.getDepartmentIds())){
			String[] ids=queryDTO.getDepartmentIds().split(",");
			StringBuilder departmentIds=new StringBuilder();
			for(String o:ids){
				departmentIds.append("'"+o+"',");
			}
			departmentIds.deleteCharAt(departmentIds.lastIndexOf(","));
			this.bulidQueryStrItem(countBuilder, listBuilder, "AND jdr.department_id IN ("+departmentIds+") ");
		}
		
		
		
		countBuilder.append(" GROUP BY a.id) x");
		listBuilder.append("GROUP BY a.id ORDER BY num DESC ");
		
		Query queryCount = this.getSession().createSQLQuery(countBuilder.toString()).addScalar("num", LongType.INSTANCE);
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
						.addScalar("userId", StringType.INSTANCE)
						.addScalar("username", StringType.INSTANCE)
						.addScalar("num", IntegerType.INSTANCE)
						.addScalar("lastCommentTime", TimestampType.INSTANCE)
						.setResultTransformer(Transformers.aliasToBean(CountCommentDTO.class));	
		

		if(queryDTO.getUsername()!=null&&queryDTO.getUsername()!=""){
			this.setQueryParamsVal(queryCount, queryList, "username", queryDTO.getUsername());
		}if(queryDTO.getCommentTimeBegin()!=null&&queryDTO.getCommentTimeEnd()==null){
			this.setQueryParamsVal(queryCount, queryList, "commentTimeBegin", queryDTO.getCommentTimeBegin());
		}if(queryDTO.getCommentTimeEnd()!=null&&queryDTO.getCommentTimeBegin()==null){
			this.setQueryParamsVal(queryCount, queryList, "commentTimeEnd", queryDTO.getCommentTimeEnd());
		}if(queryDTO.getCommentTimeEnd()!=null&&queryDTO.getCommentTimeBegin()!=null){
			this.setQueryParamsVal(queryCount, queryList, "commentTimeBegin", queryDTO.getCommentTimeBegin());
			this.setQueryParamsVal(queryCount, queryList, "commentTimeEnd", queryDTO.getCommentTimeEnd());
		}		
			
		
		queryList.setFirstResult(queryDTO.getStartQuery());
		queryList.setMaxResults(queryDTO.getPageSize());
		
		List<CountCommentDTO> list = queryList.list();
		long sort=1;
		for(CountCommentDTO o:list){
			o.setSort(sort);
			sort++;
		}
		
		
		return new Page<CountCommentDTO>(queryDTO.getCurrentPage(), queryDTO.getPageSize(),list,(Long) queryCount.uniqueResult());
	
	}





	@Override
	public List<CountCommentDTO> getCountCommentList() {
		StringBuilder listBuilder = new StringBuilder("SELECT a.chinese_name AS username,count(DISTINCT b.comment_time) AS num,MAX(b.comment_time) as lastCommentTime ");		
		listBuilder.append("FROM c_user a LEFT JOIN cy_comment b ON a.id = b.user_id where 1=1 ");		
		listBuilder.append("GROUP BY a.id ORDER BY num DESC ");
		
		Query queryList = this.getSession().createSQLQuery(listBuilder.toString())
				.addScalar("username", StringType.INSTANCE)
				.addScalar("num", IntegerType.INSTANCE)
				.addScalar("lastCommentTime", TimestampType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(CountCommentDTO.class));	
		
		List<CountCommentDTO> list = queryList.list();
		
		long i = 1;
		for (CountCommentDTO countCommentDTO : list) {
			countCommentDTO.setSort(i);
			i++;			
		}
		
		return list;
	}


}
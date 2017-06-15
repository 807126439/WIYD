package com.wb.web.portals.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.core.utils.HTMLUtil;
import com.wb.web.base.dao.IBaseFileDao;
import com.wb.web.base.dto.result.SaveResult;
import com.wb.web.base.entity.BaseFile;
import com.wb.web.base.service.IBaseFileService;
import com.wb.web.portals.dao.ICommentDao;
import com.wb.web.portals.dao.ICommunicationDao;
import com.wb.web.portals.dto.communication.CommunicationDTO;
import com.wb.web.portals.dto.communication.CommunicationQueryDTO;
import com.wb.web.portals.dto.communication.FiledDTO;
import com.wb.web.portals.entity.Comment;
import com.wb.web.portals.entity.Communication;
import com.wb.web.portals.service.ICommentService;
import com.wb.web.portals.service.ICommunicationService;
import com.wb.web.system.entity.User;
import com.wb.web.system.service.IZonePathService;

@Service("communicationService")
@Transactional
public class CommunicationServiceImpl extends BaseService implements ICommunicationService{
	
	@Resource
	private ICommunicationDao communDao;	
	@Resource
	private ICommentService commentService;
	@Resource
	private ICommentDao commentDao;
	
	@Resource
	private IBaseFileService baseFileService;
	@Resource
	private IBaseFileDao baseFileDao;
	@Resource
	private IZonePathService zonePathService;
	
	
	

	
	/**
	 * 新增互动议题
	 * 默认为: 未激活 isActive = false
	 * 		   未归档 isFiled = false
	 */
	@Override
	public void addCommunication(CommunicationDTO dto) {	
		Assert.notNull(dto.getTitle(), "议题名称不能为空!");
		Assert.notNull(dto.getSponsor(), "发起人不能为空!");
		Assert.notNull(dto.getStartDate(), "开始日期不能为空!");
		Assert.notNull(dto.getEndDate(), "结束日期不能为空!");
		Assert.notNull(dto.getContent(), "议题简述不能为空!");
		
		Communication com = new Communication(
				dto.getTitle(), 
				dto.getContent(), 
				dto.getSponsor(), 
				dto.getStartDate(), 
				dto.getEndDate(),
				Communication.STATUS_ACTIVE,
				0
				);

		if(StringUtils.isNotBlank(dto.getUuid())){

			//根据uuid查出上传文档
			Long baseFileId = null;		
			Set<SaveResult> saveResults= this.getTempCacheVal(dto.getUuid());
						
			
			for (SaveResult saveResult : saveResults) {
				baseFileId = saveResult.getId();
			}
			

				
			if(baseFileId!=null){					
				com.setVideoId(baseFileId);					
			}
				
		}
		
		this.communDao.save(com);	
	}
	
	/**
	 * 删除议题先删除评论	
	 */
	@Override
	public void deleteCommunication(Long[] ids) {
		for (int i = 0; i < ids.length; i++) {
			Communication com = this.communDao.getById(ids[i]);
			if(com!=null){
				String hql = "from Comment comment where comment.communication.id="+com.getId();
				List<Comment> commentList = this.commentDao.findList(hql);
				if(!commentList.isEmpty()){
					for (Comment comment : commentList) {
						this.commentDao.delete(comment);
					}					
				}
				
				if(com.getVideoId()!=null){
					BaseFile baseFile = this.baseFileDao.getById(com.getVideoId());
					if(baseFile!=null){
						this.baseFileDao.delete(baseFile);				
					}					
				}
				
				this.communDao.delete(com);
			}			
			if(i%20 == 0){
				this.communDao.flush();
				this.communDao.clear();
			}
		}
	}
	
	@Override
	public void updateCommunication(CommunicationDTO dto) {
		
		Assert.notNull(dto.getId(),"议题信息出错!");
		Communication com = this.communDao.getById(dto.getId());
		//归档
		if(dto.getType() == 1){
			com.setStatus(dto.getStatus());
		}else if(dto.getType() == 2){
			//修改
			Assert.notNull(dto.getTitle(), "议题名称不能为空!");
			Assert.notNull(dto.getSponsor(), "发起人不能为空!");
			Assert.notNull(dto.getStartDate(), "开始日期不能为空!");
			Assert.notNull(dto.getEndDate(), "结束日期不能为空!");
			Assert.notNull(dto.getContent(), "议题简述不能为空!");						
			com.setContent(dto.getContent());
			com.setEndDate(dto.getEndDate());
			com.setTitle(dto.getTitle());			
			com.setSponsor(dto.getSponsor());
			com.setStartDate(dto.getStartDate());
			
			
			if(StringUtils.isNotBlank(dto.getUuid())){

				//根据uuid查出上传文档
				Long baseFileId = null;		
				Set<SaveResult> saveResults= this.getTempCacheVal(dto.getUuid());
							
				
				for (SaveResult saveResult : saveResults) {
					baseFileId = saveResult.getId();
				}
				
				//判断是否已经存在videoId
				if(com.getVideoId()!=null){				
					if(baseFileId!=null){					
						BaseFile baseFile = this.baseFileDao.getById(com.getVideoId());
						if(baseFile!=null){
							this.baseFileDao.delete(baseFile);				
						}					
					}
				}				
				com.setVideoId(baseFileId);				
					
			}
			
			
			
		}else if(dto.getType() == 3){
			//点赞
			com.getVoter().add(new User(this.getNowUserId()));
		}else if(dto.getType() == 4){		
			//取消点赞
			this.communDao.deleteVote(com.getId(), this.getNowUserId());			
		}
		this.communDao.update(com);
	}
	
	
	@Override
	public CommunicationDTO getCommunicationById(Long id) {
		Assert.notNull(id, "id must not be null");		
		Communication com = this.communDao.getById(id);
		CommunicationDTO dto = new CommunicationDTO();
		this.getMapper().map(com, dto);				
		return dto;
	}
	
	
	
	@Override
	public Page<CommunicationDTO> searchCommunicationByPage(CommunicationQueryDTO queryDTO){			
		return this.communDao.searchCommunicationByPage(queryDTO);
	}
	


	@Override
	public CommunicationDTO getCurent(Long id) {	
		Assert.notNull(id, "id must not be null");	
		
		Communication com = this.communDao.getById(id);
			
		CommunicationDTO dto = this.getCommunicationBySql(id);
		
		
		if(com.getVideoId()!=null){		
			String phoneViewPath = this.baseFileService.getFileViewPath(com.getVideoId());
			dto.setVideoPath(phoneViewPath);
		}
		
		
		//统计评论数
		dto.setCommentNums(this.commentDao.conutComment(dto.getId(),1));	
		
		//统计该条评论点赞数	
		if(com.getVoter().size()>0){	
			//判断该用户是否已经点赞
			for (User user : com.getVoter()) {
				if(user.getId().equals(this.getNowUserId())){
					dto.setIsLove((short)0);
				}
			}
			dto.setLove(com.getVoter().size());
		}else{
			dto.setLove(0);
		}
		//统计剩余天数
		Date today = new Date();
		Long left = (dto.getEndDate().getTime()-today.getTime())/(24*60*60*1000);
		if(left>0){
			dto.setLeftDays(left);
		}else{
			dto.setLeftDays(0l);
		}
		return dto;					
		
	} 

	
	@Override
	public List<CommunicationDTO> getListByCondition(CommunicationQueryDTO queryDTO){
		return this.communDao.getListByCondition(queryDTO);
	}
	
	
	
	@Override
	public FiledDTO getFiledByid(Long id) {
		Assert.notNull(id, "id must not be null");
		
		Communication com = this.communDao.getById(id);
		FiledDTO filed  = new FiledDTO();
		this.getMapper().map(com, filed);			
		//查询上一篇下一篇归档议题
		List<CommunicationDTO>  preNext = this.communDao.getPreAndNextFiledById(id);		
		if(preNext.size()>0){			
			for (int i = 0; i < preNext.size(); i++) {
				if(preNext.get(i).getId()>filed.getId()){					
					filed.setNext(preNext.get(i));					
				}if(preNext.get(i).getId()<filed.getId()){					
					filed.setPre(preNext.get(i));					
				}				
			}
		}
		Integer totalPage = this.commentService.countTotlaPage(id);
		filed.setTotalPage(totalPage);
		return filed;
	}


	@Override
	public int countTotalSize() {
		return this.communDao.countTotalSize();
	}


	@Override
	public Page<CommunicationDTO> getAppPage(CommunicationQueryDTO queryDTO) {		
		Page<CommunicationDTO> dtoPage=this.communDao.searchCommunicationByPage(queryDTO);		
		for (CommunicationDTO dto : dtoPage.getList()) {
			dto.setContent(HTMLUtil.removeTag(dto.getContent()));
			dto.setCommentNums(this.commentDao.conutComment(dto.getId(),1));		
			
			Communication com = this.communDao.getById(dto.getId());
			int loveNum = com.getVoter().size();
			if(loveNum>0){
				for (User user : com.getVoter()) {
					if(user.getId().equals(this.getNowUserId())){
						dto.setIsLove((short)0);
					}
				}
				dto.setLove(loveNum);
			}else{
				dto.setLove(0);
			}
		}
		return dtoPage;	
	}

	
	


	

	private Map<String, Set<SaveResult>> tempCache = new ConcurrentHashMap<String, Set<SaveResult>>();

	@Override
	public void uploadVideo(String ucode, CommonsMultipartFile uploadFile) {
		
		Assert.hasText(ucode, "uuid could not be null");
		Assert.notNull(uploadFile, "uploadFile could not be null");
		
		SaveResult as = this.baseFileService.addPublicBaseFile(uploadFile);
	
		if(tempCache.containsKey(ucode)){				
			tempCache.get(ucode).add(as);			
		}else{
			Set<SaveResult> objSet = new HashSet<SaveResult>();
			objSet.add(as);
			tempCache.put(ucode, objSet);			
		}	
		
	}

	public Set<SaveResult> getTempCacheVal(String uuid){		
		return this.tempCache.get(uuid);		
	}

	@Override
	public CommunicationDTO getCommunicationBySql(Long id) {
		// TODO Auto-generated method stub
		return this.communDao.getCommunicationBySql(id);
	}


}
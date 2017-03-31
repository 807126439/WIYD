package com.wb.web.portals.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wb.core.common.bean.Page;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.base.dao.IBaseFileDao;
import com.wb.web.base.dto.result.SaveResult;
import com.wb.web.base.entity.BaseFile;
import com.wb.web.base.service.IBaseFileService;
import com.wb.web.portals.dao.IBanChunkDao;
import com.wb.web.portals.dao.IManuscriptDao;
import com.wb.web.portals.dto.manuscript.ManuscriptDTO;
import com.wb.web.portals.dto.manuscript.ManuscriptQueryDTO;
import com.wb.web.portals.dto.manuscript.PhotoDTO;
import com.wb.web.portals.entity.Manuscript;
import com.wb.web.portals.entity.ThemeActivity;
import com.wb.web.portals.service.IManuscriptService;
import com.wb.web.system.dto.zone.ZonePathDTO;
import com.wb.web.system.entity.User;
import com.wb.web.system.service.IZonePathService;



@Service("manuscriptService")
@Transactional
public class ManuscriptServiceImpl extends BaseService implements IManuscriptService{
	
	
	
	@Resource
	private IBaseFileService baseFileService;
	@Resource
	private IZonePathService zonePathService;
	@Resource
	private IBaseFileDao baseFileDao;
	@Resource
	private IManuscriptDao manuscriptDao;
	@Resource
	private IBanChunkDao banChunkDao;
	
	
	@Override
	public ManuscriptDTO getManuscriptById(Long id) {
		Assert.notNull(id, "id must not be null");		
		Manuscript ms = this.manuscriptDao.getById(id);
		ManuscriptDTO dto = new ManuscriptDTO();
		
		this.getMapper().map(ms, dto);	
		if(ms.getBaseFileId()!=null){			
			BaseFile file = this.baseFileDao.getById(ms.getBaseFileId());
			dto.setFilename(file.getFileName());
			if(dto.getZonePathId()!=null){
				String prefixPath = null;
				String prefixPattern=null;
				ZonePathDTO zp = this.zonePathService.getZonePathById(dto.getZonePathId());
				prefixPath = zp.getPath();
				prefixPattern=zp.getVirtualPath();
				dto.setPattern(prefixPattern+file.getPattern());
				dto.setFilePath(prefixPath+file.getSrcPath());
			}
		}
		
		dto.setMsId(ms.getId());	
		return dto;
	}

	@Override
	public void addManuscript(ManuscriptDTO dto,CommonsMultipartFile uploadFile){ 		
		Assert.notNull(dto.getDescription(), "描述不能为空!");
		Assert.notNull(dto.getTitle(), "标题不能为空!");
		
		Manuscript ms = new Manuscript(
				dto.getTitle(),
				dto.getAuthor(),
				dto.getDescription(),
				Manuscript.READY_AUDIT_STATUS,
				new Date(),
				new User(getNowUserId()),
				null,
				new ThemeActivity(dto.getActivityId()),
				getNowUser().getUsername(),
				0
				);
		
			if(uploadFile!=null && !uploadFile.isEmpty()){
			SaveResult aResult = this.baseFileService.addPublicBaseFile(uploadFile);			
			ms.setBaseFileId(aResult.getId());						
			}
			if(null!=dto.getBanChunkId()&&dto.getBanChunkId()!=0){
				ms.setBanChunk(this.banChunkDao.getById(dto.getBanChunkId()));
			}
			
			this.manuscriptDao.save(ms);			
	}





	@Override
	public void updateManuscript(ManuscriptDTO dto,CommonsMultipartFile uploadFile) {
		
		
		Manuscript ms = this.manuscriptDao.getById(dto.getMsId());
		
		if(ms!=null){
			
			if(dto.getOperateType() == 1){
				Assert.notNull(dto.getDescription(), "描述不能为空!");
				Assert.notNull(dto.getTitle(), "标题不能为空!");
				
				ms.setTitle(dto.getTitle());
				ms.setAuthor(dto.getAuthor());
				ms.setDescription(dto.getDescription());		
				ms.setLastOperatorTime(new Date());
				ms.setUpdateBy(getNowUser().getUsername());
				
				if(uploadFile!=null && !uploadFile.isEmpty()){
					SaveResult aResult = this.baseFileService.addPublicBaseFile(uploadFile);
					ms.setBaseFileId(aResult.getId());
				}
			}else if(2 == dto.getOperateType()){
				//点赞
				ms.getVoter().add(new User(this.getNowUserId()));
				ms.setLove(ms.getLove()+1);
			}else if(3 == dto.getOperateType()){
				//取消点赞
				this.manuscriptDao.deleteVote(dto.getMsId(), this.getNowUserId());	
				ms.setLove(ms.getLove()-1);
			}
			
		}
		
		
		
		this.manuscriptDao.update(ms);
		
	}
	
		

	@Override
	public void deleteManuscript(Long[] ids) {
		for (int i = 0; i < ids.length; i++) {
			Manuscript ms = this.manuscriptDao.getById(ids[i]);
			if(ms!=null){
				this.manuscriptDao.deleteVote(ms.getId());
				
				this.manuscriptDao.delete(ms);
				if(ms.getBaseFileId()!=null){
					this.baseFileService.deleteBaseFile(ms.getBaseFileId());
				}
			}			
			if(i%20 == 0){
				this.manuscriptDao.flush();
				this.manuscriptDao.clear();
			}
		}
	}
	
	
	@Override
	public void checkManuscript(ManuscriptDTO dto) {	
		
		if(dto.getIds()!=null){
			for (int i = 0; i < dto.getIds().length; i++) {
				Manuscript ms = this.manuscriptDao.getById(dto.getIds()[i]);
				if(ms!=null){
					ms.setStatus((short)1);
					this.manuscriptDao.update(ms);	
				}			
			}			
		}if(dto.getMsId()!=null){
			Manuscript ms = this.manuscriptDao.getById(dto.getMsId());
			ms.setStatus(dto.getStatus());
			this.manuscriptDao.update(ms);
		}				
	}
	
	

	@Override
	public Page<ManuscriptDTO> searchManuscriptByPage(
			ManuscriptQueryDTO queryDTO) {		
				
		Page<ManuscriptDTO> page=this.manuscriptDao.searchManuscriptByPage(queryDTO);
		List<ManuscriptDTO> dtolist=page.getList();
		for(ManuscriptDTO dto:dtolist){
			if(null!=dto.getBanChunkId()){
				dto.setBanChunkName(this.banChunkDao.getById(dto.getBanChunkId()).getChunkName());
			}else{
				dto.setBanChunkName("无");
			}
		}
		
		return page;		
	}
	
	
	@Override
	public List<PhotoDTO> getCheckedPhotosByActivityId(Long activityId) {
		return this.manuscriptDao.getCheckedPhotosByActivityId(activityId);
	}
	

	@Override
	public List<ManuscriptDTO> getAllManuscriptByActivityId(long activityId) {
		List<Manuscript> dt=manuscriptDao.getAllManuscriptByActivityId(activityId);
		List<Manuscript> mlist=manuscriptDao.delAwardWinningManuscriptByActivityId(dt, activityId);
		
		List<ManuscriptDTO> dto=new ArrayList<ManuscriptDTO>();
		for(Manuscript m:mlist){
			ManuscriptDTO mdto=new ManuscriptDTO();
			this.getMapper().map(m, mdto);
			mdto.setMsId(m.getId());
			mdto.setUsername(m.getAuthor());
			dto.add(mdto);
		}
		return dto;
	}

	@Override
	public Page<PhotoDTO> queryPhotosByPage(ManuscriptQueryDTO queryDTO) {	
		return this.manuscriptDao.queryPhotosByPage(queryDTO);
	}
	
	
	public void setPhotoViewPath(List<PhotoDTO> photos){		
		if(photos!=null){
			Map<Long, String> cache = new HashMap<Long, String>();
			for (PhotoDTO dto : photos){
				if(dto.getPattern() != null && dto.getPattern() !=""){
					dto.setPattern(this.zonePathService.viewCache(dto.getZonePathId(),cache)+dto.getPattern());
				}if(dto.getBigPattern() != null && dto.getBigPattern() !=""){
					dto.setBigPattern(this.zonePathService.viewCache(dto.getZonePathId(),cache)+dto.getBigPattern());
				}		
			}
		}			
	}
	

	public void setVoteMessage(List<PhotoDTO> photos){	
		if(photos!=null){
			for (PhotoDTO dto : photos) {				
				Manuscript ms = this.manuscriptDao.getById(dto.getId());
				//判断是否有voter
				if(ms.getVoter().size()>0){
					//遍历voterList,判断是否有当前用户
					for (User voter : ms.getVoter()) {
						if(voter.getId().equals(this.getNowUserId())){
							dto.setLoveing(true);
						}
					}
				}				
			}
		}		
	}
	
	
	
}
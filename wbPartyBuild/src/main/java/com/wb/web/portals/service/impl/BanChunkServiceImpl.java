package com.wb.web.portals.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.dto.AjaxJson;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.portals.dao.IBanChunkDao;
import com.wb.web.portals.dao.IThemeActivityDao;
import com.wb.web.portals.dto.banChunk.BanChunkDTO;
import com.wb.web.portals.dto.banChunk.BanChunkQueryDTO;
import com.wb.web.portals.dto.banChunk.BanChunkTreeDTO;
import com.wb.web.portals.entity.BanChunk;
import com.wb.web.portals.entity.ThemeActivity;
import com.wb.web.portals.service.IBanChunkService;

@Service("banChunkService")
@Transactional
public class BanChunkServiceImpl extends BaseService implements
		IBanChunkService {

	@Resource
	private IBanChunkDao banChunkDao;

	@Resource
	private IThemeActivityDao themeActivityDao;

	@Override
	public long addBanChunk(BanChunkDTO dto) {

		Assert.notNull(dto.getChunkName(), "板块名称不能为空!");
		BanChunk banChunk = new BanChunk();
		banChunk.setUpdateBy(getNowUser().getUsername());
		banChunk.setCreateTime(new Date());
		banChunk.setChunkName(dto.getChunkName());
		banChunk.setChunkMemo(dto.getChunkMemo());
		banChunk.setIsLeaf(dto.getIsLeaf());
		banChunk.setStatus(dto.getStatus());
		banChunk.setSortNum(dto.getSortNum());
		
		if(dto.getLinkContentId()!=null){
			banChunk.setLinkContentId(dto.getLinkContentId());
		}
		
		banChunk.setActivity(themeActivityDao.getById(dto.getActivityId()));
		if(dto.getLevel()==1){
			banChunk.setParent(null);
		}else{
			banChunk.setParent(banChunkDao.getById(dto.getParentId()));
		}
		this.banChunkDao.save(banChunk);
		return banChunk.getId();

	}

	@Override
	public Page<BanChunkDTO> searchBanChunkByPage(BanChunkQueryDTO queryDTO) {
		Page<BanChunk> pageResult = this.banChunkDao
				.searchBanChunkByPage(queryDTO);

		List<BanChunkDTO> dtoList = new ArrayList<BanChunkDTO>();
		for (BanChunk dt : pageResult.getList()) {
			BanChunkDTO dto = new BanChunkDTO();
			this.getMapper().map(dt, dto);
			dtoList.add(dto);
		}
		return new Page<BanChunkDTO>(pageResult.getCurrentPage(),
				pageResult.getPageSize(), dtoList, pageResult.getRecTotal());
	}

	@Override
	public BanChunkDTO getBanChunkById(String id) {
		Assert.hasText(id, "id must not be null");

		long banChunkId = Long.parseLong(id);
		BanChunk dt = this.banChunkDao.getById(banChunkId);
		BanChunkDTO dto = new BanChunkDTO();
		this.getMapper().map(dt, dto);

		if (dt.getParent() != null) {
			dto.setParentId(dt.getParent().getId());
		}

		return dto;
	}

	@Override
	public void updateBanChunk(BanChunkDTO dto) {
		Assert.hasText(dto.getId().toString(), "name must not be null");
		Assert.hasText(dto.getChunkName(), "name must not be null");
		Assert.hasText(dto.getChunkMemo(), "memo must not be null");
		
		BanChunk dt=this.banChunkDao.getById(dto.getId());
		dt.update(
					dto.getChunkName(), 
					dto.getChunkMemo(), 
					dto.getSortNum(),
					dto.getStatus(), 
					getNowUser().getUsername());
			dt.setLinkContentId(dto.getLinkContentId());
		this.banChunkDao.update(dt);
	}

	@Override
	public void updateMoveBanChunk(String departId, String parId) {
		// TODO Auto-generated method stub

	}
	/**
	 * 删除板块
	 */
	@Override
	public AjaxJson deleteBanChunk(String[] ids) {

		List<Long> successIds = new ArrayList<Long>();

		if (ids != null && ids.length > 0) {
			long[] banChunkIds = new long[ids.length];
			for (int i = 0; i < ids.length; i++) {
				banChunkIds[i] = Long.parseLong(ids[i]);// 将接收到的String类型ID转换为long类型
				BanChunk dt = this.banChunkDao.getById(banChunkIds[i]);
				if (dt != null) {
					successIds.add(dt.getId());
					//删除节点
					this.banChunkDao.delete(dt);
				}
			}

			return new AjaxJson("删除成功！", AjaxJson.success, successIds);

		} else {
			return new AjaxJson("操作记录不能为空!", AjaxJson.error);
		}
	}

	@Override
	public List<BanChunkTreeDTO> searchBanChunkZtree(BanChunkQueryDTO queryDTO) {

		List<BanChunkTreeDTO> dtoList = new ArrayList<BanChunkTreeDTO>();

		if (queryDTO.getLevel()==null) {
			// 查找活动节点放入树形图节点list
			List<ThemeActivity> taList = new ArrayList<ThemeActivity>();
			if(queryDTO.getActivityId()!=0){
				taList.add(this.themeActivityDao.getById(queryDTO.getActivityId()));
			}else{
				taList = this.themeActivityDao.loadAll();
			}
			
			
			for (int i = 0; i < taList.size(); i++) {
				ThemeActivity dt = taList.get(i);
				//过滤删除的活动
//				if(dt.getIsDelete()==true){
//					continue;
//				}
				//过滤非城建征稿的活动
				if(!(dt.getActivityType()==2)){
					continue;
				}
				
				BanChunkTreeDTO dto = new BanChunkTreeDTO(
						(dt.getId()).toString(), 
						dt.getActivityName(), 
						true,
						null, 
						queryDTO.getLevel() == null ? 0: queryDTO.getLevel(),
						dt.getId().toString());
				dtoList.add(dto);
			}
		} else{
			// 查找板块节点放入树形图节点list
			List<BanChunk> depList = this.banChunkDao.searchTreeByCondition(queryDTO);
			
			for (int i = 0; i < depList.size(); i++) {
				BanChunk dt = depList.get(i);
				BanChunkTreeDTO dto = new BanChunkTreeDTO(
						dt.getId().toString(),
						dt.getChunkName(),
						!(dt.getIsLeaf()),
						dt.getParent() == null ? dt.getActivity().getId()
								.toString() : dt.getParent().getId().toString(),
						queryDTO.getLevel() == null ? 0 : queryDTO.getLevel(),
						dt.getActivity().getId().toString(),
						dt.getLinkContentId()
						);

				dtoList.add(dto);

			}

		}
		return dtoList;
	}
	/**
	 * 查找正在征稿活动的公开征稿的板块
	 */
	@Override
	public List<BanChunkDTO> getSolicitationBanChunk(Long ThemeActivityId) {
			int type =1;
			List<BanChunk> depList=this.banChunkDao.getSolicitationBanChunk(ThemeActivityId);
			List<BanChunkDTO> dtoList = new ArrayList<BanChunkDTO>();
			for (BanChunk dt : depList) {
				BanChunkDTO dto = new BanChunkDTO();
				this.getMapper().map(dt, dto);
				dto.setChildren(translateChildrenSetForDTO(dt.getChildren(),type));
				dtoList.add(dto);
			}
		return dtoList;
	}
	/**
	 * 查找正在发布的板块信息
	 * @param ThemeActivityId
	 * @return
	 */
	public List<BanChunkDTO> getPublishedBanChunk(Long ThemeActivityId){
		int type =2;
		List<BanChunk> depList=this.banChunkDao.getPublishedBanChunk(ThemeActivityId);
		List<BanChunkDTO> dtoList = new ArrayList<BanChunkDTO>();
		for (BanChunk dt : depList) {
			BanChunkDTO dto = new BanChunkDTO();
			this.getMapper().map(dt, dto);
			dto.setChildren(translateChildrenSetForDTO(dt.getChildren(),type));
			dtoList.add(dto);
		}
	return dtoList;
	}
	
	
	//将板块对象的children转化为BanChunkDTO类型
	//为了区分1.征稿(不显示叶子节点)和2.显示页面(显示叶子节点)，加了type参数----1.征稿   2.显示城建人页面
	public List<BanChunkDTO> translateChildrenSetForDTO(Set<BanChunk> children,int type){
		if(null!=children){
			List<BanChunkDTO> childrenDTO=new ArrayList<BanChunkDTO>();
			for(BanChunk dt:children){
				translateChildrenSetForDTO(dt.getChildren(),type);
				BanChunkDTO dto = new BanChunkDTO();
				this.getMapper().map(dt, dto);
				if(type==1){
				if(!dto.getIsLeaf()){
					childrenDTO.add(dto);
				}
			  }else{
				  childrenDTO.add(dto);
			  }
			}
			return childrenDTO;
		}
		return null;
	}
}

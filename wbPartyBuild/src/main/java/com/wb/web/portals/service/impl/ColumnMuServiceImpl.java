package com.wb.web.portals.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wb.core.common.bean.Page;
import com.wb.core.common.exception.MyException;
import com.wb.core.common.service.BaseService;
import com.wb.core.common.utils.Assert;
import com.wb.web.portals.dao.IColumnMuDao;
import com.wb.web.portals.dao.IContentDao;
import com.wb.web.portals.dto.column.ColumnHead;
import com.wb.web.portals.dto.column.ColumnMuDTO;
import com.wb.web.portals.dto.column.ColumnNavDTO;
import com.wb.web.portals.dto.column.ColumnZtreeDTO;
import com.wb.web.portals.dto.column.InnnerColumnDTO;
import com.wb.web.portals.dto.content.ContentQueryDTO;
import com.wb.web.portals.dto.content.InnerShowContentDTO;
import com.wb.web.portals.entity.ColumnMu;
import com.wb.web.portals.entity.Content;
import com.wb.web.portals.service.IColumnMuService;
import com.wb.web.system.dao.IBaseDictDao;
import com.wb.web.system.dao.IUserDao;
import com.wb.web.system.dto.user.UserDTO;
import com.wb.web.system.entity.BaseDict;
import com.wb.web.system.entity.User;

@Service("columnMuService")
@Transactional
public class ColumnMuServiceImpl extends BaseService implements IColumnMuService{
	@Resource
	private IColumnMuDao columnMuDao;
	@Resource
	private IContentDao contentDao;
	@Resource
	private IBaseDictDao baseDictDao;	
	@Resource
	private IUserDao userDao;
	
	
	
	public List<ColumnHead> searchIndexHead(){
		   
		return this.columnMuDao.searchIndexHead(null);
	}
	
	
	
	/**
	 * 所有父栏目
	 * @param exceptId 排除id
	 * @return
	 */
	public List<ColumnMuDTO> getAllParentEntity(Long exceptId){
		
		List<ColumnMuDTO> firstColumns = this.columnMuDao.findColumnMuDTOByLevel((short) 1,exceptId);	//所有一级栏目	
		List<ColumnMuDTO> secondColumns = this.columnMuDao.findColumnMuDTOByLevel((short) 2,exceptId);	//所有二级栏目
		List<ColumnMuDTO> thirdColumns = this.columnMuDao.findColumnMuDTOByLevel((short) 3,exceptId);	//所有三级栏目
		
		Map<Long, List<ColumnMuDTO>> secondMap = handlerColumnList(secondColumns,2);	
		Map<Long, List<ColumnMuDTO>> thirdMap = handlerColumnList(thirdColumns,3);
		
	
		List<ColumnMuDTO> resultList = new ArrayList<ColumnMuDTO>();
		
		for (ColumnMuDTO cl : firstColumns) {
			cl.setTitle("|--"+cl.getTitle());
					
			resultList.add(cl);
			
			if(secondMap.containsKey(cl.getId())){
			
				for (ColumnMuDTO col : secondMap.get(cl.getId())) {
					resultList.add(col);
					if(thirdMap.containsKey(col.getId())){
						resultList.addAll(thirdMap.get(col.getId()));					
					}
				
				}
			
			}
			
		}
		
		return resultList;
	}
	
	
	
	
	private Map<Long, List<ColumnMuDTO>> handlerColumnList(List<ColumnMuDTO> columns,Integer level) {
		Map<Long, List<ColumnMuDTO>> map = new LinkedHashMap<Long, List<ColumnMuDTO>>();
		
		String prefix = null;
		if(level == 2){
			prefix = "|&nbsp;&nbsp;&nbsp;&nbsp;|--";
		
		}else if(level == 3){
			prefix = "|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|--";
			
		}
		
		for (ColumnMuDTO cl : columns) {
			cl.setTitle(prefix+cl.getTitle());							
			
			if(map.containsKey(cl.getParentId())){
				map.get(cl.getParentId()).add(cl);
			
			}else{
				List<ColumnMuDTO> clList = new ArrayList<ColumnMuDTO>();
				clList.add(cl);
				map.put(cl.getParentId(), clList);
			}			
			
		}
		return map;
	}
	
	
	
	
	
	/**
	 * 查找所有的对象并按照树形结构排序
	 * @return
	 */
	public List<ColumnMuDTO> getAllColumnByLevel(){
		List<ColumnMuDTO> firstColumns = this.columnMuDao.findColumnMuDTOByLevel((short) 1,null);	//所有一级栏目	
		List<ColumnMuDTO> secondColumns = this.columnMuDao.findColumnMuDTOByLevel((short) 2,null);	//所有二级栏目
		List<ColumnMuDTO> thirdColumns = this.columnMuDao.findColumnMuDTOByLevel((short) 3,null);	//所有三级栏目
		List<ColumnMuDTO> fourColumns = this.columnMuDao.findColumnMuDTOByLevel((short) 4,null);	//所有四级栏目
		
		List<BaseDict> dictList = this.baseDictDao.searcheDcitListByCondition(BaseDict.LANMU_TYPE, null, null);
		List<BaseDict> typeList = this.baseDictDao.searcheDcitListByCondition(BaseDict.COLUMN_SHOW_TYPE, null, null);
		
		
		//将所有的子栏目根据父节点分类
		Map<Long, List<ColumnMuDTO>> secondMap = handlerColumnList(secondColumns,dictList,typeList,2);	
		Map<Long, List<ColumnMuDTO>> thirdMap = handlerColumnList(thirdColumns,dictList,typeList,3);
		Map<Long, List<ColumnMuDTO>> fourMap = handlerColumnList(fourColumns,dictList,typeList,4);
		
		List<ColumnMuDTO> resultList = new ArrayList<ColumnMuDTO>();
		
		for (ColumnMuDTO cl : firstColumns) {
			cl.setTitle2("|--"+cl.getTitle());
			if(cl.getTypeId()!=null){
				for (BaseDict dict : dictList) {
					if(dict.getDictValue().equals(cl.getTypeId().toString())){
						cl.setTypeText(dict.getDictItem());
						break;
					}
				}
			}
			
			if(cl.getShowType()!=null){
				for (BaseDict dict : typeList) {
					if(dict.getDictValue().equals(cl.getShowType().toString())){
						cl.setShowTypeText(dict.getDictItem());
						break;
					}
				}
				
			}
			
			resultList.add(cl);
			
			if(secondMap.containsKey(cl.getId())){
				
			    List<ColumnMuDTO> sl = secondMap.get(cl.getId());
			    //List<ColumnMuDTO> tempList = new ArrayList<ColumnMuDTO>();
			    for (int i = 0; i < sl.size() ; i++) {
			    	resultList.add(sl.get(i));
			    	if(thirdMap.containsKey(sl.get(i).getId())){
			    					    		
			    		for (ColumnMuDTO col2 : thirdMap.get(sl.get(i).getId())) {
			    			resultList.add(col2);
			    			if(fourMap.containsKey(col2.getId())){
			    				resultList.addAll(fourMap.get(col2.getId()));
				    		}
						}
			    		
			    	}
			    					    	
				}
				
				
			}
			
		}
		
		
		
		//此处循环resultList检查权限设置isAllowEdit
		for (ColumnMuDTO cd : resultList) {
			if(!StringUtils.isBlank(cd.getAllowUserId())){
				if(cd.getAllowUserId().contains(getNowUserId().toString())){
					cd.setIsAllowEdit(true);
				}
			}else{
				cd.setIsAllowEdit(true);
			}
		}
		
		return resultList;
	}

	
	

	private Map<Long, List<ColumnMuDTO>> handlerColumnList(List<ColumnMuDTO> columns,List<BaseDict> dictList,List<BaseDict> typeList,Integer level) {
		Map<Long, List<ColumnMuDTO>> map = new LinkedHashMap<Long, List<ColumnMuDTO>>();
		
		String prefix = null;
		if(level == 2){
			prefix = "|&nbsp;&nbsp;&nbsp;&nbsp;|--";
		
		}else if(level == 3){
			prefix = "|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|--";
			
		}else if(level == 4){
			prefix = "|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|--";
		}
		
		for (ColumnMuDTO cl : columns) {
			cl.setTitle2(prefix+cl.getTitle());
						
			if(cl.getTypeId()!=null){
				for (BaseDict dict : dictList) {
					if(dict.getDictValue().equals(cl.getTypeId().toString())){
						cl.setTypeText(dict.getDictItem());
						break;
					}
				}
			}
		
			if(cl.getShowType()!=null){
				for (BaseDict dict : typeList) {
					if(dict.getDictValue().equals(cl.getShowType().toString())){
						cl.setShowTypeText(dict.getDictItem());
						break;
					}
				}
				
			}
			
			if(map.containsKey(cl.getParentId())){
				map.get(cl.getParentId()).add(cl);
			
			}else{
				List<ColumnMuDTO> clList = new ArrayList<ColumnMuDTO>();
				clList.add(cl);
				map.put(cl.getParentId(), clList);
			}			
			
		}
		return map;
	}
	
	
	
	
	
	
	/**
	 * 根据id查找对象
	 * @param id
	 * @return
	 */
	public ColumnMuDTO getColumnMuDTOById(Long id){
		if(id == null){
			throw new NullPointerException("id must not be null");
		}
		
		return this.columnMuDao.getColumnMuDTOById(id);
	}
	
	
	
	/**
	 * 添加栏目
	 * @param dto
	 */
	public void addColmnMu(ColumnMuDTO dto){
		Assert.hasText(dto.getTitle(), "标题不能为空！");
		Assert.notNull(dto.getSortNum(), "排序号不能为空！");
		
		Short level = 1;//树的层级从1开始
		ColumnMu parent = null;
		if(dto.getParentId()!=null){
			parent = this.columnMuDao.getById(dto.getParentId());
			level = (short) (parent.getLevel() + 1); 
			
			if(!dto.getIsIgnorePre()){//不忽略上级属性
				
				//检查二级栏目是否能置顶(父栏目置顶了且设置了显示子栏目的信息，则子栏目不能置顶)
				if(dto.getIsIndex() && parent.getIsIndex() &&
				  (parent.getShowType() == ColumnMu.TAB_SHOW_TYPE || parent.getShowType() == ColumnMu.LIST_SHOW_TYPE)  ){
				    
					throw new MyException("此栏目无法置于首页（原因：此栏目的上级栏目已经置顶）");
				}
				
			}
				
		}
		
		ColumnMu columnMu = new ColumnMu(
								dto.getTitle(), 
								dto.getAlias(),
								dto.getLinkUrl(),
								dto.getTypeId(), 
								dto.getSortNum(), 
								level,
								dto.getIsIndexNav(),
								dto.getIsIndex(), 
								dto.getIndexNum(),
								dto.getIsIgnorePre(),
								dto.getMaxContentSize(),
								parent, 
								getNowUser().getUsername());
		
		this.columnMuDao.save(columnMu);
		
	}
	
	
	
	/**
	 * 修改栏目的上级栏目
	 * @param colId	当前栏目id
	 * @param parId 上级栏目id
	 
	public void updateParentColumn(Long colId,Long parId){
		Assert.notNull(colId, "colId must not be null");
		
		ColumnMu curr = this.columnMuDao.getById(colId);
		Assert.notNull(curr, "could not find the column");
		
		short level = 1;
		ColumnMu parent = null;
		if(parId!=null){
			parent = this.columnMuDao.getById(parId);
			Assert.notNull(parent, "couldn't find the par column");
			level = (short) (parent.getLevel()+1);
		}
		
		short orgLevel = curr.getLevel();//原来的层级 		
		
		//原来上级栏目为null，现在上级栏目也为null，则返回
		if(curr.getParent() == null && parId == null){
			return;
			
		}else if(curr.getParent()!=null && curr.getParent().getId().equals(parId)){//上级栏目不变则返回
			return;
		}
		
		//1-2  
		if(orgLevel == 1 && level == 2){
			Assert.isTrue(!this.columnMuDao.checkIsHasChildByLevel(3, curr.getId()),
						  "当前栏目存有两层子栏目，修改失败！");
			
			columnVerifyFilter(curr, parent,2);						
			curr.setParent(parent);			
		
		//1-3				
		}else if(orgLevel == 1 && level == 3){
			Assert.isTrue(!this.columnMuDao.checkIsHasChildByLevel(2, curr.getId()),
					  	  "当前栏目存有一层子栏目，修改失败！");
			
			columnVerifyFilter(curr, parent,3);									
			curr.setParent(parent);	
		
		//2-1	
		}else if(orgLevel == 2 && level == 1){
						
			curr.setParent(null);	
		
		//2-2	
		}else if(orgLevel == 2 && level == 2){
			
			columnVerifyFilter(curr, parent,2);
			curr.setParent(parent);	
		
		//2-3	
		}else if(orgLevel == 2 && level == 3){
			Assert.isTrue(!this.columnMuDao.checkIsHasChildByLevel(2, curr.getId()),
					  	  "当前栏目存有一层子栏目，修改失败！");
			
			columnVerifyFilter(curr,parent,3);									
			curr.setParent(parent);	
		
		//3-1
		}else if(orgLevel == 3 && level == 1){
			curr.setParent(null);	
			
		//3-2
		}else if(orgLevel == 3 && level == 2){
			columnVerifyFilter(curr, parent,2);
			curr.setParent(parent);	
			
		//3-3	
		}else if(orgLevel == 3 && level == 3){
			columnVerifyFilter(curr,parent,3);									
			curr.setParent(parent);	
		}
				
				
		curr.setLevel(level);
		
		if(orgLevel == 2){
			this.columnMuDao.updateColumnLevelByCondition((short)(level+1), curr.getId(), false);
		
		}else if(orgLevel == 1){
			
			this.columnMuDao.updateColumnLevelByCondition((short)(level+1), curr.getId(), false);
			this.columnMuDao.updateColumnLevelByCondition((short)(level+2), curr.getId(), true);
			
		}
		
		this.columnMuDao.update(curr);
		
	}*/


	/**
	 * 上级栏目变更的验证过滤器
	 * @param curr    当前栏目
	 * @param parent  变更的栏目
	 * @param le      修改后的层级
	 
	private void columnVerifyFilter(ColumnMu curr, ColumnMu parent,Integer le) {
		
					
		Assert.isTrue(!(curr.getIsIndex() && parent.getIsIndex() && 
				(parent.getShowType() == ColumnMu.TAB_SHOW_TYPE || parent.getShowType() == ColumnMu.LIST_SHOW_TYPE)
				),
				  "由于当前栏目为置于首页显示，但新的上级栏目也为置于首页显示子栏目信息，修改失败！");
	
		
		if(le == 2){
			Assert.isTrue(!(curr.getIsIndex() && ((curr.getShowType() == ColumnMu.TAB_SHOW_TYPE || curr.getShowType() == ColumnMu.LIST_SHOW_TYPE)
						 && parent.getIsIndex())), 
						 "由于当前栏目为置于首页显示且显示子栏目信，但新的上级栏目为置于首页，修改失败！");
		}
		
		if(le == 3){
			Assert.isTrue(!(curr.getIsIndex() && (curr.getShowType() == ColumnMu.TAB_SHOW_TYPE || curr.getShowType() == ColumnMu.LIST_SHOW_TYPE)), 
					 	 "由于当前栏目为置于首页显示且显示子栏目信，但当前栏目修改为最底层栏目，修改失败！");
		}
		
	}*/
	
	
	
	/**
	 * 修改栏目的上级栏目
	 * @param colId	当前栏目id
	 * @param parId 上级栏目id
	 */
	public void updateParentColumn(Long colId,Long parId){
		Assert.notNull(colId, "colId must not be null");
		
		ColumnMu curr = this.columnMuDao.getById(colId);
		Assert.notNull(curr, "could not find the column");
		
		short level = 1;
		ColumnMu parent = null;
		if(parId!=null){
			parent = this.columnMuDao.getById(parId);
			Assert.notNull(parent, "couldn't find the par column");
			level = (short) (parent.getLevel()+1);
		}
		
	
		
		//原来上级栏目为null，现在上级栏目也为null，则返回
		if(curr.getParent() == null && parId == null){
			return;
			
		}else if(curr.getParent()!=null && curr.getParent().getId().equals(parId)){//上级栏目不变则返回
			return;
		}
		
		if(level > 1){
			
			Assert.isTrue(!(curr.getIsIndex() && parent.getIsIndex() && 
					(parent.getShowType() == ColumnMu.TAB_SHOW_TYPE || parent.getShowType() == ColumnMu.LIST_SHOW_TYPE)
					),
					  "由于当前栏目为置于首页显示，但新的上级栏目也为置于首页显示子栏目信息，修改失败！");
			
		}
		
		
		curr.setLevel(level);
		curr.setParent(parent);			
		
		this.columnMuDao.update(curr);
		
	}
	
	
	
	
	
	
	/**
	 * 修改栏目
	 * @param dto
	 */
	public void updateColmnMu(ColumnMuDTO dto){
		Assert.notNull(dto.getId(), "id must not be null");
		Assert.hasText(dto.getTitle(), "标题不能为空！");
		Assert.notNull(dto.getSortNum(), "排序号不能为空！");
		
		
		ColumnMu columnMu = this.columnMuDao.getById(dto.getId());
			
		Assert.isTrue(!(!dto.getIsIndex() && (dto.getShowType() == ColumnMu.TAB_SHOW_TYPE || dto.getShowType() == ColumnMu.LIST_SHOW_TYPE)),
					   "当前栏目没有置于首页，无法设置显示子栏目信息！");		
		
		//栏目和文章关系处理
		handlerRelationInColumnAndContent(dto, columnMu);

			
		columnMu.setTitle(dto.getTitle());
		columnMu.setAlias(dto.getAlias());
		columnMu.setLinkUrl(dto.getLinkUrl());
		columnMu.setTypeId(dto.getTypeId());
		columnMu.setSortNum(dto.getSortNum());
		columnMu.setIsIndexNav(dto.getIsIndexNav());
		columnMu.setIsIndex(dto.getIsIndex());
		columnMu.setIndexNum(dto.getIndexNum());
		columnMu.setMaxContentSize(dto.getMaxContentSize());
		columnMu.setShowType(dto.getShowType());
		columnMu.setIsIgnorePre(dto.getIsIgnorePre());
		columnMu.setUpdateBy(getNowUser().getUsername());		
		columnMu.setLastOperatorTime(new Date());				
		
		
		this.columnMuDao.update(columnMu);
		
	}



	private void handlerRelationInColumnAndContent(ColumnMuDTO dto,ColumnMu columnMu) {
		
		if(columnMu.getLevel()>1){//属于子栏目
			
			if(dto.getIsIgnorePre()){//忽略上级影响
				
				if((columnMu.getParent().getShowType() == ColumnMu.TAB_SHOW_TYPE || columnMu.getParent().getShowType() == ColumnMu.LIST_SHOW_TYPE) 
					 && !columnMu.getIsIndex()){
					
					this.contentDao.updateIndexFlagByCondition(Content.NOT_INDEX_FLAG, columnMu.getId(),Content.NOT_INDEX_FLAG,false);
				}
				
			
			}
			
		}
		
		
		if(!columnMu.getIsIndex().equals(dto.getIsIndex())){
						
				if(dto.getIsIndex()){//置于首页
					
					if(dto.getShowType() == ColumnMu.NORMAL_SHOW_TYPE){
												
						// 设置栏目下最新x条文章置顶
						this.contentDao.updateIndexFlagForNewestContent(Content.AUTO_IN_INDEX_FLAG, columnMu.getId(), dto.getMaxContentSize());
						
											
					}else if(dto.getShowType() == ColumnMu.TAB_SHOW_TYPE){
						Assert.isTrue(columnMu.getLevel()<=2, "层级为1或2才可设置显示子栏目信息！");
						
						//设置前3条子栏目下的最新x条文章顶
						this.contentDao.updateIndexFlagForNewestContentInPreColumn(Content.AUTO_IN_INDEX_FLAG, columnMu.getId(),ColumnMu.MAX_CHILD_NUM,dto.getMaxContentSize());
						
						
					}else if(dto.getShowType() == ColumnMu.LIST_SHOW_TYPE){
						Assert.isTrue(columnMu.getLevel()<=2, "层级为1或2才可设置显示子栏目信息！");
						
						//设置所有子栏目下的最新x条文章顶
						this.contentDao.updateIndexFlagForNewestContentInPreColumn(Content.AUTO_IN_INDEX_FLAG, columnMu.getId(), dto.getMaxContentSize());
					}
					
					
				}else{//不置于首页
					
					if(columnMu.getShowType() == ColumnMu.NORMAL_SHOW_TYPE){					
	
						//撤销原来栏目下的文章置顶 
						this.contentDao.updateIndexFlagByCondition(Content.NOT_INDEX_FLAG, columnMu.getId(), Content.NOT_INDEX_FLAG, false);
						
									
					}else if(columnMu.getShowType() == ColumnMu.TAB_SHOW_TYPE || columnMu.getShowType() == ColumnMu.LIST_SHOW_TYPE){
						
						//撤销原来 子栏目下的文章置顶 
						this.contentDao.updateIndexFlagByCondition(Content.NOT_INDEX_FLAG, columnMu.getId(), Content.NOT_INDEX_FLAG, true);
						
					}
				}
				
		}else{
			//原来栏目置于首页显示，显示类型属性改变了
			if(columnMu.getIsIndex() && (!columnMu.getShowType().equals(dto.getShowType()))){
				
				if(dto.getShowType() == ColumnMu.NORMAL_SHOW_TYPE){
					//撤销原来 子栏目下的文章置顶 
					this.contentDao.updateIndexFlagByCondition(Content.NOT_INDEX_FLAG, columnMu.getId(), Content.NOT_INDEX_FLAG, true);
					
					// 设置栏目下最新x条文章置顶
					this.contentDao.updateIndexFlagForNewestContent(Content.AUTO_IN_INDEX_FLAG, columnMu.getId(), dto.getMaxContentSize());
					
										
				}else if(dto.getShowType() == ColumnMu.TAB_SHOW_TYPE){
					Assert.isTrue(columnMu.getLevel()<=2, "层级为1或2才可设置显示子栏目信息！");
					
					if(columnMu.getShowType() == ColumnMu.NORMAL_SHOW_TYPE){
						this.contentDao.updateIndexFlagByCondition(Content.NOT_INDEX_FLAG, columnMu.getId(), Content.NOT_INDEX_FLAG, false);
					
					}else if(columnMu.getShowType() == ColumnMu.LIST_SHOW_TYPE){
						this.contentDao.updateIndexFlagByCondition(Content.NOT_INDEX_FLAG, columnMu.getId(), Content.NOT_INDEX_FLAG, true);
					}
					
					//设置前3条子栏目下的最新x条文章顶
					this.contentDao.updateIndexFlagForNewestContentInPreColumn(Content.AUTO_IN_INDEX_FLAG, columnMu.getId(),ColumnMu.MAX_CHILD_NUM,dto.getMaxContentSize());
					
					
				}else if(dto.getShowType() == ColumnMu.LIST_SHOW_TYPE){
					Assert.isTrue(columnMu.getLevel()<=2, "层级为1或2才可设置显示子栏目信息！");
					
					if(columnMu.getShowType() == ColumnMu.NORMAL_SHOW_TYPE){
						this.contentDao.updateIndexFlagByCondition(Content.NOT_INDEX_FLAG, columnMu.getId(), Content.NOT_INDEX_FLAG, false);
					
					}else if(columnMu.getShowType() == ColumnMu.TAB_SHOW_TYPE){
						this.contentDao.updateIndexFlagByCondition(Content.NOT_INDEX_FLAG, columnMu.getId(), Content.NOT_INDEX_FLAG, true);
					}
					
					//设置所有子栏目下的最新x条文章顶
					this.contentDao.updateIndexFlagForNewestContentInPreColumn(Content.AUTO_IN_INDEX_FLAG, columnMu.getId(), dto.getMaxContentSize());
				}
										
			}
			
			
			
		}
		
		
		
		
	}
	
	
	

	
	
	
	/**
	 * 删除栏目，会级联删除子栏目及其所有的文章
	 * @param ids
	 */
	public void deleteColmnMu(Long[] ids){
		
		for (int i = 0; i < ids.length; i++) {
			ColumnMu delItem = this.columnMuDao.getById(ids[i]);
			
			deleteChildrenColumns(delItem);			
			
			this.columnMuDao.delete(delItem);//级联删除子栏目
			
			if(i%20 == 0){
				this.columnMuDao.flush();
				this.columnMuDao.clear();
			}			
			
		}
	}
	
	
	/**
	 * 递归设置所有子栏目下的文章为删除状态
	 * @param cl
	 */
	public void deleteChildrenColumns(ColumnMu cl){
		
		for (Content ct : cl.getContents()) {//把要删除的栏目内的所有文章设置删除状态
			ct.setColumnMu(null);
			ct.setStatus(Content.DEL_STATUS);
			ct.setHistoryColumn(ct.getTitle());
			this.contentDao.update(ct);
		}
		
		for (ColumnMu col : cl.getChildren()) {
			deleteChildrenColumns(col);
		}
	}
	
	
	
	public Long countTotalNum(){
		return this.columnMuDao.countTotalNum();
	}
	
	
	
	private void beforeHandle(ColumnMu col,List<ColumnHead> secColums,List<ColumnHead> navColums){
		
		if(col.getParent()!=null){
			
			beforeHandle(col.getParent(), secColums, navColums);
		
		}else{
			secColums.addAll(this.columnMuDao.searchIndexHead(col.getId()));//通过上级栏目id查找子栏目
		}
		
	    navColums.add(new ColumnHead(col.getId(), col.getTitle(),col.getAlias(),col.getLinkUrl()));			
				
		
	}
	
	
	
	
	
	
	
	/**
	 * 首页栏目的内页显示处理
	 * @param columId   当前栏目id
	 * @param curPage	当前页
	 * @return
	 */
	public InnnerColumnDTO searchForInnerShow(Long columId,Integer curPage){
		ColumnMu columnMu = this.columnMuDao.getById(columId);
		
		ColumnHead currColumn = new ColumnHead(columnMu.getId(), columnMu.getTitle(),columnMu.getTypeId(),columnMu.getLinkUrl());//当前栏目
		List<ColumnHead> secColums = new ArrayList<ColumnHead>();    //二级栏目集合	
					
		List<ColumnHead> navColums = new ArrayList<ColumnHead>(); 
		
		//预处理导航条和获取二级栏目集合			
		this.beforeHandle(columnMu, secColums, navColums);
		
		if(columnMu.getParent() == null){//获取二级栏目下的各层栏目
			this.handleChildColumn(secColums,currColumn.getColId());
		}else{
			this.handleChildColumn(secColums,currColumn.getColId(),columnMu.getParent().getId());			
		}
		
		
								
		Integer pageSize = 15;
		if(columnMu.getTypeId() ==  ColumnMu.IMG_LIST_TYPE || columnMu.getTypeId() ==  ColumnMu.VIDEO_LIST_TYPE){
			pageSize = 20;
		}
		
		if(currColumn.getTypeId() == ColumnMu.SINGAL_PAGE_TYPE){
			InnerShowContentDTO singalContent = this.contentDao.getFirstContentInColumn(columnMu.getId());			
			
			return new InnnerColumnDTO(secColums, currColumn, navColums, singalContent);
			
		}else{
			Page<InnerShowContentDTO> contentPage = this.contentDao.searchEntityByCondtion(columnMu.getId(),null,curPage,pageSize,null);
			
			
			return new InnnerColumnDTO(secColums, currColumn, navColums, contentPage.getList(),
						contentPage.getCurrentPage(),contentPage.getTotalPage().intValue());
		}
		
		
		

	}
	
	
	
	private void handleChildColumn(List<ColumnHead> secColums,Long... currId){
		List<Long> ids = new ArrayList<Long>();//二级栏目id集合
		for (ColumnHead ch : secColums) {
			ids.add(ch.getColId());
		}
		
		if(!ids.isEmpty()){
			List<ColumnHead> thirdColumns = this.columnMuDao.searchColumnHeadByParIds(ids);
			if(thirdColumns!=null && !thirdColumns.isEmpty()){
				
				List<Long> thirdIds = new ArrayList<Long>();//三级栏目id集合
				for (ColumnHead ch : thirdColumns) {//循环将所有的三级栏目归依到对应的二级栏目
					thirdIds.add(ch.getColId());
					
					this.addChildItem(secColums,ch,currId);
				}
				
				List<ColumnHead> fourColumns = this.columnMuDao.searchColumnHeadByParIds(thirdIds);
				
				for (ColumnHead ch : fourColumns) {//循环将所有的四级级栏目归依到对应的四级栏目
					this.addChildItem(thirdColumns,ch,currId);
				}
				
			}
		}
					
		
	}
	
	
	private void addChildItem(List<ColumnHead> secColums,ColumnHead ch,Long... currId){
		
		for (int i = 0; i < secColums.size(); i++) {
			
			if(secColums.get(i).getColId().equals(ch.getParId())){
				
				if(!secColums.get(i).getIsHasChildren()){
					secColums.get(i).setIsHasChildren(true);//标志有子节点
				}
				
				for (int j = 0; j < currId.length; j++) {
					if(ch.getColId().equals(currId[j]) || ch.getParId().equals(currId[j])){
						secColums.get(i).setIsOpen(true);//标志为打开
						break;
					}
				}
				
				
				secColums.get(i).getChildList().add(ch);
				
				break;
			}
		}
		
	}
	
	
	
	
	
	
	
	/**
	 * 取出所有的栏目转换为树形结构
	 */
	public List<ColumnZtreeDTO> getAllEntityTransformTree(){
		
		List<ColumnMu> allColumn = this.columnMuDao.loadAll();
		List<ColumnZtreeDTO> resultList = new ArrayList<ColumnZtreeDTO>();
		
		for (ColumnMu col : allColumn) {
			boolean isParent = false;
			if(col.getParent()==null){
				isParent = true;
			}
			
			resultList.add(new ColumnZtreeDTO(col.getId().toString(), col.getParent() == null? "0":col.getParent().getId().toString(), 
					col.getTitle(),isParent));
		}
		
		
		return resultList;
	}
	
	/**
	 * 通过子栏目ID查找同级的所有栏目
	 */
	public List<ColumnHead> getAllBrotherColumnMu(Long id){
		List<ColumnMu> dtList=this.columnMuDao.getAllBrotherColumnMu(id);
		List<ColumnHead> dtoList=new ArrayList<ColumnHead>();
		for(ColumnMu dt:dtList){
			ColumnHead dto=new ColumnHead(
					dt.getId(),
					dt.getTitle(),
					dt.getTypeId(),
					dt.getLinkUrl()
					);
			dtoList.add(dto);
		}
		
		return dtoList;
	}


	//获取所有有编辑权限的用户
	public List<UserDTO> getAllEditer(Long id) {
		Assert.notNull(id, "id must not be null");
		ColumnMu dt = this.columnMuDao.getById(id);
		String allowUserId = dt.getAllowUserId();
		String[] userId=null;
		if(null!=allowUserId){
			userId=StringUtils.split(allowUserId, ",");
		}
		List<UserDTO> userList = new ArrayList<UserDTO>();
		if(userId!=null && userId.length>0){
			for(int i=0;i<userId.length;i++){
				User userdt = new User();
				UserDTO userdto = new UserDTO();
				userdt = this.userDao.getById(userId[i]);
				userdto.setUserName(userdt.getUserName());
				userdto.setId(userdt.getId());
				userdto.setCheck(true);
				userList.add(userdto);
			}
		}
		return userList;
	}



	@Override
	public void saveAllowUserId(Long id, String[] userId) {
		Assert.notNull(id, "id must not be null");
		ColumnMu dt = this.columnMuDao.getById(id);
		StringBuilder userIds = new StringBuilder();
		String ids = null;
		
		if(null!=userId && userId.length>0){
			for(int i=0;i<userId.length;i++){
				//判断若属于新增的用户则传递给子节点更改
				if(null==dt.getAllowUserId()||!dt.getAllowUserId().contains(userId[i])){
					this.setChildrenAllowUserId(dt, userId[i]);
				}
				userIds.append(userId[i].trim());
				userIds.append(",");
			}
			ids=userIds.toString();
			ids=ids.substring(0, ids.length()-1);
		}
		dt.setAllowUserId(ids);
	}
	//递归设置子栏目编辑用户
	public void setChildrenAllowUserId(ColumnMu dt,String userId){
		if(dt!=null&&dt.getChildren()!=null){
			for(ColumnMu o:dt.getChildren()){
				if(o.getAllowUserId()!=null&&o.getAllowUserId()!=""&&!o.getAllowUserId().contains(userId.trim())){
					o.setAllowUserId(o.getAllowUserId()+","+userId.trim());
				}else{
					o.setAllowUserId(userId.trim());
				}
				this.setChildrenAllowUserId(o, userId);
			}
		}
	}
	

	/* 
	 * 搜索栏搜索栏目
	 */
	@Override
	public Page<ColumnHead> searchColumnHeadByCondition(ContentQueryDTO dq) {
		
		return this.columnMuDao.searchColumnHeadByCondition(dq);
	}
	
	
	
	
	
	/**
	 * 按顺序和层级结构整理所有的栏目
	 * @return
	 */
	public List<ColumnNavDTO> getAllColumnNav(){
		List<ColumnNavDTO> allNavs = this.columnMuDao.searchColumnNavDTO();
		
		List<ColumnNavDTO> result = new ArrayList<ColumnNavDTO>();
		for (ColumnNavDTO col : allNavs) {
			if(col.getParentId() == null){
				result.add(col);
			}
		}
		
		cycleColumnNavDatas(result, allNavs);
		
	
		return result;
		
		
	}
	
	
	private void cycleColumnNavDatas(List<ColumnNavDTO> actData,List<ColumnNavDTO> allNavs){
	
	   for (ColumnNavDTO col : actData) {
		   for (ColumnNavDTO child : allNavs) {
			   if(child.getParentId()!=null && child.getParentId().equals(col.getId())){
				col.getChilds().add(child);
			   }
		   }
		   
		   if(!col.getChilds().isEmpty()){
			   this.cycleColumnNavDatas(col.getChilds(), allNavs);
		   }
	   }
	}
	
	
}
